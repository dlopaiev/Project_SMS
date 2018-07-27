/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmailContact;
import Models.EmailFile;
import Views.EmailsFromFile;
import java.io.File;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.magicwerk.brownies.collections.BigList;

/**
 *
 * @author Denys
 */
public class EmailFileController {
    
    public List<String> loadEmails(File file) {
        EmailFile emailFile = new EmailFile(file);
        return emailFile.getEmailsFromFile();        
    }
    
    public File chooseFile(EmailsFromFile eff) {
        File chosenFile = null;
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(eff);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            chosenFile = fc.getSelectedFile();
        } 
        
        return chosenFile;
    }
    
    public void fillUpTable(List<String> emailList, JTable table) {
                
        String[] recordDetails;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colcount = 0;
        int rowcount = 0;        
        model.setRowCount(0);
        model.setColumnCount(0);        
        
        if(emailList.size() > 0) {
            for(String item : emailList) {
                
                recordDetails = item.split("(\\:|\\;|\\||\\/|\\*)");                
                int tokens = recordDetails.length;
                
                while(colcount < tokens) {
                    model.addColumn("");
                    colcount++;
                }
                model.addRow(new Object []{});
                for(int i = 0; i < tokens; i++) {                    
                    model.setValueAt(recordDetails[i], rowcount, i);
                }                
                rowcount++;
                
            }
            
        }
    }
    
    public List<EmailContact> getContacts(JTable table, List<JComboBox> cblist) {
        List<EmailContact> contactList = new BigList<>();
        int[] colNumbers = new int[9];
        int rowCount = table.getRowCount();
        int counter =0;
        
        for(JComboBox item: cblist) {
            colNumbers[counter] = item.getSelectedIndex() - 1;
            counter++;                      
        }   
        
        for(int i = 0; i < rowCount; i++) {
            EmailContact emailContact = new EmailContact();
            emailContact.setEmail((colNumbers[0] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[0])));
            emailContact.setFirstName((colNumbers[1] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[1])));
            emailContact.setLastName((colNumbers[2] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[2])));
            emailContact.setPhone((colNumbers[3] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[3])));
            emailContact.setCompany((colNumbers[4] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[4])));
            emailContact.setAddress((colNumbers[5] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[5])));
            emailContact.setCity((colNumbers[6] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[6])));
            emailContact.setCountry((colNumbers[7] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[7])));
            emailContact.setDob((colNumbers[8] == -1) 
                    ? "" : checkNull(table.getValueAt(i, colNumbers[8])));
            contactList.add(emailContact);
        }
        
        for(int item:colNumbers) {
            System.out.println(item);
        }
        
        return contactList;
    }
    
    private String checkNull(Object currentField) {
        String fieldValue = "";
        if(currentField != null) {
            fieldValue = currentField.toString();
        }
        return fieldValue;
    }
}
