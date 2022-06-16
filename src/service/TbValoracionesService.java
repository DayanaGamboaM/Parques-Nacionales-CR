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
import model.TbValoraciones;
import model.TbValoracionesDto;
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
public class TbValoracionesService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA", 79

    public Respuesta getValoracion(Long valId) {
        try {
            Query qryValoracion = em.createNamedQuery("TbValoraciones.findByValId", TbValoraciones.class);
            qryValoracion.setParameter("valId", valId);

            return new Respuesta(true, "", "", "TbValoraciones", new TbValoracionesDto((TbValoraciones) qryValoracion.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe una valoracion con el código ingresado.", "getValoracion NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbValoracionesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la valoracion.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la valoracion.", "getValoracion NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbValoracionesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la valoracion.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la valoracion.", "getValoracion " + ex.getMessage());
        }
    }

    

    public Respuesta guardarValoracion(TbValoracionesDto tbvaloracionesDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbValoraciones valoracion;
            
            if (tbvaloracionesDto.getValId()!= null && tbvaloracionesDto.getValId()> 0) {
                valoracion = em.find(TbValoraciones.class, tbvaloracionesDto.getValId());
                if (valoracion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró la valoracion a modificar.", "guardarValoracion NoResultException");
                }
                valoracion.actualizarValoracion(tbvaloracionesDto);
                valoracion = em.merge(valoracion);
            } else {
                valoracion = new TbValoraciones(tbvaloracionesDto);
                em.persist(valoracion);
            }
            et.commit();
            return new Respuesta(true, "", "", "Valoracion", new TbValoracionesDto(valoracion));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbValoracionesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la valoracion.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la valoracion.", "guardarValoracion " + ex.getMessage());
        }
    }

    public Respuesta eliminarValoracion(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbValoraciones valoracion;
            if (id != null ) {
                valoracion = em.find(TbValoraciones.class, id);
                if (valoracion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la valoracion a eliminar.", "eliminarValoracion NoResultException");
                }
                em.remove(valoracion);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la valoracion a eliminar.", "eliminarValoracion NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la valoracion porque tiene relaciones con otros registros.", "eliminarValoracion " + ex.getMessage());
            }
            Logger.getLogger(TbValoracionesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la valoracion.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la valoracion.", "eliminarValoracion " + ex.getMessage());
        }
    }

    
}