import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

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
        while(run){

            //open initial csv
            Hashtable<String,String[]> inventoryTable = uploadCsv(csvPath);

            //which type of access?

            if(userType == 0) //admin
            {

                System.out.println("Admin Access - Inventory System");
                System.out.println("Enter a choice: ");
                System.out.println("0 - Search an entry (read) ");
                System.out.println("1 - Create an entry (new inventory item)");
                System.out.println("2 - Update an entry (change inventory number, prices ) ");
                System.out.println("3 - Delete an entry (delete an inventory item entirely) ");
                System.out.println("4 - Exit system "); // back to main? or fully exit


                switch(Integer.parseInt(br.readLine())){                
                case 0:
                    Read(userType, inventoryTable);
                case 1:
                    Create(inventoryTable);
                case 2:
                    Update(inventoryTable);
                case 3:
                    Destroy(inventoryTable);
                case 4:{
                    run = false;
                    break;
                }    
                default:
                    System.out.println("Invalid, try again... \n\n");  
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
                    case 0:
                        Read(userType, inventoryTable);  
                    case 1:
                        Create(inventoryTable);
                    case 2:
                        Update(inventoryTable);
                    case 3:
                        Destroy(inventoryTable);
                    case 4:{
                        run = false;
                        break;
                    }
                    default: 
                        System.out.println("Invalid, try again... \n\n"); 
                }
            }
            else if(userType ==2) //customer
            {
                System.out.println("Customer Access - Inventory System");
                System.out.println("Enter a choice: ");
                System.out.println("0 - Search an entry (read) ");
                System.out.println("1 - Purchase an item ");
                System.out.println("2 - Exit system "); // back to main? or fully exit
                switch(Integer.parseInt(br.readLine())){                
                    case 0:
                        Read(userType, inventoryTable);
                    case 1:
                        Purchase(inventoryTable);
                    case 2:
                        run = false;
                        break;
                    default: 
                        System.out.println("Invalid, try again... \n\n");
                         
                }
            }
        }
        System.out.println("Exiting system, writing back to CSV ");



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
        //customer method, uses update method.

    }

    static void Create(Hashtable<String,String[]> inventoryTable) {
        System.out.println(" method not written ");
        //team write this method

        //hashtable: create a string for Item ID, and a string array for other info. use inventoryTable.put(String, String[])


    }

    static void Update(Hashtable<String,String[]> inventoryTable){
        System.out.println(" method not written ");
        //team write this method

    }
    static void Read(int userType,Hashtable<String,String[]> inventoryTable){
        System.out.println(" method not written ");
        //team write this method
        //usertype decides which data can be shown, eg customer should not be able to see inventory or wholesale price.
    }
    static void Destroy(Hashtable<String,String[]> inventoryTable){
        System.out.println(" method not written ");
        //team write this method

    }
    
}