/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import model.TbVisitantes;
import model.TbVisitantesDto;
import util.EntityManagerHelper;
import util.Respuesta;
//import unaplanilla2.model.Empleado;
//import unaplanilla2.model.EmpleadoDto;
//import unaplanilla2.util.EntityManagerHelper;
//import unaplanilla2.util.Respuesta;

/**
 *
 * @author 
 */
public class TbVisitantesService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA"

    public Respuesta getVisitante(Long visId) {
        try {
            Query qryVisitante = em.createNamedQuery("TbVisitantes.findByVisId", TbVisitantes.class);
            qryVisitante.setParameter("visId", visId);

            return new Respuesta(true, "", "", "TbVisitantes", new TbVisitantesDto((TbVisitantes) qryVisitante.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un refugio con el visitante ingresado.", "getVisitante NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbVisitantesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el visitante.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el visitante.", "getVisitante NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbVisitantesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el visitante.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el visitante.", "getVisitante " + ex.getMessage());
        }
    }

    

    public Respuesta guardarVisitante(TbVisitantesDto tbvisitantesDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbVisitantes visitante;
            
            if (tbvisitantesDto.getVisId()!= null && tbvisitantesDto.getVisId()> 0) {
                visitante = em.find(TbVisitantes.class, tbvisitantesDto.getVisId());
                if (visitante == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el visitante a modificar.", "guardarVisitante NoResultException");
                }
                visitante.actualizarVisitante(tbvisitantesDto);
                visitante = em.merge(visitante);
            } else {
                visitante = new TbVisitantes(tbvisitantesDto);
                em.persist(visitante);
            }
            et.commit();
            return new Respuesta(true, "", "", "Visitante", new TbVisitantesDto(visitante));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbVisitantesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el visitante.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el visitante.", "guardarVisitante " + ex.getMessage());
        }
    }

    public Respuesta eliminarVisitante(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbVisitantes visitante;
            if (id != null ) {
                visitante = em.find(TbVisitantes.class, id);
                if (visitante == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el visitante a eliminar.", "eliminarVisitante NoResultException");
                }
                em.remove(visitante);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el visitante a eliminar.", "eliminarVisitante NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el visitante porque tiene relaciones con otros registros.", "eliminarVisitante " + ex.getMessage());
            }
            Logger.getLogger(TbVisitantesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el visitante.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el visitante.", "eliminarVisitante " + ex.getMessage());
        }
    }

    
}