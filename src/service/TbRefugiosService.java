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
import model.TbRefugios;
import model.TbRefugiosDto;
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
public class TbRefugiosService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA"

    public Respuesta getRefugio(Long refId) {
        try {
            Query qryRefugio = em.createNamedQuery("TbRefugios.findByRefId", TbRefugios.class);
            qryRefugio.setParameter("refId", refId);

            return new Respuesta(true, "", "", "TbRefugios", new TbRefugiosDto((TbRefugios) qryRefugio.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un refugio con el código ingresado.", "getRefugio NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbRefugiosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el refugio.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el refugio.", "getRefugio NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbRefugiosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el refugio.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el refugio.", "getRefugio " + ex.getMessage());
        }
    }

    

    public Respuesta guardarRefugio(TbRefugiosDto tbrefugiosDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbRefugios refugio;
            
            if (tbrefugiosDto.getRefId()!= null && tbrefugiosDto.getRefId()> 0) {
                refugio = em.find(TbRefugios.class, tbrefugiosDto.getRefId());
                if (refugio == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el refugio a modificar.", "guardarRefugio NoResultException");
                }
                refugio.actualizarRefugio(tbrefugiosDto);
                refugio = em.merge(refugio);
            } else {
                refugio = new TbRefugios(tbrefugiosDto);
                em.persist(refugio);
            }
            et.commit();
            return new Respuesta(true, "", "", "Refugio", new TbRefugiosDto(refugio));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbRefugiosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el refugio.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el refugio.", "guardarRefugio " + ex.getMessage());
        }
    }

    public Respuesta eliminarRefugio(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbRefugios refugio;
            if (id != null ) {
                refugio = em.find(TbRefugios.class, id);
                if (refugio == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el refugio a eliminar.", "eliminarRefugio NoResultException");
                }
                em.remove(refugio);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el refugio a eliminar.", "eliminarRefugio NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el refugio porque tiene relaciones con otros registros.", "eliminarRefugio " + ex.getMessage());
            }
            Logger.getLogger(TbRefugiosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el refugio.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el refugio.", "eliminarRefugio " + ex.getMessage());
        }
    }

    
}