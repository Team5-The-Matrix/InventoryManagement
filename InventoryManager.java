/*
Inventory Manager Class

Authors: Noah Pearson Kramer, Aria Comeau

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class InventoryManager{



    public static void main(String[] args) throws FileNotFoundException, IOException{
       /*  String csvPath = "/Users/noahpearsonkramer/Desktop/Sprint1/inventory_team5.csv";
        boolean authenticated = true;
        int userType = 0;
        InventoryManager(csvPath,authenticated,userType); */

    }
    // constructor: creates and runs the inventory manager
    // csvPath: local path of initial csv file
    // authenticated: is the user authenticated by the authentication side?
    // userType: 0 - Admin, 1 - Employee, 2 - Customer

    public static void InventoryManager (String csvPath, int authenticated, int userType ) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //open initial csv
        Hashtable<String,String[]> inventoryTable = uploadCsv(csvPath);
        
        //run program
        boolean run = true;
        while(run){

            //which type of access?

            if(userType == 0) {         //admin

                System.out.println("Admin Access - Inventory System");
                System.out.println("Enter a choice: ");
                System.out.println("0 - Search an entry (read) ");
                System.out.println("1 - Create an entry (new inventory item)");
                System.out.println("2 - Update an entry (change inventory number, prices ) ");
                System.out.println("3 - Delete an entry (delete an inventory item entirely) ");
                System.out.println("4 - Exit system \n\n "); // back to main? or fully exit

                
                switch(Integer.parseInt(br.readLine())){                
                    case 0 :{
                        Read(userType, inventoryTable);
                        break;
                    }
                    case 1 :{
                        Create(inventoryTable);
                        break;
                    }
                    case 2 :{
                        Update(inventoryTable);
                        break;
                    }
                    case 3 :{
                        Destroy(inventoryTable);
                        break;
                    }
                    case 4 :{
                        run = false;
                        break;
                    }    
                    default:{
                        System.out.println("Invalid, try again... \n\n"); 
                        break;
                    } 
                }
            }
            else if (userType ==1) //Employee
            {
                System.out.println("Employee Access - Inventory System");
                System.out.println("Enter a choice: ");
                System.out.println("0 - Search an entry (read) ");
                System.out.println("1 - Create an entry (new inventory item)");
                System.out.println("2 - Update an entry (change inventory number, prices ) ");
                System.out.println("3 - Delete an entry (delete an inventory item entirely) ");
                System.out.println("4 - Exit system "); // back to main? or fully exit
                switch(Integer.parseInt(br.readLine())){                
                    case 0 :{
                        Read(userType, inventoryTable);
                        break;
                    }  
                    case 1 :{
                        Create(inventoryTable);
                        break;
                    }
                    case 2 :{
                        Update(inventoryTable);
                        break;
                    }
                    case 3 :{                      
                        Destroy(inventoryTable);
                        break;
                    }
                    case 4 :{
                        run = false;
                        break;
                    }
                    default: 
                        System.out.println("Invalid, try again... \n\n"); 
                }
            }
            else if(userType == 2) //customer
            {
                System.out.println("Customer Access - Inventory System");
                System.out.println("Enter a choice: ");
                System.out.println("0 - Search an entry (read) ");
                System.out.println("1 - Purchase an item ");
                System.out.println("2 - Exit system "); // back to main? or fully exit
                switch(br.read()){                
                    case 0 :{
                        Read(userType, inventoryTable);
                        break;
                        }
                    case 1 :{
                        Purchase(inventoryTable);
                        break;
                    }
                    case 2 :{
                        run = false;
                        break;
                    }
                    default :{
                        System.out.println("Invalid, try again... \n\n");
                        break;
                    }
                }
            }
        }
        
        
        System.out.println("Exiting system, writing back to CSV ");
        WriteToCSV(inventoryTable,csvPath);
        br.close();

    }
    static void WriteToCSV(Hashtable<String,String[]> inventoryTable, String path) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path,false)); //false overwrites the file, true appends. for max efficiency in future we could append /update only changed lines.
        Enumeration<String> en = inventoryTable.keys();
        String[] values = new String[4];
        int count=0;
        while(en.hasMoreElements())
        {
            String key = en.nextElement();
            values = inventoryTable.get(key);
            bw.write(key+","+values[0]+","+values[1]+","+values[2]+","+values[3]+"\n");
            count++;
        }
        bw.close();
        System.out.println("Wrote " +count+ "Lines");
        System.out.println("Successfully wrote to CSV ");
    }


    //uploadCsv : method to read initial csv file into a hashTable for fast search
    static Hashtable<String,String[]> uploadCsv(String csvPath) throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        Hashtable<String, String[]> inventoryTable = new Hashtable<String,String[]>();
        System.out.println("Loading... ");
        int lineCount = 0;
        String line;
        while ((line = br.readLine())!= null)
        {
            String[] sepLine = line.split(",");
            String[] itemInfo = new String[4];
            itemInfo[0] = sepLine[1];
            itemInfo[1] = sepLine[2];
            itemInfo[2] = sepLine[3];
            itemInfo[3] = sepLine[4];
            inventoryTable.put(sepLine[0],itemInfo); 
            lineCount++;
        }
        System.out.println("Read "+lineCount+" Lines...");
        System.out.println("Successfully Uploaded CSV");
        
        br.close();
        
        return inventoryTable;
        
        
        
    }

    



    static void Purchase(Hashtable<String,String[]> inventoryTable)
    {
        System.out.println(" method not written ");
        //customer method, uses update method.

    }

    static void Create(Hashtable<String,String[]> inventoryTable) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean create= true;
        String itemID;
        String info[] = new String[4];
        do
        {
            System.out.println(" Create An Inventory Entry: \n");
            System.out.println(" Enter Item ID:  ");
            itemID = br.readLine();
            
            System.out.println("Item ID Entered: " + itemID + "\n");
            

            System.out.println("Enter Inventory Quantity: ");
            info[0] = br.readLine();

            System.out.println("Enter Wholesale Price (do not include '$') : ");
            info[1] = br.readLine();

            System.out.println("Enter Sale Price (do not include '$') : ");
            info[2] = br.readLine();

            System.out.println("Enter Supplier ID :" );
            info[3] = br.readLine();

            System.out.println(" Item to be added: \n  ");
            System.out.println(itemID + "," + info[0]+","+ info[1]+","+info[2]+","+info[3]);
            System.out.println(" is this correct? enter y or n ");

            if(br.readLine().equals("y"))
                {
                    create = false;
                }
            else if(br.readLine().equals("n"))
                {
                    System.out.println("/n Restarting... /n");
                }    
        }
        while(create);
        
        inventoryTable.put(itemID, info);
        System.out.println(" Item Added! ");
    }

    static void Update(Hashtable<String,String[]> inventoryTable) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" Enter Item ID to Update: ");
        String itemID = br.readLine();
        String updatedValue = null;
        if (inventoryTable.containsKey(itemID)){
            String[] info = inventoryTable.get(itemID);
            System.out.println("Inventory listing: Enter a number to update" );
            System.out.println("(1)"+"Product ID: " +itemID);
            System.out.println("(2)"+"Quantity: "+ info[0]);
            System.out.println("(3)"+"Wholesale Cost: " + info[1]);
            System.out.println("(4)"+"Sale Price: " + info[2]);
            System.out.println("(5)"+"Supplier ID " + info[3]);

            switch(Integer.parseInt(br.readLine())){

                case 1:{
                    System.out.println("Update Product ID: ");
                    System.out.println("Enter new value: ");
                    updatedValue = br.readLine();

                    //delete old entry, create new one
                    inventoryTable.remove(itemID);
                    inventoryTable.put(updatedValue,info);
                    break;
                }
                case 2:{
                    System.out.println("Update Quantity: ");
                    System.out.println("Enter new value: ");
                    updatedValue = br.readLine();
                    // update array, delete old entry, create new one
                    info[0] = updatedValue;
                    inventoryTable.remove(itemID);
                    inventoryTable.put(itemID, info);
                    break;
                }
                case 3:{
                    System.out.println("Update Wholesale Cost: ");
                    System.out.println("Enter new value: ");
                    updatedValue = br.readLine();
                    // update array, delete old entry, create new one
                    info[1] = updatedValue;
                    inventoryTable.remove(itemID);
                    inventoryTable.put(itemID, info);
                    break;
                }
                case 4:{
                    System.out.println("Update Sale Price: ");
                    System.out.println("Enter new value: ");
                    updatedValue = br.readLine();
                    // update array, delete old entry, create new one
                    info[2] = updatedValue;
                    inventoryTable.remove(itemID);
                    inventoryTable.put(itemID, info);
                    break;

                }
                case 5:{
                    System.out.println("Update Supplier ID: ");
                    System.out.println("Enter new value: ");
                    updatedValue = br.readLine();
                    // update array, delete old entry, create new one
                    info[3] = updatedValue;
                    inventoryTable.remove(itemID);
                    inventoryTable.put(itemID, info);
                    break;

                }
                default:{
                    System.out.println("Entered invalid choice");
                    break;

                }
            }


        }
        else{
            System.out.println("\n Key not found, no operation performed... \n");
        } 


        //TODO


    }
    static void Read(int userType,Hashtable<String,String[]> inventoryTable){
        Scanner scn = new Scanner(System.in);
        System.out.println(" Enter Inventory ID to Read: ");
        String readID = scn.nextLine();

        if (inventoryTable.containsKey(readID)){
        String itemInfo[] = inventoryTable.get(readID);
        
        switch(userType){                
            case 0 :{ //admin login
               System.out.println(readID);
               System.out.println("Quantity: " + itemInfo[0]);
               System.out.println("Wholesale Price: " + itemInfo[1]);
               System.out.println("Sale Price: " + itemInfo[2]);
               System.out.println("Supplier: " + itemInfo[3]);
               break;
               }
            case 1 :{ //employee login
               System.out.println(readID);
               System.out.println("Quantity: " + itemInfo[0]);
               System.out.println("Wholesale Price: " + itemInfo[1]);
               System.out.println("Sale Price: " + itemInfo[2]);
               System.out.println("Supplier: " + itemInfo[3]);
               break;
               }
            case 2:{ //customer login
               System.out.println(readID);
               System.out.println("Quantity: " + itemInfo[0]);
               System.out.println("Sale Price: " + itemInfo[2]);
               break;
               }
            default:{
               System.out.println("Error, please validate authentication.)");
               }
            }
            

            }
            else{
                System.out.println(" Key not found, no operation performed \n ");
            }
         }
         
         
    
    static void Destroy(Hashtable<String,String[]> inventoryTable){
        Scanner scn = new Scanner(System.in);
        System.out.println(" Enter Inventory ID to Delete: ");
        String itemKey = scn.nextLine();
        if(inventoryTable.containsKey(itemKey)){
            inventoryTable.remove(itemKey);
            System.out.println("Entry deleted.");
        }
        else{
            System.out.println("Key not found, no operation performed \n ");
        }
        
        }
        
        

    }
    
