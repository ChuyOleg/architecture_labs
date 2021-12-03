package com.example.supplier2;

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
import java.util.regex.Pattern;

public class Supplier2DetailsServlet extends HttpServlet {

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

        final String URI = request.getRequestURI();

        String query = buildQuery(URI);

        if (query == null) return;

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

    private String buildQuery(String URI) {
        String query;

        final String URI_REMAINDER = URI.substring(URI.indexOf("/", 1) + 1);
        Pattern pattern = Pattern.compile("\\d+");

        if (pattern.matcher(URI_REMAINDER).matches()) {
            int productId = Integer.parseInt(URI_REMAINDER);
            query = String.format("SELECT * FROM product WHERE product_id = %d", productId);
        } else {
            query = null;
        }

        return query;
    }

}
