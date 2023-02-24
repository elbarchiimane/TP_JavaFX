package ma.enset.tpjavafxorm.dao;

import ma.enset.tpjavafxorm.dao.ConnectionDBSingleton;
import ma.enset.tpjavafxorm.dao.ProductDao;
import ma.enset.tpjavafxorm.dao.entities.Category;
import ma.enset.tpjavafxorm.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product find(Long id) {
        Connection connection=ConnectionDBSingleton.getCnx();
        Product p=new Product();
        try {
            PreparedStatement pstm=connection.prepareStatement("SELECT * FROM products WHERE ID=?");
            pstm.setLong(1,id);
           ResultSet rs= pstm.executeQuery();
            while (rs.next()){

                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrix(rs.getFloat("PRIX"));
                PreparedStatement pst1 = connection.prepareStatement("SELECT * FROM category WHERE ID=?");
                pst1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1=pst1.executeQuery();
                Category c =new Category();
                if (rs1.next())
                    c.setName(rs1.getString("NAME"));
                p.setCategory(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products=new ArrayList<>();
        try {
            Connection connection = ConnectionDBSingleton.getCnx();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM products");
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrix(rs.getFloat("PRIX"));
                PreparedStatement pst1 = connection.prepareStatement("SELECT * FROM category WHERE ID=?");
                pst1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1=pst1.executeQuery();
                Category c =new Category();
                if (rs1.next())
                    c.setName(rs1.getString("NAME"));
                    p.setCategory(c);
                    products.add(p);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product o) {
        Connection connection=ConnectionDBSingleton.getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO products(NAME,REFERENCE,PRIX,ID_CAT) " +
                                                                     "VALUES (?,?,?,?)");
            pst.setString(1,o.getName());
            pst.setString(2,o.getReference());
            pst.setFloat(3,o.getPrix());
            pst.setLong(4,o.getCategory().getId());
            pst.executeUpdate();
        }catch (SQLException e){
           e.printStackTrace();
        }


    }

    @Override
    public void delete(Product o) {
        Connection connection=ConnectionDBSingleton.getCnx();
        try {
            PreparedStatement pstm=connection.prepareStatement("DELETE FROM products WHERE ID= ?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Product o) {
        Connection connection=ConnectionDBSingleton.getCnx();
        try {
            PreparedStatement pstm=connection.prepareStatement("UPDATE products SET NAME= ?, REFERENCE= ?,PRIX=? ,ID_CAT= ? WHERE ID= ?");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getPrix());
            pstm.setLong(4,o.getCategory().getId());
            pstm.setLong(5,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        Connection connection=ConnectionDBSingleton.getCnx();
        List<Product> products=new ArrayList<>();
        try {
            PreparedStatement pstm=connection.prepareStatement("SELECT * FROM products WHERE NAME LIKE ? or REFERENCE LIKE ? or PRIX LIKE ? ");
            pstm.setString(1,"%"+query+"%");
            pstm.setString(2,"%"+query+"%");
            pstm.setString(3, "%"+query+"%");
            ResultSet rs=pstm.executeQuery();

            while (rs.next()){

                Product p=new Product();
                Category c=new Category();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p.setReference(rs.getString("REFERENCE"));
                p.setPrix(rs.getFloat("PRIX"));
                PreparedStatement pstm1=connection.prepareStatement("SELECT NAME from category WHERE ID=?");
                pstm1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1=pstm1.executeQuery();
               if (rs1.next()) c.setName(rs1.getString("NAME"));
                p.setCategory(c);
                products.add(p);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
