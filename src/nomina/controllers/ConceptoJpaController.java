/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.controllers;

import nomina.controllers.exceptions.NonexistentEntityException;
import nomina.entities.Concepto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nomina.entities.Rol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RickyTB
 */
public class ConceptoJpaController implements Serializable {

    public ConceptoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Concepto concepto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rolId = concepto.getRolId();
            if (rolId != null) {
                rolId = em.getReference(rolId.getClass(), rolId.getId());
                concepto.setRolId(rolId);
            }
            em.persist(concepto);
            if (rolId != null) {
                rolId.getConceptoList().add(concepto);
                rolId = em.merge(rolId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Concepto concepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto persistentConcepto = em.find(Concepto.class, concepto.getId());
            Rol rolIdOld = persistentConcepto.getRolId();
            Rol rolIdNew = concepto.getRolId();
            if (rolIdNew != null) {
                rolIdNew = em.getReference(rolIdNew.getClass(), rolIdNew.getId());
                concepto.setRolId(rolIdNew);
            }
            concepto = em.merge(concepto);
            if (rolIdOld != null && !rolIdOld.equals(rolIdNew)) {
                rolIdOld.getConceptoList().remove(concepto);
                rolIdOld = em.merge(rolIdOld);
            }
            if (rolIdNew != null && !rolIdNew.equals(rolIdOld)) {
                rolIdNew.getConceptoList().add(concepto);
                rolIdNew = em.merge(rolIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = concepto.getId();
                if (findConcepto(id) == null) {
                    throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.");
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
            Concepto concepto;
            try {
                concepto = em.getReference(Concepto.class, id);
                concepto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.", enfe);
            }
            Rol rolId = concepto.getRolId();
            if (rolId != null) {
                rolId.getConceptoList().remove(concepto);
                rolId = em.merge(rolId);
            }
            em.remove(concepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Concepto> findConceptoEntities() {
        return findConceptoEntities(true, -1, -1);
    }

    public List<Concepto> findConceptoEntities(int maxResults, int firstResult) {
        return findConceptoEntities(false, maxResults, firstResult);
    }

    private List<Concepto> findConceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Concepto.class));
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

    public Concepto findConcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Concepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getConceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Concepto> rt = cq.from(Concepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
