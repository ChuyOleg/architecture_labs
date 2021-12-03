package com.example.service.specification.rules;

import com.example.service.specification.Rule;

public class PriceLessThan extends Rule<Integer> {

    public PriceLessThan(Integer value) {
        super(value);
    }

    @Override
    protected String generate() {
        return String.format("price < %d", value);
    }
}
