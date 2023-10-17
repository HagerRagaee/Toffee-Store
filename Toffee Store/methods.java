import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class methods {

    Customer customer = new Customer(null, 0, null) ; 
    Scanner input = new Scanner(System.in) ;
    items itm = new items();
    order ordr = new order();
    payment pay = new payment();
    
    /** 
     * @return String
     */
    public String logInMethod()
    {
        System.out.print("Enter Your Email: ")  ; 
        String Email , pass ;  
        while(true)
        {
            Email = input.nextLine() ; 
            customer.login.setEmail(Email);
            if(customer.login.checkEmail())
            {
                System.out.println("Great!");
                break ; 
            }

            else 
            {
                System.out.println("Enter Your Correct Email");
            }
        }

        while(true)
        {
           System.out.print("Enter Your Pass: ");

            pass = input.nextLine() ; 
            customer.login.setPass(pass);
            if(customer.login.checkPass())
            {
                System.out.println("LogIn successfully");
                break ; 
            }

            else 
            {
                System.out.println("Enter Your Correct Pass");
            }
        }
        return Email;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public String signUpmethod() throws Exception
    {
        System.out.print("Enter Your Name: ");
        String name , phoneNumber , email , address;
        name = input.nextLine() ;
        customer.setName(name);

        System.out.print("Enter Your Phone number: ");
        phoneNumber = input.nextLine() ;
        customer.setPhoneNumber(phoneNumber);
        
        System.out.print("Enter Your Address : ");
        address = input.nextLine() ;
        customer.signup.set_address(address);


        while (true)
        {
            System.out.print("Enter Your Email: ") ;
            email = input.nextLine() ;
            customer.signup.set_email(email);
            if(customer.signup.checkEmail())
            {
                String pass ;
                System.out.print("Enter your pass: ");
                pass = input.nextLine() ; 
                customer.signup.set_pass(pass);

                String mail ; 
                System.out.print("Enter a Google Email to vefiy your Toffee account: " );
                mail = input.nextLine() ; 
          
                while(true)
                {
                 
                    if(customer.signup.checkAccount(mail))
                    {
                        System.out.println("SignUp Secussfully"); 
                        break ;
                    }
                    else 
                    {
                        System.out.println("UnCorrect code\nAnother code will be sent to "+ mail) ;
                        continue ;
                    }
                    
                }

                customer.setID();
                customer.saveToDatabase();
                return email;

              
           
            }
            else 
            {
                System.out.println("Not Accepted\nPlease Enter A Correct Emial") ; 
            }
        }
    }
    
    /**
     * 
     */
    public void diplayCatalog(){
       System.out.println("We Have\n1-Chocolates\n2-Candies\n3-Lollipops\n");
       while(true){
       System.out.println("Enter catalog name that you search for :");
       String catalogName;
       catalogName = input.nextLine();
       if(!itm.searchByCatalog(catalogName)){

        System.out.println("Catalog Name Dose Not Found");
      
        }

       else

       {itm.displayCatalog(catalogName);
             break;
       } 

       }
   } 

   String []itemnames = new String [1000] ;
   /**
    * 
    * @param email
    */
   public void adding(String email){
      String itemName;
       int quantity,choice,sz = 0;
       while(true){
       System.out.println("Enter Item Name");
       itemName = input.nextLine();
      if(itm.checkExistItem(itemName)) {
       System.out.println("Enter the Quantity");
        quantity = input.nextInt();
        input.nextLine(); // consume newline character
        ordr.addItem(itemName,email,quantity);
        itemnames[sz] = itemName; 
           sz++;
          System.out.println("============");
            break;
        }
        else
        {
            System.out.println("Item dose not Found ");
            continue;
        }
           
    }
   }

   /**
    * 
    * @param email
    * @throws Exception
    */
   public void Placeorder(String email) throws Exception{
      adding(email);
       while (true){
        System.out.println("Options:\n1-Add New Item\n2-Cancel Order\n3-Confirm Your Order\nany Key for exit");
        int choice = input.nextInt();
        if(choice == 1){
          input.nextLine(); 
          adding(email);
           
        }
        else if(choice==2){
            Scanner input = new Scanner(System.in);

            System.out.println("Enter Item Name");
            String itemName = input.nextLine();
            if(itm.checkExistItem(itemName)) {
            System.out.println("Enter Item amount");
            int itemamount = Integer.parseInt(input.nextLine());

            
            ordr.cancelItem(itemName,itemamount);
        }
        }
         else if(choice ==3){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("mm");
        String formattedDateTime = currentDateTime.format(formatter);
        String formattedDateTime2 = currentDateTime.format(formatter2);

        // int min = Integer.parseInt(formattedDateTime2);
        // int hour = Integer.parseInt(formattedDateTime);

        // range(hour,min);
      ordr.viewCart(email);
      payment p = new payment();
      System.out.println(p.getTotalPrice(email,formattedDateTime )); 
      cash c = new cash();
      System.out.println(c.checkPayment());
        }
        else
            break;
       }

   }   
}

