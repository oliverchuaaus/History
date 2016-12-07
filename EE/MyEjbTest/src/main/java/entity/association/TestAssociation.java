package entity.association;

import javax.naming.NamingException;

import junit.framework.TestCase;
import util.EjbUtil;
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

public class TestAssociation extends TestCase {
    private AssociationService getBean() {
	try {
	    return (AssociationService) EjbUtil
		    .getBean("AssociationServiceImpl/remote");
	} catch (NamingException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public void testOneToOneBi() throws NamingException {
	AssociationService service = getBean();
	EntityA entityA = new EntityA();
	EntityB entityB = new EntityB();
	entityA = service.createAB(entityA, entityB);
	entityA = service.getA(entityA.getId());
	assertNotNull(entityA.getEntityB());
    }

    public void testManyToOneBi() throws NamingException {
	AssociationService service = getBean();
	EntityC entityC = new EntityC();
	EntityD entityD = new EntityD();
	entityC = service.createCD(entityC, entityD);
	entityC = service.getC(entityC.getId());
	assertNotNull(entityC.getEntityD());
    }

    public void testManyToManyBi() throws NamingException {
	AssociationService service = getBean();
	EntityE entityE = new EntityE();
	EntityF entityF = new EntityF();
	entityE = service.createEF(entityE, entityF);
	entityE = service.getE(entityE.getId());
	assertTrue(entityE.getEntityF().size() > 0);
    }

    public void testOneToOneUni() throws NamingException {
	AssociationService service = getBean();
	EntityG entityG = new EntityG();
	EntityH entityH = new EntityH();
	entityG = service.createGH(entityG, entityH);
	entityG = service.getG(entityG.getId());
	assertNotNull(entityG.getEntityH());
    }

    public void testManyToOneUni() throws NamingException {
	AssociationService service = getBean();
	EntityI entityI = new EntityI();
	EntityJ entityJ = new EntityJ();
	entityI = service.createIJ(entityI, entityJ);
	entityI = service.getI(entityI.getId());
	assertNotNull(entityI.getEntityJ());
    }

    public void testOneToManyUni() throws NamingException {
	AssociationService service = getBean();
	EntityK entityK = new EntityK();
	EntityL entityL = new EntityL();
	entityK = service.createKL(entityK, entityL);
	entityK = service.getK(entityK.getId());
	assertTrue(entityK.getEntityL().size() > 0);
    }

    public void testManyToManyUni() throws NamingException {
	AssociationService service = getBean();
	EntityM entityM = new EntityM();
	EntityN entityN = new EntityN();
	entityM = service.createMN(entityM, entityN);
	entityM = service.getM(entityM.getId());
	assertTrue(entityM.getEntityN().size() > 0);
    }
}