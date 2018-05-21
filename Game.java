import java.util.*;


public class Game
{

	public static void main(String[] args) 
	{	
		boolean currentRound;
		boolean exit = false;
		int PLAYERBET = 10;// we are fixing the amount which can be bet or doubled in any round
		int playerAmount = 	100; //the starting amount for the player
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to The ALICE Casino!" + '\n' + "You have $100 in the bank. Do you want to play? Enter y or n");


		String playOrNot = input.next();

		while (!(playOrNot.equals("n") || playOrNot.equals("y")))//Ensure the player enter's the correct letter
		{
			System.out.println("You have $100 in the bank. Do you want to play? Enter y or n");
			playOrNot = input.next();

		}


		if (playOrNot.equals("n"))
		{
			System.out.println('\n' + "See you later. Exiting");
			
		}

		else
			// Player decides to play the game
		{	
			while (playerAmount>9 && !exit)
			{

				System.out.println('\n');
				System.out.println("Yay! Let's play! You are betting $10" + '\n');
				int resp; //used to record responses to player input
				int response;//used to record the actions which the player decides to undertake
				int playerHandValue;
				int dealerHandValue;

				//create the fresh Deck that will be used to draw cards for both the player and the dealer
				//for simplicty, we are creating a new deck for each round that the player decides to play
				Deck gameDeck = new Deck();
				gameDeck.fullDeck();
				
				//Shuffle cards in the game deck
				gameDeck.shuffle();

				//Initializing the player's hand
				Deck playerDeck = new Deck();

				//Initializing the dealer's hand
				Deck dealerDeck = new Deck();

				//drawing two cards for the player
				playerDeck.draw(gameDeck);
				playerDeck.draw(gameDeck);

				//drawing two cards for the dealer
				dealerDeck.draw(gameDeck);
				dealerDeck.draw(gameDeck);

				// this flag indicates that we are starting the current round of play; when currentRound is set to False, this indicates that the current
				//round can be concluded i.e. we have a winner
				currentRound = true;


					System.out.println("Your cards are: " + playerDeck.toString() + '\n');

					playerHandValue = playerDeck.deckValue();

					System.out.println("The value of your hand is: " + playerHandValue + '\n');

					System.out.println('\n' + "Dealer cards are: " + dealerDeck.getCard(0).toString() + " and a " + "Hidden card" + '\n');	


					dealerHandValue = dealerDeck.deckValue();
					
					// if the dealer has BlackJack, we evaluate if the game is a tie or if the dealer has won; no need to execute the remaining code
					if (dealerHandValue == 21)
					{
						System.out.println("It's a BlackJack for the Dealer!" + '\n');
						System.out.println('\n'  + "Dealer cards are: ");
						for (Card card: dealerDeck.getAllCards())
						{
							System.out.println(card.toString());
						}

						// checking for a tie with the player
						if (dealerHandValue == playerHandValue)
						{
						System.out.println("It's a push!" + '\n');
						currentRound = false;
						}	
						
						else
						{
							System.out.println("Dealer wins, you lose!" + '\n');
							playerAmount-= PLAYERBET;
							currentRound = false;

						
						}

					}

				//if dealer does not have a BlackJack, then it is the turn of the player 	
				else if (dealerHandValue< 21)
				{
					// System.out.println("What do you want to do? " + "Enter: " +  '\n' + "1 to Hit" + '\n' + "2 to Stand" + '\n' + "3 to Double Down" + '\n' + "4 to Split" + '\n' + "5 to Surrender");
					System.out.println("What do you want to do? " + "Enter: " +  '\n' + "1 to Hit" + '\n' + "2 to Stand" + '\n' + "3 to Double Down" + '\n' + "4 to Surrender");

					while (!input.hasNextInt()) input.next();//ensuring the the player only enters numbers as response
					response = input.nextInt();
							
					if (response==1)//Hit
						{	
							//a card is drawn for the player and the new value of his hand is computed
							boolean stand = false;
							playerDeck.draw(gameDeck);
							int lastIdx = playerDeck.deckSize() - 1;
							System.out.println("The card drawn is " + playerDeck.getCard(lastIdx).toString());
							playerHandValue = playerDeck.deckValue();
							System.out.println("The value of your hand is: " + playerHandValue);


							//the player can continue to choose to hit or stand while is total hand value is less than 21
							while (playerHandValue<21 && !stand)
							{
								System.out.println("Enter: " +  '\n' + "1 to Hit" + '\n' + "2 to Stand" + '\n');
								while (!input.hasNextInt()) input.next();
								resp = input.nextInt();
								if (resp == 2)
								{	
									stand = true;
									break;
								}
								
								else
								{
									playerDeck.draw(gameDeck);
									lastIdx = playerDeck.deckSize() - 1;
									System.out.println("The card drawn is " + playerDeck.getCard(lastIdx).toString());
									playerHandValue = playerDeck.deckValue();
									System.out.println("The value of your hand is: " + playerHandValue);

								}

							}

							// Checking if player has gone bust and loses
							if (playerHandValue>21)
							{
								System.out.println("You are bust. You lose, dealer wins! :( " + '\n');
								currentRound = false;
								playerAmount-= PLAYERBET;
								// break;
							}

							//if player has not lost, then he has decided to stand. Now it is time for the dealer's turn
							else if (playerHandValue <= 21 || stand == true)
							{
								System.out.println("You Stand; the value of your hand is " + playerHandValue + '\n');					

							}


						}
					
					//Player decides to Stand	
					else if (response==2)

					{	
						playerHandValue = playerDeck.deckValue();
						System.out.println("You Stand; the value of your hand is " + playerHandValue + '\n');					


					}
					
					else if (response==3)//Double Down
					//Note: We assume that the player will only choose this option if the value of his hand is less than 12.
					//We are not acccounting for bad decisions made by novice players
					// player doubles initial bet amount, draws a card and then is forced to stand irrespective of hand value

					{	
						PLAYERBET*=2;//doubling the initial bet amount
						playerDeck.draw(gameDeck);
						int lastIdx = playerDeck.deckSize() - 1;
						System.out.println("The card drawn is " + playerDeck.getCard(lastIdx).toString());
						playerHandValue = playerDeck.deckValue();
						System.out.println("The value of your hand is: " + playerHandValue);
						System.out.println("You Stand" + '\n');					



						// if (playerHandValue>21)//a good player will only double down if playerHandValue is 9,10 or 11, so this case should not happen
						// {
						// 	System.out.println("You are bust. You lose, dealer wins! :( " + '\n');
						// 	currentRound = false;
						// 	playerAmount-= PLAYERBET;

						// }

						// else
						// {
						// 	System.out.println("You Stand" + '\n');					
						
						// }




					}
					
					//Surrender: player foregoes half the bet amount and the round concludes
					else //(response==4)
					{	
						System.out.println("You have surrendered :/");
						playerAmount -= PLAYERBET/2;
						currentRound = false;
						// break;
						
					}


				///Player's turn is over. Time for Dealer's turn. But this is only required if the currentRound is true

					if (currentRound)
					{	
						//Once again, checking the values of the hands of the dealer and the player
						playerHandValue = playerDeck.deckValue();
						dealerHandValue = dealerDeck.deckValue();
						
						System.out.println("The value of dealer's hand is: " + dealerHandValue + '\n');

							
						//The dealer can continue to hit i.e. draw a card until the value of his hand is 17 or above
							while (dealerHandValue<=16)
							{
							dealerDeck.draw(gameDeck);
							int lastIdx = dealerDeck.deckSize() - 1;
							System.out.println("The card drawn by the dealer is " + dealerDeck.getCard(lastIdx).toString());
							dealerHandValue = dealerDeck.deckValue();
							System.out.println("The value of dealer's hand is: " + dealerHandValue + '\n');

							}

					
						
						//checking if dealer is bust	
						if (dealerHandValue>21)
						{
							System.out.println("Dealer is bust! You win! :)" + '\n');
							playerAmount+= PLAYERBET;
						}	
							

						//dealer may have won
						else if (dealerHandValue > playerHandValue)
						{
							System.out.println("Dealer wins! You lose! :( " + '\n');
							playerAmount -= PLAYERBET;

						}

						//checking for a push
						else if (dealerHandValue == playerHandValue)
						{
							System.out.println("It's a push" + '\n');

						}

						// player may have won
						else
						{
							System.out.println("You win! :)" + '\n');
							playerAmount+= PLAYERBET;
						
						}


					}

						

			}

				
			//end of current round. We have a result.  Now check with the player if he wants to play another round

						System.out.println("You have " + "$" + playerAmount + " in the bank" + '\n');

						System.out.println('\n' + "Enter 1 to play another round, or 2 to exit Alice's Casino" + '\n');
						while (!input.hasNextInt()) input.next();
						resp = input.nextInt();
						if (resp == 2)
						{
							exit = true;
						}


			} 

			//Game ends here; either player wants to leave the Casino or he is out of money

			if (exit)
			{	
				System.out.println("Thanks for playing. Hope to see you again soon at The ALICE Casino!");
				System.out.println("You leave with " + "$" + playerAmount + " and " + "your reputation intact");
			}

			else// must be out of money
			{	
				System.out.println("You have $ " + playerAmount + " remaining");
				System.out.println("You are out of cash! Come back with more dough. Ciao!");

			}

		}





	}


}
