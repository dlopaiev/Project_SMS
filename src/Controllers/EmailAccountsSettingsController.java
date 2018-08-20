/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EmailAccount;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deniel
 */
public class EmailAccountsSettingsController {
    private Path dirSettings;    
    private List<EmailAccount> accounts = new ArrayList<>();

    public EmailAccountsSettingsController() {
        this.dirSettings = Paths.get(/*getJarLocation()+*/"./settings");
    }
    
    public void setAccounts(List<EmailAccount> accounts) {
        System.out.println(accounts);
        this.accounts = accounts;
    }
    
    public void getAccountsFromParent(List<EmailAccount> accounts) {
        setAccounts(accounts);
        
    }
    
    public List<Path> getSettingsFiles() {
        List<Path> settingsFiles = new ArrayList<>();
        if(Files.exists(dirSettings)) {            
            try(DirectoryStream<Path> stream = Files.newDirectoryStream(dirSettings, "*.eas")) {
                for(Path entry : stream) {
                    settingsFiles.add(entry.getFileName());
                }
            } catch(IOException ioe) {
                System.err.println(ioe);
            }
        }
        return settingsFiles;
    }
    
    public void saveSettings(String fileName) {
        Path settingsFile = Paths.get(dirSettings + "/" + fileName + ".eas");
        try {
            System.out.println(this.accounts);
        /*
        CodeSource codeSource = EmailAccountsSettingsController.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        dirSettings = Paths.get(jarDir+"/settings");
        */
        System.out.println(dirSettings);
        
            if (Files.notExists(dirSettings)) {
                Files.createDirectory(dirSettings);
            }
            if (Files.notExists(settingsFile)) {
                Files.createFile(settingsFile);
            }
            FileOutputStream fos = new FileOutputStream(settingsFile.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.accounts);
            System.out.println(this.accounts);
            oos.close();
            fos.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch (IOException ioe) {
            System.err.println(ioe);
        } /*catch (URISyntaxException use) {
            System.out.println("Url not found");
        }*/

    }
    
    public List<EmailAccount> loadSettings(String fileName) {
        Path settingsFile = Paths.get(dirSettings + "/" + fileName + ".eas");
        System.out.println(settingsFile);
        List<EmailAccount> accounts = new ArrayList<>();

        try {            
            FileInputStream fis = new FileInputStream(settingsFile.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            accounts = (List<EmailAccount>) ois.readObject();
            
            ois.close();
            fis.close();
            System.out.println(accounts);
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch (IOException ioe) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return accounts;  
    }

    private String getJarLocation(){
        String jarDir = null;
        try {
            CodeSource codeSource = EmailAccountsSettingsController.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            jarDir = jarFile.getParentFile().getPath();
        } catch (URISyntaxException use) {
            System.out.println("Url not found");
        }

        return jarDir;
    }
}
