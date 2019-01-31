public class BasicStrategy implements Strategy{
    @Override
    public Card chooseCard(Hand h, Trick t) {
        while(h.handList.get(0) != null){
            Card play = h.handList.get(0);
            h.remove(0);
            return play;
        }
        return null;
    }

    @Override
    public void updateData(Trick c) {

    }
}
