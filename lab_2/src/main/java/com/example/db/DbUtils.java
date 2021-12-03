package com.example.db;

import com.example.AppUtil;
import com.example.model.Helper;
import com.example.model.Product;
import org.json.JSONObject;

public class DbUtils {

    public static Product buildProductFromRequestParameters(JSONObject body) {
        String title = body.getString("title");
        int price = body.getInt("price");

        try {

            return new Product.Builder(title, price)
                    .setDescription(body.getString("description"))
                    .setCategory(body.getString("category"))
                    .setSale_type_id(Helper.getSaleTypeIdFromString(body.getString("sale_type")))
                    .setStart_date(AppUtil.toLocalDate(body.get("start_date")))
                    .setEnd_date(AppUtil.toLocalDate(body.get("end_date")))
                    .setDelivery_type_id(Helper.getDeliveryTypeIdFromString(body.getString("delivery_type")))
                    .setDelivery_price(body.getInt("delivery_price"))
                    .setDelivery_time_days(body.getInt("delivery_time_days"))
                    .setSeller_id(body.getInt("seller_id"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Exception during adding new Product in `buildProduct`", e);
        }
    }

}
