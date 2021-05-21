import java.util.*;
    public class Hand {

        private ArrayList<Card> hand;
        

        public Hand() {

            hand = new ArrayList<Card>();
            
        }
        
        public int calculateTotal()
        {
            int value = 0;
            boolean hasAce = false;
            for (int x = 0; x < hand.size(); x++)
            {
                int weight = hand.get(x).getWeight();
                if (weight != 1 && weight != -1)
                {
                    value += weight;
                }
                else if (weight == 1)
                {
                    hasAce = true;
                    value += 1;
                }
                else if (weight == -1)
                {
                    System.out.println("error, invalid weight");
                }   
            }

            if (hasAce && (value + 10) < 22)
            {
                //System.out.println("Found Ace and added 10.");
                return (value + 10);
            }
            else if (hasAce && (value + 10) > 21)
            {
                //System.out.println("Found Ace, didn't add 10.");
                return value;
            }
            else if (!hasAce)
            {
                //System.out.println("Didn't find Ace.");
                return value;
            }
            else
            {
                System.out.println("Oh, boy..");
                return value;
            }
        }
        
        public void addCard(Card card)
        {
            hand.add(card);
        }

        public ArrayList<Card> getHand()
        {
            return hand;
        }
}
