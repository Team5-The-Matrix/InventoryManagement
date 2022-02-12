/*

Main Class for Invertory Manager Program

Author: Noah Pearson Kramer
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main{

public static void main(String[] args) throws IOException{

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
System.out.println("Welcome to Inventory Manager V1.0 \n");

System.out.println("Log in to get started: ");
//Call Authentication class or method here

System.out.println(" Authenticated. "); //or not authenticated

        // These are test values - Authenticated Class should return something similar
        boolean authenticated = true; //(true)
        int userType = 0; //admin 
       


System.out.println("Enter local path of inventory file: ");
String path = br.readLine();

// call to inventory manager static method
InventoryManager.InventoryManager(path, authenticated, userType); 




}



}