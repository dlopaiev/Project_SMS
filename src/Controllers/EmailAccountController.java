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

/**
 *
 * @author Denys
 */
public class EmailAccountController {
    
    private List<EmailAccount> accountlist;
    
    public List<EmailAccount> getAccounts(JTable accTable) {
        accountlist = new ArrayList<>();
        int rowCount = accTable.getRowCount();        
        
        try {
            for (int i =0; i < rowCount; i++) {
                EmailAccount emailAccount = new EmailAccount();
                emailAccount.setAccEmail(accTable.getValueAt(i, 0).toString());
                emailAccount.setAccPassword(accTable.getValueAt(i, 1).toString());
                emailAccount.setAccSMTP(accTable.getValueAt(i, 2).toString());
                emailAccount.setStatus((Boolean) accTable.getValueAt(i, 3));
                if(emailAccount.isStatus() == true ) {
                    accountlist.add(emailAccount);
                }
            }
        } catch (NullPointerException npe) {
            System.out.println("No values in the table");
        }
               
        
        return accountlist;        
    }
    
}
