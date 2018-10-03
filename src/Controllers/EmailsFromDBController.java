/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Deniel
 */
public class EmailsFromDBController {

    //Object variables required to perform data manipulations
    private JTable table;
    private JPanel panel;
    private SQLiteJDBCController sjdbcController;

    public EmailsFromDBController() {

    }

    public EmailsFromDBController(JTable table, JPanel panel) {
        //Assigns provided object variables to current class variables
        this.table = table;
        this.panel = panel;
    }

    /**     
     * Method fills table up with records from given result set .     
     * @param rs ResultSet object containing records from database
     */
    public void fillUpTable(ResultSet rs) {
        try {
            //Required to get information about database table
            ResultSetMetaData rsmd = rs.getMetaData();
            //Number of table columns
            int columns = rsmd.getColumnCount();
            //List with table column names
            List<String> columnNames = new ArrayList<>();
            int i = 1;

            //Block retrieves the name for each column of the table and adds it
            //to the list
            while (i <= columns) {
                columnNames.add(rsmd.getColumnName(i));
                i++;
            }

            //Model for the table is created
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowcount = 0;
            
            //Remove all records from the table
            model.setRowCount(0);
            model.setColumnCount(0);

            //Adds required number of columns to the table assigning names
            for (String name : columnNames) {
                model.addColumn(name);
            }
            i = 1;
            
            //Block adds records from the result set to the table
            while (rs.next()) {
                i = 1;
                //New row is added to the table
                model.addRow(new Object[]{});
                while (i <= columns) {
                    //Value is set for each field of current row
                    model.setValueAt(rs.getString(i), rowcount, (i - 1));
                    i++;
                }
                rowcount++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**     
     * Method fills combo box with column numbers.
     */
    public void setComboBoxList() {
        //Block searches provided panel for any presence of combo box to add
        //values
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JComboBox) {
                //All values removed to avoid duplicates
                ((JComboBox) comp).removeAllItems();
                ((JComboBox) comp).addItem("None");
                //Block adds column numbers to found combo box
                for (int i = 0; i < table.getColumnCount(); i++) {
                    ((JComboBox) comp).addItem("Column " + (i + 1));
                }
            }
        }
    }

    /**     
     * Method assigns given controller object to class object variable.     
     * @param sjdbcController SQLiteJDBCController object
     */
    public void setSQLiteJDBCController(SQLiteJDBCController sjdbcController) {
        this.sjdbcController = sjdbcController;
    }

    /**     
     * Method creates new records in database table.     
     * @param newRows list of new rows indices that were added to the table
     */
    public void insertToDatabase(List<Integer> newRows) {
        //List of values from each field of the table row
        List<String> cellValues = new ArrayList<>();
        //Number of columns
        int columns = table.getColumnCount();

        //Block retrieves values from each field of the table row and adds it
        //to the list
        for (Integer row : newRows) {
            cellValues.clear();
            //Adds NULL or value to the list
            for (int i = 0; i < columns; i++) {
                if (table.getValueAt((row - 1), i) == null) {
                    cellValues.add(null);
                } else {
                    cellValues.add(table.getValueAt((row - 1), i).toString());
                }
            }

            //Asserts that row contains values
            assert cellValues.size() > 0 :
                    "There is no info to insert into database";

            //Record gets added to the database table
            sjdbcController.createRecord(cellValues);
        }
    }
}
