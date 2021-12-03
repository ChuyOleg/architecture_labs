package com.example.service;

import com.example.model.Product;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class GetDataFromSuppliers {

    private static Product[] getData(String uri) {
        HttpGet request = new HttpGet(uri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String result = EntityUtils.toString(entity);

                    Gson gson = new Gson();
                    return gson.fromJson(result, Product[].class);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Product> getSupplier1Data(Map<String, String> params) {
        String searchUri= buildSearchUriForSupplier1(params);

        Product[] supplier1Date = getData(searchUri);

        List<Product> products = new ArrayList<>();
        if (supplier1Date == null) return new ArrayList<>();

        Collections.addAll(products, supplier1Date);
        return products;
    }

    public static List<Product> getSupplier2Data(Map<String, String> params) {
        Product[] allProducts = getData("http://localhost:8080/price-list");
        List<Product> products = new ArrayList<>();

        if (allProducts == null) return products;
        for (Product product : allProducts) {
            Product productDetails = Objects.requireNonNull(getData("http://localhost:8080/details/" + product.getProduct_id()))[0];
            if (params.get("minPrice") != null && Integer.parseInt(params.get("minPrice")) >= productDetails.getPrice()) continue;
            if (params.get("maxPrice") != null && Integer.parseInt(params.get("maxPrice")) <= productDetails.getPrice()) continue;
            if (params.get("category") != null && !params.get("category").equals(productDetails.getCategory())) continue;

            products.add(productDetails);
        }
        return products;
    }

    private static String buildSearchUriForSupplier1(Map<String, String> params) {

        StringBuilder baseURL = new StringBuilder("http://localhost:8080/search");

        params.forEach((k, v) -> {
            if (baseURL.toString().equals("http://localhost:8080/search")) {
                baseURL.append(String.format("?%s=%s", k, v));
            } else {
                baseURL.append(String.format("&%s=%s", k, v));
            }
        });

        return baseURL.toString();
    }
}
