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
    
    public void fillUpTable(List<EmailAccount> accounts, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();        
        int rowcount = 0;        
        model.setRowCount(0);               
        
        if(accounts.size() > 0) {
            for(EmailAccount item : accounts) {
                model.addRow(new Object []{});
                                   
                model.setValueAt(item.getAccEmail(), rowcount, 0);
                model.setValueAt(item.getAccPassword(), rowcount, 1);
                model.setValueAt(item.getAccSMTP(), rowcount, 2);
                model.setValueAt(item.isStatus(), rowcount, 3);                
                rowcount++;
                
            }
            
        }
        
    }
    
}
