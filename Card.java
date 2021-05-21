public class Card {

    private char suit;
    private int value;



    protected Card()
    {
        suit = ' ';
        value = 0;
    }

    public Card(char newSuit, int newValue) 
    {
        if (newValue < 1 || newValue > 13) 
        {
            System.out.println("Error, my cpu cannot handle these incorrect 0's and 1's mate");
        } 
        else 
        {
            this.value = newValue;
        }
        if (newSuit != 'H' && newSuit != 'S' && newSuit != 'D' && newSuit != 'C')
        {
            System.out.println("Error wrong suit");
        } 
        else 
        {
            this.suit = newSuit;
        }
    }

    public String toString()
    {
        return ( getValueName() + "_of_"  + getSuitName() + ".png");
    }

    public String getSuitName()
    {
        String suit;
		
		if (this.suit == 'H') {

			suit = "Hearts";

		}
		else if (this.suit == 'S') {

			suit = "Spades";

		}
		else if (this.suit == 'C') {

			suit = "Clubs";

		}
		else if (this.suit == 'D') {

			suit = "Diamonds";

		} else {

			suit = "Unknown";
		}
		
		return suit;
    }

    public String getValueName(){

		String name = "Unknown";

		if (this.value == 1) {		
			name = "Ace";
		}
		else if (this.value == 2) {
			name = "Two";
		}
		else if (this.value == 3) {
			name = "Three";
		}
		else if (this.value == 4) {
			name = "Four";
		}
		else if (this.value == 5) {
			name = "Five";
		}
		else if (this.value == 6) {
			name = "Six";
		}
		else if (this.value == 7) {
			name = "Seven";
		}
		else if (this.value == 8) {
			name = "Eight";
		}
		else if (this.value == 9) {

			name = "Nine";
		}
		else if (this.value == 10) {

			name = "Ten";
		}
		else if (this.value == 11) {

			name = "Jack";
		}
		else if (this.value == 12) {

			name = "Queen";
		}
		else if (this.value == 13) {

			name = "King";

		} 
		return name;
	}

    public int getValue()
    {
        return this.value;
    }

    public int getSuitChar()
    {
        return this.suit;
    }

	public int getWeight()
	{
		if (this.value > 1 && this.value < 10)
		{
			return this.value;
		}
		else if (this.value > 9 && this.value < 14)
		{
			return 10;
		}
		else if (this.value == 1)
		{
			return 1;
		}
		else
		{
			System.out.println("Card get weight error");
			return -1;
		}

	}
    
}
