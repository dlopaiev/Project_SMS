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
    //Methods to be implemented by concrete class
    
    /**     
     * Method performs connection to a database.     
     * @param dbName name of the database
     */
    public void connect(String dbName);
    /**     
     * Method disconnects from a database.
     */
    public void disconnect();
    /**     
     * Method inserts record to database table.
     */
    public void createRecord();
    /**     
     * Method reads record from database table.
     */
    public void readRecord();
    /**     
     * Method updates record in database table.
     */
    public void updateRecord();
    /**     
     * Method deletes record from database table.
     */
    public void deleteRecord();
}
