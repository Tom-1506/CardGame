import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Deck implements Iterable<Card>, Serializable {
    static final long serialVersionUID = 49L;

    public ArrayList<Card> deckList;

    public Deck(){
        Card[] deckArray = new Card[52];
        int current = 0;

        for(Card.Rank rank : Card.Rank.values()){
            for(Card.Suit suit: Card.Suit.values()){
                deckArray[current] = new Card(rank, suit);
                current++;
            }
        }
        deckList = new ArrayList<>(Arrays.asList(deckArray));
        Collections.shuffle(deckList);
    }

    public Iterator<Card> iterator(){return new DeckIterator();}

    public class DeckIterator implements Iterator {
        int currentPos = 0;

        @Override
        public boolean hasNext(){
            if(currentPos < deckList.size()){
                return true;
            }
            return false;
        }

        @Override
        public Card next(){
            if(this.hasNext()){
                return deckList.get(currentPos++);
            }
            return null;
        }
    }

    public Iterator<Card> spadeIterator(){return new SpadeIterator();}

    public class SpadeIterator implements Iterator{
        int currentPos = 0;

        @Override
        public boolean hasNext(){
            while(currentPos < deckList.size()){
                if(deckList.get(currentPos).suit == Card.Suit.SPADES){
                    return true;
                }
                currentPos++;
            }
            return false;
        }

        @Override
        public Card next(){
            if(this.hasNext()){
                return deckList.get(currentPos++);
            }
            return null;
        }
    }

    public Card deal(){
        Iterator<Card> itr = this.iterator();

        Card nextCard = itr.next();
        System.out.println(nextCard);
        return nextCard;
    }

    public int size() {
        int cardCount = 0;
        for(Card c : deckList){
            cardCount++;
        }
        return cardCount;
    }

    public void newDeck(){
        Card[] deckArray = new Card[52];
        int current = 0;

        for(Card.Rank rank : Card.Rank.values()){
            for(Card.Suit suit: Card.Suit.values()){
                deckArray[current] = new Card(rank, suit);
                current++;
            }
        }
        deckList = new ArrayList<>(Arrays.asList(deckArray));
        Collections.shuffle(deckList);
    }

    @Override
    public String toString(){
        for(Card c : deckList){
            System.out.println(c);
        }
        return "";
    }

    public void serialize() throws Exception{
        String file = "deck.ser";

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    public Deck unserialize(String file) throws Exception{
        Deck deck = new Deck();

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Deck)ois.readObject();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

        //Serializable Testing
        /*System.out.println(deck);
        System.out.println("----------------------------");

        try{
            deck.serialize();
        }
        catch(Exception e){
            System.out.println("exception");
        }

        deck.newDeck();
        System.out.println(deck);
        System.out.println("----------------------------");

        try{
            deck = deck.unserialize("deck.ser");
        }
        catch(Exception e){
            System.out.println("exception");
        }

        System.out.println(deck);*/
    }
}
