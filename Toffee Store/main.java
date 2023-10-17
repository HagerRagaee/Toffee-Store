
import java.util.Scanner;

public class main {
   
   /** 
    * @param email
    * @throws Exception
    */
   public static void  Run(String email) throws Exception{
    while (true)
    {
      int choice ;
      Boolean checkSignUp = false;  
      methods method = new methods() ; 
      Scanner input = new Scanner(System.in) ; 
      System.out.println("*****************Enter Your chioce*****************"); 
      System.out.println("1- Visiting Our Store");
      System.out.println("2- place an order");
      System.out.println("0- Exit APP");

        
      choice = input.nextInt() ; 

      if (choice == 0 )
      {
          break ; 
      }
  
     else if(choice == 1){
        method.diplayCatalog();
      }
      else if(choice == 2){
          method.Placeorder(email);
      }

  }
}
  /**
   * @param arg
   * @throws Exception
   */
    public static void main(String[]arg) throws Exception
    {
     Scanner input = new Scanner(System.in) ; 
      System.out.println("*****************Welcom to Toffee Store**************\nPress\n1-Sign Up\n2-Login\n3-Complete as general User"
    ); 
    int option ;
    Boolean checkSignUp = false;  
    methods method = new methods() ; 
    option = input.nextInt();
    if(option == 1){
    
      String em = method.signUpmethod();
       Run(em);
      
    }
    else if(option == 2){
     String em = method.logInMethod();
         Run(em);
    }
    else if(option == 3)
         method.diplayCatalog();                 
  }
}
