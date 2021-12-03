package com.example.servlets.pages;

import com.example.model.Product;
import com.example.servlets.PageChainBase;
import com.example.servlets.ServletUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ReadProductPage extends PageChainBase {

    @Override
    public void workUri(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String uri = req.getRequestURI();

        if (uri.equals("/read")) {
            Map<String, String> params = ServletUtils.mapRequestParams(req);

            JSONObject body = ServletUtils.processRequestBody(req.getReader().lines());

            List<Product> products = FACADE.makeTransaction(ServletUtils.ACTION.READ, params, body);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            try {
                PrintWriter out = resp.getWriter();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                String productsJson = gson.toJson(products);

                out.write(productsJson);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            workUriNext(req, resp);
        }
    }

}
