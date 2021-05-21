import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GUI implements ActionListener {

    /* **** Variables START **** */
    
    //Main Frame Object
    private JFrame frame = new JFrame();


    //Label Objects
    private JLabel usernameLoginLabel;
    private JLabel passwordLabel;
    private JLabel error;
    private JLabel totalAmount;
    private JLabel name,balance,wins,losses;
    private JLabel dealerCardHidden;
    private JLabel backgroundCreateAccount = new JLabel(new ImageIcon("./images/blackjack.png"));
    private JLabel backgroundLogin = new JLabel(new ImageIcon("./images/blackjack.png"));
    private JLabel backgroundMenu = new JLabel(new ImageIcon("./images/blackjack.png"));
    private JLabel backgroundStats = new JLabel(new ImageIcon("./images/stats_background.jpeg"));
    private JLabel backgroundGame;

    //Text & Password Fields
    private JTextField userText;
    private JPasswordField passText;
    private JPasswordField createPassText;

    //Buttons
    private JButton confirmLoginButton = new JButton("Login");; //Login Button
    private JButton confirmCreateAccountButton = new JButton("Confirm");
    private JButton startGameButton= new JButton("Play a Game");
    private JButton viewStatsButton  = new JButton("View your Stats");;
    private JButton createAccountButton  = new JButton("Create Account");
    private JButton quitStats = new JButton("Main Menu");
    private JButton standButton = new JButton("Stand");
    private JButton hitButton = new JButton("Hit");
    private JButton playAgainButton = new JButton("Play Again");
    private JButton MMButton = new JButton("Main Menu");

    //Strings
    private String username;
    private String password;
    //private static  String filepath="./players/obj";

    //Integers
    private int widthChange,widthChange2; 
    private int cardsPrinted;

    //Booleans
    private boolean busted = false;
    
    //Player Objects
    public Player player = null;

    //Hand Objects
    private Hand handPlayer;
    private Hand handDealer;

    //Deck Objects
    private Deck deck;

    //File Objects
    File newFile;

    //Dimension Objects
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //Server init
    public static Socket s;
    public static DataInputStream din;
    public static DataOutputStream dout;
    private static String IP = "217.105.46.146";
    private static int PORT = 51374;
    /* **** Variables END **** */



    /* **** Functions START **** */
    public void setupActionListener()
    {
        startGameButton.addActionListener(this);
        playAgainButton.addActionListener(this);
        MMButton.addActionListener(this);
        quitStats.addActionListener(this);
        viewStatsButton.addActionListener(this);
        hitButton.addActionListener(this);
        standButton.addActionListener(this);
        confirmLoginButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        confirmCreateAccountButton.addActionListener(this);
        frame.setResizable(false);

    }
    //Login Page
    public void buildLogin()
    {
        //Frame modifications
        frame.setSize(275,275);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login for BlackJack");
        backgroundLogin.setLayout(null);
        frame.add(backgroundLogin);

        

        //Label for username text field
        usernameLoginLabel = new JLabel("Username");
        usernameLoginLabel.setBounds(10,20,85,25);
        backgroundLogin.add(usernameLoginLabel);
        
        //Label for password text field
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,85,20);
        backgroundLogin.add(passwordLabel);
        
        //Textfield for username
        userText = new JTextField(10);
        userText.setBackground(Color.GRAY);
        userText.setBounds(100,20,165, 25);
        backgroundLogin.add(userText);

        //Textfield for password
        passText = new JPasswordField();
        passText.setBackground(Color.GRAY);
        passText.setBounds(100,50,165, 25);
        backgroundLogin.add(passText);
        
        //Sumbit login info button
        confirmLoginButton.setBounds(10,80,80,25);
        confirmLoginButton.setBackground(Color.gray);
        backgroundLogin.add(confirmLoginButton);
        
        //Button to create a new account
        createAccountButton.setBounds(100,80,160,25);
        createAccountButton.setBackground(Color.GRAY);
        backgroundLogin.add(createAccountButton);
        
        //Validate and show changes to frame
        frame.setVisible(true);     
    }
    
    //New Account Page
    public void buildCreateAccountPage()
    {
        //Frame modifications
        frame.setSize(275,275);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Create an Account");
        backgroundCreateAccount.setLayout(null);
        frame.add(backgroundCreateAccount);

        //Label for username text field
        usernameLoginLabel = new JLabel("Username");
        usernameLoginLabel.setBounds(10,20,85,25);
        backgroundCreateAccount.add(usernameLoginLabel);

        //Label for password text field
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,85,20);
        backgroundCreateAccount.add(passwordLabel);

        //Textfield for username
        userText = new JTextField(10);
        userText.setBounds(100,20,165, 25);
        userText.setBackground(Color.GRAY);
        backgroundCreateAccount.add(userText);

        //Textfield for password
        createPassText = new JPasswordField();
        createPassText.setBounds(100,50,165, 25);
        createPassText.setBackground(Color.GRAY);
        backgroundCreateAccount.add(createPassText);
        
        //Confirm Account Creation Button
        confirmCreateAccountButton.setBounds(100,80,80,25);
        backgroundCreateAccount.add(confirmCreateAccountButton);

        frame.setVisible(true);
    }

    //Function to draw Main Menu
    public void buildMenu()
    {
        //Frame modifications
        frame.setSize(275,275);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BlackJack Main Menu");
        backgroundMenu.setLayout(null);
        frame.add(backgroundMenu);
        

        //Start Game Button
        startGameButton.setBounds(65,90,150,25);
        backgroundMenu.add(startGameButton);

        //View Statistics Button
        viewStatsButton.setBounds(65,120,150,25);
        backgroundMenu.add(viewStatsButton);

        //Validate and Show Frame
        frame.setVisible(true);
    }

    public void buildStats()
    {
        //Font for page
        Font font = new Font("Courier", Font.BOLD,15);

        //Frame modifications
        frame.setSize(275,275);
        backgroundStats.setLayout(null);
        frame.add(backgroundStats);
        frame.setTitle("Statistics Page for BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Label for Name of User
        name = new JLabel();
        name.setBounds(10,0,350,20);
        name.setFont(font);
        name.setText("Welcome " + player.getUID() + "!");
        backgroundStats.add(name);
        
        //Label for balance of User
        balance = new JLabel();
        balance.setBounds(10,20,165,25);
        balance.setFont(font);
        balance.setText("Balance: $" + String.valueOf(player.getBank()));
        backgroundStats.add(balance);
        
        //Label for wins of User
        wins = new JLabel();
        wins.setText("Wins: " + String.valueOf(player.getWins()));
        wins.setFont(font);
        wins.setBounds(10,50,85,25);
        backgroundStats.add(wins);

        //Label for losses of User
        losses = new JLabel("Losses: " + String.valueOf(player.getLosses()));
        losses.setBounds(10,75,85,25);
        losses.setFont(font);
        backgroundStats.add(losses);
        
        //Return to Main Menu Button
        quitStats.setBounds(25,100,85,25);
        backgroundStats.add(quitStats);
        
        //Validate and Show Frame
        frame.setVisible(true);
    }


    public void buildGame()
    {
        busted = false;
        handPlayer = new Hand();
        handDealer = new Hand();
        deck = new Deck();

        //Frame modifications
        frame.setSize(screenSize.width-200,screenSize.height-100);
        frame.setTitle("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        backgroundGame = new JLabel(new ImageIcon("./images/blackjack_background.jpeg"));
        frame.add(backgroundGame);

        MMButton.setBounds(frame.getWidth()/2 - 150,frame.getHeight()*(1/10),150,25);
        playAgainButton.setBounds(frame.getWidth()/2,frame.getHeight()*(1/10),150,25);

        //Initalize Deck and Hand
        deck.shuffle();
        handPlayer.addCard(deck.getCardFromDeck());
        handPlayer.addCard(deck.getCardFromDeck());
        handDealer.addCard(deck.getCardFromDeck());
        handDealer.addCard(deck.getCardFromDeck());
        
        //Initalize width objects   
        widthChange2 = frame.getWidth()/2 + 325;
        widthChange = frame.getWidth()/2 - 625;

        //Print first two of users playing cards
        printOriginalPlayerCards();
        cardsPrinted = 2;

        //Print total amount of users first two cards
        totalAmount = new JLabel();
        totalAmount.setBounds(frame.getWidth()/2 - 500, frame.getHeight() - 150,150,25);
        totalAmount.setText("Total: " + String.valueOf(handPlayer.calculateTotal()));
        backgroundGame.add(totalAmount);
        
        //Print dealers cards
        JLabel dealerCardShown = new JLabel(new ImageIcon("./cards/" + handDealer.getHand().get(0).toString().toLowerCase()));
        dealerCardHidden = new JLabel(new ImageIcon("./cards/back.jpg"));
        dealerCardShown.setBounds(widthChange2 - 210, frame.getHeight()/9, 209,303);
        dealerCardHidden.setBounds(widthChange2, frame.getHeight()/9,209,303);
        backgroundGame.add(dealerCardHidden);
        backgroundGame.add(dealerCardShown);

        //Setup Hit BUtton
        hitButton.setBounds(frame.getWidth()/2 - 85,frame.getHeight()*(1/10),85,25);
        backgroundGame.add(hitButton);

        //Setup stand Button
        standButton.setBounds(frame.getWidth()/2,frame.getHeight()*(1/10),85,25);
        backgroundGame.add(standButton);


        //Validate and show frame
        frame.repaint();
        frame.setVisible(true);
    }

    //helper function to print cards
    public void printOriginalPlayerCards()
    {
        for (int i = 0; i < handPlayer.getHand().size(); i++)
        {
            JLabel playerCard2 = new JLabel(new ImageIcon("./cards/" + handPlayer.getHand().get(i).toString().toLowerCase()));
            playerCard2.setBounds(widthChange, frame.getHeight() - 400,209,303);
            backgroundGame.add(playerCard2);
            widthChange += 210;
        }
        backgroundGame.repaint();
    }

    //Write a new player to file
    public static void WriteObjectToFile(Player serObj) throws UnknownHostException, IOException
    {
        s = new Socket(IP, PORT);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        boolean waiting = true;
        boolean name = false; 
        String response = "";
        String message = playerToString(serObj);
        while (waiting)
        {
            if (!name)
            {
                dout.writeUTF(serObj.getUID());
                name = true; 
            }
            response = din.readUTF();

            if (response.equals("ready, already exists."))
            {
                dout.writeUTF("##write");
            }
            else if (response.equals("write"))
            {
                dout.writeUTF(message);
            }
            else if (response.equals("done"))
            {
                dout.writeUTF("stop");
                waiting = false;
                break;
            }
            else
            {
                System.out.println("File save issue!");
            }

            dout.flush();

        }

        din.close();
        dout.close();
        s.close();
    }

    //Read a player from a file
    public static Player ReadObjectFromFile(String username) throws UnknownHostException, IOException
    {
        s = new Socket(IP, PORT);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        boolean waiting = true;
        boolean name = false; 
        String response = "";
        Player p = null;
        while (waiting)
        {
            if (!name)
            {
                dout.writeUTF(username);
                name = true;
            }

            response = din.readUTF();

            if (response.equals("ready, already exists."))
            {
                dout.writeUTF("##read");
            }
            else if (response.length() > 10)
            {
                p = stringToPlayer(response);
                dout.writeUTF("stop");
                waiting = false;
                break;
            }
            else 
            {
                System.out.println("response error: " + response);
            }

            dout.flush();
        } 

        din.close();
        dout.close();
        s.close();
        return p;
    }

    public static String playerToString(Player p)
    {
        String strPlayer = "";
        strPlayer += p.getUID() + "," + p.getPassword() + "," + p.getBank() + "," + p.getWins() + "," + p.getLosses();
        return strPlayer;
    }

    public static Player stringToPlayer(String str)
    {
        String username, password, bank, wins, losses;
        String[] stats = str.split(",");
        username = stats[0];
        password = stats[1];
        bank = stats[2];
        wins = stats[3];
        losses = stats[4];
        Player p = new Player(username);
        p.setPassword(password);
        p.setBank(Double.valueOf(bank));
        p.setWins(Integer.parseInt(wins));
        p.setLosses(Integer.parseInt(losses));

        return p;
    }


    public static boolean createNewUser(String username) throws IOException
    {
        s = new Socket(IP, PORT);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        boolean waiting = true;
        boolean name = false; 
        String response = "";
        boolean output = false;
        while (waiting)
        {
            if (!name)
            {
                dout.writeUTF(username);
                name = true;
            }

            response = din.readUTF();
            //System.out.println(response);

            if (response.equals("ready, file created."))
            {
                dout.writeUTF("stop");
                output = true;
                waiting = false;

                
                break;
            }
            else if (response.equals("ready, already exists."))
            {
                dout.writeUTF("stop");
                output = false;
                waiting = false;
                break;
            }
            else 
            {
                output = false;
                
            }

            dout.flush();

        }
        din.close();
        dout.close();
        s.close();
        return output;
    }

    //!!!!!ALL BUTTON ACTION LISTENERS!!!!!
    @Override
    public void actionPerformed(ActionEvent e) 
    {

        //Find which button was clicked
        Object obj = e.getSource();
        
        // *** Login Button ActionEvent ***
        if (obj == confirmLoginButton) 
        {
            //Get username and password
            username = userText.getText();
            password = String.valueOf(passText.getPassword());

            //Find file associated with username
            try {
                player = ReadObjectFromFile(username);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } //Add fix so usernames who dont exist dont throw errors
        
            //Check that password matches, then switch to Main Menu
            if (player.getPassword().equals(password))
            {
                backgroundLogin.setVisible(false);
                backgroundLogin.removeAll();
                backgroundLogin.repaint();
                frame.remove(backgroundLogin);
                buildMenu();
            }
            else //Otherwise indicate incorrect password
            {
                System.out.println("Invalid login Info!");
                JLabel error = new JLabel("Incorrect Password");
                error.setForeground(Color.RED);
                error.setFont(new Font("Times New Roman", Font.BOLD,24));
                error.setBounds(25,0,300,25);
                backgroundLogin.add(error);
                frame.repaint();
            }
        }
        // *** Switch from login to create account page ***
        else if (obj == createAccountButton)
        {
            backgroundLogin.setVisible(false);
            backgroundLogin.removeAll();
            frame.remove(backgroundLogin);
            buildCreateAccountPage();
        }
        // *** Confirm account creation ***
        else if (obj == confirmCreateAccountButton)
        {
            //Get username and password
            username = userText.getText();
            password = String.valueOf(createPassText.getPassword());

            //Set default filepath and create a file
            //filepath = "./players/player_" + username;
            //newFile = new File(filepath);
            
            try
            {
                if (createNewUser(username)) //Create a file
                {
                    System.out.println("File created: " + username);
                }
                else //User already exists
                {
                    System.out.println("User already exists, please enter a different username.");
                    username = null;
                }

            } catch (IOException exep) {
                System.out.println("An error occurred during file creation");
                exep.printStackTrace();
            }

            if (username != null) //If no errors occured create an account
            {
                player = new Player(username);
                player.setPassword(password);
                System.out.println("Account Created!");
                player.addBank(100);
                try {
                    WriteObjectToFile(player);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } 
            else //Error occured: let user know, account already exists
            { 
                System.out.println("Error creating account");
                error = new JLabel("Account already exists");
                error.setForeground(Color.RED);
                error.setFont(new Font("Times New Roman", Font.BOLD,24));
                error.setBounds(25,0,300,25);
                backgroundCreateAccount.add(error);
                frame.repaint();
            }
            if (player != null) //No errors occured and account was created, go to Main Menu
            { 
                backgroundCreateAccount.setVisible(false);
                backgroundCreateAccount.removeAll();
                frame.remove(backgroundCreateAccount);
                buildMenu();
            }
        }
        // *** Start Game Action ***
        else if (obj == startGameButton)
        {
            backgroundMenu.removeAll();
            frame.remove(backgroundMenu);
            buildGame();
            frame.repaint();
        }
        // *** View Stats Action ***
        else if (obj == viewStatsButton)
        {
            backgroundMenu.removeAll();
            frame.remove(backgroundMenu);
            buildStats();
            frame.repaint();
        }
        else if (obj == quitStats)
        {
            backgroundStats.removeAll();
            frame.remove(backgroundStats);
            buildMenu();
            frame.repaint();
        }
        else if (obj == hitButton)
        {
            if (handPlayer.calculateTotal() <= 21 && busted == false) //If they have less than 21 and havent busted
            {
                //Add card to hand, and print to screen
                handPlayer.addCard(deck.getCardFromDeck());
                JLabel playerCard = new JLabel(new ImageIcon("./cards/" + handPlayer.getHand().get(cardsPrinted).toString().toLowerCase()));
                playerCard.setBounds(widthChange, frame.getHeight() - 400,209,303);
                backgroundGame.add(playerCard);

                //update width to keep track of where to print, note another cards been printed
                widthChange += 210;
                cardsPrinted++;
                backgroundGame.repaint();

                if (handPlayer.calculateTotal() <= 21)
                {
                    totalAmount.setText("Total: " + String.valueOf(handPlayer.calculateTotal()));
                    backgroundGame.repaint();
                } 
                else //BUST
                {
                    totalAmount.setText("Total: " + String.valueOf(handPlayer.calculateTotal()) + ": BUST");
                    busted = true;
                    player.addLoss();

                    //Reveal dealer's hidden card
                    dealerCardHidden.setIcon(new ImageIcon("./cards/" + handDealer.getHand().get(1).toString().toLowerCase()));

                    //Remove hit and stand buttons replace w/ play again and main menu
                    backgroundGame.remove(hitButton);
                    backgroundGame.remove(standButton);

                    
                    backgroundGame.add(playAgainButton);
                   
                    backgroundGame.add(MMButton);
                    
                    //Repaint and validate background
                    backgroundGame.repaint();
                }
                //Repaint and revalidate frame
            } 
        }
        else if (obj == standButton)
        {
            backgroundGame.remove(hitButton);
            backgroundGame.remove(standButton);
            backgroundGame.add(playAgainButton);
            backgroundGame.add(MMButton);
            int cardsPrintedDealer = 2;
            widthChange2 = widthChange2 - 210;
            //Reveal dealer's hidden card
            dealerCardHidden.setIcon(new ImageIcon("./cards/" + handDealer.getHand().get(1).toString().toLowerCase()));
            while (handDealer.calculateTotal() < 17)
            {
                widthChange2 -= 210;
                handDealer.addCard(deck.getCardFromDeck());
                JLabel dealerCard = new JLabel(new ImageIcon("./cards/" + handDealer.getHand().get(cardsPrintedDealer).toString().toLowerCase()));
                dealerCard.setBounds(widthChange2, frame.getHeight()/9,209,303);
                cardsPrintedDealer++;
                backgroundGame.add(dealerCard);
                backgroundGame.repaint();
            }
            if (handDealer.calculateTotal() > 21)
            {
                busted = true;
            }
            if (busted == false && handDealer.calculateTotal() < handPlayer.calculateTotal())
            {
                System.out.println("Win");
            }
            else if (busted == false && handDealer.calculateTotal() > handPlayer.calculateTotal())
            {
                System.out.println("Loss");
            }
            else if (busted == false && handDealer.calculateTotal() == handPlayer.calculateTotal())
            {
                System.out.println("Push");
            }
            else if (busted == true)
            {
                System.out.println("Dealer Bust! You Win!");
            }
        }
        else if (obj == MMButton)
        {
            backgroundGame.removeAll();
            frame.remove(backgroundGame);
            buildMenu();
        }
        else if (obj == playAgainButton)
        {
            backgroundGame.removeAll();
            frame.remove(backgroundGame);
            buildGame();
        }
    }
    
}
