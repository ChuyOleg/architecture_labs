package com.example.service.specification;

public abstract class Rule<T> {

    protected final T value;

    public Rule(T value) {
        this.value = value;
    }

    abstract protected String generate();

}
