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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deniel
 */
public class SQLiteJDBCController implements JDBCController {

    private Path dbLocation = Paths.get(getJarLocation() + "/db");
    private Connection connection = null;

    @Override
    public void connect(String dbName) {        
        String url = "jdbc:sqlite:" + dbLocation + "/" + dbName + ".db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection with " + dbName + ".db has been established!");            
        } catch (SQLException sqle) {
            System.out.println("Connection has not been established!");
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void createNewDatabase(String dbName) {
        String url = "jdbc:sqlite:" + dbLocation + "/" + dbName + ".db";

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
    
    private void createNewTable(Connection connection) {
        //TODO
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Contacts (\n"
                +"ID integer NOT NULL PRIMARY KEY,\n"
                +"Email text NOT NULL,\n"
                +"FirstName text,\n"
                +"LastName text,\n"
                +"Phone text,\n"
                +"Company text,\n"
                +"Address text,\n"
                +"City text,\n"
                +"Country text,\n"
                +"DOB text \n"
                +");";
        try(Statement stmnt = connection.createStatement()) {
            stmnt.execute(sqlQuery);
            System.out.println("Table Contacts has been created");
        }catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }        
    }

    @Override
    public void creatRecord() {
        //TODO
    }

    @Override
    public void readRecord() {
        //TODO
    }

    @Override
    public void updateRecord() {
        //TODO
    }

    @Override
    public void deleteRecord() {
        //TODO
    }

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

    public List<Path> getDatabaseList() {
        List<Path> dbList = new ArrayList<>();
        if (Files.exists(dbLocation)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dbLocation, "*.db")) {
                for (Path entry : stream) {
                    dbList.add(entry.getFileName());
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        } else {
            try {
                Files.createDirectory(dbLocation);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return dbList;
    }
    
    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "%", null);
            while(rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
                //System.out.println(rs.getString("TABLE_NAME"));
            }
            rs.close();            
        }catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return tables;
    }
    
    public ResultSet getRecords(String tableName) {
        
        ResultSet rs = null;
        if (connection != null) {
            try {
                String query = "SELECT * FROM " + tableName;
                Statement stmnt = connection.createStatement();
                rs = stmnt.executeQuery(query);
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
        }
        return rs;
    }

}
