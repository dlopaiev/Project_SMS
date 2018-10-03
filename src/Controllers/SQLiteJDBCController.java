/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deniel
 */
public class SQLiteJDBCController implements JDBCController {

    //Path to folder with available databases
    private Path dbLocation = Paths.get(/*getJarLocation() + */"./db");
    //Required to get data from database
    private Connection connection = null;
    private ResultSet rs = null;
    //Name of the database table
    private String tableName;

    /**     
     * Method performs connection to a database.     
     * @param dbName name of the database
     */
    @Override
    public void connect(String dbName) {

        //Database connection string
        String url = "jdbc:sqlite:" + dbLocation + "/" + dbName + ".db";
        try {
            //Connects to database
            connection = DriverManager.getConnection(url);
            System.out.println("Connection with " + dbName + ".db has been established!");
        } catch (SQLException sqle) {
            System.out.println("Connection has not been established!");
        }
    }

    /**     
     * Method performs connection to a database.
     * @param dbName name of the database
     * @return Connection object
     */
    public Connection connectToDB(String dbName) {

        //Database connection string
        String url = "jdbc:sqlite:" + dbLocation + "/" + dbName + ".db";
        try {
            //Connects to database
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Connection with " + dbName + ".db has been established!");
        } catch (SQLException sqle) {
            System.out.println("Connection has not been established!");
        }
        return connection;
    }

    /**     
     * Method disconnects from the database.
     */
    @Override
    public void disconnect() {
        //Block closes connection if it's open
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    /**     
     * Method creates a new database.
     * @param dbName name of the database
     */
    public void createNewDatabase(String dbName) {
        
        //Connection string
        String url = "jdbc:sqlite:" + dbLocation + "/" + dbName + ".db";

        //Block establishes connection, creates a new database if it doesn't
        //exist and calls another method to create a new table in this database
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created!");
                createNewTable(connection);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    /**     
     * Method creates a new table in database.
     * @param connection required to create a new table
     */
    private void createNewTable(Connection connection) {
        //TODO
        //SQL query that creates new table with specified columns
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Contacts (\n"
                + "ID integer NOT NULL PRIMARY KEY,\n"
                + "Email text NOT NULL,\n"
                + "FirstName text,\n"
                + "LastName text,\n"
                + "Phone text,\n"
                + "Company text,\n"
                + "Address text,\n"
                + "City text,\n"
                + "Country text,\n"
                + "DOB text \n"
                + ");";
        //Block tries to execute query provided above
        try (Statement stmnt = connection.createStatement()) {
            stmnt.execute(sqlQuery);
            //System.out.println("Table Contacts has been created");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    /**     
     * Method inserts record to database table.
     */
    @Override
    public void createRecord() {
        //TODO

    }

    /**     
     * Method inserts record to database table.
     * @param cellValues provided list with values for the row fields
     */
    public void createRecord(List<String> cellValues) {
        try {
            //Contains names for the table columns
            String colNames = "";
            //Values to be inserted in database table
            String values = "";
            //Block adds column names and values to variables separating them 
            //with coma
            for (String colName : getColumnsNames()) {
                colNames += "," + colName;
                values += ",?";
            }

            //Asserts that database table has columns in there
            assert !",".equals(colNames) : "The database table has no columns!";

            //SQL insert query
            String query = "INSERT INTO " + tableName + "(" + colNames.replaceFirst(",", "") + ") VALUES(" + values.replaceFirst(",", "") + ")";

            //Creates statement that will execute the query
            PreparedStatement pstmnt = connection.prepareStatement(query);

            int i = 0;
            //Block checks data type for each database table column and replaces
            //? mark in querry with value of matching data type
            for (String value : cellValues) {
                switch (getColumnsDataType().get(i)) {
                    case "INTEGER":
                        pstmnt.setInt(i + 1, Integer.parseInt(value));
                        break;
                    case "TEXT":
                        pstmnt.setString(i + 1, value);
                        break;
                    case "NULL":
                        pstmnt.setNull(i + 1, java.sql.Types.INTEGER);
                        break;
                }
                i++;
            }
            //Insertion is performed
            pstmnt.executeUpdate();
            pstmnt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**     
     * Method reads record from database table.
     */
    @Override
    public void readRecord() {
        //TODO
    }

    /**     
     * Method updates record in database table.
     */
    @Override
    public void updateRecord() {
        //TODO
    }

    /**     
     * Method deletes record from database table.
     */
    @Override
    public void deleteRecord() {
        //TODO
    }

    /**     
     * Method retrieves location for executable jar file.
     * @return path to executable jar file
     */
    private String getJarLocation() {
        String jarDir = null;
        try {
            CodeSource codeSource = EmailAccountsSettingsController.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            jarDir = jarFile.getParentFile().getPath();
        } catch (URISyntaxException use) {
            System.out.println("Url not found");
        }

        return jarDir;
    }

    /**     
     * Method retrieves list of available databases in db folder.
     * @return list containing paths to available databases
     */
    public List<Path> getDatabaseList() {
        //List with paths to databases
        List<Path> dbList = new ArrayList<>();
        //Block gets all files with .db extension located in db folder and adds
        //paths to them to the list
        if (Files.exists(dbLocation)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dbLocation, "*.db")) {
                for (Path entry : stream) {
                    dbList.add(entry.getFileName());
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        } else {
            //New db folder is created if it doesn't exist
            try {
                Files.createDirectory(dbLocation);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return dbList;
    }

    /**     
     * Method retrieves list of available tables in database.
     * @return list containing tables names
     */
    public List<String> getTables() {
        //list containing tables names
        List<String> tables = new ArrayList<>();
        try {
            //Required to get information about database
            DatabaseMetaData dbmd = connection.getMetaData();
            //Retrieves all tables from database
            ResultSet rs = dbmd.getTables(null, null, "%", null);
            //And saves them to the list
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
                //System.out.println(rs.getString("TABLE_NAME"));
            }
            rs.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return tables;
    }

    /**     
     * Method retrieves records from the database table.
     * @param tableName given name of the database table
     * @return ResultSet object containing records from the database table
     */
    public ResultSet getRecords(String tableName) {
        //Assigns given table name to class variable
        this.tableName = tableName;
        //Block tries to get records from the table if connection is open
        if (connection != null) {
            try {
                //SQL query to get all records from the table
                String query = "SELECT * FROM " + tableName;
                Statement stmnt = connection.createStatement();
                //Assigns retrieved records to ResultSet variable
                rs = stmnt.executeQuery(query);
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
        }
        return rs;
    }

    /**     
     * Method retrieves data types for each column in database table.
     * @return list containing columns data types
     */
    public List<String> getColumnsDataType() {
        //list containing columns data types
        List<String> dataTypes = new ArrayList<>();
        try {
            //Required to get information about result set
            ResultSetMetaData rsmd = rs.getMetaData();
            //Adds data type for each column to the list
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dataTypes.add(rsmd.getColumnTypeName(i));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return dataTypes;
    }

    /**     
     * Method retrieves names for each column in database table.
     * @return list containing columns names
     */
    public List<String> getColumnsNames() {
        //list containing columns names
        List<String> colNames = new ArrayList<>();
        try {
            //Required to get information about result set
            ResultSetMetaData rsmd = getRecords(tableName).getMetaData();
            //Adds name for each column to the list
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                colNames.add(rsmd.getColumnName(i));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return colNames;
    }

}
