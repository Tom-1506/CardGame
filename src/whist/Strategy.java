package whist;
import cards.Card;
import cards.Hand;
/**
*
* Programming 2 coursework file
* @author ajb
 */
public interface Strategy {
/**
 * Choose a card from hand h to play in trick t 
 * @param h
 * @param t
 * @return 
 */    
    Card chooseCard(Hand h, Trick t);

/**
 * Update internal memory to include completed trick c
 * @param c 
 */    
    void updateData(Trick c);
}
