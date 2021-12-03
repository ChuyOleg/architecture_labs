package com.example.service.specification.rules;

import com.example.service.specification.Rule;

public class CategoryIs extends Rule<String> {

    public CategoryIs(String value) {
        super(value);
    }

    @Override
    protected String generate() {
        return String.format("category like '%s'", value);
    }
}
