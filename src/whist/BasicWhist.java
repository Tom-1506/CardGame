package whist;
import cards.Card;
import cards.Deck;
import java.util.Iterator;

/**
 *
 * @author ajb
 */
public class BasicWhist{
    private static final int NOS_PLAYERS = 4;
    private static final int NOS_TRICKS = 13;
    private static final int WINNING_POINTS = 7;
    private int team1Points, team2Points = 0;
    private int team1tricks, team2tricks = 0;
    private boolean team1WinTrick, team2WinTrick = false;
    private Player[] players;

    /**
     * constructor takes array of players and sets them to players Array
     * @param pl
     */
    public BasicWhist(Player[] pl){
        players = pl;
    }

    /**
     * deals hands to players from newDeck
     * @param newDeck
     */
    public void dealHands(Deck newDeck){
        Iterator<Card> itr = newDeck.iterator();
        for(int i=0;i<NOS_TRICKS*NOS_PLAYERS;i++){
            players[i%NOS_PLAYERS].dealCard(newDeck.deal(itr));
        }
    }

    /**
     * play a trick taking firstPlayer to pass to Trick as the leadPlayer
     * @param firstPlayer
     * @return the completed Trick
     */
    public Trick playTrick(Player firstPlayer){
        Trick t=new Trick(firstPlayer.getID());
        int playerID=firstPlayer.getID();

        //go to each player and have them play a card, starting from the firstPlayer
        for(int i=0;i<NOS_PLAYERS;i++){
            int next=(playerID+i)%NOS_PLAYERS;
            t.setCard(players[next].playCard(t),players[next]);
        }
        return t;
    }

    /**
     * play a game(full set of 13 tricks)
     */
    public void playGame(){
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("|---------------------------: NEW GAME :---------------------------|");
        System.out.println("+------------------------------------------------------------------+");

        //create new deck and deal to players
        Deck d=new Deck();
        dealHands(d);

        //randomly choose first player
        int firstPlayer=(int)(NOS_PLAYERS*Math.random());

        //randomly choose and set trumps
        Card.Suit trumps = Card.Suit.randSuit();
        Trick.setTrumps(trumps);

        //set trumps for all players
        for(int i=0;i<NOS_PLAYERS;i++)
            players[i].setTrumps(trumps);

        //play 13 tricks
        for(int i=0;i<NOS_TRICKS;i++){
            System.out.println("+-----------------------------------------------+");
            System.out.println("|-----------------: NEW TRICK :-----------------|");
            System.out.println("+-----------------------------------------------+");
            System.out.println("Trumps are " + trumps); //print trumps for readability in game

            //play a trick and save completed trick in t
            Trick t=playTrick(players[firstPlayer]);            
            System.out.println("Trick = " + t);
            //find the winner of the trick
            firstPlayer=t.findWinner();
            System.out.println("Winner = " + firstPlayer);

            //find what team won and which to add a trick to
            if(firstPlayer == 0 || firstPlayer == 2){
                team1tricks++;
            }
            else{
                team2tricks++;
            }

            //send completed trick to all players
            for(Player p : players){
                p.viewTrick(t);
            }
        }
        //find which team won the game
        if(team1tricks > team2tricks){
            team1WinTrick = true;
        }
        else{
            team2WinTrick = true;
        }
    }

    /**
     * plays a full match of whist until one team gets 7 points
     */
    public void playMatch(){
        //reset points at start of each match
        team1Points=0;
        team2Points=0;

        //while neither team has enough points to win
        while(team1Points<WINNING_POINTS && team2Points<WINNING_POINTS){
            //reset trick winner flags and tricks won
            team1WinTrick = team2WinTrick = false;
            team1tricks = team2tricks = 0;

            playGame();

            //if a team won then award them points (if they won any)
            if(team1WinTrick && team1tricks > 6){
                team1Points += (team1tricks - 6);
            }
            if(team2WinTrick && team2tricks > 6){
                team2Points += (team2tricks - 6);
            }
        }
        //if team1 wins print they won otherwise print team2 won
        if(team1Points>=WINNING_POINTS){
            System.out.println("Winning team is team1 with "+team1Points);
        }
        else{
            System.out.println("Winning team is team2 with "+team2Points);
        }
    }

    /**
     * plays a test game (used for testing)
     */
    public static void playTestGame(){
        Player[] p = new Player[NOS_PLAYERS];
        for(int i=0;i<p.length;i++){
            p[i] = new BasicPlayer(i);
        }
        BasicWhist bg=new BasicWhist(p);
        bg.playMatch(); //Just plays a single match
    }

    /**
     * plays a game of 4 BasicStrategy Players
     */
    public static void basicGame(){
        System.out.println("Game of Four Basic Players");
        Player[] p = new Player[NOS_PLAYERS];
        for(int i=0;i<p.length;i++){
            p[i] = new BasicPlayer(i);
        }
        BasicWhist bg=new BasicWhist(p);
        bg.playMatch(); //Just plays a single match
    }

    /**
     * plays a game of 3 BasicStrategy Players and 1 HumanStrategy Player
     */
    public static void humanGame(){
        System.out.println("Game of Three Basic Players and One Human Player");
        Player[] p = new Player[NOS_PLAYERS];
        for(int i=0;i<p.length;i++){
            p[i] = new BasicPlayer(i);
        }

        //set player 0 to be the human
        HumanStrategy h = new HumanStrategy();
        p[0].setStrategy(h);

        BasicWhist bg=new BasicWhist(p);
        bg.playMatch(); //Just plays a single match
    }

    public static void advancedGame(){
        System.out.println("Game of Two Basic Players and Two Advanced Players");
        Player[] p = new Player[NOS_PLAYERS];
        for(int i=0;i<p.length;i++){
            p[i] = new BasicPlayer(i);
        }

        //set team1 to be advanced
        AdvancedStrategy a = new AdvancedStrategy();
        p[0].setStrategy(a);
        p[2].setStrategy(a);

        BasicWhist bg=new BasicWhist(p);
        bg.playMatch(); //Just plays a single match
    }

    public static void main(String[] args){
        humanGame();
    }
}
