package com.projects.nathan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcMethods {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	CallableStatement cs;

	private DataSource dataSource;
	
	
	public void createClient(Client client) {
		
		
		try {
			conn = dataSource.getConnection();
			cs = conn.prepareCall("{ call CreateClient (?,?,?,?,?,?,?,?) }");
			cs.setInt(1, client.getId());
			cs.setString(2, client.getFirstName());
			cs.setString(3, client.getLastName());
			cs.setString(4, client.getAddress());
			cs.setString(5, client.getCity());
			cs.setInt(6, client.getZip());
			cs.setInt(7, client.getPhone());
			cs.setString(8, client.getEmail());
			cs.execute();	
			cs.close();
			conn.close();	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public Client readClient(int id) {

		Client client = new Client();
		
		try {
			conn = dataSource.getConnection();
			
			cs = conn.prepareCall("{ call ReadClient (?,?,?,?,?,?,?,?) }");
			cs.setInt(1, id);
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.VARCHAR);
			cs.execute();

			client.setId(id);
			client.setFirstName(cs.getString(2));
			client.setLastName(cs.getString(3));
			client.setAddress(cs.getString(4));
			client.setCity(cs.getString(5));
			client.setZip(cs.getInt(6));
			client.setPhone(cs.getInt(7));
			client.setEmail(cs.getString(8));
						    
			conn.close();
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;

	}
	
	
	public void updateClient(Client client) {
		
		
		try {
			conn = dataSource.getConnection();
			cs = conn.prepareCall("{ call UpdateClient (?,?,?,?,?,?,?,?) }");
			cs.setInt(1, client.getId());
			cs.setString(2, client.getFirstName());
			cs.setString(3, client.getLastName());
			cs.setString(4, client.getAddress());
			cs.setString(5, client.getCity());
			cs.setInt(6, client.getZip());
			cs.setInt(7, client.getPhone());
			cs.setString(8, client.getEmail());
			cs.execute();	
			cs.close();
			conn.close();	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void deleteClient(int id) {
		
		try {
			conn = dataSource.getConnection();
			cs = conn.prepareCall("{ call DeleteClient (?) }");
			cs.setInt(1, id);
			cs.execute();
			cs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
	}
}