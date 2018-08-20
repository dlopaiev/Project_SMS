/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.nio.file.Path;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Deniel
 */
public class DatabaseSettingsController {
    
    public void getVendors(String[] vendors, JComboBox combobox) {
        for(String vendor : vendors) {
            combobox.addItem(vendor);
        }
    }
    
    public void getDatabases(List<Path> dbList, JComboBox combobox) {
        for(Path db : dbList) {
            combobox.addItem(db.getFileName().toString().replaceAll(".db", ""));
        }
        
    }
    
}
