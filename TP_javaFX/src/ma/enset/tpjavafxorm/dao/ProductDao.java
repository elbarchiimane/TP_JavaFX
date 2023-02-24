package ma.enset.tpjavafxorm.dao;

import ma.enset.tpjavafxorm.dao.entities.Product;

import java.util.List;

public interface ProductDao  extends Dao<Product> {

    List<Product> findByQuery(String query);

}
