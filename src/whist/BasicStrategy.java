package whist;
import cards.Card;
import cards.Hand;

public class BasicStrategy implements Strategy{
    private boolean chosen = false;
    private boolean trumped = false;
    private Card partnerCard;
    private int playerID;

    public BasicStrategy(int p){playerID = p;}

    /**
     * chooses which card to play based on the given Hand h and current Trick t
     * takes into account the currently winning Card
     * whether the partner of the player is winning
     * and if the player is the first to play
     * @param h
     * @param t
     * @return the chosen most optimal Card
     */
    @Override
    public Card chooseCard(Hand h, Trick t){
        System.out.println(h.handList);
        Card choice = h.handList.get(0);
        //reset flags for card searching
        chosen = false;
        trumped = false;

        //if this is not the first card to be played
        if(t.getWinningCard() != null){
            for(Card c : h){
                if(c.compareTo(choice) == 1 && c.getSuit() == t.getLeadSuit()){
                    //find the largest card of the leading suit
                    choice = c;
                }
            }
            if(choice.getSuit() != t.getLeadSuit() || choice.compareTo(t.getWinningCard()) == -1){
                for(Card c : h){
                    if(c.getSuit() == t.getLeadSuit()){
                        if(c.compareTo(choice) == -1){
                            //the largest card of leading suit wont win, so choose the smallest
                            choice = c;
                        }
                    }
                }
                if(choice.getSuit() == t.getLeadSuit()){
                    chosen = true;
                }
            }
            else{
                chosen = true;
            }
            if(choice.compareTo(t.getWinningCard()) != -1 && !chosen){
                //couldn't find a lead suit so free to try and trump the winning card
                if(t.getLeadSuit() != t.getTrumps()) {
                    for(Card c : h){
                        if(c.compareTo(choice) == -1 && c.getSuit() == t.getTrumps()){
                            //successfully found a smaller trump card
                            choice = c;
                            trumped = true;
                        }
                    }
                }
                if(!trumped){
                    //if all else fails, pick the smallest card to discard
                    for(Card c : h){
                        if(c.compareTo(choice) == -1){
                            choice = c;
                        }
                    }
                }
            }
        }
        else{
            for(Card c : h){
                if(c.compareTo(choice) == 1){
                    //first player so pick the largest card
                    choice = c;
                }
            }
        }

        //sets partnerCard to be whatever Card the partner played
        if(playerID > 1){
            Card partnerCard = t.getCard(playerID - 2);
        }
        else{
            Card partnerCard = t.getCard(playerID + 2);
        }

        //if your partner has played a Card and it is the winning Card
        if(t.getWinningCard() != null && partnerCard == t.getWinningCard()){
            for(Card c : h){
                if(c.getSuit() == t.getLeadSuit()){
                       if(c.compareTo(choice) == -1){
                        //partner is winning so find a smaller card of the lead suit
                        choice = c;
                        chosen = true;
                    }
                }
            }
            if(!chosen){
                for(Card c : h){
                    if(c.getSuit() != t.getTrumps() && c.compareTo(choice) == -1){
                        //couldn't find a smaller of lead suit so discard the smallest non trump card
                        choice = c;
                    }
                }
            }
        }

        System.out.println(playerID + " plays " + choice + "\n");
        h.handList.remove(choice); //remove the chosen card from the players hand
        return choice;
    }

    @Override
    public void updateData(Trick t){
    }
}
