package com.ntlx.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {
	private Connection conn;
	
	public Database() throws NamingException, SQLException{
		initializeDatabaseConnection();
	}
	
	public void initializeDatabaseConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/kanban");
		conn = ds.getConnection();
    }

    public ResultSet executeQuery(String sql) throws SQLException{
    	Statement statement = conn.createStatement();
		return statement.executeQuery(sql);
    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException{
    	PreparedStatement statement = conn.prepareStatement(sql);
    	return statement;
    }
    
    public void executeUpdate(String sql) throws SQLException {
    	Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
    }
}