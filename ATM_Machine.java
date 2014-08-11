/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atm_machine;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pavilion
 */
public class ATM_Machine {

    /**
     * @param args the command line arguments
     */
    
    ////////Global Variables////
    static double[] account = {0.00, 0.00}; //0: Checking, 1:Savings
    static String mainPin = "1199"; 
    static String options, pin;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        BufferedReader myBReader;
        
        try{
        FileReader myReader = new FileReader("accounts.txt");
        myBReader = new BufferedReader(myReader);
        double checking, saving;
        String firstLine = myBReader.readLine();
        String secondLine = myBReader.readLine();
        checking = Double.parseDouble(firstLine);
        saving = Double.parseDouble(secondLine);
        account[0] = checking;
        account[1] = saving;
        
        }
        catch (IOException io){
            
            JOptionPane.showMessageDialog(null, "I've found this exception: " + io);
            account[0] = 0;
            account[1] = 0;
        
        }
        
        
        JOptionPane.showMessageDialog(null, "Welcome to our ATM Machine");
        Initialization();
        
    }   
    
    public static void depositFunction(){
        String options = JOptionPane.showInputDialog("Please Select the Account for Deposit \n[0] Checking \n[1] Savings");
        
        if (options.equals("0")){
            
            account[0] = Double.parseDouble(JOptionPane.showInputDialog("Please enter the amount:"));
            
        }
        
        else if (options.equals("1")){
            
            account[1] = Double.parseDouble(JOptionPane.showInputDialog("Please enter the amount:"));
            
        }        
                
        
        else {
                
            depositFunction();
            return;
            
             }
        
        JOptionPane.showMessageDialog(null, "Your Balance is now: \nChecking = " + account[0] + "\nSavings = " + account[1]);
        Initialization();
        return;
        
    }
    
    
    public static void Initialization(){
        
        
        
        
        int pinTryOut = 0;
        pin = JOptionPane.showInputDialog("Please input your security PIN:");
        
        
        while (!pin.equalsIgnoreCase(mainPin) && pinTryOut != 2){
              pin = JOptionPane.showInputDialog("The PIN was incorrect. Please try again");
              pinTryOut++;
        }
        
        if (pin.equalsIgnoreCase(mainPin)){
        
        options = JOptionPane.showInputDialog("Please Select from the Menu Options:"
                + "\n[0] Deposit"
                + "\n[1] Check Balance"
                + "\n[2] Withdraw"
                + "\n[3] Account Options");
        
                if (options.equals("0")){ //for deposits                    
                    
                   depositFunction();     
                }
                
                else if (options.equals("1")){ //for checking balances
                    
                    balance();
                }
        
                else if (options.equals("2")){ // for wthdrawing
                    
                    withdraw();
                }
                
                else if (options.equals("3")){ //for Account Options
                    
                    accountOptions();
                }
                
                else{
                    Initialization();
                    return;
                    
                }
        }
        
        else 
            JOptionPane.showMessageDialog(null, "You have entered a wrong password more than 3 times. \nYour acount is now blocked. Please call 111.111.1111");
            return;
                
         
        
        
    }
    
    
    public static void balance(){       
        
        JOptionPane.showMessageDialog(null, "This is your current balance: \nChecking: " + account[0] + "\nSavings: " + account[1]);
        Initialization();
        return;        
    }
    
    public static void withdraw(){
        
        String option = JOptionPane.showInputDialog("Please select where to withdraw from + \n[0] Checking \n[1] Savings");
        
        
       if (option.equals("0")){
           
           int amount = Integer.parseInt(JOptionPane.showInputDialog("Please select the amount you will like to withdraw"));
           account[0] = account[0] - amount;
       }
        
       else if (option.equals("1")){
           int amount = Integer.parseInt(JOptionPane.showInputDialog("Please select the amount you will like to withdraw"));
           account[1] = account[1] - amount;
           
           
       } 
        
       else{           
           withdraw();
           return;
       }
       
       JOptionPane.showMessageDialog(null, "Your Balance is now: \nChecking = " + account[0] + "\nSavings = " + account[1]);
       Initialization();
       return;
       
       
    }
    
    
    public static void accountOptions(){
        
        String option = JOptionPane.showInputDialog("Please select an option \n[0] Change the pin \n[1] Exit");
        
        if (option.equals("0")){
            
         mainPin = JOptionPane.showInputDialog("Please insert the new pin (4 digits)");
         Initialization();
         return;
            
        }
        
        else if (option.equals("1")){
            
            return;
            
        }
        
        else{
            
           accountOptions();
           return;
            
        }
        
    }
    
    
}
