package com.example.service.specification;

public class CompositeSpecification implements Specification {

    private final String query;

    public CompositeSpecification() {
        query = "SELECT * FROM product";
    }

    public CompositeSpecification(String query) {
        this.query = query;
    }

    @Override
    public Specification And() {
        return new CompositeSpecification(query + " AND ");
    }

    @Override
    public Specification Or() {
        return new CompositeSpecification(query + " OR ");
    }

    @Override
    public Specification isSatisfiedBy(Rule rule) {
        String adder = (query.contains("WHERE")) ? "" : " WHERE ";

        String ruleResult = rule.generate();

        return new CompositeSpecification(query + adder + ruleResult);
    }

    @Override
    public String getRule() {
        return query;
    }

    @Override
    public boolean isEmpty() {
        return query.equals("SELECT * FROM product");
    }

}
