/*Author: Asim Faiaz
*Student No: 3296512
*Date: 29-04-2022
*Description: This is the Client class. This class contains methods:
*             Method 1: calTax()       which will return double type - Calculation yearly tax
*             Method 2: calTaxWeekly() which will return double type - Calculation weekly tax
*             Method 3: netSYearly()   which will return double type - Calculation Net income yearly
*             Method 4: netSWeeklyly() which will return double type - Calculation Net income weekly
*             Method 5: Medicare()     which will return double type - Calculation medicare levy
*             Method 6: savingAccInv(double amount, double rate, int numberOfWeeks)  which takes 3 parameters and return a String type
*/


import java.util.*;

public class Client
{
    private String firstName;
    private String lastName;
    private Account savingAccount;
    private double grossSalary;
    private boolean resident;
    private double medicare;
    private double weeklyExpenses;
    
    /*--------------------------
    
    This two are not in use because i broke them out into Weekly and Yearly for myself easier to use in the methods
    
    private double tax;
    private double netSalary;
    -------------------------*/
    
    //Create these variables for myself easier to use
    private double netSW = 0;               //Income Weekly
    private double netSY = 0;               //Income Yearly
    private double taxW  = 0;               //Tax weekly
    private double taxY  = 0;               //Tax yearly
    

    public Client()
    {

    }
    
    //First Name
    public String getfirstName()
    {
        return (firstName);
    }

    public void setfirstName(String inputName)
    {
        firstName = inputName;
    }
    
    //Last Name
    public String getlastName()
    {
        return (lastName);
    }

    public void setlastName(String inputName)
    {
        lastName = inputName;
    }
    
    //Gross Salary
    public double getgrossSalary()
    {
        return (grossSalary);
    }
    
    public void setgrossSalary(double inputSalary)
    {
        grossSalary = inputSalary;
    }
    
    //Resident
    public boolean getresident()
    {
       return(resident);
    }
    
    public void setresident(Boolean checkResident)
    {
        resident = checkResident;
    }   

    //Living costs
    public double getweeklyExpenses()
    {
        return(weeklyExpenses);
    }
    
    public void setweeklyExpenses(double checkExp)
    {
        weeklyExpenses = checkExp;
    }
    
    /*----------------------------------------------------------------
    Methods
    *This part is for all the method that will be use in this prorgam
    -----------------------------------------------------------------*/
    
    /*
     * Description: This method will calculate yearly tax from user's gross income.
     * Formula: 0 to 18000 no tax so it will be 0; for the rest we need to minus the gross income(User input) with the smallest number of the condition, eg. in between 18001 to 45000 the 
     *          smallest number is 18001. So if we minus the gross income with that, we will get the rests and multiply that amount with whatever the condition is.
     * Check condition 1 : First the program will check if the user is australian resident or not
     * Check condition 2 : After checking the residency condition then the program will check the gross income and calculate accordingly
     */
    public double calTax()
    {
        
        if(resident)                            //Checking the boolean value from user input
        {
            //Income 0 to 18,200
            //Doesn't need to do this statement indeed because the initial value will be 0 anyway, still doing to show the result and make things clear
            if(grossSalary > 0 && grossSalary <= 18200)
            {
                taxY = 0;                                       //No tax yearly
            }
            
            //Income 18201 to 45000
            if(grossSalary >= 18201 && grossSalary <= 45000)
            {
                taxY = (grossSalary - 18200) * 0.19;            //(19/100 = 0.19)
            }
            
            //Income 45001 to 120000
            if(grossSalary >= 45001 && grossSalary <= 120000)
            {
                taxY = ((grossSalary - 45000) * 0.325) + 5092;  //(32.5/100 = 0.325)  
            }
            
            //Income 120001 to 180000
            if(grossSalary >= 120001 && grossSalary <= 180000)
            {
                taxY = ((grossSalary - 120000) * 0.37) + 29467;  //(37/100 = 0.37)
            }
            
            //Income 1800001 to up
            if(grossSalary >= 180001)
            {
                taxY = ((grossSalary - 180000) * 0.45) + 51667;  //(45/100 = 0.45)
            }
        }
            
        if(!resident)                               //Chceking boolean value from user input
        {
            //Non resident income 0 to 120000      
            if(grossSalary > 0 && grossSalary <= 120000)
            {
                taxY = grossSalary * 0.325;                        //(32.5/100 = 0.325)
            }
            
            //Non resident income 120001 to 180000   
            if(grossSalary >= 120001 && grossSalary <= 180000)
            {
                taxY = ((grossSalary - 120000) * 0.37) + 39000;    //(37/100 = 0.37)
            }
            
            //Non resident income 180001 to up  
            if(grossSalary >= 180001)
            {
                taxY = ((grossSalary - 180000) * 0.45) + 61200;    //(45/100 = 0.45)
            }
        }
        
        return taxY;
            
    }
    
