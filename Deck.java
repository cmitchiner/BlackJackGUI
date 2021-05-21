import java.util.*;
public class Deck {
    
    private ArrayList<Card> cards = new ArrayList<Card>(52);

    public Deck() //initalizes deck
    {
        char[] suits = new char[4];

        suits[0] = 'D';
        suits[1] = 'S';
        suits[2] = 'C';
        suits[3] = 'H';

        for(int y = 0; y < 4; y++) 
        {
            for (int x = 1; x < 14; x++) 
            {
                cards.add(new Card(suits[y], x));
            }
        }
    }


    public void shuffle()
    {
        Collections.shuffle(cards);
    }


    public Card getCardFromDeck()
    {
        Card top = cards.get(0);
        cards.remove(0);
        cards.add(top);
        return top;
    }
}
