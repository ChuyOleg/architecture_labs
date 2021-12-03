package com.example.model;

public class Helper {

    private Helper(){}

    public static int getDeliveryTypeIdFromString(String delivery_type_id_String) throws Exception {
        switch (delivery_type_id_String) {
            case "FAST_MAIL":
                return 1;
            case "MAIL":
                return 2;
            case "SLOW_MAIL":
                return 3;
            default: throw new Exception("Incorrect delivery_type_id");
        }
    }

    public static int getSaleTypeIdFromString(String sale_type_id_String) throws Exception {
        switch (sale_type_id_String) {
            case "SELLING":
                return 1;
            case "AUCTION":
                return 2;
            case "CONTRACT":
                return 3;
            default: throw new Exception("Incorrect delivery_type_id");
        }
    }

}