    /*
     * Description: This method will calculate weekly tax amount
     * Formula: The amount of yearly tax divided by 52
     * Check condition 1: The method will fetch the yearly tax amount from the method "calTax()"
     * Check condition 2: After getting the amount the method will force to divided by 52.14 (1 year = 52.14 weeks sourced from google)
     */
    public double calTaxWeekly()
    {
        calTax();
        taxW = taxY / 52;
        return taxW;
    }
    
    /*
     * Description: This method will calculate Net income yearly (income after tax)
     * Formula: Gross income needs to minus with yearly tax and then minus the medicare levy if exist
     * Check condition 1: The method will fetch the yearly tax amount from the method "calTax()"
     * Check condition 2: After getting the amount the method will minus the gross income (from user input) with the yearly tax
     * Check condition 3: If there's and medicare levy exist that will deduct from the net income
     */
    public double netSYearly()
    {
        Medicare();
        calTax();
        netSY = (grossSalary - taxY) - medicare;
        return netSY;
    }
    
    /*
     * Description: This method will calculate Net income weekly
     * Formula: Yearly net income divided by 52
     * Check condition 1: The method will fetch the yearly net income amount from the method "netSYearly()"
     * Check condition 2: After getting the amount the method will force to divided by 52.14 (1 year = 52.14 weeks sourced from google)
     */
    public double netSWeekly()
    {
        netSYearly();
        netSW = netSY / 52;
        return netSW;
    }
    
    /*
     * Description: This method will calculate medicare levy 2%
     * Check condition 1: The method will check if the user is Australian resident
     * Check condition 2: The method will check if the user has gross income of more than 29032
     * Check condition 3: To get the medicare levy amount the program will multiply the gross income with 2%
     * Check condition 4: If all the conditions will be true the program will do the calculation, return 0 otherwise
     */
    public double Medicare()
    {
        if(resident && grossSalary > 29032)
        {
            medicare = grossSalary * 0.02;      //(2/100 = 0.02)
        }
        else
        {
            medicare = 0;
        }

        return medicare;
    }
    
    /*
     * Description: This method is aimed to get the data from the account class.
     * Condition 1: Since the CalculatorInterface can only accessed by the client class (Given in the assignment specs) So, whatever user input is getting through the calcInterface
     *              to the Account class, those values will be set in this function and show output
     * Condition 2: Again, since the CalculatorInterface can only accessed by the client class (Given in the assignment specs) and the Account class must have a method called calcInvestment()
     *              so, the method is also called here, the way we are only the client class accessing the CalculatorInterface
     */
    public String savingAccInv(double amount, double rate, int numberOfWeeks)
    {
        Account savingAccount = new Account();              //To access the Account class
        
        savingAccount.setAmount(amount);
        savingAccount.setRate(rate);
        savingAccount.setnumberOfWeeks(numberOfWeeks);
        return savingAccount.calcInvestment();              //Returing the funtion from Account class
    } 
}

