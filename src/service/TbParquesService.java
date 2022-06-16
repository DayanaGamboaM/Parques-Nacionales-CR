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
import model.TbParques;
import model.TbParquesDto;
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
public class TbParquesService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA"

    public Respuesta getParque(Long parId) {
        try {
            Query qryParque = em.createNamedQuery("TbParques.findByParId", TbParques.class);
            qryParque.setParameter("parId", parId);

            return new Respuesta(true, "", "", "TbParques", new TbParquesDto((TbParques) qryParque.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un parque con el código ingresado.", "getParque NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbParquesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el parque.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el parque.", "getParque NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbParquesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el parque.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el parque.", "getParque " + ex.getMessage());
        }
    }

    

    public Respuesta guardarParque(TbParquesDto tbparquesDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbParques parque;
            
            if (tbparquesDto.getParId()!= null && tbparquesDto.getParId()> 0) {
                parque = em.find(TbParques.class, tbparquesDto.getParId());
                if (parque == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el parque a modificar.", "guardarParque NoResultException");
                }
                parque.actualizarParque(tbparquesDto);
                parque = em.merge(parque);
            } else {
                parque = new TbParques(tbparquesDto);
                em.persist(parque);
            }
            et.commit();
            return new Respuesta(true, "", "", "Parque", new TbParquesDto(parque));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbParquesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el parque.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el parque.", "guardarParque " + ex.getMessage());
        }
    }

    public Respuesta eliminarParque(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbParques parque;
            if (id != null ) {
                parque = em.find(TbParques.class, id);
                if (parque == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el parque a eliminar.", "eliminarParque NoResultException");
                }
                em.remove(parque);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el parque a eliminar.", "eliminarParque NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el parque porque tiene relaciones con otros registros.", "eliminarParque " + ex.getMessage());
            }
            Logger.getLogger(TbParquesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el parque.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el parque.", "eliminarParque " + ex.getMessage());
        }
    }

    
}