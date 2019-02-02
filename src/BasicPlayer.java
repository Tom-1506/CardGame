public class BasicPlayer implements Player{
    private int playerID;
    private Card.Suit trump;
    private Hand playerHand;
    private Strategy playerStrategy = new BasicStrategy();

    public BasicPlayer(int id){
        playerID = id;
        playerHand = new Hand();
    }

    @Override
    public Card playCard(Trick t){
        return playerStrategy.chooseCard(playerHand, t);
    }

    @Override
    public void setStrategy(Strategy s){
        playerStrategy = s;
    }

    @Override
    public void dealCard(Card c){
        playerHand.add(c);
    }

    @Override
    public void viewTrick(Trick t){
        playerStrategy.updateData(t);
    }

    @Override
    public void setTrumps(Card.Suit s){
        this.trump = s;
    }

    @Override
    public int getID(){
        return playerID;
    }
}
