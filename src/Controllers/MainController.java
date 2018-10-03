/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Timestamp;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Denys
 */
public class MainController {

    //Class object variable
    private JTable table;

    /**     
     * Method sets table to work with.
     * @param table given table
     */
    public void setTable(JTable table) {
        //Assigns given table to class object variable
        this.table = table;
    }

    /**     
     * Method clears all records from the table.
     */
    public void clearTable() {
        //Model is created for the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    /**     
     * Method fills table up with records.
     * @param sentTo email recipient
     * @param status email sending status
     * @param sentFrom email sender
     */
    public void fillUpReportTable(String sentTo, String status, String sentFrom) {
        //Model for table is created
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colCount = model.getColumnCount();
        int rowCount = model.getRowCount();

        //New row is added to the table and values get assigned to each field
        model.addRow(new Object[]{});
        model.setValueAt(sentTo, rowCount, 0);
        model.setValueAt(status, rowCount, 1);
        //Time when message was sent
        model.setValueAt(new Timestamp(System.currentTimeMillis()), rowCount, 2);
        model.setValueAt(sentFrom, rowCount, 3);
    }
}
