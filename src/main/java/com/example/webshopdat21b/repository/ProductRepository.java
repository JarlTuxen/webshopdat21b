package com.example.webshopdat21b.repository;

import com.example.webshopdat21b.model.Product;
import com.example.webshopdat21b.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    //getConnetion refactored to ConnectionManager
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        //connect to db
        Connection connection = ConnectionManager.getConnection();
        try {

            Statement s = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM product"; //" WHERE id = 1 OR 1=1; --";
            ResultSet rs = s.executeQuery(SQL_QUERY);


            //read data from resultset
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                //System.out.println(id + " " + name + " " + price);
                productList.add(new Product(id, name, price));

            }

            s.close();
        } catch (
                SQLException e) {
            System.out.println("Could not create connection");
            e.printStackTrace();
        }
        return productList;
    }

    public void addProduct(Product product) {

        //connect to db
        Connection connection = ConnectionManager.getConnection();
        try {
            //prep statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO product(name, price) VALUES (?, ?)");
            //set attributer
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Could not create");
            sqlException.printStackTrace();
        }
    }

    public void deleteById(int id) {

        //connect to db
        Connection connection = ConnectionManager.getConnection();
        try {
            //create prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM product WHERE id = ?"
            );
            //set parameter
            preparedStatement.setInt(1, id);
            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Could not delete!");
            sqlException.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        final String UPDATE_QUERY = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        int id = product.getId();
        String name = product.getName();
        int price = product.getPrice();
        //connect to db
        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement psUpdateRow = connection.prepareStatement(UPDATE_QUERY);  //prepared statement
            psUpdateRow.setString(1, name);
            psUpdateRow.setInt(2, price);
            psUpdateRow.setInt(3, id);
            psUpdateRow.executeUpdate();  // Execute query
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.out.println("Could not update");
            e.printStackTrace();
        }
    }

    public Product findProductById(int id){
        //connect to db
        Connection connection = ConnectionManager.getConnection();
        try {

            Statement s = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM product WHERE id = ?"; //" WHERE id = 1 OR 1=1; --";

            PreparedStatement psProduct = connection.prepareStatement(SQL_QUERY); //prepared statement

            psProduct.setInt(1, id); // set id der skal søges på
            ResultSet rs = psProduct.executeQuery();  // Execute query

            //read data from resultset
            rs.next();
            {
                int pId = rs.getInt(1);
                String pName = rs.getString(2);
                int pPrice = rs.getInt(3);
                //System.out.println(id + " " + name + " " + price);
                return new Product(pId, pName, pPrice);
            }

        } catch (SQLException e) {
            System.out.println("Could not create connection");
            e.printStackTrace();
        }
        return null; //product not found
    }

}
