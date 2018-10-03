/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmailAccount;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Denys
 */
public class EmailAccountController {

    //Holds list of email accounts
    private List<EmailAccount> accountlist;

    /**     
     * Method retrieves active email accounts only from the table.
     * @param accTable provided table with email accounts
     * @return list of active email accounts 
     */
    public List<EmailAccount> getAccounts(JTable accTable) {
        accountlist = new ArrayList<>();
        int rowCount = accTable.getRowCount();

        try {
            //Block is responsible for storing values from each field of the
            //table row into EmailAccount object
            for (int i = 0; i < rowCount; i++) {
                EmailAccount emailAccount = new EmailAccount();
                emailAccount.setAccEmail(checkNull(accTable.getValueAt(i, 0)));
                emailAccount.setAccPassword(checkNull(accTable.getValueAt(i, 1)));
                emailAccount.setAccSMTP(checkNull(accTable.getValueAt(i, 2)));
                emailAccount.setStatus((Boolean) accTable.getValueAt(i, 3));
                if (emailAccount.isStatus() == true) {
                    //Only active email accounts will be added to the list
                    accountlist.add(emailAccount);
                }
            }
        } catch (NullPointerException npe) {
            System.out.println("No values in the table");
        }

        return accountlist;
    }

    /**     
     * Method retrieves all email accounts from the table.
     * @param accTable provided table with email accounts
     * @return list of all email accounts 
     */
    public List<EmailAccount> getAllAccounts(JTable accTable) {
        List<EmailAccount> list = new ArrayList<>();
        int rowCount = accTable.getRowCount();

        try {
            //Block is responsible for storing values from each field of the
            //table row into EmailAccount object
            for (int i = 0; i < rowCount; i++) {
                EmailAccount emailAccount = new EmailAccount();
                emailAccount.setAccEmail(checkNull(accTable.getValueAt(i, 0)));
                emailAccount.setAccPassword(checkNull(accTable.getValueAt(i, 1)));
                emailAccount.setAccSMTP(checkNull(accTable.getValueAt(i, 2)));
                
                //Check box should have true or false status, not null
                if (accTable.getValueAt(i, 3) == null) {
                    emailAccount.setStatus(false);
                } else {
                    emailAccount.setStatus((Boolean) accTable.getValueAt(i, 3));
                }
                //Adds email account to the list
                list.add(emailAccount);
            }
        } catch (NullPointerException npe) {
            System.out.println("No values in the table");
        }
        
        return list;
    }

    /**     
     * Method fills provided table up with email contacts.
     * @param accounts list of email accounts
     * @param table provided to be filled up     
     */
    public void fillUpTable(List<EmailAccount> accounts, JTable table) {
        //Creates model for the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowcount = 0;
        model.setRowCount(0);

        //The table will be filled up with email accounts in case if list 
        //contains one or more email accounts
        if (accounts.size() > 0) {
            //Block adds new row to the table for each email account and fills
            //each field up with values retrieved from EmailAccount object
            for (EmailAccount item : accounts) {
                model.addRow(new Object[]{});
                model.setValueAt(item.getAccEmail(), rowcount, 0);
                model.setValueAt(item.getAccPassword(), rowcount, 1);
                model.setValueAt(item.getAccSMTP(), rowcount, 2);
                model.setValueAt(item.isStatus(), rowcount, 3);
                rowcount++;
            }
        }
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
