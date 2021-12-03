package com.example.db;

import com.example.AppUtil;
import com.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void create(Product product, Connection connection) throws Exception {

        try (PreparedStatement stmt = connection.prepareStatement(SQL_QUERY.CREATE.value)) {

            stmt.setString(1, product.getTitle());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());
            stmt.setInt(5, product.getSale_type_id());
            stmt.setDate(6, AppUtil.toSQLDate(product.getStart_date()));
            stmt.setDate(7, AppUtil.toSQLDate(product.getEnd_date()));
            stmt.setInt(8, product.getDelivery_type_id());
            stmt.setDouble(9, product.getDelivery_price());
            stmt.setInt(10, product.getDelivery_time_days());
            stmt.setInt(11, product.getSeller_id());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("SQLException during processing `create`", e);
        }
    }

    public List<Product> getByTitle(String title, Connection connection) throws Exception {

        try (PreparedStatement stmt = connection.prepareStatement(SQL_QUERY.READ.value)) {

            List<Product> products = new ArrayList<>();

            stmt.setString(1, title);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                final int price = resultSet.getInt("price");

                Product product = new Product.Builder(title, price)
                        .setProductId(resultSet.getInt("product_id"))
                        .setDescription(resultSet.getString("description"))
                        .setCategory(resultSet.getString("category"))
                        .setStart_date(AppUtil.toLocalDate(resultSet.getDate("start_date")))
                        .setEnd_date(AppUtil.toLocalDate(resultSet.getDate("end_date")))
                        .setDelivery_price(resultSet.getInt("delivery_price"))
                        .setDelivery_time_days(resultSet.getInt("delivery_time_days"))
                        .build();

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            throw new Exception("SQLException during processing `getOneByTitle`", e);
        }

    }

    public void updatePrice(int product_id, int price, Connection connection) throws Exception {

        try (PreparedStatement stmt = connection.prepareStatement(SQL_QUERY.UPDATE_PRICE.value)) {

            stmt.setInt(1, price);
            stmt.setInt(2, product_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("SQLException during processing `updatePrice`", e);
        }

    }

    public void deleteById(int id ,Connection connection) throws Exception {

        try (PreparedStatement stmt = connection.prepareStatement(SQL_QUERY.DELETE.value)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("SQLException during processing `deleteById`", e);
        }

    }

    public List<Product> getListProductByFilter(String query, Connection connection) throws Exception {

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            List<Product> products = new ArrayList<>();

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                final String title = resultSet.getString("title");
                final int price = resultSet.getInt("price");

                Product product = new Product.Builder(title, price)
                        .setProductId(resultSet.getInt("product_id"))
                        .setDescription(resultSet.getString("description"))
                        .setCategory(resultSet.getString("category"))
                        .setStart_date(AppUtil.toLocalDate(resultSet.getDate("start_date")))
                        .setEnd_date(AppUtil.toLocalDate(resultSet.getDate("end_date")))
                        .setDelivery_price(resultSet.getInt("delivery_price"))
                        .setDelivery_time_days(resultSet.getInt("delivery_time_days"))
                        .build();

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            throw new Exception("SQLException during processing 'getListProductByFilter'", e);
        }

    }


    enum SQL_QUERY {
        CREATE("INSERT INTO PRODUCT(title, description, price, category, sale_type_id, start_date, end_date," +
                " delivery_type_id, delivery_price, delivery_time_days, seller_id)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
        READ("SELECT * FROM product WHERE title like ?"),
        UPDATE_PRICE("UPDATE product set price = ? WHERE product_id = ?"),
        DELETE("DELETE FROM product WHERE product_id = ?");

        final String value;

        SQL_QUERY(String value) {
            this.value = value;
        }

    }

}
