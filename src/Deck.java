import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Deck{

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

    public Iterator<Card> iterator(){
        return new DeckIterator();
    }

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

    public static void main(String[] args) {
        Deck deck = new Deck();

        for(Card c : deck.deckList){
            System.out.println(c);
        }

        deck.deal();
    }
}
