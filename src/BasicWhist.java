import java.util.Iterator;

/**
 *
 * @author ajb
 */
public class BasicWhist{
    static final int NOS_PLAYERS = 4;
    static final int NOS_TRICKS = 13;
    static final int WINNING_POINTS = 7;
    int team1Points = 0;
    int team2Points = 0;
    int team1tricks = 0;
    int team2tricks = 0;
    boolean team1win = false;
    boolean team2win = false;
    Player[] players;

    public BasicWhist(Player[] pl){
        players = pl;
    }

    public void dealHands(Deck newDeck){
        Iterator<Card> itr = newDeck.iterator();
        for(int i=0;i<NOS_TRICKS*NOS_PLAYERS;i++){
            players[i%NOS_PLAYERS].dealCard(newDeck.deal(itr));
        }
    }

    public Trick playTrick(Player firstPlayer){
        Trick t=new Trick(firstPlayer.getID());
        int playerID=firstPlayer.getID();
        for(int i=0;i<NOS_PLAYERS;i++){
            int next=(playerID+i)%NOS_PLAYERS;
            t.setCard(players[next].playCard(t),players[next]);
        }
        return t;
    }

    public void playGame(){
        System.out.println("+----------------------------------------------+");
        System.out.println("|-----------------: NEW GAME :-----------------|");
        System.out.println("+----------------------------------------------+");

        Deck d=new Deck();
        dealHands(d);

        int firstPlayer=(int)(NOS_PLAYERS*Math.random());

        Card.Suit trumps = Card.Suit.randSuit();
        Trick.setTrumps(trumps);
        System.out.println(trumps);

        for(int i=0;i<NOS_PLAYERS;i++)
            players[i].setTrumps(trumps);
        
        for(int i=0;i<NOS_TRICKS;i++){
            Trick t=playTrick(players[firstPlayer]);            
            System.out.println("Trick = " + t);
            firstPlayer=t.findWinner();
            System.out.println("Winner = " + firstPlayer);

            if(firstPlayer == 0 || firstPlayer == 2){
                team1tricks++;
            }
            else{
                team2tricks++;
            }
        }
        if(team1tricks > team2tricks){
            team1win = true;
        }
        else{
            team2win = true;
        }
    }
/**
 * Method to find the winner of a trick. Note 
 * @param //t: current trick
 * @return the index of the winning player
 */    
    public void playMatch(){
        team1Points=0;
        team2Points=0;
        while(team1Points<WINNING_POINTS && team2Points<WINNING_POINTS){
            team1win = team2win = false;
            team1tricks = team2tricks = 0;
            playGame();
            if(team1win && team1tricks > 6){
                team1Points += (team1tricks - 6);
            }
            if(team2win && team2tricks > 6){
                team2Points += (team2tricks - 6);
            }
        }
        if(team1Points>=WINNING_POINTS)
            System.out.println("Winning team is team1 with "+team1Points);
        else
            System.out.println("Winning team is team2 with "+team2Points);
    }

    public static void playTestGame(){
        Player[] p = new Player[NOS_PLAYERS];
        Strategy s = new BasicStrategy();
        for(int i=0;i<p.length;i++){
            p[i] = new BasicPlayer(i);
        }
        BasicWhist bg=new BasicWhist(p);
        bg.playMatch(); //Just plays a single match
    }

    public static void main(String[] args){


        playTestGame();
    }
    
}
