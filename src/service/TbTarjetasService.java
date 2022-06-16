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
import model.TbTarjetas;
import model.TbTarjetasDto;
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
public class TbTarjetasService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA", 79

    public Respuesta getTarjeta(Long tarId) {
        try {
            Query qryTarjeta = em.createNamedQuery("TbTarjetas.findByTarId", TbTarjetas.class);
            qryTarjeta.setParameter("tarId", tarId);

            return new Respuesta(true, "", "", "TbTarjetas", new TbTarjetasDto((TbTarjetas) qryTarjeta.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe una tarjeta con el código ingresado.", "getTarjeta NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbTarjetasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la tarjeta.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la tarjeta.", "getTarjeta NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbTarjetasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la tarjeta.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la tarjeta.", "getTarjeta " + ex.getMessage());
        }
    }

    

    public Respuesta guardarTarjeta(TbTarjetasDto tbtarjetasDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbTarjetas tarjeta;
            
            if (tbtarjetasDto.getTarId()!= null && tbtarjetasDto.getTarId()> 0) {
                tarjeta = em.find(TbTarjetas.class, tbtarjetasDto.getTarId());
                if (tarjeta == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró la tarjeta a modificar.", "guardarTarjeta NoResultException");
                }
                tarjeta.actualizarTarjeta(tbtarjetasDto);
                tarjeta = em.merge(tarjeta);
            } else {
                tarjeta = new TbTarjetas(tbtarjetasDto);
                em.persist(tarjeta);
            }
            et.commit();
            return new Respuesta(true, "", "", "Tarjeta", new TbTarjetasDto(tarjeta));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbTarjetasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la tarjeta.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la tarjeta.", "guardarTarjeta " + ex.getMessage());
        }
    }

    public Respuesta eliminarTarjeta(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbTarjetas tarjeta;
            if (id != null ) {
                tarjeta = em.find(TbTarjetas.class, id);
                if (tarjeta == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la tarjeta a eliminar.", "eliminarTarjeta NoResultException");
                }
                em.remove(tarjeta);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la tarjeta a eliminar.", "eliminarTarjeta NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la tarjeta porque tiene relaciones con otros registros.", "eliminarTarjeta " + ex.getMessage());
            }
            Logger.getLogger(TbTarjetasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la tarjeta.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la tarjeta.", "eliminarTarjeta " + ex.getMessage());
        }
    }

    
}