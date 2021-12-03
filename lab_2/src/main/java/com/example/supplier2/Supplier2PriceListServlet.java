package com.example.supplier2;

import com.example.model.Product;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier2PriceListServlet extends HttpServlet {

    Connection connection;

    {
        try {
            connection = ConnectionPoolHolderSupplier2.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String query = "SELECT product_id, title, price FROM product";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            List<Product> products = new ArrayList<>();

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                final int product_id = resultSet.getInt("product_id");
                final String title = resultSet.getString("title");
                final int price = resultSet.getInt("price");

                Product product = new Product.Builder(title, price)
                        .setProductId(product_id).build();

                products.add(product);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();

            Gson gson = new Gson();
            String productsJson = gson.toJson(products);

            out.write(productsJson);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
