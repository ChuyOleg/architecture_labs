package com.example.servlets.pages;

import com.example.model.Product;
import com.example.service.GetDataFromSuppliers;
import com.example.servlets.PageChainBase;
import com.example.servlets.ServletUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CatalogProductPage extends PageChainBase {

    @Override
    public void workUri(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String uri = req.getRequestURI();

        if (uri.equals("/catalog")) {
            Map<String, String> params = ServletUtils.mapRequestParams(req);

            List<Product> supplier1Products = GetDataFromSuppliers.getSupplier1Data(params);
            List<Product> supplier2Products = GetDataFromSuppliers.getSupplier2Data(params);

            String queryWithRule = ServletUtils.buildQueryWithFilter(params);
            params.put("queryWithFilter", queryWithRule);

            List<Product> products = FACADE.makeTransaction(ServletUtils.ACTION.GET_BY_FILTER, params, null);

            products.addAll(supplier1Products);
            products.addAll(supplier2Products);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String productsJson = gson.toJson(products);

            try {
                PrintWriter out = resp.getWriter();
                out.write(productsJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            workUriNext(req, resp);
        }

    }
}
