package entity.association;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.association.manytomany.bi.EntityE;
import entity.association.manytomany.bi.EntityF;
import entity.association.manytomany.uni.EntityM;
import entity.association.manytomany.uni.EntityN;
import entity.association.manytoone.bi.EntityC;
import entity.association.manytoone.bi.EntityD;
import entity.association.manytoone.uni.EntityI;
import entity.association.manytoone.uni.EntityJ;
import entity.association.onetomany.uni.EntityK;
import entity.association.onetomany.uni.EntityL;
import entity.association.onetoone.bi.EntityA;
import entity.association.onetoone.bi.EntityB;
import entity.association.onetoone.uni.EntityG;
import entity.association.onetoone.uni.EntityH;

@Stateless
public class AssociationServiceImpl implements AssociationService {
    @PersistenceContext(unitName = "ejbSandbox")
    private EntityManager em;

    public EntityA createAB(EntityA entityA, EntityB entityB) {
	em.persist(entityB);
	entityA.setEntityB(entityB);
	em.persist(entityA);
	return entityA;
    }

    public EntityA getA(Long id) {
	return em.find(EntityA.class, id);
    }

    public EntityC createCD(EntityC entityC, EntityD entityD) {
	em.persist(entityD);
	entityC.setEntityD(entityD);
	em.persist(entityC);
	return entityC;
    }

    public EntityC getC(Long id) {
	return em.find(EntityC.class, id);
    }

    public EntityE createEF(EntityE entityE, EntityF entityF) {
	em.persist(entityF);
	entityE.getEntityF().add(entityF);
	em.persist(entityE);
	return entityE;
    }

    public EntityE getE(Long id) {
	EntityE entityE = em.find(EntityE.class, id);
	Set<EntityF> entityFSet = entityE.getEntityF();
	//load lazy
	entityFSet.iterator().next().getId();
	return entityE;
    }

    public EntityG createGH(EntityG entityG, EntityH entityH) {
	em.persist(entityH);
	entityG.setEntityH(entityH);
	em.persist(entityG);
	return entityG;
    }

    public EntityG getG(Long id) {
	return em.find(EntityG.class, id);
    }
    
    public EntityI createIJ(EntityI entityI, EntityJ entityJ) {
	em.persist(entityJ);
	entityI.setEntityJ(entityJ);
	em.persist(entityI);
	return entityI;
    }

    public EntityI getI(Long id) {
	return em.find(EntityI.class, id);
    }
    
    public EntityK createKL(EntityK entityK, EntityL entityL) {
	em.persist(entityL);
	entityK.getEntityL().add(entityL);
	em.persist(entityK);
	return entityK;
    }

    public EntityK getK(Long id) {
	EntityK entityK = em.find(EntityK.class, id);
	Set<EntityL> entityLSet = entityK.getEntityL();
	//load lazy
	entityLSet.iterator().next().getId();
	return entityK;
    }
    
    public EntityM createMN(EntityM entityM, EntityN entityN) {
	em.persist(entityN);
	entityM.getEntityN().add(entityN);
	em.persist(entityM);
	return entityM;
    }

    public EntityM getM(Long id) {
	EntityM entityM = em.find(EntityM.class, id);
	Set<EntityN> entityNSet = entityM.getEntityN();
	//load lazy
	entityNSet.iterator().next().getId();
	return entityM;
    }
}
