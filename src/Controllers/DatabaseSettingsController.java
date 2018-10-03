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
    /**     
     * Fills combo box up with available vendors.
     * @param vendors array that consists of vendor names     * 
     * @param combobox represents drop-down list with vendors
     */
    public void getVendors(String[] vendors, JComboBox combobox) {
        for (String vendor : vendors) {
            combobox.addItem(vendor);
        }
    }

    /**
     * Fills combo box up with the names of available databases.
     * @param dbList the list that contains paths to existing databases 
     * @param combobox represents drop-down list with database names
     */
    public void getDatabases(List<Path> dbList, JComboBox combobox) {
        for (Path db : dbList) {
            combobox.addItem(db.getFileName().toString().replaceAll(".db", ""));
        }
    }
}
