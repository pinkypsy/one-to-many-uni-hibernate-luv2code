package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	private static ConnectionManager instance = null;

	private static String userName = null;
	private static String password = null;

	private DBtype dbType = DBtype.MySQL;

	private Connection conn = null;

	private ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public void setDBType(DBtype dbType) {
		this.dbType = dbType;
	}

	private boolean openConnection()  {
		try {
			String oracleConnectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";

			String mysqlConnectionUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
			switch (dbType) {

			case MySQL:
				Class.forName("com.mysql.jdbc.Driver");
				userName = "hbstudent";
				password = "hbstudent";
				conn = DriverManager.getConnection(mysqlConnectionUrl, userName, password);
				return true;

			case ORACLE:
				Class.forName("oracle.jdbc.driver.OracleDriver");
				userName = "hr";
				password = "hr";
				conn = DriverManager.getConnection(oracleConnectionUrl, userName, password);
				return true;

			default: 
				return false;
			}
		}
		catch (SQLException e) {
			processException(e);
			return false;
		}catch (ClassNotFoundException e){
			e.getMessage();
			return false;
		}

	}

	public Connection getConnection()  {
		if (conn == null) {
			if (openConnection()) {
				System.out.println("Connection opened");
				return conn;
			} else {
				return null;
			}
		}
		return conn;
	}

	public static void processException (SQLException e){
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}

	public void close() {
		System.out.println("Connection closed");
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
		}
	}

}