import java.util.*;
// import Main.clearsceen;

public class Game {
    
    private Player player;
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private double bet;

    private Scanner sc = new Scanner(System.in);

    
    public Game(Player p)
    {
        this.player = p;
    }
    
    public void initalizeGame()
    {
        System.out.println("Welcome to the game " + player.getUID() + "\n");
        System.out.println("-----------------------------------------------------");
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        

    }

    public void shuffle()
    {
        deck.shuffle();
    }
    
    public void getBets()
    {
        boolean complete = false;
        while (!complete)
        {
            System.out.println("You have $" + player.getBank() + " in your account.");
            System.out.println("-----------------------------------------------------");
            System.out.println("Please enter the amount of dollars you wish to bet:");
            this.bet = sc.nextDouble();
            
            if (bet <= player.getBank() && bet > 0 && !player.isBankrupt())
            {
                player.subtractBank(bet);
                complete = true;
            }
            else
            {
                System.out.println("Insufficient Funds! You have $" + player.getBank());
                if (player.isBankrupt())
                {
                    System.out.print("Please type 1 for a free dollar: ");
                    int input;
                    while(true)
                    {
                        try {
                            input = sc.nextInt();
                            if (input == 1)
                            {
                                player.addBank(1);
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid input");
                            continue;
                        }
                    }
                }
            }
        }
        
    }

    public void gameStart()
    {
        int choice = -1;
        System.out.println("----------------------BLACKJACK----------------------");
        System.out.println("\nTHE GAME HAS BEGUN\n");
        System.out.println("-----------------------------------------------------");
        playerHand.addCard(deck.getCardFromDeck());
        playerHand.addCard(deck.getCardFromDeck());

        dealerHand.addCard(deck.getCardFromDeck());
        dealerHand.addCard(deck.getCardFromDeck());
        System.out.println(whatsInMyHand());
        System.out.println("Which totals to: " + playerHand.calculateTotal() + "\n");
        System.out.println("The DEALER has a " + dealerHand.getHand().get(0).toString() + " and 1 card that's unknown");
        if (playerHand.calculateTotal() == 21)
        {
            gameEnd(1);
        }
        else
        {
            while(choice != 2)
            {
                System.out.println("-----------------------------------------------------");
                System.out.println("1.Hit\n2.Stand");
                System.out.println("-----------------------------------------------------");
                System.out.print("Pick an option: ");
                while(true)
                {
                    try {
                        choice = sc.nextInt();
                        break;
                    } catch (Exception e) {
                        System.err.print("Invalid input, please only input 1 or 2! \nTry again: ");
                        sc.nextLine();
                        continue;
                    }
                }
                System.out.println("-----------------------------------------------------");
                System.out.println();
                switch(choice)
                {
                    case 1:
                        hit();
                        System.out.println("----------------------BLACKJACK----------------------");
                        System.out.println(whatsInMyHand());
                        System.out.println("Which totals to: " + playerHand.calculateTotal());
                        if (playerHand.calculateTotal() > 21)
                        {
                            System.out.println("\nBUST!\n");
                            System.out.println("-----------------------------------------------------");
                            gameEnd(-1);
                            choice = 2;
                            break;
                        }
                        break;
                    case 2:
                        gameEnd(0);
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            }
        } 
    }

    public String whatsInMyHand()
    {
        ArrayList<Card> list = playerHand.getHand();
        String output = "Your cards are: " + list.get(0).toString();
        for (int x = 1; x < list.size(); x++)
        {
            output +=  ", " + list.get(x).toString();
        }
        return output;
    }

    public String whatsInHisHand()
    {
        ArrayList<Card> list = dealerHand.getHand();
        String output = "The dealer's cards are: " + list.get(0).toString();
        for (int x = 1; x < list.size(); x++)
        {
            output +=  ", " + list.get(x).toString();
        }
        return output;
    }

    public void hit()
    {
        playerHand.addCard(deck.getCardFromDeck());
    }

    public void hitD()
    {
        dealerHand.addCard(deck.getCardFromDeck());
    }

    public void gameEnd(int var)
    {
        switch(var)
        {
            case -1:
                System.out.println("****** Try again next time, loser ******");
                player.addLoss();
                System.out.println("Press Q Followed by Enter to Quit...");
                sc.next();
                break;
            case 0:
                //normal playout
                System.out.println(whatsInHisHand());
                System.out.println();
                System.out.println("-----------------------------------------------------");
                while (dealerHand.calculateTotal() < 17) 
                {
                    System.out.println("He chooses to hit");
                    System.out.println("-----------------------------------------------------");
                    hitD();
                    System.out.println(whatsInHisHand());
                    System.out.println();
                }
                if (dealerHand.calculateTotal() > 21)
                {
                    System.out.println("****** Dealer Bust! ****** \nYou won $" + (bet * 2.00) + "!");
                    System.out.println("-----------------------------------------------------");
                    player.addBank(bet * 2.00);
                    player.addWin();
                    System.out.println("\nPress Q Followed by Enter to Quit...");
                    sc.next();
                    
                }
                else if (dealerHand.calculateTotal() < 22 && dealerHand.calculateTotal() > playerHand.calculateTotal())
                {
                    System.out.println("****** The Dealer wins with a total of " + dealerHand.calculateTotal() + ". Try again next round ******");
                    System.out.println("-----------------------------------------------------");
                    player.addLoss();
                    System.out.println("\nPress Q Followed by Enter to Quit...");
                    sc.next();
                }
                else if (dealerHand.calculateTotal() < playerHand.calculateTotal() && dealerHand.calculateTotal() > 0)
                {
                    
                    System.out.println("****** The Dealer has a final of " + dealerHand.calculateTotal() + ". You won $" + (bet * 2.00) + " ******");
                    System.out.println("-----------------------------------------------------");
                    player.addBank(bet * 2.00);
                    player.addWin();
                    System.out.println("\nPress Q Followed by Enter to Quit...");
                    sc.next();
                }
                else if (dealerHand.calculateTotal() == playerHand.calculateTotal())
                {
                    System.out.println("****** Push! You got your money back! ******");
                    System.out.println("-----------------------------------------------------");
                    player.addBank(bet * 1.00);
                    System.out.println("\nPress Q Followed by Enter to Quit...");
                    sc.next();
                }
                else 
                {
                    System.out.println("Err, something unexpected happened!");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("\nPress Q Followed by Enter to Quit...");
                    sc.next();
                }
                break;
            case 1:
                System.out.println("******Congratulations! You got blackjack******");
                System.out.println("-----------------------------------------------------");
                player.addBank(bet*2.50);
                player.addWin();
                System.out.println("\nPress Q Followed by Enter to Quit...");
                sc.next();
                break;
            default:
                System.out.println("Error, we passed incorrect args");
                System.out.println("-----------------------------------------------------");
                System.out.println("\nPress Q Followed by Enter to Quit...");
                sc.next();
                break;
            
            
            
        }
    }
}
