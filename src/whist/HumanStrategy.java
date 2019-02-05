package whist;
import cards.Card;
import cards.Hand;
import java.util.Scanner;

public class HumanStrategy implements Strategy{
    private boolean chosen = false;
    private Card choice;

    public HumanStrategy(){
    }

    /**
     * gives a human the choice over what card to play in Trick t from their Hand h
     * @param h
     * @param t
     * @return the chosen Card
     */
    public Card chooseCard(Hand h, Trick t){
        chosen = false;
        while(!chosen){
            try{
                //initiate a Scanner
                Scanner scan = new Scanner(System.in);

                //print out the player hand with indexes to show what the player can choose from
                for (int i = 0; i < h.handList.size(); i++) {
                    System.out.println(i + ":  " + h.handList.get(i));
                }

                //prompt for card choice
                System.out.println("\nChoose the index of which Card you'd like to play");
                int i = scan.nextInt(); //take input

                if(i < 0 || i+1 > h.handList.size()){
                    System.out.println("\n--- invalid index, please try again ---\n");
                }
                else{
                    Card choice = h.handList.get(i); //get the chosen card
                    System.out.println("You play " + choice + "\n"); //tell player what card they chose
                    h.handList.remove(i); //remove chosen card from hand
                    return choice;
                }
            }catch (Exception e){
                System.out.println("\nException: " + e);
                System.out.println("\n--- invalid index, please try again ---\n");
            }
        }
        return null;
    }

    public void updateData(Trick t){

    }
}
