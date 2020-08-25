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
import nomina.entities.Cargo;
import nomina.entities.Departamento;
import nomina.entities.TipoContrato;
import nomina.entities.Permiso;
import java.util.ArrayList;
import java.util.List;
import nomina.entities.CargaFamiliar;
import nomina.entities.Empleado;
import nomina.entities.Rol;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author RickyTB
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getPermisoList() == null) {
            empleado.setPermisoList(new ArrayList<Permiso>());
        }
        if (empleado.getCargaFamiliarList() == null) {
            empleado.setCargaFamiliarList(new ArrayList<CargaFamiliar>());
        }
        if (empleado.getRolList() == null) {
            empleado.setRolList(new ArrayList<Rol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargoId = empleado.getCargoId();
            if (cargoId != null) {
                cargoId = em.getReference(cargoId.getClass(), cargoId.getId());
                empleado.setCargoId(cargoId);
            }
            Departamento departamentoId = empleado.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId = em.getReference(departamentoId.getClass(), departamentoId.getId());
                empleado.setDepartamentoId(departamentoId);
            }
            TipoContrato tipoContratoId = empleado.getTipoContratoId();
            if (tipoContratoId != null) {
                tipoContratoId = em.getReference(tipoContratoId.getClass(), tipoContratoId.getId());
                empleado.setTipoContratoId(tipoContratoId);
            }
            List<Permiso> attachedPermisoList = new ArrayList<Permiso>();
            for (Permiso permisoListPermisoToAttach : empleado.getPermisoList()) {
                permisoListPermisoToAttach = em.getReference(permisoListPermisoToAttach.getClass(), permisoListPermisoToAttach.getId());
                attachedPermisoList.add(permisoListPermisoToAttach);
            }
            empleado.setPermisoList(attachedPermisoList);
            List<CargaFamiliar> attachedCargaFamiliarList = new ArrayList<CargaFamiliar>();
            for (CargaFamiliar cargaFamiliarListCargaFamiliarToAttach : empleado.getCargaFamiliarList()) {
                cargaFamiliarListCargaFamiliarToAttach = em.getReference(cargaFamiliarListCargaFamiliarToAttach.getClass(), cargaFamiliarListCargaFamiliarToAttach.getId());
                attachedCargaFamiliarList.add(cargaFamiliarListCargaFamiliarToAttach);
            }
            empleado.setCargaFamiliarList(attachedCargaFamiliarList);
            List<Rol> attachedRolList = new ArrayList<Rol>();
            for (Rol rolListRolToAttach : empleado.getRolList()) {
                rolListRolToAttach = em.getReference(rolListRolToAttach.getClass(), rolListRolToAttach.getId());
                attachedRolList.add(rolListRolToAttach);
            }
            empleado.setRolList(attachedRolList);
            em.persist(empleado);
            if (cargoId != null) {
                cargoId.getEmpleadoList().add(empleado);
                cargoId = em.merge(cargoId);
            }
            if (departamentoId != null) {
                departamentoId.getEmpleadoList().add(empleado);
                departamentoId = em.merge(departamentoId);
            }
            if (tipoContratoId != null) {
                tipoContratoId.getEmpleadoList().add(empleado);
                tipoContratoId = em.merge(tipoContratoId);
            }
            for (Permiso permisoListPermiso : empleado.getPermisoList()) {
                Empleado oldEmpleadoIdOfPermisoListPermiso = permisoListPermiso.getEmpleadoId();
                permisoListPermiso.setEmpleadoId(empleado);
                permisoListPermiso = em.merge(permisoListPermiso);
                if (oldEmpleadoIdOfPermisoListPermiso != null) {
                    oldEmpleadoIdOfPermisoListPermiso.getPermisoList().remove(permisoListPermiso);
                    oldEmpleadoIdOfPermisoListPermiso = em.merge(oldEmpleadoIdOfPermisoListPermiso);
                }
            }
            for (CargaFamiliar cargaFamiliarListCargaFamiliar : empleado.getCargaFamiliarList()) {
                Empleado oldEmpleadoIdOfCargaFamiliarListCargaFamiliar = cargaFamiliarListCargaFamiliar.getEmpleadoId();
                cargaFamiliarListCargaFamiliar.setEmpleadoId(empleado);
                cargaFamiliarListCargaFamiliar = em.merge(cargaFamiliarListCargaFamiliar);
                if (oldEmpleadoIdOfCargaFamiliarListCargaFamiliar != null) {
                    oldEmpleadoIdOfCargaFamiliarListCargaFamiliar.getCargaFamiliarList().remove(cargaFamiliarListCargaFamiliar);
                    oldEmpleadoIdOfCargaFamiliarListCargaFamiliar = em.merge(oldEmpleadoIdOfCargaFamiliarListCargaFamiliar);
                }
            }
            for (Rol rolListRol : empleado.getRolList()) {
                Empleado oldEmpleadoIdOfRolListRol = rolListRol.getEmpleadoId();
                rolListRol.setEmpleadoId(empleado);
                rolListRol = em.merge(rolListRol);
                if (oldEmpleadoIdOfRolListRol != null) {
                    oldEmpleadoIdOfRolListRol.getRolList().remove(rolListRol);
                    oldEmpleadoIdOfRolListRol = em.merge(oldEmpleadoIdOfRolListRol);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId());
            Cargo cargoIdOld = persistentEmpleado.getCargoId();
            Cargo cargoIdNew = empleado.getCargoId();
            Departamento departamentoIdOld = persistentEmpleado.getDepartamentoId();
            Departamento departamentoIdNew = empleado.getDepartamentoId();
            TipoContrato tipoContratoIdOld = persistentEmpleado.getTipoContratoId();
            TipoContrato tipoContratoIdNew = empleado.getTipoContratoId();
            List<Permiso> permisoListOld = persistentEmpleado.getPermisoList();
            List<Permiso> permisoListNew = empleado.getPermisoList();
            List<CargaFamiliar> cargaFamiliarListOld = persistentEmpleado.getCargaFamiliarList();
            List<CargaFamiliar> cargaFamiliarListNew = empleado.getCargaFamiliarList();
            List<Rol> rolListOld = persistentEmpleado.getRolList();
            List<Rol> rolListNew = empleado.getRolList();
            List<String> illegalOrphanMessages = null;
            for (Permiso permisoListOldPermiso : permisoListOld) {
                if (!permisoListNew.contains(permisoListOldPermiso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Permiso " + permisoListOldPermiso + " since its empleadoId field is not nullable.");
                }
            }
            for (CargaFamiliar cargaFamiliarListOldCargaFamiliar : cargaFamiliarListOld) {
                if (!cargaFamiliarListNew.contains(cargaFamiliarListOldCargaFamiliar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CargaFamiliar " + cargaFamiliarListOldCargaFamiliar + " since its empleadoId field is not nullable.");
                }
            }
            for (Rol rolListOldRol : rolListOld) {
                if (!rolListNew.contains(rolListOldRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rol " + rolListOldRol + " since its empleadoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cargoIdNew != null) {
                cargoIdNew = em.getReference(cargoIdNew.getClass(), cargoIdNew.getId());
                empleado.setCargoId(cargoIdNew);
            }
            if (departamentoIdNew != null) {
                departamentoIdNew = em.getReference(departamentoIdNew.getClass(), departamentoIdNew.getId());
                empleado.setDepartamentoId(departamentoIdNew);
            }
            if (tipoContratoIdNew != null) {
                tipoContratoIdNew = em.getReference(tipoContratoIdNew.getClass(), tipoContratoIdNew.getId());
                empleado.setTipoContratoId(tipoContratoIdNew);
            }
            List<Permiso> attachedPermisoListNew = new ArrayList<Permiso>();
            for (Permiso permisoListNewPermisoToAttach : permisoListNew) {
                permisoListNewPermisoToAttach = em.getReference(permisoListNewPermisoToAttach.getClass(), permisoListNewPermisoToAttach.getId());
                attachedPermisoListNew.add(permisoListNewPermisoToAttach);
            }
            permisoListNew = attachedPermisoListNew;
            empleado.setPermisoList(permisoListNew);
            List<CargaFamiliar> attachedCargaFamiliarListNew = new ArrayList<CargaFamiliar>();
            for (CargaFamiliar cargaFamiliarListNewCargaFamiliarToAttach : cargaFamiliarListNew) {
                cargaFamiliarListNewCargaFamiliarToAttach = em.getReference(cargaFamiliarListNewCargaFamiliarToAttach.getClass(), cargaFamiliarListNewCargaFamiliarToAttach.getId());
                attachedCargaFamiliarListNew.add(cargaFamiliarListNewCargaFamiliarToAttach);
            }
            cargaFamiliarListNew = attachedCargaFamiliarListNew;
            empleado.setCargaFamiliarList(cargaFamiliarListNew);
            List<Rol> attachedRolListNew = new ArrayList<Rol>();
            for (Rol rolListNewRolToAttach : rolListNew) {
                rolListNewRolToAttach = em.getReference(rolListNewRolToAttach.getClass(), rolListNewRolToAttach.getId());
                attachedRolListNew.add(rolListNewRolToAttach);
            }
            rolListNew = attachedRolListNew;
            empleado.setRolList(rolListNew);
            empleado = em.merge(empleado);
            if (cargoIdOld != null && !cargoIdOld.equals(cargoIdNew)) {
                cargoIdOld.getEmpleadoList().remove(empleado);
                cargoIdOld = em.merge(cargoIdOld);
            }
            if (cargoIdNew != null && !cargoIdNew.equals(cargoIdOld)) {
                cargoIdNew.getEmpleadoList().add(empleado);
                cargoIdNew = em.merge(cargoIdNew);
            }
            if (departamentoIdOld != null && !departamentoIdOld.equals(departamentoIdNew)) {
                departamentoIdOld.getEmpleadoList().remove(empleado);
                departamentoIdOld = em.merge(departamentoIdOld);
            }
            if (departamentoIdNew != null && !departamentoIdNew.equals(departamentoIdOld)) {
                departamentoIdNew.getEmpleadoList().add(empleado);
                departamentoIdNew = em.merge(departamentoIdNew);
            }
            if (tipoContratoIdOld != null && !tipoContratoIdOld.equals(tipoContratoIdNew)) {
                tipoContratoIdOld.getEmpleadoList().remove(empleado);
                tipoContratoIdOld = em.merge(tipoContratoIdOld);
            }
            if (tipoContratoIdNew != null && !tipoContratoIdNew.equals(tipoContratoIdOld)) {
                tipoContratoIdNew.getEmpleadoList().add(empleado);
                tipoContratoIdNew = em.merge(tipoContratoIdNew);
            }
            for (Permiso permisoListNewPermiso : permisoListNew) {
                if (!permisoListOld.contains(permisoListNewPermiso)) {
                    Empleado oldEmpleadoIdOfPermisoListNewPermiso = permisoListNewPermiso.getEmpleadoId();
                    permisoListNewPermiso.setEmpleadoId(empleado);
                    permisoListNewPermiso = em.merge(permisoListNewPermiso);
                    if (oldEmpleadoIdOfPermisoListNewPermiso != null && !oldEmpleadoIdOfPermisoListNewPermiso.equals(empleado)) {
                        oldEmpleadoIdOfPermisoListNewPermiso.getPermisoList().remove(permisoListNewPermiso);
                        oldEmpleadoIdOfPermisoListNewPermiso = em.merge(oldEmpleadoIdOfPermisoListNewPermiso);
                    }
                }
            }
            for (CargaFamiliar cargaFamiliarListNewCargaFamiliar : cargaFamiliarListNew) {
                if (!cargaFamiliarListOld.contains(cargaFamiliarListNewCargaFamiliar)) {
                    Empleado oldEmpleadoIdOfCargaFamiliarListNewCargaFamiliar = cargaFamiliarListNewCargaFamiliar.getEmpleadoId();
                    cargaFamiliarListNewCargaFamiliar.setEmpleadoId(empleado);
                    cargaFamiliarListNewCargaFamiliar = em.merge(cargaFamiliarListNewCargaFamiliar);
                    if (oldEmpleadoIdOfCargaFamiliarListNewCargaFamiliar != null && !oldEmpleadoIdOfCargaFamiliarListNewCargaFamiliar.equals(empleado)) {
                        oldEmpleadoIdOfCargaFamiliarListNewCargaFamiliar.getCargaFamiliarList().remove(cargaFamiliarListNewCargaFamiliar);
                        oldEmpleadoIdOfCargaFamiliarListNewCargaFamiliar = em.merge(oldEmpleadoIdOfCargaFamiliarListNewCargaFamiliar);
                    }
                }
            }
            for (Rol rolListNewRol : rolListNew) {
                if (!rolListOld.contains(rolListNewRol)) {
                    Empleado oldEmpleadoIdOfRolListNewRol = rolListNewRol.getEmpleadoId();
                    rolListNewRol.setEmpleadoId(empleado);
                    rolListNewRol = em.merge(rolListNewRol);
                    if (oldEmpleadoIdOfRolListNewRol != null && !oldEmpleadoIdOfRolListNewRol.equals(empleado)) {
                        oldEmpleadoIdOfRolListNewRol.getRolList().remove(rolListNewRol);
                        oldEmpleadoIdOfRolListNewRol = em.merge(oldEmpleadoIdOfRolListNewRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getId();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Permiso> permisoListOrphanCheck = empleado.getPermisoList();
            for (Permiso permisoListOrphanCheckPermiso : permisoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Permiso " + permisoListOrphanCheckPermiso + " in its permisoList field has a non-nullable empleadoId field.");
            }
            List<CargaFamiliar> cargaFamiliarListOrphanCheck = empleado.getCargaFamiliarList();
            for (CargaFamiliar cargaFamiliarListOrphanCheckCargaFamiliar : cargaFamiliarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the CargaFamiliar " + cargaFamiliarListOrphanCheckCargaFamiliar + " in its cargaFamiliarList field has a non-nullable empleadoId field.");
            }
            List<Rol> rolListOrphanCheck = empleado.getRolList();
            for (Rol rolListOrphanCheckRol : rolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Rol " + rolListOrphanCheckRol + " in its rolList field has a non-nullable empleadoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo cargoId = empleado.getCargoId();
            if (cargoId != null) {
                cargoId.getEmpleadoList().remove(empleado);
                cargoId = em.merge(cargoId);
            }
            Departamento departamentoId = empleado.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId.getEmpleadoList().remove(empleado);
                departamentoId = em.merge(departamentoId);
            }
            TipoContrato tipoContratoId = empleado.getTipoContratoId();
            if (tipoContratoId != null) {
                tipoContratoId.getEmpleadoList().remove(empleado);
                tipoContratoId = em.merge(tipoContratoId);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
