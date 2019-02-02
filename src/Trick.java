import java.util.ArrayList;

/**
 * Skeleton class for storing information about whist tricks
 * @author ajb
 */
public class Trick{
    private int leadPlayer;
    public static Card.Suit trumps;
    private Integer winner = null;
    private ArrayList<Card> cardList = new ArrayList<>(4);

    public Trick(int p){
       leadPlayer = p;
       for(int i = 0; i < 4; i++){
           cardList.add(null);
       }
       if(trumps == null){
           trumps = Card.Suit.randSuit();
       }
    }    //p is the lead player

    public static void setTrumps(Card.Suit s){
       trumps=s;
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
        System.out.println(p.getID());
        cardList.set(p.getID(), c);
    }
    /**
    * Returns the card played by player with id p for this trick
    * @param p
    * @return
    */
    public Card getCard(Player p){
        return cardList.get(p.getID());
    }

    /**
    * Finds the ID of the winner of a completed trick
    */
    public int findWinner(){
        System.out.println(cardList);
        winner = leadPlayer;
        Card.Suit winSuit = cardList.get(winner).getSuit();

        for(int i = 0; i < cardList.size(); i++){
            Card c = cardList.get(i);

            if(c.getSuit() == trumps){
                winSuit = trumps;
                if(cardList.get(winner).getSuit() != winSuit && c.compareTo(cardList.get(winner)) != 1){
                    winner = i;
                }
            }

            if(c.compareTo(cardList.get(winner)) == 1 && c.getSuit() == winSuit){
                winner = i;
            }
        }
        return winner;
    }

    public Integer getWinner(){
        return winner;
    }
}
