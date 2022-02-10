import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserAuthentication{


    public static HashMap<String, String[]> GetLogin ()  throws FileNotFoundException, IOException{
        
        //CSV should be formatted: username, user type, password
        //read and map CSV to HashMap
        FileReader loginFileReader = new FileReader("LoginInformation.csv");
        HashMap<String, String[]> login = new HashMap<String, String[]>();
        BufferedReader loginBufferedReader = new BufferedReader(loginFileReader);

        
        String loginLine = "";
        while((loginLine = loginBufferedReader.readLine()) != null){
            String[] row = loginLine.split(",");
            String[] UTP = new String[2];
            UTP[0] = row[1];
            UTP[1] = row[2];
            login.put(row[0], UTP);
        }
        loginBufferedReader.close();
        return login;
    }


    public static boolean RunAuthenticator(HashMap<String, String[]> login){

        //user inputs login information
        System.out.println("Username:");
        Scanner loginEntry = new Scanner(System.in); 
        String unInput = loginEntry.nextLine();
        System.out.println("Password:");
        String passwordInput = loginEntry.nextLine();

        if (CorrectLogin(login, unInput, passwordInput)){
            System.out.println("login successful");
        } else{
            System.out.println("Username or password is incorrect");
        }
        loginEntry.close();
        return true;
    }


    //check if username exists and password is correct
    public static boolean CorrectLogin(HashMap<String, String[]> login, String unInput, String passwordInput){
        for(String i : login.keySet()){
            if (unInput.equals(i) && passwordInput.equals(login.get(i)[1])){
                return true;
            }
        }
        return false;
    }


    //writes updates to CSV
    public static void WriteToCSV(){
        
    }


    /*public static int PriorityLevel(){

    }*/

    //creates new user. Not functional yet.
    public static void CreateNewUser(HashMap<String, String[]> login){
        System.out.println("Please input username to be added:");
        //add username to login hashmap
        Scanner addLogin = new Scanner(System.in);
        String newUn = addLogin.nextLine();

        //add password to corresponding username
        System.out.println("Please input password:");
        String newPassword = addLogin.nextLine();

        System.out.println("Please input user type \n 1. Admin \n 2."); //1. admin can add new users 2.?? can only add products
        String newUserType = addLogin.nextLine();

        String[] newUTP = new String[2];
        newUTP[0] = newUserType;
        newUTP[1] = newPassword;
        login.put(newUn,newUTP);
        addLogin.close();
    }


    //deletes user from both login hashmap and CSV
    public static void DeleteUser(HashMap<String, String[]> login){
        
    }


    //upadates user in both login hashmap and CSV
    public static void UpdateUser(HashMap<String, String[]> login){
        
    }
}
