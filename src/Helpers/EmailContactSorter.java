/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Models.EmailContact;
import java.util.Comparator;

/**
 *
 * @author Deniel
 */
public class EmailContactSorter {

    class SortByEmail implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getEmail().compareTo(two.getEmail());
        }
    }
    
    class SortByFirstName implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getFirstName().compareTo(two.getFirstName());
        }
    }
    
    class SortByLastName implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getLastName().compareTo(two.getLastName());
        }
    }
    
    class SortByPhone implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getPhone().compareTo(two.getPhone());
        }
    }
    
    class SortByCompany implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getCompany().compareTo(two.getCompany());
        }
    }
    
    class SortByAddress implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getAddress().compareTo(two.getAddress());
        }
    }
    
    class SortByCity implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getCity().compareTo(two.getCity());
        }
    }
    
    class SortByCountry implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getCountry().compareTo(two.getCountry());
        }
    }
    
    class SortByDOB implements Comparator<EmailContact> {
        @Override
        public int compare(EmailContact one, EmailContact two) {
            return one.getDob().compareTo(two.getDob());
        }
    }
}
