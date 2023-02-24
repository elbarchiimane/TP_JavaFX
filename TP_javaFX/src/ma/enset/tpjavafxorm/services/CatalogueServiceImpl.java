package ma.enset.tpjavafxorm.services;

import ma.enset.tpjavafxorm.dao.CategoryDao;
import ma.enset.tpjavafxorm.dao.ProductDao;
import ma.enset.tpjavafxorm.dao.entities.Category;
import ma.enset.tpjavafxorm.dao.entities.Product;

import java.util.List;

public class CatalogueServiceImpl implements CatalogueService{
    private CategoryDao categoryDao;
    private ProductDao productDao;

    public CatalogueServiceImpl(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Product> searchProductByQuery(String query) {
        return productDao.findByQuery(query);
    }

    @Override
    public void addProduct(Product p) {
    productDao.save(p);
    }

    @Override
    public void updateProduct(Product p) {

        productDao.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
    productDao.delete(p);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public void addCategory(Category c) {
    categoryDao.save(c);
    }

    @Override
    public void updateCategory(Category c) {
    categoryDao.update(c);
    }

    @Override
    public void deleteCategory(Category c) {
    categoryDao.delete(c);
    }
}
