package em.containerManaged;

import java.util.List;

import javax.ejb.Local;

import common.MyEntity;

@Local
public interface ConManagedEM2Service {
    public MyEntity find(Long id);

    public void clearAll();

    public List<MyEntity> findAll();

    public void required();

    public void requiresNew();

    public void never();

    public void notSupported();

    public void supports();

    public void mandatory();
}
