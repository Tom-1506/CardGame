package cards;
import java.io.Serializable;
import java.util.*;

public class Hand implements Iterable<Card>, Serializable{
    static final long serialVersionUID = 300;

    //define arrayList to hold cards in the hand
    public ArrayList<Card> handList;

    //variables to count number of each suit in hand
    private int clubCount;
    private int diamondCount;
    private int heartCount;
    private int spadeCount;

    //holds possible total values for the hand including aces as high and low
    private ArrayList<Integer> totalValues;

    //empty constructor initilialises a new arrayList to hold the cards
    public Hand(){handList = new ArrayList<>();}

    //constructor passed array of cards copies the cards into handList then counts suits and calculates total value
    public Hand(Card[] cardArr){
        handList = new ArrayList<>();
        for(int i = 0; i < cardArr.length; i++){
            handList.add(cardArr[i]);
        }
        recalculate();
    }

    //constructor passed another Hand object and copies the cards in the other Hand into this Hand
    public Hand(Hand otherHand){
        handList = new ArrayList<>();
        for(int i = 0; i < otherHand.handList.size(); i++){
            this.handList.add(otherHand.handList.get(i));
        }
        recalculate();
    }

    //method defines handIterator to iterate through a Hand in order
    public Iterator<Card> iterator(){return new HandIterator();}

    //definitions for HandIterator
    public class HandIterator implements Iterator {
        int currentPos = 0;

        //hasNext if current position is not the end of the handList
        @Override
        public boolean hasNext(){
            if(currentPos < handList.size()){
                return true;
            }
            return false;
        }

        //get next card in handList
        @Override
        public Card next(){
            if(this.hasNext()){
                return handList.get(currentPos++);
            }
            return null;
        }
    }

    //count number of cards in hand of each suit
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

    //calculate total values of the hand including aces high and low
    public void calcHandValue(){
        totalValues = new ArrayList<>();
        int totalValue = 0;

        //count total value with aces as low
        for(Card card : handList){
            if(!(card.getRank() == Card.Rank.ACE)){
                totalValue += card.rank.getValue();
            }
            else{
                totalValue += 1;
            }
        }
        totalValues.add(totalValue);

        //add 10 for number of aces and include as separate totals
        for(Card card : handList){
            if(card.getRank() == Card.Rank.ACE){
                totalValue += 10;
                totalValues.add(totalValue);
            }
        }
    }

    //add by passing card
    public void add(Card card){
        handList.add(card);
        recalculate();
    }

    //add passing arrayList of cards
    public void add(ArrayList<Card> cardList){
        for(Card card : cardList){
            handList.add(card);
        }
        recalculate();
    }

    //add passing hand
    public void add(Hand hand){
        handList.addAll(hand.handList);
        recalculate();
    }

    //remove first card from hand return true if removed
    public boolean remove(){
        if(!handList.isEmpty()){
            handList.remove(0);
            recalculate();
            return true;
        }
        else{
            recalculate();
            return false;
        }
    }

    //remove all cards from passed hand
    public boolean remove(Hand hand){
        int cardCheck = 0;
        int size = hand.handList.size();

        for(int i = 0; i < size; i++){
            Card thisCard = hand.handList.get(0);
            for(int j = 0; j < size; j++){
                System.out.println(handList.get(j));
                if(thisCard == handList.get(j)){
                    cardCheck++;
                    this.remove(handList.indexOf(handList.get(j)));
                    break;
                }
            }
        }
        if(cardCheck == hand.handList.size()){
            return true;
        }
        else{
            return false;
        }
    }

    //remove card of passed index position
    public Card remove(int position){
        Card returnCard = handList.get(position);
        handList.remove(position);
        recalculate();
        return returnCard;
    }

    //toString used for testing purposes
    @Override
    public String toString(){
        for(Card card : handList){
            System.out.println(card);
        }
        return "";
    }

    //sorts handlist
    public void sort(){
        Collections.sort(this.handList);
    }

