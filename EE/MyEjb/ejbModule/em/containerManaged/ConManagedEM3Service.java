package em.containerManaged;

import java.util.List;

import javax.ejb.Remote;

import common.MyEntity;

@Remote
public interface ConManagedEM3Service {
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
