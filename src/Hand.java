import java.io.Serializable;
import java.util.*;

public class Hand implements Iterable<Card>, Serializable{
    static final long serialVersionUID = 300;

    private ArrayList<Card> handList;
    private int clubCount;
    private int diamondCount;
    private int heartCount;
    private int spadeCount;
    private ArrayList<Integer> totalValues;

    public Hand(){}

    public Hand(Card[] cardArr){
        handList = new ArrayList<>();
        for(int i = 0; i < cardArr.length; i++){
            handList.add(cardArr[i]);
        }
        suitCount();
        calcHandValue();
    }

    public Hand(Hand otherHand){
        handList = new ArrayList<>();
        for(int i = 0; i < otherHand.handList.size(); i++){
            this.handList.add(otherHand.handList.get(i));
        }
        suitCount();
        calcHandValue();
    }

    public Iterator<Card> iterator(){return new HandIterator();}

    public class HandIterator implements Iterator {
        int currentPos = 0;

        @Override
        public boolean hasNext(){
            if(currentPos < handList.size()){
                return true;
            }
            return false;
        }

        @Override
        public Card next(){
            if(this.hasNext()){
                return handList.get(currentPos++);
            }
            return null;
        }
    }

    public void suitCount(){
        for(Card card : this.handList){
            switch(card.suit){
                case CLUBS:
                    clubCount++;
                    break;
                case DIAMONDS:
                    diamondCount++;
                    break;
                case HEARTS:
                    heartCount++;
                    break;
                case SPADES:
                    spadeCount++;
                    break;
                default:
                    break;
            }
        }
    }

    public void calcHandValue(){
        totalValues = new ArrayList<>();
        int totalValue = 0;
        for(Card card : handList){
            if(!(card.getRank() == Card.Rank.ACE)){
                totalValue += card.rank.getValue();
            }
            else{
                totalValue += 1;
            }
        }
        totalValues.add(totalValue);

        for(Card card : handList){
            if(card.getRank() == Card.Rank.ACE){
                totalValue += 10;
                totalValues.add(totalValue);
            }
        }
    }

    public void addCard(Card card){
        handList.add(card);
    }

    public void addCollection(ArrayList<Card> cardList){
        for(Card card : cardList){
            handList.add(card);
        }
    }

    public void addHand(Hand hand){
        for(Card card: hand.handList){
            this.handList.add(card);
        }
    }

    public boolean removeTopCard(){
        if(!handList.isEmpty()){
            handList.remove(0);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeAll(Hand hand){
        int removed = 0;
        int size = hand.handList.size();

        for(int i = 0; i < size; i++){
            hand.handList.remove(i);
            removed++;
        }

        if(removed == size){
            return true;
        }
        else{
            return false;
        }
    }

    public Card removeCard(int position){
        Card returnCard = handList.get(position);
        handList.remove(position);
        return returnCard;
    }

    @Override
    public String toString(){
        for(Card card : handList){
            System.out.println(card);
        }
        return "";
    }

    public static void main(String[] args) {
        Card c1 = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS);
        Card c2 = new Card(Card.Rank.FOUR, Card.Suit.SPADES);
        Card c3 = new Card(Card.Rank.TEN, Card.Suit.SPADES);
        Card c4 = new Card(Card.Rank.EIGHT, Card.Suit.SPADES);

        Card[] cardArr = {c1, c2, c3, c4};

        Hand hand = new Hand(cardArr);

        Iterator<Card> itr = hand.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}