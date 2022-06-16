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
import model.TbEspecies;
import model.TbEspeciesDto;
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
public class TbEspeciesService {

    
    EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    //REVISAR "ENTRADA" Ó "TBENTRADA"

    public Respuesta getEspecie(Long espId) {
        try {
            Query qryEspecie = em.createNamedQuery("TbEspecies.findByEspId", TbEspecies.class);
            qryEspecie.setParameter("espId", espId);

            return new Respuesta(true, "", "", "TbEspecies", new TbEspeciesDto((TbEspecies) qryEspecie.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe una especie con el código ingresado.", "getEspecie NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TbEspeciesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la especie.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la especie.", "getEspecie NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TbEspeciesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la especie.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar la especie.", "getEspecie " + ex.getMessage());
        }
    }

    public Respuesta guardarEspecie(TbEspeciesDto tbespeciesDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TbEspecies especie;
            
            if (tbespeciesDto.getEspId()!= null && tbespeciesDto.getEspId()> 0) {
                especie = em.find(TbEspecies.class, tbespeciesDto.getEspId());
                if (especie == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró la especie a modificar.", "guardarEspecie NoResultException");
                }
                especie.actualizarEspecie(tbespeciesDto);
                especie = em.merge(especie);
            } else {
                especie = new TbEspecies(tbespeciesDto);
                em.persist(especie);
            }
            et.commit();
            return new Respuesta(true, "", "", "Especie", new TbEspeciesDto(especie));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TbEspeciesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la especie.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar la especie.", "guardarEspecie " + ex.getMessage());
        }
    }

    public Respuesta eliminarEspecie(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TbEspecies especie;
            if (id != null ) {
                especie = em.find(TbEspecies.class, id);
                if (especie == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la especie a eliminar.", "eliminarEspecie NoResultException");
                }
                em.remove(especie);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar la especie a eliminar.", "eliminarEspecie NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar la especie porque tiene relaciones con otros registros.", "eliminarEspecie " + ex.getMessage());
            }
            Logger.getLogger(TbEspeciesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la especie.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la especie.", "eliminarEspecie " + ex.getMessage());
        }
    }

    
}