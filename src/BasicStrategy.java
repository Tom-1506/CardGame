public class BasicStrategy implements Strategy{

    @Override
    public Card chooseCard(Hand h, Trick t){
        System.out.println(h.handList);
        Card choice = h.handList.get(0);

        if(t.getWinner() != null){
            for(Card c : h){
                if(c.compareTo(choice) == 1 && c.getSuit() == t.getLeadSuit()){
                    choice = c;
                }
                else if(c.compareTo(choice) == -1){
                    choice = c;
                }
            }
        }
        else{
            for(Card c : h){
                if(c.compareTo(choice) == 1){
                    choice = c;
                }
            }
        }

        h.handList.remove(choice);
        return choice;
    }

    @Override
    public void updateData(Trick c) {

    }
}
