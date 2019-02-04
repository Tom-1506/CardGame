package cards;
import java.io.*;
import java.util.*;

public class Deck implements Iterable<Card>, Serializable{
    static final long serialVersionUID = 49;

    //arrayList to hold the 52 cards of the deck
    public ArrayList<Card> deckList;

    //Constructor creates array of 52 Cards and generates every card of each rank and suit
    //it then puts the contents of the array into the deckList arrayList and shuffles the deck
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

    //method defines DeckIterator to iterate through deck in order
    public Iterator<Card> iterator(){return new DeckIterator();}

    //definitions for DeckIterator
    public class DeckIterator implements Iterator {
        int currentPos = 0;

        //hasNext if the currentPos is not the end of the deck
        @Override
        public boolean hasNext(){
            if(currentPos < deckList.size()){
                return true;
            }
            return false;
        }

        //next card if there is a next card
        @Override
        public Card next(){
            if(this.hasNext()){
                return deckList.get(currentPos++);
            }
            return null;
        }
    }

    //method defines spadeIterator to iterate through the spades of a deck in order
    public Iterator<Card> spadeIterator(){return new SpadeIterator();}

    //definitions for SpadeIterator
    public class SpadeIterator implements Iterator{
        int currentPos = 0;

        //hasNext if there is another card later in the deck of suit SPADES
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

        //if there is a next card, return next spade
        @Override
        public Card next(){
            if(this.hasNext()){
                return deckList.get(currentPos++);
            }
            return null;
        }
    }

    //use iterator to return the top card off of the deck
    public Card deal(Iterator<Card> itr){
        Card nextCard = itr.next();
        return nextCard;
    }

    //returns number of cards in the deck
    public int size() {
        int cardCount = 0;
        for(Card c : deckList){
            cardCount++;
        }
        return cardCount;
    }

    //remakes and reshuffles a deck
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

    //toString made simply for testing that prints the whole deck
    @Override
    public String toString(){
        for(Card c : deckList){
            System.out.println(c);
        }
        return "";
    }

    //serialize method that defines output stream and serializes to deck.ser
    public void serialize() throws Exception{
        String file = "deck.ser";

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    //unserialize method that defines input from String file and reads into Deck object
    public Deck unserialize(String file) throws Exception{
        Deck deck = new Deck();

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Deck)ois.readObject();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

        //Test SpadeIterator
        Iterator<Card> spadeItr = deck.spadeIterator();
        System.out.println("Should print all spade cards in the deck:");
        while(spadeItr.hasNext()){
            System.out.println(spadeItr.next());
        }

        //Test Deal, this also tests deck iterator
        Iterator<Card> itr = deck.iterator();
        System.out.println("\nShould return the top card of the deck: " + deck.deal(itr));

        //Test size
        System.out.println("\nShould return size of deck 52: " + deck.size());

        //Serializable Testing also tests newDeck
        Deck deckSerial = new Deck();
        System.out.println("\nThis will print 3 decks, the first being the one I will serialize ,the \n" +
                "next being a newly shuffled deck and finally the first deck unserialised and read \n" +
                "back into the deck object.\n");

        System.out.println(deckSerial);
        System.out.println("----------------------------");

        try{
            deckSerial.serialize();
        }
        catch(Exception e){
            System.out.println("exception");
        }

        deckSerial.newDeck();
        System.out.println(deckSerial);
        System.out.println("----------------------------");

        try{
            deckSerial = deckSerial.unserialize("deck.ser");
        }
        catch(Exception e){
            System.out.println("exception");
        }

        System.out.println(deckSerial + "\n");
    }
}
