package em.applicationManaged;

import javax.ejb.Remote;

import common.MyEntity;

@Remote
public interface AppManagedEMStatelessService {

    public String find(Long id);

    public MyEntity add(MyEntity entity);

    public void destroy();
}