package ma.enset.tpjavafxorm.dao;

import ma.enset.tpjavafxorm.dao.CategoryDao;
import ma.enset.tpjavafxorm.dao.ConnectionDBSingleton;
import ma.enset.tpjavafxorm.dao.entities.Category;
import ma.enset.tpjavafxorm.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao{

    @Override
    public Category find(Long id) {
        Connection connection= ConnectionDBSingleton.getCnx();
        Category c=new Category();
        try {
            PreparedStatement pstm=connection.prepareStatement("SELECT * FROM category WHERE ID=?");
            pstm.setLong(1,id);
            ResultSet rs= pstm.executeQuery();
            while (rs.next()){
                c.setName(rs.getString("NAME"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories=new ArrayList<>();
        try {
            Connection connection = ConnectionDBSingleton.getCnx();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Category c=new Category();
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));
                categories.add(c);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void save(Category o) {
        Connection connection=ConnectionDBSingleton.getCnx();
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO category(NAME) VALUES (?)");
            pst.setString(1,o.getName());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category o) {
        Connection connection=ConnectionDBSingleton.getCnx();
        try {
            PreparedStatement pstm=connection.prepareStatement("DELETE FROM category WHERE ID= ?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category o) {
        Connection connection=ConnectionDBSingleton.getCnx();
        try {
            PreparedStatement pstm=connection.prepareStatement("UPDATE category SET NAME= ? WHERE ID=?");
            pstm.setString(1,o.getName());
            pstm.setLong(2,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
