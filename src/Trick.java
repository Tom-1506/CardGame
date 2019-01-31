import java.util.ArrayList;

/**
 * Skeleton class for storing information about whist tricks
 * @author ajb
 */
public class Trick{
   public static Card.Suit trumps;
   private ArrayList<Card> cardList = new ArrayList<>();
   private ArrayList<Player> playerList = new ArrayList<>();
   
   public Trick(int p){}    //p is the lead player 
   
   public static void setTrumps(Card.Suit s){
       trumps=s;
   }
    
/**
 * 
 * @return the Suit of the lead card.
 */    
    public Card.Suit getLeadSuit(){
        throw new UnsupportedOperationException("get lead suit not supported yet."); 
    }
/**
 * Records the Card c played by Player p for this trick
 * @param c
 * @param p 
 */
    public void setCard(Card c, Player p){
        cardList.add(c);
        playerList.add(p);
    }
/**
 * Returns the card played by player with id p for this trick
 * @param p
 * @return 
 */    
    public Card getCard(Player p){
        throw new UnsupportedOperationException("get card not supported yet."); 
    }
    
/**
 * Finds the ID of the winner of a completed trick
 */    
    public int findWinner(){
        throw new UnsupportedOperationException("get find winner not supported yet."); 
    }
}
