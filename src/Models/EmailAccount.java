/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author Denys
 */
public class EmailAccount implements Serializable {

    //required for serialization
    private static final long serialVersionUID = 1L;

    //Variables required to keep:
    //Email account
    private String accEmail;
    //Password to this account
    private String accPassword;
    //SMTP server for this email account
    private String accSMTP;
    //Status of account (active or not in the table)
    private boolean status;

    //Setters and Getters for each class variable
    public String getAccEmail() {
        return accEmail;
    }

    public void setAccEmail(String accEmail) {
        this.accEmail = accEmail;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public String getAccSMTP() {
        return accSMTP;
    }

    public void setAccSMTP(String accSMTP) {
        this.accSMTP = accSMTP;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
