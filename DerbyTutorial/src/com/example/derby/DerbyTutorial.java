package com.example.derby;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.drda.NetworkServerControl;

/* This is an example of a Java application that uses a in-file database
 * to store its information.
 */
public class DerbyTutorial {
	// Should be present in the build dir, you can also specify a path
	private static final String databaseName = "MyDatabase";
	// This is the driver to use for the server mode
	private static final String driver = "org.apache.derby.jdbc.ClientDriver";
	// Default params are localhost:1527
	private static final String protocol = "jdbc:derby://localhost:1527/";
	// This class is in derbynet.jar but it requires derby.jar also
	private final NetworkServerControl serverControl;

	public DerbyTutorial(NetworkServerControl serverControl) {
		this.serverControl = serverControl;
	}

	private void loadDriver() {
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void startServer() {
		try {
			serverControl.start(new PrintWriter(System.out));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void shutdownServer() {
		try {
			serverControl.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertData(Connection connection) throws SQLException {
		PreparedStatement psInsert = connection
				.prepareStatement("INSERT INTO PERSON VALUES (?, ?)");

		psInsert.setString(1, "Samuel Sandru");
		psInsert.setInt(2, 24);
		psInsert.executeUpdate();

		psInsert.setString(1, "Michal Mucha");
		psInsert.setInt(2, 28);
		psInsert.executeUpdate();
	}

	private void printData(Connection connection) throws SQLException {
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT name, age FROM PERSON");
		while (rs.next()) {
			System.out.println(String.format(
					"The persons name is : %s and his age is %d ",
					rs.getString(1), rs.getInt(2)));
		}
	}

	public static void main(String[] args) throws Exception {
		DerbyTutorial derbyTutorial = new DerbyTutorial(
				new NetworkServerControl());
		derbyTutorial.startServer();
		derbyTutorial.loadDriver();

		Connection connection = DriverManager.getConnection(protocol
				+ databaseName + ";create=true");
		Statement statement = connection.createStatement();
		try {
			statement
					.execute("CREATE TABLE PERSON(name varchar(100), age int)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		derbyTutorial.insertData(connection);

		derbyTutorial.printData(connection);

		if (connection != null) {
			connection.close();
		}

		derbyTutorial.shutdownServer();
	}

}
