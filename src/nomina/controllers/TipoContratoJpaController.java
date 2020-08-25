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
import nomina.entities.TipoContrato;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RickyTB
 */
public class TipoContratoJpaController implements Serializable {

    public TipoContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoContrato tipoContrato) {
        if (tipoContrato.getEmpleadoList() == null) {
            tipoContrato.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : tipoContrato.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getId());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            tipoContrato.setEmpleadoList(attachedEmpleadoList);
            em.persist(tipoContrato);
            for (Empleado empleadoListEmpleado : tipoContrato.getEmpleadoList()) {
                TipoContrato oldTipoContratoIdOfEmpleadoListEmpleado = empleadoListEmpleado.getTipoContratoId();
                empleadoListEmpleado.setTipoContratoId(tipoContrato);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldTipoContratoIdOfEmpleadoListEmpleado != null) {
                    oldTipoContratoIdOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldTipoContratoIdOfEmpleadoListEmpleado = em.merge(oldTipoContratoIdOfEmpleadoListEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoContrato tipoContrato) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoContrato persistentTipoContrato = em.find(TipoContrato.class, tipoContrato.getId());
            List<Empleado> empleadoListOld = persistentTipoContrato.getEmpleadoList();
            List<Empleado> empleadoListNew = tipoContrato.getEmpleadoList();
            List<String> illegalOrphanMessages = null;
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleado " + empleadoListOldEmpleado + " since its tipoContratoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getId());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            tipoContrato.setEmpleadoList(empleadoListNew);
            tipoContrato = em.merge(tipoContrato);
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    TipoContrato oldTipoContratoIdOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getTipoContratoId();
                    empleadoListNewEmpleado.setTipoContratoId(tipoContrato);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldTipoContratoIdOfEmpleadoListNewEmpleado != null && !oldTipoContratoIdOfEmpleadoListNewEmpleado.equals(tipoContrato)) {
                        oldTipoContratoIdOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldTipoContratoIdOfEmpleadoListNewEmpleado = em.merge(oldTipoContratoIdOfEmpleadoListNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoContrato.getId();
                if (findTipoContrato(id) == null) {
                    throw new NonexistentEntityException("The tipoContrato with id " + id + " no longer exists.");
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
            TipoContrato tipoContrato;
            try {
                tipoContrato = em.getReference(TipoContrato.class, id);
                tipoContrato.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoContrato with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empleado> empleadoListOrphanCheck = tipoContrato.getEmpleadoList();
            for (Empleado empleadoListOrphanCheckEmpleado : empleadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoContrato (" + tipoContrato + ") cannot be destroyed since the Empleado " + empleadoListOrphanCheckEmpleado + " in its empleadoList field has a non-nullable tipoContratoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoContrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoContrato> findTipoContratoEntities() {
        return findTipoContratoEntities(true, -1, -1);
    }

    public List<TipoContrato> findTipoContratoEntities(int maxResults, int firstResult) {
        return findTipoContratoEntities(false, maxResults, firstResult);
    }

    private List<TipoContrato> findTipoContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoContrato.class));
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

    public TipoContrato findTipoContrato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoContrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoContrato> rt = cq.from(TipoContrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
