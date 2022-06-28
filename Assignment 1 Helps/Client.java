// You can add extra methods if you think it is necessary

public class Client
{
    private String name;                     
    private Account savingAccount;
    double grossSalary;
    double netSalary;
    boolean resident;
    double tax;
    double medicare;
    double weeklyExpenses;

    public Client()
    {

    }

    public String getName()
    {
        return (name);
    }

    public void setName(String inputName)
    {
        name = inputName;
    }
    
}
