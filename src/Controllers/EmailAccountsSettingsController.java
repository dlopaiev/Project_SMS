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

    //Keeps path to the settings folder
    private Path dirSettings;
    //List with email accounts
    private List<EmailAccount> accounts = new ArrayList<>();

    public EmailAccountsSettingsController() {
        this.dirSettings = Paths.get(/*getJarLocation()+*/"./settings");
    }

    /**     
     * Method assigns given list with email accounts to class variable.     
     * @param accounts provided list with email accounts           
     */
    public void setAccounts(List<EmailAccount> accounts) {
        //System.out.println(accounts);
        this.accounts = accounts;
    }

    /**     
     * Method retrieves list with email accounts.     
     * @param accounts provided list with email accounts     
     */
    public void getAccountsFromParent(List<EmailAccount> accounts) {
        setAccounts(accounts);
    }

    /**     
     * Method looks for settings files with extension .eas within settings 
     * folder if it exists.     
     * @return list with paths to existing settings files      
     */
    public List<Path> getSettingsFiles() {
        List<Path> settingsFiles = new ArrayList<>();
        //Block checks if settings folder exists
        if (Files.exists(dirSettings)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirSettings, "*.eas")) {
                //Adds name of the settings file to the list
                for (Path entry : stream) {
                    settingsFiles.add(entry.getFileName());
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        return settingsFiles;
    }

    /**     
     * Method saves email accounts settings to the file with specified name.     
     * @param fileName name for the file that will keep email accounts settings     
     */
    public void saveSettings(String fileName) {
        //Keeps full path to the file
        Path settingsFile = Paths.get(dirSettings + "/" + fileName + ".eas");
        try {
            //System.out.println(this.accounts);
            //System.out.println(dirSettings);
            
            //Settings folder is created if it doesn't exist
            if (Files.notExists(dirSettings)) {
                Files.createDirectory(dirSettings);
            }
            //File to keep email accounts settings is created if it doesn't exist
            if (Files.notExists(settingsFile)) {
                Files.createFile(settingsFile);
            }
            //The list with email accounts gets written to the file as object
            FileOutputStream fos = new FileOutputStream(settingsFile.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.accounts);
            //System.out.println(this.accounts);
            oos.close();
            fos.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**     
     * Method retrieves list with email accounts from required file.     
     * @param fileName provided name of the file with email accounts settings 
     * @return list with email accounts
     */
    public List<EmailAccount> loadSettings(String fileName) {
        //Keeps full path to the file
        Path settingsFile = Paths.get(dirSettings + "/" + fileName + ".eas");
        //System.out.println(settingsFile);
        List<EmailAccount> accounts = new ArrayList<>();

        //Block opens the file and gets object with settings from there
        try {
            FileInputStream fis = new FileInputStream(settingsFile.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Object from file is assigned to local variable as List that 
            //contains EmailAccount objects
            accounts = (List<EmailAccount>) ois.readObject();

            ois.close();
            fis.close();
            //System.out.println(accounts);

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

    /**     
     * Method retrieves location for executable jar file.
     * @return path to executable jar file
     */
    private String getJarLocation() {
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
