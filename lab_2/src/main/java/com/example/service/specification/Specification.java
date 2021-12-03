package com.example.service.specification;

public interface Specification {

    Specification And();
    Specification Or();
    Specification isSatisfiedBy(Rule rule);
    String getRule();
    boolean isEmpty();

}
