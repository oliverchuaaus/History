package resource.bmt;

import javax.ejb.Remote;

import common.MyEntity;

@Remote
public interface ResourceBMTService {

    public MyEntity find();

    public void clear();

    public void add(MyEntity entity);

}