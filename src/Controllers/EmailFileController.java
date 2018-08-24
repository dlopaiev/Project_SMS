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

    public List<String> loadEmails(File file) {
        EmailFile emailFile = new EmailFile(file);
        return emailFile.getEmailsFromFile();
    }

    public File chooseFile(EmailsFromFile eff) {
        File chosenFile = null;
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(eff);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            chosenFile = fc.getSelectedFile();
        }

        return chosenFile;
    }

    public void fillUpTable(List<String> emailList, JTable table) {

        String[] recordDetails;
        ecList.clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colcount = 0;
        int rowcount = 0;
        model.setRowCount(0);
        model.setColumnCount(0);

        assert (model.getRowCount() == 0) && (model.getColumnCount() == 0) :
                "The table is not empty!";

        if (emailList.size() > 0) {
            for (String item : emailList) {

                recordDetails = item.split("(\\:|\\;|\\||\\/|\\*)");
                int tokens = recordDetails.length;

                while (colcount < tokens) {
                    model.addColumn("");
                    colcount++;
                }
                model.addRow(new Object[]{});
                for (int i = 0; i < tokens; i++) {
                    model.setValueAt(recordDetails[i], rowcount, i);
                }
                rowcount++;
                ecList.add(recordDetails);
            }
        }
    }

    public void fillUpSortByCombo(JTable table, JComboBox combo) {
        /*
        String[] SORT_BY_LIST = {"Email", "First Name", "Last Name", "Phone",
            "Company", "Address", "City", "Country", "Date of Birth"};
        
        for(String item : SORT_BY_LIST) {
            combo.addItem(item);
        }
         */
        combo.removeAllItems();
        for (int i = 0; i < table.getColumnCount(); i++) {
            combo.addItem("Column " + (i + 1));
        }
    }

    public void sortTable(JTable table, int selectedColumn, int direction) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colCount = model.getColumnCount();
        int rowCount = model.getRowCount();
        List<String[]> rowData = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < rowCount) {
            String[] currentRow = new String[colCount];
            while (j < colCount) {
                currentRow[j] = checkNull(model.getValueAt(i, j));
                j++;
            }
            rowData.add(currentRow);
            j = 0;
            i++;
        }

        Collections.sort(rowData, new CellComparator(selectedColumn, direction));

        model.setRowCount(0);
        rowCount = 0;

        for (String[] row : rowData) {
            model.addRow(new Object[]{});
            j = 0;
            while (j < colCount) {
                model.setValueAt(row[j], rowCount, j);
                j++;
            }
            rowCount++;
        }
    }

    public void searchTable(JTable table, int selectedColumn, String searchedText) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int colCount = model.getColumnCount();
        model.setRowCount(0);
        Pattern pattern = Pattern.compile(searchedText.toLowerCase());
        Matcher matcher;
        int index = 0;
        List<Integer> indices = new ArrayList<>();
        for (String[] contact : ecList) {
            matcher = pattern.matcher(contact[selectedColumn].toLowerCase());
            if (matcher.find()) {
                indices.add(index);
            }
            index++;
        }
        int i = 0;
        int j = 0;
        for (int idx : indices) {
            model.addRow(new Object[]{});
            j = 0;
            while (j < colCount) {
                model.setValueAt(ecList.get(idx)[j], i, j);
                j++;
            }
            i++;
        }
    }

    public List<EmailContact> getContacts(JTable table, List<JComboBox> cblist) {
        List<EmailContact> contactList = new BigList<>();
        int[] colNumbers = new int[9];
        int rowCount = table.getRowCount();
        int counter = 0;

        for (JComboBox item : cblist) {
            colNumbers[counter] = item.getSelectedIndex() - 1;
            counter++;
        }

        for (int i = 0; i < rowCount; i++) {
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

        for (int item : colNumbers) {
            System.out.println(item);
        }

        return contactList;
    }

    private String checkNull(Object currentField) {
        String fieldValue = "";
        if (currentField != null) {
            fieldValue = currentField.toString();
        }
        return fieldValue;
    }
}

class CellComparator implements Comparator<String[]> {

    private int colNum;
    private int direction;

    CellComparator(int colNum, int direction) {
        this.colNum = colNum;
        this.direction = direction;
    }

    @Override
    public int compare(String[] rowOne, String[] rowTwo) {
        String colValue1 = "";
        String colValue2 = "";
        if (direction == 0) {
            colValue1 = rowOne[colNum];
            colValue2 = rowTwo[colNum];
        } else {
            colValue2 = rowOne[colNum];
            colValue1 = rowTwo[colNum];
        }
        return colValue1.compareTo(colValue2);
    }
}
