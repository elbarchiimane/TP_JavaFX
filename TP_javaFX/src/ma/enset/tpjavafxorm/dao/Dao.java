package ma.enset.tpjavafxorm.dao;

import java.util.List;

public interface Dao <T>{

    T find(Long id) ;
    List<T> findAll();
    void save(T o);
    void delete(T o);
    void update(T o);


}
