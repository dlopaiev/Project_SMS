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
    
    private static final long serialVersionUID = 1L;
    
    private String accEmail;
    private String accPassword;
    private String accSMTP;
    private boolean status;
    
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
