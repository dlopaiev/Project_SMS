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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    private List<String[]> ecList = new ArrayList<>();

    /**     
     * Method retrieves records from specified file.     
     * @param file with records 
     * @return list with records retrieved from file
     */
    public List<String> loadEmails(File file) {
        EmailFile emailFile = new EmailFile(file);
        return emailFile.getEmailsFromFile();
    }

    /**     
     * Method returns file from local computer.     
     * @param eff represents EmailsFromFile object - form from which file 
     * chooser window is opened  
     * @return chosen file
     */
    public File chooseFile(EmailsFromFile eff) {
        File chosenFile = null;
        //Creates file chooser where desired file will be selected
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(eff);
        //Selected file gets assigned to local variable
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            chosenFile = fc.getSelectedFile();
        }

        return chosenFile;
    }

    /**     
     * Method fills provided table up with information stored in the list.
     * @param emailList list containing lines of text
     * @param table provided to be filled up     
     */
    public void fillUpTable(List<String> emailList, JTable table) {

        //Array that will keep tokens retrieved from the lines of text
        String[] recordDetails;
        //All records get removed from the list
        ecList.clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colcount = 0;
        int rowcount = 0;
        //Removes all rows and columns from the table
        model.setRowCount(0);
        model.setColumnCount(0);

        //We assert that table has no records in it
        assert (model.getRowCount() == 0) && (model.getColumnCount() == 0) :
                "The table is not empty!";

        if (emailList.size() > 0) {
            for (String item : emailList) {

                //Line of text gets splitted and stored in array
                recordDetails = item.split("(\\:|\\;|\\||\\/|\\*)");                
                //Required to define number of columns
                int tokens = recordDetails.length;

                //Block adds columns to the table
                while (colcount < tokens) {
                    model.addColumn("");
                    colcount++;
                }
                //Adds new row to the table
                model.addRow(new Object[]{});
                //Block inserts values to each field of the row
                for (int i = 0; i < tokens; i++) {
                    model.setValueAt(recordDetails[i], rowcount, i);
                }
                rowcount++;
                //Array is added to the list
                ecList.add(recordDetails);
            }
        }
    }

    /**     
     * Method fills up provided combo box with number of columns in the table.
     * @param table provided table
     * @param combo serves as drop-down list with all available columns in the 
     * table     
     */
    public void fillUpSortByCombo(JTable table, JComboBox combo) {
        /*
        String[] SORT_BY_LIST = {"Email", "First Name", "Last Name", "Phone",
            "Company", "Address", "City", "Country", "Date of Birth"};
        
        for(String item : SORT_BY_LIST) {
            combo.addItem(item);
        }
         */
        //Clears combo box from previous values to avoid duplicates
        combo.removeAllItems();
        //Adds to the combo box column numbers
        for (int i = 0; i < table.getColumnCount(); i++) {
            combo.addItem("Column " + (i + 1));
        }
    }

    /**     
     * Method allows to sort provided table in both ascending and descending 
     * order.
     * @param table table is being sorted
     * @param selectedColumn represents column the table has to be sorted by   
     * @param direction defines the way of sorting - ascending or descending
     */
    public void sortTable(JTable table, int selectedColumn, int direction) {
        //Creates model for the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colCount = model.getColumnCount();
        int rowCount = model.getRowCount();
        //List containing table rows with information
        List<String[]> rowData = new ArrayList<>();

        //Counters
        int i = 0;
        int j = 0;

        //Block retrieves values from each field of the table row and saves it
        //to the array
        while (i < rowCount) {
            String[] currentRow = new String[colCount];
            while (j < colCount) {
                currentRow[j] = checkNull(model.getValueAt(i, j));
                j++;
            }
            //Array is added to the list
            rowData.add(currentRow);
            j = 0;
            i++;
        }

        //Actual sorting is happening here
        Collections.sort(rowData, new CellComparator(selectedColumn, direction));

        //Clears table from records
        model.setRowCount(0);
        rowCount = 0;

        //Table is filled up with sorted values
        for (String[] row : rowData) {
            //Adds new row
            model.addRow(new Object[]{});
            j = 0;
            //Fills up each column with values
            while (j < colCount) {
                model.setValueAt(row[j], rowCount, j);
                j++;
            }
            rowCount++;
        }
    }

    /**     
     * Method allows to search provided table for specified text.
     * @param table table is being searched
     * @param selectedColumn represents column the table has to be searched by   
     * @param searchedText text that needs to be find in the table
     */
    public void searchTable(JTable table, int selectedColumn, String searchedText) {

        //Model is created for the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colCount = model.getColumnCount();
        //Remove all rows from the table if exist
        model.setRowCount(0);
        //Pattern object gets created to define what we need to find
        Pattern pattern = Pattern.compile(searchedText.toLowerCase());
        Matcher matcher;
        int index = 0;
        //List that keeps rows numbers containing searched text
        List<Integer> indices = new ArrayList<>();
        //Block searches each row for required text
        for (String[] contact : ecList) {
            matcher = pattern.matcher(contact[selectedColumn].toLowerCase());
            //Row number is added to the list
            if (matcher.find()) {
                indices.add(index);
            }
            index++;
        }
        
        //Counters
        int i = 0;
        int j = 0;
        //Block fills table up only with rows that contain searched text
        for (int idx : indices) {
            //New row is added
            model.addRow(new Object[]{});
            j = 0;
            //Each field of the row gets its value
            while (j < colCount) {
                model.setValueAt(ecList.get(idx)[j], i, j);
                j++;
            }
            i++;
        }
    }

    /**     
     * Method retrieves all records from the table and saves them to the email
     * contact list.
     * @param table provided table with records
     * @param cblist list with combo boxes   
     * @return list with email contacts
     */
    public List<EmailContact> getContacts(JTable table, List<JComboBox> cblist) {
        //List containing EmailContact objects inside
        List<EmailContact> contactList = new BigList<>();
        //Number of columns
        int[] colNumbers = new int[9];
        //Keeps number of rows
        int rowCount = table.getRowCount();
        int counter = 0;

        //Block saves to the array indices of selected fields from each combo 
        //box
        for (JComboBox item : cblist) {
            colNumbers[counter] = item.getSelectedIndex() - 1;
            counter++;
        }

        //Block creats EmailContact object and adds it to the list
        //Table can have many columns with information, but we need to specify
        //only those that need to be used to create records within EmailContact
        //object. In apropriate combo box one of the columns needs to be chosen
        //to match certain record of EmailContact object
        for (int i = 0; i < rowCount; i++) {
            //New EmailContact object is created
            EmailContact emailContact = new EmailContact();
            //Assigns values of each column to the EmailContact object records
            //verifying each field of the row on NULL value
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
            //EmailContact object is added to the list
            contactList.add(emailContact);
        }

        /*
        for (int item : colNumbers) {
            System.out.println(item);
        }
        */

        return contactList;
    }

    /**     
     * Method verifies if current field of the table represents NULL.     
     * @param currentField current table field
     * @return empty string in case if current field is NULL or value from 
     * the current field if it is not NULL      
     */
    private String checkNull(Object currentField) {
        String fieldValue = "";
        if (currentField != null) {
            fieldValue = currentField.toString();
        }
        return fieldValue;
    }
}

//Concrete class that implements Comparator interface to provide sorting feature
class CellComparator implements Comparator<String[]> {

    //Number of column and direction for sorting (ASC or DES)
    private int colNum;
    private int direction;

    CellComparator(int colNum, int direction) {
        //Assigns values to class variables
        this.colNum = colNum;
        this.direction = direction;
    }

    //Method implementation
    @Override
    public int compare(String[] rowOne, String[] rowTwo) {
        String colValue1 = "";
        String colValue2 = "";
        if (direction == 0) {
            //Ascending order
            colValue1 = rowOne[colNum];
            colValue2 = rowTwo[colNum];
        } else {
            //Descending order
            colValue2 = rowOne[colNum];
            colValue1 = rowTwo[colNum];
        }
        return colValue1.compareTo(colValue2);
    }
}
