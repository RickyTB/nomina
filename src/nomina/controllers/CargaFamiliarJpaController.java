/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.controllers;

import nomina.controllers.exceptions.NonexistentEntityException;
import nomina.entities.CargaFamiliar;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nomina.entities.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RickyTB
 */
public class CargaFamiliarJpaController implements Serializable {

    public CargaFamiliarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CargaFamiliar cargaFamiliar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoId = cargaFamiliar.getEmpleadoId();
            if (empleadoId != null) {
                empleadoId = em.getReference(empleadoId.getClass(), empleadoId.getId());
                cargaFamiliar.setEmpleadoId(empleadoId);
            }
            em.persist(cargaFamiliar);
            if (empleadoId != null) {
                empleadoId.getCargaFamiliarList().add(cargaFamiliar);
                empleadoId = em.merge(empleadoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CargaFamiliar cargaFamiliar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargaFamiliar persistentCargaFamiliar = em.find(CargaFamiliar.class, cargaFamiliar.getId());
            Empleado empleadoIdOld = persistentCargaFamiliar.getEmpleadoId();
            Empleado empleadoIdNew = cargaFamiliar.getEmpleadoId();
            if (empleadoIdNew != null) {
                empleadoIdNew = em.getReference(empleadoIdNew.getClass(), empleadoIdNew.getId());
                cargaFamiliar.setEmpleadoId(empleadoIdNew);
            }
            cargaFamiliar = em.merge(cargaFamiliar);
            if (empleadoIdOld != null && !empleadoIdOld.equals(empleadoIdNew)) {
                empleadoIdOld.getCargaFamiliarList().remove(cargaFamiliar);
                empleadoIdOld = em.merge(empleadoIdOld);
            }
            if (empleadoIdNew != null && !empleadoIdNew.equals(empleadoIdOld)) {
                empleadoIdNew.getCargaFamiliarList().add(cargaFamiliar);
                empleadoIdNew = em.merge(empleadoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cargaFamiliar.getId();
                if (findCargaFamiliar(id) == null) {
                    throw new NonexistentEntityException("The cargaFamiliar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargaFamiliar cargaFamiliar;
            try {
                cargaFamiliar = em.getReference(CargaFamiliar.class, id);
                cargaFamiliar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargaFamiliar with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoId = cargaFamiliar.getEmpleadoId();
            if (empleadoId != null) {
                empleadoId.getCargaFamiliarList().remove(cargaFamiliar);
                empleadoId = em.merge(empleadoId);
            }
            em.remove(cargaFamiliar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CargaFamiliar> findCargaFamiliarEntities() {
        return findCargaFamiliarEntities(true, -1, -1);
    }

    public List<CargaFamiliar> findCargaFamiliarEntities(int maxResults, int firstResult) {
        return findCargaFamiliarEntities(false, maxResults, firstResult);
    }

    private List<CargaFamiliar> findCargaFamiliarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CargaFamiliar.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CargaFamiliar findCargaFamiliar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CargaFamiliar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargaFamiliarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CargaFamiliar> rt = cq.from(CargaFamiliar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
