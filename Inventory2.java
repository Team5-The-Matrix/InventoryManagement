import java.io.*;
import java.util.*;

public class Inventory2 {

   public static List product = Collections.synchronizedList(new ArrayList());
   public static List productId = Collections.synchronizedList(new ArrayList());
   
   public static void authUser(){
      String Username="0";
      String Password="0";

      Scanner input1 = new Scanner(System.in);
      System.out.println("Enter Username : ");
      String username = input1.next();

      Scanner input2 = new Scanner(System.in);
      System.out.println("Enter Password : ");
      String password = input2.next();

      if (username.equals(Username) && password.equals(Password)) {
         inventoryMenu();  
      }
      
      else if (username.equals(Username)) {
          System.out.println("Invalid Password!");
          System.exit(0);
      }
       
      else if (password.equals(Password)) {
          System.out.println("Invalid Username!");
          System.exit(0);
      } 
      
      else {
          System.out.println("Invalid Username & Password!");
          System.exit(0);
      }
   }
   
   public static void inventoryMenu(){
      System.out.println("Inventory Managent Menu");
      Scanner input3 = new Scanner(System.in);
      System.out.println("1.Create Product");
      System.out.println("2.Read Product ");
      System.out.println("3.Update Product ");
      System.out.println("4.Delete Product ");
      System.out.println("5.Save Inventory ");
      System.out.println("6.Log Out");
      System.out.println("7.Exit System ");
      String menuoption = input3.next();
      
      if (menuoption.equals("1")){
         Scanner input4 = new Scanner(System.in);
         System.out.println("Enter New Product(product_id,quantity,wholesale_cost,sale_price,supplier_id): ");
         String newProduct = input4.next();
         product.add(newProduct);
         String[] item1 = newProduct.split(",");     
         productId.add(item1[0]); 
         System.out.println("Product Added");
         inventoryMenu();
      }
      
      else if (menuoption.equals("2")){
         Scanner input5 = new Scanner(System.in);
         System.out.println("Enter Product Id: ");
         String searchProductId = input5.next();
         
         if (productId.contains(searchProductId)) {
            int index = productId.indexOf(searchProductId);
            System.out.println(product.get(0));
            System.out.println(product.get(index));
            inventoryMenu();
            }
         else {
            System.out.println("Product Id Not Found\n");
            inventoryMenu();
         }
         
      }
       
      else if (menuoption.equals("3")){
      
         Scanner input6 = new Scanner(System.in);
         System.out.println("Enter Product Id: ");
         String updateProduct = input6.next();

         if (productId.contains(updateProduct)) {
            int index1 = productId.indexOf(updateProduct);
            System.out.println(product.get(0));
            System.out.println(product.get(index1));
            
            Scanner input8 = new Scanner(System.in);
            System.out.println("Enter Updated Product(product_id,quantity,wholesale_cost,sale_price,supplier_id): ");
            String updatedProduct = input8.next();
            product.set(index1,updatedProduct);
            System.out.println("Producted Updated");
            inventoryMenu();
         }
         
         else {
            System.out.println("Product Id Not Found\n");
            inventoryMenu();
         }
      }
        
      else if (menuoption.equals("4")){
         Scanner input7 = new Scanner(System.in);
         System.out.println("Enter Product Id: ");
         String deleteProduct = input7.next();

         if (productId.contains(deleteProduct)) {         
            int index2 = productId.indexOf(deleteProduct);
            product.remove(index2);
            productId.remove(index2);
            System.out.println("Product Deleted");
            inventoryMenu();  
         }
         else {
            System.out.println("Product Id Not Found\n");
            inventoryMenu();
         }           

       }
       
       else if (menuoption.equals("5")){
            

         try   {  
            PrintWriter output = new PrintWriter(new FileWriter("inventory_team5a.csv"));
            int i = 0;         
            while (i < product.size()){       
               output.println(product.get(i));
               i++;
            }           
            output.close();
            System.out.println("File Saved\n");
            inventoryMenu();
         } 
        
         catch (IOException e)   {  
            e.printStackTrace();  
         }
       }  
       else if (menuoption.equals("6")){
          System.out.println("Logging Out");      
          authUser();
       }
       
       else {
          System.out.println("Bye");
          System.exit(0);
       }
       }
   public static void main(String[] args) {

      String line = "";         

      try   {  
        BufferedReader br = new BufferedReader(new FileReader("inventory_team5.csv"));  
        while ((line = br.readLine()) != null){   
            product.add(line);      
            String[] item = line.split(",");     
            productId.add(item[0]); 
         }
      } 
        
      catch (IOException e)   {  
         e.printStackTrace();  
      }
      
   authUser();  
   } 
}
