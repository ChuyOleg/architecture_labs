package com.example.servlets;

import com.example.service.specification.CompositeSpecification;
import com.example.service.specification.Specification;
import com.example.service.specification.rules.CategoryIs;
import com.example.service.specification.rules.PriceGreaterThan;
import com.example.service.specification.rules.PriceLessThan;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class ServletUtils {

    public static JSONObject processRequestBody(Stream<String> lines) {
        if (lines == null) return null;

        String body = lines.reduce("", (accumulator, actual) -> accumulator + actual);

        if (body.isEmpty()) return new JSONObject();

        return new JSONObject(body);

    }

    public static Map<String, String> mapRequestParams(HttpServletRequest req) {

        Map<String, String> params = new HashMap<>();

        Enumeration<String> paramKeys = req.getParameterNames();

        while (paramKeys.hasMoreElements()) {
            String paramKey = paramKeys.nextElement();
            String paramValue = req.getParameter(paramKey);

            params.put(paramKey, paramValue);
        }

        return params;
    }

    public static String buildQueryWithFilter(Map<String, String> params) {

        Specification specification = new CompositeSpecification();

        Set<String> keys = params.keySet();
        for (String key : keys) {
            if (!specification.isEmpty()) {
                specification = specification.And();
            }

            if (key.equals("category")) {
                specification = specification.isSatisfiedBy(new CategoryIs(params.get("category")));
            } else if (key.equals("minPrice")) {
                specification = specification.isSatisfiedBy(new PriceGreaterThan(Integer.parseInt(params.get("minPrice"))));
            } else if (key.equals("maxPrice")) {
                specification = specification.isSatisfiedBy(new PriceLessThan(Integer.parseInt(params.get("maxPrice"))));
            }
        }
        return specification.getRule();

    }

    public enum ACTION {
        CREATE, READ, UPDATE, DELETE, GET_BY_FILTER
    }
}
