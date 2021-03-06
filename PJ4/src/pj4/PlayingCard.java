// author : Gabriel Vieira Equitz
// id: 915254839
package pj4;

//package PJ4;

import java.util.*;



//=================================================================================
/** class PlayingCardException: It is used for errors related to Card and Deck objects
 *  Note: this is a checked exception!
 *  Do not modify this class!
 */
class PlayingCardException extends Exception {

    /* Constructor to create a PlayingCardException object */
    PlayingCardException (){
		super ();
    }

    PlayingCardException ( String reason ){
		super ( reason );
    }
}



//=================================================================================
/** class Card : for creating playing card objects
 *  it is an immutable class.
 *  Rank - valid values are 1 to 13
 *  Suit - valid values are 1 to 4
 *  Do not modify this class!
 */
class Card {
	
    /* constant suits and ranks */
    static final String[] Suit = {"Clubs", "Diamonds", "Hearts", "Spades" };
    static final String[] Rank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /* Data field of a card: rank and suit */
    private int cardRank;  /* values: 1-13 (see Rank[] above) */
    private int cardSuit;  /* values: 1-4  (see Suit[] above) */

    /* Constructor to create a card */
    /* throw PlayingCardException if rank or suit is invalid */
    public Card(int rank, int suit) throws PlayingCardException { 
	if ((rank < 1) || (rank > 13))
		throw new PlayingCardException("Invalid rank:"+rank);
	else
        	cardRank = rank;
	if ((suit < 1) || (suit > 4))
		throw new PlayingCardException("Invalid suit:"+suit);
	else
        	cardSuit = suit;
    }

    /* Accessor and toString */
    /* You may impelemnt equals(), but it will not be used */
    public int getRank() { return cardRank; }
    public int getSuit() { return cardSuit; }
    public String toString() { return Rank[cardRank-1] + " " + Suit[cardSuit-1]; }

    
    /* Few quick tests here */
    public static void main(String args[])
    {
	try {
	    Card c1 = new Card(1,4);    // A Spades
	    System.out.println(c1);
	    c1 = new Card(10,1);	// 10 Clubs
	    System.out.println(c1);
	    c1 = new Card(10,5);        // generate exception here
	}
	catch (PlayingCardException e)
	{
	    System.out.println("PlayingCardException: "+e.getMessage());
	}
    }
}



//=================================================================================
/** class Deck represents : n decks of 52 playing cards
 *  Use class Card to construct n * 52 playing cards!
 *
 *  Do not add new data fields!
 *  Do not modify any methods
 *  You may add private methods 
 */

class Deck {

    /* this is used to keep track of original n*52 cards */
    private List<Card> originalDeck;   

    /* this starts with n*52 cards deck from originalDeck  */
    /* it is used to keep track of remaining playing cards */
    /* Note : reset() resets playingDeck to a full decks   */
    private List<Card> playingDeck;

    /* number of decks in this object */
    private int numberDecks;


    /**
     * Constructor: Creates default one deck of 52 playing cards in originalDeck and
     * 		    copy them to playingDeck.
     *              initialize numberDecks=1
     * Note: You need to catch PlayingCardException from Card constructor
     *	     Use ArrayList or LinkedList for both originalDeck & playingDeck
     */
    public Deck()
    {
        this(1);      
        

    }


    /**
     * Constructor: Creates n decks (52 cards each deck) of playing cards in
     *              originalDeck and copy them to playingDeck.
     *              initialize numberDecks=n
     * Note: You need to catch PlayingCardException from Card constructor
     *	     Use ArrayList or LinkedList for both originalDeck & playingDeck
     */
    public Deck(int n)
    {
        numberDecks=n;
    	originalDeck = new ArrayList<Card>(numberDecks*52);
	    for (int k=0; k<numberDecks; k++){ //decks
		for (int j = 1; j <= 4; j++) { // suit
		    for (int i = 1; i <= 13; i++) { // rank
			Card tempCard;
			    try {
				tempCard = new Card(i, j);
				originalDeck.add(tempCard);
			    } catch (PlayingCardException exc) {
				exc.printStackTrace();
			    }
			}
		}
	    }
		
	playingDeck = new ArrayList<Card>(originalDeck); 
     
    }


    /**
     * Task: Shuffles cards in deal deck.
     * Hint: Look at java.util.Collections
     */
    public void shuffle()
    {
        Collections.shuffle(playingDeck);
    }

    /**
     * Task: Deals cards from the playing deck.
     *
     * @param numberCards number of cards to deal
     * @return a list containing cards that were dealt
     * @throw PlayingCardException if numberCard > number of remaining cards
     *
     * Note: You need to create list to stored dealt cards
     *       and should removed dealt cards from playingDeck
     *
     */
    public List<Card> deal(int numberCards) throws PlayingCardException
    {
      
         // implement this method!
        List<Card> cdDeal = new ArrayList<Card>();
        if ( numberCards > playingDeck.size() ){
          throw new PlayingCardException("Not enough cards to deal");
        }
        for ( int i = 0; i < numberCards; i++ ){
          cdDeal.add(playingDeck.remove(0));
        }
        return cdDeal;
    }

    /**
     * Task: Resets playing deck by getting all cards from the original deck.
     */
    public void reset()
    {
        playingDeck = new ArrayList<Card>(originalDeck);
        
       
    }

    /**
     * Task: Return number of remaining cards in deal deck.
     */
    public int remain()
    {
	return playingDeck.size();
    }

    /**
     * Task: Returns a string representing cards in the deal deck 
     */
    public String toString()
    {
	return ""+playingDeck;
    }


    /* Quick test                   */
    /*                              */
    /* Do not modify these tests    */
    /* Generate 2 decks of cards    */
    /* Loop 2 times:                */
    /*   Deal 30 cards for 4 times  */
    /*   Expect exception last time */
    /*   reset()                    */

    public static void main(String args[]) {

        System.out.println("*******    Create 2 decks of cards      *********\n\n");
        Deck decks  = new Deck(2);
         
	for (int j=1; j <= 2; j++)
	{
        	System.out.println("\n************************************************\n");
        	System.out.println("Loop # " + j + "\n");
		System.out.println("Before shuffle:"+decks.remain()+" cards");
		System.out.println("\n\t"+decks);
        	System.out.println("\n==============================================\n");

                int numHands = 4;
                int cardsPerHand = 30;

        	for (int i=1; i <= numHands; i++)
	 	{
	    		decks.shuffle();
		        System.out.println("After shuffle:"+decks.remain()+" cards");
		        System.out.println("\n\t"+decks);
			try {
            		    System.out.println("\n\nHand "+i+":"+cardsPerHand+" cards");
            		    System.out.println("\n\t"+decks.deal(cardsPerHand));
            		    System.out.println("\n\nRemain:"+decks.remain()+" cards");
		            System.out.println("\n\t"+decks);
        	            System.out.println("\n==============================================\n");
			}
			catch (PlayingCardException e) 
			{
		 	        System.out.println("*** In catch block:PlayingCardException:Error Msg: "+e.getMessage());
			}
		}


		decks.reset();
	}
    }

}
    
