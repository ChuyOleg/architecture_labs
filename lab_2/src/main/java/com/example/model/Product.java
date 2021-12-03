package com.example.model;

import java.time.LocalDate;

public class Product {

    private final int product_id;
    private final String title;
    private final String description;
    private final String category;
    private final int price;
    private final int sale_type_id;
    private final LocalDate start_date;
    private final LocalDate end_date;
    private final int delivery_type_id;
    private final int delivery_price;
    private final int delivery_time_days;
    private final int seller_id;

    public Product(Builder builder) {
        this.product_id = builder.product_id;
        this.title = builder.title;
        this.description = builder.description;
        this.category = builder.category;
        this.price = builder.price;
        this.sale_type_id = builder.sale_type_id;
        this.start_date = builder.start_date;
        this.end_date = builder.end_date;
        this.delivery_type_id = builder.delivery_type_id;
        this.delivery_price = builder.delivery_price;
        this.delivery_time_days = builder.delivery_time_days;
        this.seller_id = builder.seller_id;
    }

    public static class Builder {
        private int product_id;
        private final String title;
        private String description;
        private String category;
        private final int price;
        private int sale_type_id;
        private LocalDate start_date;
        private LocalDate end_date;
        private int delivery_type_id;
        private int delivery_price;
        private int delivery_time_days;
        private int seller_id;

        public Builder(String title, int price) {
            this.title = title;
            this.price = price;
        }

        public Builder setProductId(int product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setSale_type_id(int sale_type_id) {
            this.sale_type_id = sale_type_id;
            return this;
        }

        public Builder setStart_date(LocalDate start_date) {
            this.start_date = start_date;
            return this;
        }

        public Builder setEnd_date(LocalDate end_date) {
            this.end_date = end_date;
            return this;
        }

        public Builder setDelivery_type_id(int delivery_type_id) {
            this.delivery_type_id = delivery_type_id;
            return this;
        }

        public Builder setDelivery_price(int delivery_price) {
            this.delivery_price = delivery_price;
            return this;
        }

        public Builder setDelivery_time_days(int delivery_time_days) {
            this.delivery_time_days = delivery_time_days;
            return this;
        }

        public Builder setSeller_id(int seller_id) {
            this.seller_id = seller_id;
            return this;
        }

        public Product build() {
            return new Product(this);
        }

    }

    public int getProduct_id() {
        return product_id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getSale_type_id() {
        return sale_type_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public int getDelivery_type_id() {
        return delivery_type_id;
    }

    public int getDelivery_price() {
        return delivery_price;
    }

    public int getDelivery_time_days() {
        return delivery_time_days;
    }

    public int getSeller_id() {
        return seller_id;
    }

}
