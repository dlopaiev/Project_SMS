/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Denys
 */
public class EmailFile {
    
    private File loadedFile;
    
    public EmailFile() {
        
    }

    public EmailFile(File loadedFile) {
        this.loadedFile = loadedFile;
    }
    
    public List<String> getEmailsFromFile() {
        List<String> emailsList = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loadedFile));
            String line = reader.readLine();
            while(line != null) {
                emailsList.add(line);
                line = reader.readLine();
            }            
            reader.close();
        } catch(NullPointerException npe) {
            System.out.println("File wasn't chosen");
        } catch(FileNotFoundException fnfe) {
            System.out.println("File wasn't found");
        } catch(IOException ioe) {
            System.out.println("Error while reading the file");
        }
        
        return emailsList;
    }
       
}
