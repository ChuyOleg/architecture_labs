package com.example.service.specification.rules;

import com.example.service.specification.Rule;

public class PriceGreaterThan extends Rule<Integer> {

    public PriceGreaterThan(Integer value) {
        super(value);
    }

    @Override
    protected String generate() {
        return String.format("price > %d", value);
    }
}