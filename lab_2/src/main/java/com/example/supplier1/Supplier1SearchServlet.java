package com.example.supplier1;

import com.example.AppUtil;
import com.example.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Supplier1SearchServlet extends HttpServlet {

    Connection connection;

    {
        try {
            connection = ConnectionPoolHolderSupplier1.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String category = request.getParameter("category");
        String minPriceString = request.getParameter("minPrice");
        String maxPriceString = request.getParameter("maxPrice");

        int minPrice = (minPriceString != null) ? Integer.parseInt(minPriceString) : Integer.MIN_VALUE;
        int maxPrice = (maxPriceString != null) ? Integer.parseInt(maxPriceString) : Integer.MAX_VALUE;
        String query = buildQuery(category, minPrice, maxPrice);

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

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String productsJson = gson.toJson(products);

            out.write(productsJson);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildQuery(String category, int minPrice, int maxPrice) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM product");

        boolean whereIsUsed = false;
        if (category != null) {
            query.append(String.format(" WHERE LOWER(category) like '%s'", category));
            whereIsUsed = true;
        }
        if (minPrice != Integer.MIN_VALUE) {
            if (whereIsUsed) {
                query.append(String.format(" AND price > %d", minPrice));
            } else {
                query.append(String.format(" WHERE price > %d", minPrice));
                whereIsUsed = true;
            }
        }
        if (maxPrice != Integer.MAX_VALUE) {
            if (whereIsUsed) {
                query.append(String.format(" AND price < %d", maxPrice));
            } else {
                query.append(String.format(" WHERE price < %d", maxPrice));
            }
        }

        return query.toString();
    }

}
