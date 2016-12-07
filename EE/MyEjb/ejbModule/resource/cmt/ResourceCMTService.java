package resource.cmt;

import javax.ejb.Remote;

import common.MyEntity;

@Remote
public interface ResourceCMTService {

    public MyEntity find();

    public void clear();

    public void add(MyEntity entity);

    public void addRollback(MyEntity entity);
}