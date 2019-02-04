package whist;
import cards.Card;
import cards.Hand;
import java.util.ArrayList;

public class BasicPlayer implements Player{
    private int playerID;
    private Card.Suit trump;
    private Hand playerHand;
    private Strategy playerStrategy;
    private ArrayList<Trick> trickList = new ArrayList<Trick>();

    /**
     * constructor
     * sets player id to id
     * creates new hand for the player
     * sets the strategy to a BasicStrategy
     * @param id
     */
    public BasicPlayer(int id){
        playerID = id;
        playerHand = new Hand();
        playerStrategy = new BasicStrategy(playerID);
    }

    /**
     * passes the trick and uses the playerStrategy to decide which card to choose
     * @param t
     * @return the chosen card by the players strategy
     */
    @Override
    public Card playCard(Trick t){
        return playerStrategy.chooseCard(playerHand, t);
    }

    /**
     * sets strategy of player to s
     * @param s
     */
    @Override
    public void setStrategy(Strategy s){
        playerStrategy = s;
    }

    /**
     * adds Card c to players hand
     * @param c
     */
    @Override
    public void dealCard(Card c){
        playerHand.add(c);
    }

    /**
     * adds Trick t to the trickList to store completed tricks
     * @param t
     */
    @Override
    public void viewTrick(Trick t){
        trickList.add(t);
    }

    /**
     * sets trumps to the Suit s
     * @param s
     */
    @Override
    public void setTrumps(Card.Suit s){
        this.trump = s;
    }

    /**
     *
     * @return this players id
     */
    @Override
    public int getID(){
        return playerID;
    }

    //Test Harness
    public static void main(String[] args) {
        Player player0 = new BasicPlayer(0);
        Player player1 = new BasicPlayer(1);
        Player player2 = new BasicPlayer(2);
        Player player3 = new BasicPlayer(3);

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player0);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        //sets all player trumps to CLUBS
        for(Player p : playerList){
            p.setTrumps(Card.Suit.CLUBS);
        }

        Card card = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS);

        //Testing dealCard
        player0.dealCard(card);
        System.out.println("should print Six of Diamonds: " + ((BasicPlayer) player0).playerHand);

        //Test setTrumps
        System.out.println("\nshould have player trump as CLUBS: ");
        System.out.println(((BasicPlayer) player0).trump);
        player0.setTrumps(Card.Suit.SPADES);
        System.out.println("\nshould have first player trump as Spades: ");
        System.out.println(((BasicPlayer) player0).trump);

        //Test setStrategy
        System.out.println("\nshould have player strategy as BasicStrategy: " + ((BasicPlayer) player0).playerStrategy);
        Strategy s = new HumanStrategy();
        player0.setStrategy(s);
        System.out.println("\nshould have player strategy as HumanStrategy: " + ((BasicPlayer) player0).playerStrategy);

        /**
         * The other methods in this class are impractical to test without me having to make a complete trick from
         * scratch so I have decided to test them in BasicWhist where they are called as part of its test harness.
         */
    }
}
