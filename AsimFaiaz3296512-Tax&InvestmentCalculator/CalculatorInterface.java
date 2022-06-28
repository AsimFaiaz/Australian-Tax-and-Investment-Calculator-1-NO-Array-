/* Assignemnt 1 - Tax and Investment Calculator
   Aurthor: Asim Faiaz
   Student no: 3296512
   Description: The assignemnet is aimed to create a Calculator which will calculate Tax and investment for the user. 
   */


import java.util.*;

//Importing packages to get a round value on the calculations
import java.text.NumberFormat; 
import java.util.Formatter;


public class CalculatorInterface
{
    public void run()
    {
        Scanner console = new Scanner (System.in);
        
        Client client;          //Declare variables
        client = new Client();  //Create client
        
        //-----
        //Step 1
        //-----
        
        //------------------------------------------
        //Ask user to enter first name
        System.out.print("Please enter your FIRST name: ");
        String firstName = console.next();
        
        while (firstName == null)
        {
            //Checking if user insert first name, will show error message otherwise
            System.out.println();
            System.out.print("ERROR: You must enter your FIRST name: ");
            firstName = console.next();
        }
        
        client.setfirstName(firstName);
        //------------------------------------------
        
        //------------------------------------------
        //Ask user to enter last name
        System.out.print("Please enter your LAST name: ");
        String lastName = console.next();
        
        while (lastName == null)
        {
            //Checking if user insert last name, will show error message otherwise
            System.out.println();
            System.out.print("ERROR: You must enter your LAST name: ");
            lastName = console.next();
        }
        
        client.setlastName(lastName);
        //------------------------------------------
        
        //------------------------------------------
        //Ask user for gross income
        System.out.print("Please enter your GROSS income: ");
        double grossSalary = console.nextDouble();
        
        while(grossSalary <= 0)
        {
            //Checking if user insert any negative or null value
            System.out.println();
            System.out.print("ERROR: Wrong input! Please enter your GROSS income again(No Negative or 0): ");
            grossSalary = console.nextDouble();
        }
        
        client.setgrossSalary(grossSalary);
        //------------------------------------------
        
        //------------------------------------------
        //Ask user if Australian resident
        System.out.print("Are you an Australian resident? (Yes/No):" );
        String resident = console.next();
        
        while(!(resident.equalsIgnoreCase("Yes")  || resident.equalsIgnoreCase("No")))
        {
            //Checking if user inputing other than Yes or No
            System.out.println();
            System.out.print("ERROR: Please type only Yes or No only: ");
            resident = console.next();
        }
        
        boolean answer = false;     //Default
        
        if(resident.equalsIgnoreCase("Yes"))
        {
            answer = true;
        }
        else if(resident.equalsIgnoreCase("No"))
        {
            answer = false;
        }
    
        client.setresident(answer);
        //------------------------------------------
    
        
        /*Outputs:
        This part will show all the outputs which will require to show the user
        */
        
        //------------------------------------------
        /*
         * This piece of code will trim the decimal values
         */
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        //------------------------------------------
        
        //-------------------------------------------
        //Step 1 outputs
        
        System.out.println("---------------------------------");
        System.out.println("Name:" + " " + firstName + " " + lastName);
        System.out.println();
        System.out.println("Net Salary");
        System.out.println("Per Week: $" + nf.format(client.netSWeekly()));
        System.out.println("Per Year: $" + nf.format(client.netSYearly()));
        System.out.println();
        System.out.println("Tax Paid");
        System.out.println("Per Week: $" + nf.format(client.calTaxWeekly()));
        System.out.println("Per Year: $" + nf.format(client.calTax()));
        System.out.println();
        System.out.println("Medicare levy: $" + client.Medicare());
        System.out.println("--------------------------------");
        //--------------------------------------------
        
        
        //Step 2:
        
        //------------------------------------------
        //Ask for weekly expenditure 
        
        /*
         * Important
         * Concept and Reason: Since there's no condition about the weekly expenses part in the assignment specs and I need to assume about that so I implement a condition made by me.
         *                     So, this piece of code will first check if the user is inputing any negative or 0 input. After doing that, the program will check the how much money left
         *                     for the user after paying weekly exp. I set a condition here. If the user save less than $150 after paying weekly exp, they can not invest.
         *                     For example if the user is earning $600 and his weekly exp is $350 per week; he/she is eligible to enter the investment program, if not the program will
         *                     give them options to enter anoter amount of exit the program.
         */
        System.out.print("Please enter your weekly expenses: ");
        double weeklyExpenses = console.nextDouble();
        
        while(weeklyExpenses <= 0)
        {
            //Checking if user insert any negative or null value
            System.out.println();
            System.out.print("ERROR: Wrong input! Please enter your weekly expenses again(No Negative or 0) :");
            weeklyExpenses = console.nextDouble();
        }
        
        client.setweeklyExpenses(weeklyExpenses);
        
        //Checking the difference of weekly exp with the weekly income
        double chk1 = client.netSWeekly() - weeklyExpenses;
        
        //Condition(Assuming): If the difference between weekly income and exp less than 150
        while(chk1 < 150)
        {
            System.out.println();
            System.out.println("ERROR: NOT enough to invest! You should atleast have $150 after paying your expenses.");
            System.out.println("Would you like to enter another amount or exit the program?");
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("Press YES - Enter another amount");
            System.out.println("Press NO - Exit the program");
            System.out.println("---------------------------------");
            System.out.println();
            
            //To store the answer from user input
            String ans = console.next(); 
            
            while(!(ans.equalsIgnoreCase("Yes")  || ans.equalsIgnoreCase("No")))
            {
                System.out.print("ERROR: Please type only Yes or No only!: ");
                ans = console.next();
            }
            
            if(ans.equalsIgnoreCase("Yes"))
            {
                System.out.println();
                System.out.print("Please enter your weekly expenses again: ");
                weeklyExpenses = console.nextDouble();
                
                while(weeklyExpenses <= 0)
                {
                    System.out.println();
                    System.out.print("ERROR: No negative value or 0! Please enter your expenses again: ");
                    weeklyExpenses = console.nextDouble();
                }
                chk1 = client.netSWeekly() - weeklyExpenses; //Checking again
                client.setweeklyExpenses(weeklyExpenses);    //Setting the new amount
            }
               
            else if(ans.equalsIgnoreCase("No"))
            {
                System.exit(0);
            }    
        }
        //------------------------------------------------
        
        //------------------------------------------------
        //Ask user for amount
        System.out.print("Please enter the amount you want to invest every week: ");
        double amount = console.nextDouble();
        
        //Condition: If the amount will grater than the amount of chk1 (The amount left after paying weekly exp) and not negative or 0
        while (amount > chk1 || amount <= 0)
        {  
            System.out.println();
            System.out.print("ERROR: Please enter a proper amount(Not negative and Zero or more than the amount left after paying your weekly expenses): ");
            amount = console.nextDouble();
        }
        //-------------------------------------------------
        
        //-------------------------------------------------
        //Ask user for interest rate
        System.out.print("Please enter interest rate(Must be 1% - 20%): ");
        double rate = console.nextDouble(); 
        
        //Interest rate condition
        while (rate < 1 || rate > 20)
        {   
            System.out.println();
            System.out.print("ERROR: Please enter in between 1% to 20% (No negative or 0): ");
            rate = console.nextDouble();
        }
        //-------------------------------------------------
        
        //-------------------------------------------------
        //Ask user for number of weeks
        System.out.print("Please enter the number of weeks: ");
        int numberOfWeeks = console.nextInt();  
        
        while (numberOfWeeks <= 0)
        {   
            System.out.println();            
            System.out.print("ERROR: Please enter valid number of weeks (Not negative or 0): ");
            numberOfWeeks = console.nextInt();
        }
        //-----------------------------------------------
        
        //-----------------------------------------------
        //Step 2 outputs
        
        System.out.println();
        System.out.println("Investment");
        System.out.println("Weeks         Balance");
        System.out.println("----------------------");
        
        String inv = client.savingAccInv(amount, rate, numberOfWeeks);
        System.out.println(inv);
    }
    
  
    public static void main(String[] args)
    {
        CalculatorInterface calc = new CalculatorInterface();
        calc.run();
    }
}
        
    

    
    
    
    
    
    
    


        

    