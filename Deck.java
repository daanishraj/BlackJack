import java.util.*;


/**
*Creating a Deck class. A Deck will contain one or many Card objects
*
*/

public class Deck
{
	private ArrayList<Card> cards;


	public Deck()
	{
		this.cards = new ArrayList<Card>();
	}


/**
*
* Adds a full deck of cards with 13 unique values within each Suit and 4 Suits in total. This yields a total of 52 cards
*/


	public void fullDeck()
	{

		Suit[] allSuits =  Suit.values();
		Value[] allValues = Value.values();

		for (int i = 0; i<allSuits.length; i++)
		{
			for (int j=0; j<allValues.length; j++)
			{
				Card newCard = new Card(allSuits[i],allValues[j]);
				cards.add(newCard);
			}
		}

	}


/**
*
* Shuffles the Deck of cards to ensure that card positions are changed at random
*/



	public void shuffle(){
		ArrayList<Card> interimDeck = new ArrayList<Card>();
		int maxSize = this.cards.size();
		Random rand = new Random();

		for (int i = 0; i< maxSize; i++)
		{	
			int randIdx = rand.nextInt((this.cards.size()-1-0) + 1);
			Card movedCard = this.cards.get(randIdx);
			interimDeck.add(movedCard);
			this.cards.remove(randIdx);
		}

		this.cards = interimDeck;

	}

/**
*
*Removes a card from the game's Deck and adds it to the Deck
*@param gameDeck 
*				(Deck) the deck that we are using to draw a card from
* 
*/


	public void draw(Deck gameDeck)
	{
		Card drawnCard = gameDeck.getCard(0);
		this.cards.add(drawnCard);
		gameDeck.removeCard(0);
	}

/**
*Get's the card at the given index in the deck. 
*@param idx
*			(int) index or position in the deck
* @return (Card) Card object at that position in the deck
*/


	public Card getCard(int idx)
	{
		return cards.get(idx);

	}

/**
*Removes the card at the given index in the deck
*@param idx
*			(int) index or position in the deck
*/

	public void removeCard(int idx)
	{
		cards.remove(idx);

	}

/**
*Returns all the cards in the deck 
*
* @return (ArrayList) returns an ArrayList of cards 
*/

	public ArrayList<Card> getAllCards()
	{
		return this.cards;
	}

/**
*Returns the size of the deck 
*
* @return (int) An int representing the size of the arrayList containing all the cards in the Deck
*/


	public int deckSize()
	{
		return this.cards.size();
	}



/**
*Returns the sum of the values of all the cards in the deck
* @return (int) An Integer represting the total value of all cards in the deck
*/

	public int deckValue()
	{
		int totalVal = 0;
		int acesCount = 0;

		for (Card card: this.cards)
		{
			switch(card.getValue())
			{
				case TWO:
					totalVal += 2;
					break;
				case THREE:
					totalVal += 3;
					break;
				case FOUR:
					totalVal += 4;
					break;
				case FIVE:
					totalVal += 5;
					break;
				case SIX:
					totalVal += 6;
					break;
				case SEVEN:
					totalVal += 7;
					break;
				case EIGHT:
					totalVal += 8;
					break;
				case NINE:
					totalVal += 9;
					break;
				case TEN:
					totalVal += 10;
					break;
				case JACK:
					totalVal += 10;
					break;
				case QUEEN:
					totalVal += 10;
					break;
				case KING:
					totalVal += 10;
					break;
				case ACE:
					acesCount += 1;
					break;

			}

		}
			for (int i = 0; i<acesCount; i++)
			{
				if (totalVal<=10)
				{
					totalVal += 11;
				}
				else
				{
					totalVal+=1;
				}

			}

		return totalVal;

	}

/**
* This method determines how the Deck object is printed
*@return(String) String which contains the position and suit and value of each card in the deck 
*/


	public String toString(){
		String output = "";
		for (int i = 0; i<cards.size(); i++){
			output += "\n" + i + ":" + " "	 + cards.get(i).toString();	
			// System.out.println(i + ":" + " " +  cards.get(i).toString());
		}
		return output;
	}
}

