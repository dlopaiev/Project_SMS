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
    
    private JTable table;
    private JPanel panel;
    private SQLiteJDBCController sjdbcController;
    
    public EmailsFromDBController() {
        
    }
    
    public EmailsFromDBController (JTable table, JPanel panel) {
        this.table = table;
        this.panel = panel;
    }
    
    public void fillUpTable(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            int i = 1;
            
            while(i <= columns) {
                columnNames.add(rsmd.getColumnName(i));
                i++;
            }

            DefaultTableModel model = (DefaultTableModel) table.getModel();            
            int rowcount = 0;
            model.setRowCount(0);
            model.setColumnCount(0);
            
            for(String name : columnNames) {
                model.addColumn(name);
            }
            i = 1;
            while(rs.next()) {
                i = 1;
                model.addRow(new Object[]{});
                while(i <= columns) {
                    model.setValueAt(rs.getString(i), rowcount, (i - 1));
                    i++;
                }
                rowcount++;
            }            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setComboBoxList() {
        for(Component comp: panel.getComponents()) {
            if (comp instanceof JComboBox) {
                ((JComboBox) comp).removeAllItems();
                ((JComboBox) comp).addItem("None");
                for(int i =0; i < table.getColumnCount(); i++) {
                    ((JComboBox) comp).addItem("Column " + (i + 1));
                }
            }
        }
    }
    
    public void setSQLiteJDBCController(SQLiteJDBCController sjdbcController) {
        this.sjdbcController = sjdbcController;
    }
    
    public void insertToDatabase(List<Integer> newRows) {
        List<String> cellValues = new ArrayList<>();
        int columns = table.getColumnCount();
        
        for(Integer row : newRows) {
            cellValues.clear();
            for(int i=0; i<columns; i++) {
                if(table.getValueAt((row-1), i) == null) {
                    cellValues.add(null);
                } else {
                    cellValues.add(table.getValueAt((row-1), i).toString());
                }                
            }
            
            assert cellValues.size() > 0 : 
                    "There is no info to insert into database";
            
            sjdbcController.createRecord(cellValues);
            
        }
    }
    
}
