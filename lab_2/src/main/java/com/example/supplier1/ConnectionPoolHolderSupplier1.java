package com.example.supplier1;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolderSupplier1 {

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolderSupplier1.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:postgresql://localhost:5432/supplier1");
                    ds.setUsername("postgres");
                    ds.setPassword("postgreSQL");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setDriverClassName("org.postgresql.Driver");
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

}
