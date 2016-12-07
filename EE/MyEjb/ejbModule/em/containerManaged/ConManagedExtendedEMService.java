package em.containerManaged;

import javax.ejb.Remote;

import common.MyEntity;

@Remote
public interface ConManagedExtendedEMService {
    public MyEntity add(MyEntity entity);

    public String find(Long id);

}
