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
import model.TbPaises;
import model.TbPaisesDto;
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
public class TbPaisesService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA", 79

    public Respuesta getPais(Long paiId) {
        try {
            Query qryEspecie = em.createNamedQuery("TbPaises.findByPaiId", TbPaises.class);
            qryEspecie.setParameter("paiId", paiId);

            return new Respuesta(true, "", "", "TbPaises", new TbPaisesDto((TbPaises) qryEspecie.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un pais con el código ingresado.", "getPais NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbPaisesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el pais.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el pais.", "getPais NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbPaisesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el pais.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el pais.", "getPais " + ex.getMessage());
        }
    }

    

    public Respuesta guardarPais(TbPaisesDto tbpaisesDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbPaises pais;
            
            if (tbpaisesDto.getPaiId()!= null && tbpaisesDto.getPaiId()> 0) {
                pais = em.find(TbPaises.class, tbpaisesDto.getPaiId());
                if (pais == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el pais a modificar.", "guardarPais NoResultException");
                }
                pais.actualizarPais(tbpaisesDto);
                pais = em.merge(pais);
            } else {
                pais = new TbPaises(tbpaisesDto);
                em.persist(pais);
            }
            et.commit();
            return new Respuesta(true, "", "", "Pais", new TbPaisesDto(pais));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbPaisesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el pais.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el pais.", "guardarPais " + ex.getMessage());
        }
    }

    public Respuesta eliminarPais(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbPaises pais;
            if (id != null ) {
                pais = em.find(TbPaises.class, id);
                if (pais == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el pais a eliminar.", "eliminarPais NoResultException");
                }
                em.remove(pais);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el pais a eliminar.", "eliminarPais NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el pais porque tiene relaciones con otros registros.", "eliminarPais " + ex.getMessage());
            }
            Logger.getLogger(TbPaisesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el pais.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el pais.", "eliminarPais " + ex.getMessage());
        }
    }

    
}