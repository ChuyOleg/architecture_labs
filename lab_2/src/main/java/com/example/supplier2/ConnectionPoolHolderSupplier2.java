package com.example.supplier2;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolderSupplier2 {

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolderSupplier2.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:postgresql://localhost:5432/supplier2");
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
