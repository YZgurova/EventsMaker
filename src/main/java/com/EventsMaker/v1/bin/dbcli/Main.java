package com.EventsMaker.v1.bin.dbcli;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventsmaker_db","root","root");

        DataSource dataSource = new SingleConnectionDataSource(conn, false);
    }
}
