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

    private JTable table;

    public void setTable(JTable table) {
        this.table = table;
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public void fillUpReportTable(String sentTo, String status, String sentFrom) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colCount = model.getColumnCount();
        int rowCount = model.getRowCount();

        model.addRow(new Object[]{});
        model.setValueAt(sentTo, rowCount, 0);
        model.setValueAt(status, rowCount, 1);
        model.setValueAt(new Timestamp(System.currentTimeMillis()), rowCount, 2);
        model.setValueAt(sentFrom, rowCount, 3);
    }
}
