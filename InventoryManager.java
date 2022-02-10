import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;

public class InventoryManager{



    public static void main(String[] args) throws FileNotFoundException, IOException{
        String csvPath = "/Users/noahpearsonkramer/Desktop/Sprint1/inventory_team5.csv";
        boolean authenticated = true;
        int userType = 0;
        InventoryManager(csvPath,authenticated,userType);

    }
    // constructor: creates and runs the inventory manager
    // csvPath: local path of initial csv file
    // authenticated: is the user authenticated by the authentication side?
    // userType: 0 - Admin, 1 - Employee, 2 - Customer

    public static void InventoryManager (String csvPath, boolean authenticated, int userType ) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        Hashtable<String,String[]> inventoryTable = uploadCsv(csvPath);
        while(run){

            //open initial csv
            

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
        //TODO: WRITE OUT TO CSV



    }


    public static void search(int userType,Hashtable<String,String[]> inventoryTable,String itemID)
    {



    }



    //uploadCsv : method to read initial csv file into a hashTable for fast search
    static Hashtable<String,String[]> uploadCsv(String csvPath) throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        Hashtable<String, String[]> inventoryTable = new Hashtable<String,String[]>();
        System.out.println("Loading... ");
        while (br.readLine()!= null)
        {
            String line;
            String[] sepLine;
            line = br.readLine();
            sepLine = line.split(",");
            String[] itemInfo = new String[4];
            itemInfo[0] = sepLine[1];
            itemInfo[1] = sepLine[2];
            itemInfo[2] = sepLine[3];
            itemInfo[3] = sepLine[4];
            inventoryTable.put(sepLine[0],itemInfo); 
        }
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
                    System.out.println(" Restarting... ");
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
        String[] info = inventoryTable.get(itemID);
        

    }
    static void Read(int userType,Hashtable<String,String[]> inventoryTable){
    System.out.println("Not yet Written.");
        
    }
    static void Destroy(Hashtable<String,String[]> inventoryTable){
        Scanner scn = new Scanner(System.in);
        
        System.out.println(" Enter Inventory Key to Delete: ");
        int itemKey = scn.nextInt();
        inventoryTable.remove(itemKey);
        System.out.println("Entry deleted.");

    }
    
}