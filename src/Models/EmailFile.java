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

    //File with records
    private File loadedFile;

    public EmailFile() {

    }

    public EmailFile(File loadedFile) {
        this.loadedFile = loadedFile;
    }

    /**     
     * Method retrieves records from the file and saves them in the list.
     * @return list with records from the file
     */
    public List<String> getEmailsFromFile() {
        //list with records from the file
        List<String> emailsList = new ArrayList<>();

        try {
            //Opens file to read records from it
            BufferedReader reader = new BufferedReader(new FileReader(loadedFile));
            //Removes spacing on the beginning and on the end of line of text
            String line = reader.readLine().trim();
            //If line of text is not empty it gets added to the list
            while (line != null) {
                if (!line.isEmpty()) {
                    emailsList.add(line);
                }
                //Moves to the next line of the file
                line = reader.readLine();
            }
            reader.close();
        } catch (NullPointerException npe) {
            System.out.println("File wasn't chosen");
        } catch (FileNotFoundException fnfe) {
            System.out.println("File wasn't found");
        } catch (IOException ioe) {
            System.out.println("Error while reading the file");
        }

        return emailsList;
    }

}
