/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author Deniel
 */
public interface JDBCController {    
    public void connect(String dbName);
    public void disconnect();
    public void createRecord();
    public void readRecord();
    public void updateRecord();
    public void deleteRecord();
}
