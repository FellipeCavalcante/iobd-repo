package com.dev.fellipe.redirectUrlShortner.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {

    private final String dbname;
    private final String username;
    private final String password;
    private final String port;
    private final String host;
    private final String url;


    public ConexaoPostgreSQL() {
        this.dbname = "anotacao";
        this.username = "postgres";
        this.password = "postgres";
        this.port = "5432";
        this.host = "localhost";
        url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
    }

    public Connection getConexao() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
