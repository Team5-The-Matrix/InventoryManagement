import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

public static void main(String[] args) throws IOException{  
System.out.println("\n Welcome to Inventory Manager V1.0 \n");
System.out.println("Log in to get started: \n");
        //Call Authentication class or method here
UserAuthentication.main();
int[] isAuthenticated = UserAuthentication.GetUserType();
int authenticated = isAuthenticated[0]; 
int userType = isAuthenticated[1];
if (authenticated == 0){
        System.out.println("\nAuthenticated\n");        
}
else{
        System.out.println("Authentication failed, try again");
}

System.out.println("Enter local path of inventory file: ");
BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in)); 
String path = br1.readLine(); 
try{
InventoryManager.InventoryManager(path, authenticated, userType); 
}
catch(NullPointerException e)
{
e.printStackTrace();
}

}
}
// call to inventory manager static method



