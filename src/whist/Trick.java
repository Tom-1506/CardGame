package whist;
import cards.Card;
import java.util.ArrayList;

/**
 * Skeleton class for storing information about whist tricks
 * @author ajb
 */
public class Trick{
    private int leadPlayer;
    private static Card.Suit trumps;
    private Integer winner = null;
    private Card winningCard = null;
    private ArrayList<Card> cardList = new ArrayList<>(4);

    /**
     * constructor for trick, set lead player to be p
     * set all values of the cardList to be null
     * if trumps is null, choose a random trump
     * print out which player is first (for readability in the game)
     * @param p
     */
    public Trick(int p){
       leadPlayer = p;
       winner = leadPlayer;
       for(int i = 0; i < 4; i++){
           cardList.add(null);
       }
       if(trumps == null){
           trumps = Card.Suit.randSuit();
       }
        System.out.println(leadPlayer + " is first");
    }    //p is the lead player

    /**
     * set trumps to s
     * @param s
     */
    public static void setTrumps(Card.Suit s){
       trumps=s;
    }

    /**
     *
     * @return trumps if the current trick
     */
    public Card.Suit getTrumps(){
        return trumps;
    }

    /**
    *
    * @return the Suit of the lead card.
    */
    public Card.Suit getLeadSuit(){
        return cardList.get(leadPlayer).getSuit();
    }

    /**
    * Records the Card c played by Player p for this trick
    * @param c
    * @param p
    */
    public void setCard(Card c, Player p){
        cardList.set(p.getID(), c);
        System.out.println("current winning card = " + getWinningCard());
    }

    /**
    * Returns the card played by player with id p for this trick
    * @param p
    * @return
    */
    public Card getCard(int p){
        return cardList.get(p);
    }

    /**
    * Finds the ID of the winner of a completed trick
    */
    public int findWinner(){
        System.out.println(cardList);
        winner = leadPlayer;
        Card.Suit winSuit = cardList.get(winner).getSuit();

        for(Card c : cardList){
            if (c.getSuit() == trumps){
                winSuit = trumps;
            }
            if (cardList.get(winner).getSuit() != winSuit && c.compareTo(cardList.get(winner)) != 1){
                winner = cardList.indexOf(c);
            }
            if (c.compareTo(cardList.get(winner)) == 1 && c.getSuit() == winSuit){
                winner = cardList.indexOf(c);
            }
        }
        return winner;
    }

    /**
     *
     * @return the currently winning Card for this trick
     */
    public Card getWinningCard(){
        if(winningCard == null){
            winningCard = cardList.get(leadPlayer);
        }
        else{
            for(Card c : cardList){
                if(c != null){
                    if (c.getSuit() == trumps || winningCard.getSuit() == trumps){
                        if(c.compareTo(winningCard) == 1 && c.getSuit() == trumps){
                            winningCard = c;
                        }
                    }

                    if(c.compareTo(winningCard) == 1 && c.getSuit() == winningCard.getSuit()){
                        winningCard = c;
                    }
                }
            }
        }
        return winningCard;
    }

    //Test Harness
    public static void main(String[] args) {
        Trick trick = new Trick(0);

        //Testing setting and getting trumps
        System.out.println("should return a random suit chosen as trumps in the constructor: " + trick.getTrumps());

        if(trick.getTrumps() != Card.Suit.DIAMONDS){
            trick.setTrumps(Card.Suit.DIAMONDS);
        }
        else{
            trick.setTrumps((Card.Suit.CLUBS));
        }
        System.out.println("\nshould print Diamonds if not should print Clubs: " + trick.getTrumps());
    }
}
