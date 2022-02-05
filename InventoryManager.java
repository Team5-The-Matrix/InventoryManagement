import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class InventoryManager{
    // constructor: creates and runs the inventory manager
    // csvPath: local path of initial csv file
    // authenticated: is the user authenticated by the authentication side?
    // userType: 0 - Admin, 1 - Employee, 2 - Customer

    public static void InventoryManager (String csvPath, boolean authenticated, int userType ) throws IOException,FileNotFoundException{

        boolean run = true;
        while(run){

            //open initial csv
        Hashtable<String,String[]> inventoryTable = uploadCsv(csvPath);


        // prompt user ? Or GUI?







        }
    }

    //uploadCsv : method to read initial csv file into a hashTable for fast search
    static Hashtable<String,String[]> uploadCsv(String csvPath) throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        Hashtable<String,String[]> inventoryTable = new Hashtable<String,String[]>();
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
        br.close();
        return inventoryTable;
    }

    static void Create() {

        //team write this method

    }

    static void Update(){

        //team write this method

    }
    static void Read(){

        //team write this method

    }
    static void Destroy(){

        //team write this method

    }
    
}