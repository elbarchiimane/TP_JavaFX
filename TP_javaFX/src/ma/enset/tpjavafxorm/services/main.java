package ma.enset.tpjavafxorm.services;

import ma.enset.tpjavafxorm.dao.CategoryDaoImpl;
import ma.enset.tpjavafxorm.dao.ProductDaoImpl;
import ma.enset.tpjavafxorm.dao.entities.Category;
import ma.enset.tpjavafxorm.dao.entities.Product;

public class main {
    public static void main(String[] args) {
        CatalogueService catalogueService=new CatalogueServiceImpl(new CategoryDaoImpl(),new ProductDaoImpl());
        Category c1=new Category();
        c1.setId(1);
        c1.setName("gaming");
        Product p1=new Product();
        p1.setId(3);
        p1.setName("MSI");
        p1.setReference("REF002");
        p1.setPrix(6000);
        p1.setCategory(c1);
        //catalogueService.addProduct(p1);
      catalogueService.deleteCategory(c1);

    }
}
