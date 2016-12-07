package entity.listener;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EntityService {

    public abstract SimpleEntity add(SimpleEntity entity);

    public abstract SimpleEntity update(SimpleEntity entity);

    public abstract SimpleEntity remove(SimpleEntity entity);

    public abstract SimpleEntity find(Long id);

    public abstract List<SimpleEntity> find();

}