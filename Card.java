


/**
*Create a Card class
*/
public class Card{
	private Suit suit;
	private Value value;

	public Card(Suit suit, Value value)
	{
	this.suit = suit;
	this.value = value;
	}

/**
*Returns the value of the card
*@return(Value) a Value object indicating value of the card
*
*/

	public Value getValue()
	{
	return this.value;
	}

/**
*method determines how the Card object is printed
*@return(String) String which contains the Suit and Value of the card
*/

	public String toString()
	{
	return this.suit.toString() + " " + "-" + " " + this.value.toString();
	}


}


