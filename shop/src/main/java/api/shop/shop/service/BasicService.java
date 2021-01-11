package api.shop.shop.service;

import java.util.List;

public interface BasicService<CLASS, ID> {

    boolean save(CLASS object);

    List<CLASS> saveAll(List<CLASS> objects);

    boolean deleteById(ID id);

    CLASS findById(ID id);

    List<CLASS> findAll();
}
