package com.example.servlets.pages;

import com.example.servlets.PageChainBase;
import com.example.servlets.ServletUtils;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DeleteProductPage extends PageChainBase {

    @Override
    public void workUri(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String uri = req.getRequestURI();

        if (uri.equals("/delete")) {
            Map<String, String> params = ServletUtils.mapRequestParams(req);
            JSONObject body = ServletUtils.processRequestBody(req.getReader().lines());

            FACADE.makeTransaction(ServletUtils.ACTION.DELETE, params, body);
        } else {
            workUriNext(req, resp);
        }
    }

}
