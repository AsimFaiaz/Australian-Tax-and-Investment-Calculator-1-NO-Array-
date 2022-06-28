// You can add extra methods if you think it is necessary

import java.util.*;


public class CalculatorInterface
{
    public void run()
    {
        Scanner console = new Scanner (System.in);
	Client client;
        
	client = new Client();
        System.out.print("name = ");
        String name = console.next();
        client.setName(name);

     }
    
  
    public static void main(String[] args)
    {
        CalculatorInterface calc = new CalculatorInterface();
        calc.run();
    }
}
        
    

    
    
    
    
    
    
    


        

	