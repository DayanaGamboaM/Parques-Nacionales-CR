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
import model.TbUbicacion;
import model.TbUbicacionDto;
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
public class TbUbicacionService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA", 79

    public Respuesta getUbicacion(Long ubiId) {
        try {
            Query qryUbicacion = em.createNamedQuery("TbUbicacion.findByUbiId", TbUbicacion.class);
            qryUbicacion.setParameter("ubiId", ubiId);

            return new Respuesta(true, "", "", "TbUbicacion", new TbUbicacionDto((TbUbicacion) qryUbicacion.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe una ubicacion con el código ingresado.", "getUbicacion NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbUbicacionService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la ubicacion.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la ubicacion.", "getUbicacion NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbUbicacionService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la ubicacion.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la ubicacion.", "getUbicacion " + ex.getMessage());
        }
    }

    

    public Respuesta guardarUbicacion(TbUbicacionDto tbubicacionDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbUbicacion ubicacion;
            
            if (tbubicacionDto.getUbiId()!= null && tbubicacionDto.getUbiId()> 0) {
                ubicacion = em.find(TbUbicacion.class, tbubicacionDto.getUbiId());
                if (ubicacion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró la ubicacion a modificar.", "guardarUbicacion NoResultException");
                }
                ubicacion.actualizarUbicacion(tbubicacionDto);
                ubicacion = em.merge(ubicacion);
            } else {
                ubicacion = new TbUbicacion(tbubicacionDto);
                em.persist(ubicacion);
            }
            et.commit();
            return new Respuesta(true, "", "", "Ubicacion", new TbUbicacionDto(ubicacion));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbUbicacionService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la ubicacion.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la ubicacion.", "guardarUbicacion " + ex.getMessage());
        }
    }

    public Respuesta eliminarUbicacion(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbUbicacion ubicacion;
            if (id != null ) {
                ubicacion = em.find(TbUbicacion.class, id);
                if (ubicacion == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la ubicacion a eliminar.", "eliminarUbicacion NoResultException");
                }
                em.remove(ubicacion);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la ubicacion a eliminar.", "eliminarUbicacion NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la ubicacion porque tiene relaciones con otros registros.", "eliminarUbicacion " + ex.getMessage());
            }
            Logger.getLogger(TbUbicacionService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la ubicacion.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la ubicacion.", "eliminarUbicacion " + ex.getMessage());
        }
    }

    
}