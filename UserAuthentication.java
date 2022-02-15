import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserAuthentication{
    public static String unInput;
    public static HashMap<String, String[]> login = new HashMap<String, String[]>();
    private static String passwordInput;

    //Formats CSV into hashmap, asks for user input, and checks if it is correct
    public static void main() throws FileNotFoundException, IOException{
         //CSV should be formatted: username, user type, password
        //read and map CSV to HashMap
        FileReader loginFileReader = new FileReader("LoginInformation.csv");
        BufferedReader loginBufferedReader = new BufferedReader(loginFileReader);

        String loginLine = "";
        while((loginLine = loginBufferedReader.readLine()) != null){
            String[] row = loginLine.split(",");
            //UTP = UserType, Password
            String[] UTP = new String[2];
            UTP[0] = row[1];
            UTP[1] = row[2];
            login.put(row[0], UTP);
        }
        loginBufferedReader.close();

        //user inputs login information
        System.out.println("Username:");
        Scanner loginEntry = new Scanner(System.in); 
        unInput = loginEntry.nextLine();
        System.out.println("Password:");
        passwordInput = loginEntry.nextLine();
        //loginEntry.close(); - closing stream breaks main program, keeping it open.

        if (CorrectLogin()){
            System.out.println("login successful");
        } else{
            System.out.println("Username or password is incorrect");
        }
    }


    public static String GetUsername(){
        return unInput;
    }

        
    //check if username exists and password is correct
    public static boolean CorrectLogin(){
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


    //gives user type and if they are authenticated
    public static int[] GetUserType(){
        //userType[0] = type of user (0 = admin, 1 = employee, 2 = customer) 
        //userType[1] = authenticated(1) or not authenticated(0)
        int[] userType = new int[2];
        userType[0] = (Integer.parseInt(login.get(unInput)[0]));
        if (userType[0] == 0 || userType[0] == 1)
            userType[1] = 1;
        else
            userType[1] = 0;
        return userType;
    }

    
    //creates new user. Not functional yet.
    public static void CreateNewUser(){
        System.out.println("Please input username to be added:");
        //add username to login hashmap
        Scanner addLogin = new Scanner(System.in);
        String newUn = addLogin.nextLine();

        //add password to corresponding username
        System.out.println("Please input password:");
        String newPassword = addLogin.nextLine();

        System.out.println("Please input user type \n 1. Admin \n 2."); 
        //0. admin can add new users 1.employee can only add products 2. customer ??
        String newUserType = addLogin.nextLine();

        String[] newUTP = new String[2];
        newUTP[0] = newUserType;
        newUTP[1] = newPassword;
        login.put(newUn,newUTP);
        addLogin.close();
    }


    //deletes user from both login hashmap and CSV
    public static void DeleteUser(){
        Scanner loginC = new Scanner(System.in);

        System.out.println("Enter the username to delete");
        String UserD = loginC.nextLine();

        //removes user from hast table
        login.remove(UserD);
        loginC.close();
    }


    //upadates user in both login hashmap and CSV
    public static void UpdateUser(){
        
        Scanner loginC = new Scanner(System.in);

        System.out.println("Enter the username to update");
        String User = loginC.nextLine();

        System.out.println("Enter the updated username");
        String UserUP = loginC.nextLine();

        //adds new username and removes the older one from hast table
        login.put(UserUP, login.get(User));
        login.remove(User);

        loginC.close();

    }
}
