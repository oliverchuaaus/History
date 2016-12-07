package resource.cmt;

import javax.ejb.Remote;

import common.MyEntity;

@Remote
public interface ResourceCMTStatefulService {

    public MyEntity find();

    public void add(MyEntity entity);
}