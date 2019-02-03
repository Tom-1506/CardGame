package whist;
import cards.Card;
import cards.Hand;

public class BasicStrategy implements Strategy{
    private boolean chosen = false;
    private boolean trumped = false;
    private Card partnerCard;
    private int playerID;

    public BasicStrategy(int p){playerID = p;}

    @Override
    public Card chooseCard(Hand h, Trick t){
        System.out.println(h.handList);
        Card choice = h.handList.get(0);

        if(t.getWinningCard() != null){
            for(Card c : h){
                if(c.compareTo(choice) == 1 && c.getSuit() == t.getLeadSuit()){
                    System.out.println("largest and same suit");
                    choice = c;
                }
            }
            if(choice.getSuit() != t.getLeadSuit() || choice.compareTo(t.getWinningCard()) == -1){
                for(Card c : h){
                    if(c.getSuit() == t.getLeadSuit()){
                        if(c.compareTo(choice) == -1){
                            System.out.println("find smaller of lead suit");
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
                System.out.println("try pick smaller trump");
                if(t.getLeadSuit() != t.getTrumps()) {
                    for(Card c : h){
                        if(c.compareTo(choice) == -1 && c.getSuit() == t.getTrumps()){
                            System.out.println("picked a smaller trump");
                            choice = c;
                            trumped = true;
                        }
                    }
                }
                if(!trumped){
                    System.out.println("pick smallest");
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
                    System.out.println("first player picking largest");
                    choice = c;
                }
            }
        }

        if(playerID > 1){
            Card partnerCard = t.getCard(playerID - 2);
        }
        else{
            Card partnerCard = t.getCard(playerID + 2);
        }

        if(partnerCard == t.getWinningCard() && t.getWinningCard() != null){
            for(Card c : h){
                if(c.getSuit() == t.getLeadSuit()){
                    if(c.compareTo(choice) == -1){
                        System.out.println("find smaller of lead suit because partner winning");
                        choice = c;
                        chosen = true;
                    }
                }
            }
            if(!chosen){
                for(Card c : h){
                    if(c.getSuit() != t.getTrumps() && c.compareTo(choice) == -1){
                        System.out.println("choose smallest non trump to discard");
                        choice = c;
                    }
                }
            }
        }

        h.handList.remove(choice);
        chosen = false;
        trumped = false;
        return choice;
    }

    @Override
    public void updateData(Trick c) {

    }
}
