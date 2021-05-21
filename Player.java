import java.io.Serializable;

public class Player implements Serializable {

    private double bank;
    private String uniqueID;
    private int wins;
    private int losses;
    private String password;
    //private Hand hand;
    private static final long serialVersionUID = 1L;

    public Player(String name)
    {
        this.bank = 0;
        this.wins = 0;
        this.losses = 0;
        this.uniqueID = name;
        this.password = " ";
        //hand = new Hand();
    }

    public void setUID(String id)
    {
        this.uniqueID = id;
    }

    public double getBank()
    {
        return this.bank;
    }

    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String pass)
    {
        this.password = pass;
    }

    public String getUID()
    {
        return this.uniqueID;
    }

    public int getWins()
    {
        return this.wins;
    }
    public int getLosses()
    {
        return this.losses;
    }

    public void setWins(int wins)
    {
        this.wins = wins;
    }

    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    public void addWin()
    {
        this.wins++;
    }
    public void addLoss()
    {
        this.losses++;
    }

    public void addBank(double amount)
    {
        this.bank += amount;
    }
    public void subtractBank(double amount)
    {
        this.bank -= amount;
    }
    public void setBank(double amount)
    {
        this.bank = amount;
    }
    public boolean isBankrupt() 
    {
        if (this.bank <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}