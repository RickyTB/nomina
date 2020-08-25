/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.controllers;

import nomina.controllers.exceptions.IllegalOrphanException;
import nomina.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nomina.entities.Empleado;
import nomina.entities.Concepto;
import nomina.entities.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RickyTB
 */
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getConceptoList() == null) {
            rol.setConceptoList(new ArrayList<Concepto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoId = rol.getEmpleadoId();
            if (empleadoId != null) {
                empleadoId = em.getReference(empleadoId.getClass(), empleadoId.getId());
                rol.setEmpleadoId(empleadoId);
            }
            List<Concepto> attachedConceptoList = new ArrayList<Concepto>();
            for (Concepto conceptoListConceptoToAttach : rol.getConceptoList()) {
                conceptoListConceptoToAttach = em.getReference(conceptoListConceptoToAttach.getClass(), conceptoListConceptoToAttach.getId());
                attachedConceptoList.add(conceptoListConceptoToAttach);
            }
            rol.setConceptoList(attachedConceptoList);
            em.persist(rol);
            if (empleadoId != null) {
                empleadoId.getRolList().add(rol);
                empleadoId = em.merge(empleadoId);
            }
            for (Concepto conceptoListConcepto : rol.getConceptoList()) {
                Rol oldRolIdOfConceptoListConcepto = conceptoListConcepto.getRolId();
                conceptoListConcepto.setRolId(rol);
                conceptoListConcepto = em.merge(conceptoListConcepto);
                if (oldRolIdOfConceptoListConcepto != null) {
                    oldRolIdOfConceptoListConcepto.getConceptoList().remove(conceptoListConcepto);
                    oldRolIdOfConceptoListConcepto = em.merge(oldRolIdOfConceptoListConcepto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getId());
            Empleado empleadoIdOld = persistentRol.getEmpleadoId();
            Empleado empleadoIdNew = rol.getEmpleadoId();
            List<Concepto> conceptoListOld = persistentRol.getConceptoList();
            List<Concepto> conceptoListNew = rol.getConceptoList();
            List<String> illegalOrphanMessages = null;
            for (Concepto conceptoListOldConcepto : conceptoListOld) {
                if (!conceptoListNew.contains(conceptoListOldConcepto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Concepto " + conceptoListOldConcepto + " since its rolId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empleadoIdNew != null) {
                empleadoIdNew = em.getReference(empleadoIdNew.getClass(), empleadoIdNew.getId());
                rol.setEmpleadoId(empleadoIdNew);
            }
            List<Concepto> attachedConceptoListNew = new ArrayList<Concepto>();
            for (Concepto conceptoListNewConceptoToAttach : conceptoListNew) {
                conceptoListNewConceptoToAttach = em.getReference(conceptoListNewConceptoToAttach.getClass(), conceptoListNewConceptoToAttach.getId());
                attachedConceptoListNew.add(conceptoListNewConceptoToAttach);
            }
            conceptoListNew = attachedConceptoListNew;
            rol.setConceptoList(conceptoListNew);
            rol = em.merge(rol);
            if (empleadoIdOld != null && !empleadoIdOld.equals(empleadoIdNew)) {
                empleadoIdOld.getRolList().remove(rol);
                empleadoIdOld = em.merge(empleadoIdOld);
            }
            if (empleadoIdNew != null && !empleadoIdNew.equals(empleadoIdOld)) {
                empleadoIdNew.getRolList().add(rol);
                empleadoIdNew = em.merge(empleadoIdNew);
            }
            for (Concepto conceptoListNewConcepto : conceptoListNew) {
                if (!conceptoListOld.contains(conceptoListNewConcepto)) {
                    Rol oldRolIdOfConceptoListNewConcepto = conceptoListNewConcepto.getRolId();
                    conceptoListNewConcepto.setRolId(rol);
                    conceptoListNewConcepto = em.merge(conceptoListNewConcepto);
                    if (oldRolIdOfConceptoListNewConcepto != null && !oldRolIdOfConceptoListNewConcepto.equals(rol)) {
                        oldRolIdOfConceptoListNewConcepto.getConceptoList().remove(conceptoListNewConcepto);
                        oldRolIdOfConceptoListNewConcepto = em.merge(oldRolIdOfConceptoListNewConcepto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rol.getId();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Concepto> conceptoListOrphanCheck = rol.getConceptoList();
            for (Concepto conceptoListOrphanCheckConcepto : conceptoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Concepto " + conceptoListOrphanCheckConcepto + " in its conceptoList field has a non-nullable rolId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado empleadoId = rol.getEmpleadoId();
            if (empleadoId != null) {
                empleadoId.getRolList().remove(rol);
                empleadoId = em.merge(empleadoId);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
