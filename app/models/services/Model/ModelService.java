package models.services.Model;

import play.db.ebean.Model;
import play.libs.F;

import java.util.List;

public interface ModelService<T extends Model> {

    public F.Option<T> findById(Long id);

    public F.Option<T> save(T entry);

    public F.Option<List<T>> findWithPage(Integer pageSource);
}
