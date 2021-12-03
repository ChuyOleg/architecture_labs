package com.example.db;

import com.example.model.Product;
import com.example.servlets.ServletUtils;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class Facade {

    private final ProductDAO PRODUCT_DAO = new ProductDAO();

    public List<Product> makeTransaction(ServletUtils.ACTION action, Map<String, String> params, JSONObject body) {

        List<Product> products = null;

        try {
            Connection connection = ConnectionPoolHolder.getConnection();
            connection.setAutoCommit(false);

            products = makeAction(action, params, body, connection);

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private List<Product> makeAction(ServletUtils.ACTION action, Map<String, String> params, JSONObject body, Connection connection) {
        List<Product> products = null;

        try {
            switch (action) {
                case CREATE:
                    createAction(body, connection);
                    break;
                case READ:
                    products = readAction(params, connection);
                    break;
                case UPDATE:
                    updateAction(body, connection);
                    break;
                case DELETE:
                    deleteAction(body, connection);
                    break;
                case GET_BY_FILTER:
                    products = getByFilter(params.get("queryWithFilter"), connection);
                    break;
                default:
                    throw new RuntimeException("Incorrect action of the transaction 'makeTransaction' = " + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    private void createAction(JSONObject body, Connection connection) throws Exception {
        Product product = DbUtils.buildProductFromRequestParameters(body);

        PRODUCT_DAO.create(product, connection);
    }

    private List<Product> readAction(Map<String, String> params, Connection connection) throws Exception {
        String title = params.get("title");

        return PRODUCT_DAO.getByTitle(title, connection);
    }

    private void updateAction(JSONObject body, Connection connection) throws Exception {
        int target_id = body.getInt("id");
        int new_price = body.getInt("price");

        PRODUCT_DAO.updatePrice(target_id, new_price, connection);
    }

    private void deleteAction(JSONObject body, Connection connection) throws Exception {
        int target_id = body.getInt("id");

        PRODUCT_DAO.deleteById(target_id, connection);
    }

    private List<Product> getByFilter(String queryWithFilter, Connection connection) throws Exception {
        return PRODUCT_DAO.getListProductByFilter(queryWithFilter, connection);
    }

}
