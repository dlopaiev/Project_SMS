/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmailFile;
import Views.EmailsFromFile;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;

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
    
}
