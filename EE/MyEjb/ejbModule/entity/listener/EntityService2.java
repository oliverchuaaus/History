package entity.listener;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EntityService2 {

    public abstract SimpleEntity2 add(SimpleEntity2 entity);

    public abstract SimpleEntity2 update(SimpleEntity2 entity);

    public abstract SimpleEntity2 remove(SimpleEntity2 entity);

    public abstract SimpleEntity2 find(Long id);

    public abstract List<SimpleEntity2> find();

}