package entity.association;

import javax.ejb.Remote;

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

@Remote
public interface AssociationService {
    public EntityA createAB(EntityA entityA, EntityB entityB);

    public EntityA getA(Long id);

    public EntityC createCD(EntityC entityC, EntityD entityD);

    public EntityC getC(Long id);

    public EntityE createEF(EntityE entityE, EntityF entityF);

    public EntityE getE(Long id);

    public EntityG createGH(EntityG entityG, EntityH entityH);

    public EntityG getG(Long id);

    public EntityI createIJ(EntityI entityI, EntityJ entityJ);

    public EntityI getI(Long id);

    public EntityK createKL(EntityK entityK, EntityL entityL);

    public EntityK getK(Long id);

    public EntityM createMN(EntityM entityM, EntityN entityN);

    public EntityM getM(Long id);
}