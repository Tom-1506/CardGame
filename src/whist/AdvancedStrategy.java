package whist;
import cards.Card;
import cards.Hand;

public class AdvancedStrategy implements Strategy{

    @Override
    public Card chooseCard(Hand h, Trick t){
        return null;
    }

    @Override
    public void updateData(Trick t){
    }
}
