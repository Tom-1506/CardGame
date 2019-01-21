import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Card implements Serializable, Comparable<Card>{
    static final long serialVersionUID = 100;

    //definition for the Rank attribute
    public enum Rank{
        //all values of enum and values assigned
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        //define value variable
        final int rankValue;

        //set rankValue
        Rank(int val){rankValue = val;}

        //creates array of all values of Rank
        public static Rank[] rankArray = Rank.values();

        //function used to find next rank in enum
        public Rank getNext(){
            return rankArray[(this.ordinal()+1) % rankArray.length];
        }

        //accessor method for rank value
        public int getValue(){
            return rankValue;
        }

    }
    public Rank rank;

    //definition for Suit attribute
    public enum Suit{
        //all values of enum
        CLUBS, DIAMONDS, HEARTS, SPADES;

        //function to produce random suit
        public static Suit randSuit(){
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }
    }
    public Suit suit;

    //Card constructor, takes Rank and Suit
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString(){
        return (this.rank + " of " + this.suit + " - Value: " + this.rank.getValue());
    }

    @Override
    public int compareTo(Card compCard){
        int[] thisCardValue = new int[] {this.rank.getValue(), this.suit.ordinal()};
        int[] compCardValue = new int[] {compCard.rank.getValue(), this.suit.ordinal()};

        if(thisCardValue[0] > compCardValue[0]){
            return 1;
        }
        if(thisCardValue[0] < compCardValue[0]){
            return -1;
        }
        else{
            if(thisCardValue[1] > compCardValue[1]){
                return 1;
            }
            if(thisCardValue[1] < compCardValue[1]){
                return -1;
            }
            else{
                return 0;
            }
        }
    }

    //returns largest Card in arraylist of Cards
    public static Card max(ArrayList<Card> cardList){
        Iterator<Card> itr= cardList.iterator();
        Card max = itr.next();

        while(itr.hasNext()){
            Card comp = itr.next();

            if(max.compareTo(comp) == -1){
                max = comp;
            }
        }
        return max;
    }

    //function returns all cards in the cardList that are greater than choiceCard
    public static ArrayList<Card> chooseGreater(ArrayList<Card> cardList, Comparator<Card> comp, Card choiceCard){
        //sorts cards using Comparator
        cardList.sort(comp);

        //create new arrayList where we'll add the greater cards
        ArrayList<Card> greater = new ArrayList<Card>();

        for(int i = 0; i < cardList.size(); i++){
            if(cardList.get(i).compareTo(choiceCard) == 1){
                for(int j = i; j < cardList.size(); j++){
                    greater.add(cardList.get(j));
                }
                return greater;
            }
        }
        return greater;
    }

    //comparator that compares for ascending order sort
    public static class CompareRank implements Comparator<Card>{
        public int compare(Card firstCard, Card secondCard){
            return firstCard.compareTo(secondCard);   
        }
    }

    //comparator that compares for descending order sort
    public static class CompareDescending implements Comparator<Card>{
        public int compare(Card firstCard, Card secondCard){
            int[] firstCardValue = new int[] {firstCard.rank.getValue(), firstCard.suit.ordinal()};
            int[] secondCardValue = new int[] {secondCard.rank.getValue(), secondCard.suit.ordinal()};

            if(firstCardValue[0] > secondCardValue[0]){
                return -1;
            }
            if(firstCardValue[0] < secondCardValue[0]){
                return 1;
            }
            else{
                if(firstCardValue[1] > secondCardValue[1]){
                    return 1;
                }
                if(firstCardValue[1] < secondCardValue[1]){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }
    }

    //Accessors
    public Rank getRank(){
        return this.rank;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public static void main(String[] args) {
        ArrayList<Card> cardList = new ArrayList<Card>();

        Card c1 = new Card(Rank.TEN, Suit.DIAMONDS);
        Card c2 = new Card(Rank.FOUR, Suit.SPADES);
        Card c3 = new Card(Rank.TEN, Suit.SPADES);
        Card c4 = new Card(Rank.TWO, Suit.CLUBS);
        Card c5 = new Card(Rank.SIX, Suit.HEARTS);
        Card c6 = new Card(Rank.THREE, Suit.CLUBS);
        Card c7 = new Card(Rank.THREE, Suit.DIAMONDS);

        Card choiceCard = new Card(Rank.SIX, Suit.SPADES);

        cardList.add(c1);
        cardList.add(c2);
        cardList.add(c3);
        cardList.add(c4);
        cardList.add(c5);
        cardList.add(c6);
        cardList.add(c7);

        Comparator<Card> comp = new CompareRank();

        ArrayList<Card> greater = chooseGreater(cardList, comp, choiceCard);

        System.out.println(choiceCard);
        System.out.println("less than:");

        for(Card card : greater){
            System.out.println(card);
        }
    }
}