    //sorts handList using CompareRank Comparator
    public void sortByRank(){
        this.handList.sort(new Card.CompareRank());
    }

    //returns the number of cards of a passed suit in this hand
    public int countSuit(Card.Suit suit){
        int count = 0;
        for(Card card : this.handList){
            if(card.suit == suit){
                count++;
            }
        }
        return count;
    }

    //returns the number of cards of a passed rank in this hand
    public int countRank(Card.Rank rank){
        int count = 0;
        for(Card card : this.handList){
            if(card.rank == rank){
                count++;
            }
        }
        return count;
    }

    //returns true if this hand contains a card of passed suit
    public boolean hasSuit(Card.Suit suit){
        for(Card card : this.handList){
            if(card.suit == suit){
                return true;
            }
        }
        return false;
    }

    //used to recalculate suitCount and calcHandValue where relevant to maintain correct data
    public void recalculate(){
        suitCount();
        calcHandValue();
    }

    public static void main(String[] args) {
        Card c1 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS);
        Card c2 = new Card(Card.Rank.THREE, Card.Suit.SPADES);
        Card c3 = new Card(Card.Rank.TEN, Card.Suit.SPADES);
        Card c4 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card c5 = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS);
        Card c6 = new Card(Card.Rank.THREE, Card.Suit.CLUBS);
        Card c7 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS);
        Card c8 = new Card(Card.Rank.SEVEN, Card.Suit.HEARTS);

        Card[] cardArr = {c1, c2, c3, c4, c5, c6, c7, c8};

        //Test all constructors
        Hand handEmpty = new Hand();
        Hand handTemp = new Hand(cardArr);
        System.out.println("should print out contents of hand: ");
        System.out.println(handTemp);
        handTemp = new Hand(handEmpty);
        System.out.println("\nshouldn't print anything: " + handTemp);
        System.out.println("\n--------------------------");

        //Test Iterator
        Hand hand = new Hand(cardArr);
        System.out.println("\nshould print out the contents of hand: ");
        Iterator<Card> itr = hand.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        //Test add methods
        Card[] cardArrSmall = {c1, c2};
        ArrayList<Card> addCardArr = new ArrayList<Card>();
        addCardArr.add(c4);
        addCardArr.add(c5);
        Card[] handAddArr = {c6, c7, c8};
        hand = new Hand(cardArrSmall);

        hand.add(c3);
        hand.add(addCardArr);
        hand.add(hand);

        System.out.println("\nshould print hand containing c1-c8: ");
        System.out.println(hand);

        //Test remove methods
        Card[] cardRemoveArr = {c3, c4, c5, c6, c7, c8};
        Hand removeHand = new Hand(cardRemoveArr);
        hand = new Hand(cardArrSmall);

        removeHand.remove(removeHand);

        System.out.println("\nshouldn't print anything: ");
        System.out.println(removeHand);
        System.out.println("--------------------------");

        hand.remove(1);
        System.out.println("\nShould print Ten of Diamonds : ");
        System.out.println(hand);

        hand.remove();
        System.out.println("\nshouldn't print anything: ");
        System.out.println(hand);
        System.out.println("--------------------------");

        //Test sort
        hand = new Hand(cardArr);
        System.out.println("\nshould return hand in order: ");
        hand.sort();
        System.out.println(hand);

        //Test sortByRank
        hand = new Hand(cardArr);
        System.out.println("\nshould return hand in Rank order: ");
        hand.sortByRank();
        System.out.println(hand);

        //Test countSuit and count Rank
        System.out.println("\nshould return 3 and 2: " + hand.countSuit(Card.Suit.DIAMONDS)
                + " and " + hand.countRank(Card.Rank.TEN));

        //Test hasSuit
        System.out.println("\nshould return true: " + hand.hasSuit(Card.Suit.HEARTS));
        hand.remove(5);
        System.out.println("\nshould return false: " + hand.hasSuit(Card.Suit.HEARTS));
    }
}