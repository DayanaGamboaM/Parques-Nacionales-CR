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
import model.TbEntradas;
import model.TbEntradasDto;
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
public class TbEntradasService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA"

    public Respuesta getEntrada(Long enId) {
        try {
            Query qryEntrada = em.createNamedQuery("TbEntradas.findByEnId", TbEntradas.class);
            qryEntrada.setParameter("enId", enId);

            return new Respuesta(true, "", "", "TbEntradas", new TbEntradasDto((TbEntradas) qryEntrada.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe una entrada con el código ingresado.", "getEntrada NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbEntradasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la entrada.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la entrada.", "getEntrada NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbEntradasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la entrada.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la entrada.", "getEntrada " + ex.getMessage());
        }
    }

    

    public Respuesta guardarEntrada(TbEntradasDto tbentradasDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbEntradas entrada;
            
            if (tbentradasDto.getEnId()!= null && tbentradasDto.getEnId()> 0) {
                entrada = em.find(TbEntradas.class, tbentradasDto.getEnId());
                if (entrada == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró la entrada a modificar.", "guardarEntrada NoResultException");
                }
                entrada.actualizarEntrada(tbentradasDto);
                entrada = em.merge(entrada);
            } else {
                entrada = new TbEntradas(tbentradasDto);
                em.persist(entrada);
            }
            et.commit();
            return new Respuesta(true, "", "", "Entrada", new TbEntradasDto(entrada));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbEntradasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la entrada.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la entrada.", "guardarEntrada " + ex.getMessage());
        }
    }

    public Respuesta eliminarEntrada(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbEntradas entrada;
            if (id != null ) {
                entrada = em.find(TbEntradas.class, id);
                if (entrada == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la entrada a eliminar.", "eliminarEntrada NoResultException");
                }
                em.remove(entrada);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la entrada a eliminar.", "eliminarEntrada NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la entrada porque tiene relaciones con otros registros.", "eliminarEntrada " + ex.getMessage());
            }
            Logger.getLogger(TbEntradasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la entrada.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la entrada.", "eliminarEntrada " + ex.getMessage());
        }
    }

    
}

