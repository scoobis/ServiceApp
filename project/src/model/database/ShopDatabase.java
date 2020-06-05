package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Shop;

public class ShopDatabase {
	
	Connection connection = DatabaseConnector.getConnection();
	
	public ArrayList<Shop> getAllShops(String companyName) {
		try {
			
			ArrayList<Shop> shops = new ArrayList<>();
			
			String statement = "SELECT * FROM shop WHERE company_name = '" + companyName + "'";
			Statement query = connection.prepareStatement(statement);
			ResultSet result = query.executeQuery(statement);
			
			while(result.next()) {
				Shop shop = new Shop(result.getString("company_name"), result.getString("address"),
						result.getString("name"));
				shop.setId(result.getInt("id"));
				
				shops.add(shop);
			}
			return shops;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean saveShop(Shop shop) {
		try {
			PreparedStatement query;
			String statement = "INSERT INTO shop (name, address, company_name)"
						+ " VALUES ('" + shop.getName() + 
						"', '" + shop.getAddress() + 
						"', '" +  shop.getCompanyName() +"')";
			
			query = connection.prepareStatement(statement);
			query.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean editShop(Shop shop) {
		try {
			String statement = "UPDATE shop SET name = '" + shop.getName() + "', " +
								"address = '" + shop.getAddress() + "'" + 
								" WHERE id='" + shop.getId() + "';";
			PreparedStatement query = connection.prepareStatement(statement);
			query.executeUpdate(statement);
			
			return true;
			
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public boolean deleteShop(int id) {
		String statement = "DELETE FROM shop WHERE id=" + id + ";";
		Statement query;
		try {
			query = connection.prepareStatement(statement);
			query.executeUpdate(statement);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
