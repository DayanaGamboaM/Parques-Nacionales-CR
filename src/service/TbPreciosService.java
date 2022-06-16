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
import model.TbPrecios;
import model.TbPreciosDto;
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
public class TbPreciosService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA"

    public Respuesta getPrecio(Long preId) {
        try {
            Query qryPrecio = em.createNamedQuery("TbPrecios.findByPreId", TbPrecios.class);
            qryPrecio.setParameter("preId", preId);

            return new Respuesta(true, "", "", "TbPrecios", new TbPreciosDto((TbPrecios) qryPrecio.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un precio con el código ingresado.", "getPrecio NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbPreciosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el precio.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el precio.", "getPrecio NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbPreciosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el precio.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el precio.", "getPrecio " + ex.getMessage());
        }
    }

    

    public Respuesta guardarPrecio(TbPreciosDto tbpreciosDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbPrecios precio;
            
            if (tbpreciosDto.getPreId()!= null && tbpreciosDto.getPreId()> 0) {
                precio = em.find(TbPrecios.class, tbpreciosDto.getPreId());
                if (precio == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el precio a modificar.", "guardarPrecio NoResultException");
                }
                precio.actualizarPrecio(tbpreciosDto);
                precio = em.merge(precio);
            } else {
                precio = new TbPrecios(tbpreciosDto);
                em.persist(precio);
            }
            et.commit();
            return new Respuesta(true, "", "", "Precio", new TbPreciosDto(precio));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbPreciosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el precio.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el precio.", "guardarPrecio " + ex.getMessage());
        }
    }

    public Respuesta eliminarPrecio(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbPrecios precio;
            if (id != null ) {
                precio = em.find(TbPrecios.class, id);
                if (precio == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el precio a eliminar.", "eliminarPrecio NoResultException");
                }
                em.remove(precio);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el precio a eliminar.", "eliminarPrecio NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el precio porque tiene relaciones con otros registros.", "eliminarPrecio " + ex.getMessage());
            }
            Logger.getLogger(TbPreciosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el precio.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el precio.", "eliminarPrecio " + ex.getMessage());
        }
    }

    
}