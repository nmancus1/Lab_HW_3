import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static final int DECK_SIZE = 52;

    public static void main(String[] args) {

        //String arrays representing suits and ranks of cards, the use of the % operator allows for the correct
        //distribution through the ranks for comparisons, e.g. (card % 13 = correct rank), and the / operator
        //allows for the correct indexing of the suits, e.g. (card / 13 = correct suit)
        final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};

        //Accumulator for player's scores and match number
        int player1Score = 0;
        int player2Score = 0;
        int matchNumber = 1;

        //Stacks, representing decks
        Stack<Node> deck = new Stack<>();
        Stack<Node> player1hand = new Stack<>();
        Stack<Node> player2hand = new Stack<>();
        Stack<Node> player1winnings = new Stack<>();
        Stack<Node> player2winnings = new Stack<>();

        //Set up game
        fillDeck(deck);
        shuffle(deck);
        deal(deck, player1hand, player2hand);
        System.out.println("******************************************************************");

        //War!!
        while (!(player1hand.isEmpty() && player2hand.isEmpty())) {

            System.out.println("Match #" + matchNumber++ + ":");

            //Place cards on table
            int card_1 = (int) player1hand.pop();
            int card_2 = (int) player2hand.pop();
            System.out.println("******************************************************************");
            System.out.println("Player #1's card: " + RANKS[card_1 % 13] + " of " + SUITS[card_1 / 13]);
            System.out.println("Player #2's card: " + RANKS[card_2 % 13] + " of " + SUITS[card_2 / 13]);

            //Compare cards
            int winner = compare(card_1, card_2);

            //If player one wins, they take the cards and get one point
            if (winner == card_1) {
                player1winnings.push(card_1);
                player1winnings.push(card_2);
                player1Score++;

                //If player two wins, they take the cards and get one point
            } else if (winner == card_2) {
                player2winnings.push(card_1);
                player2winnings.push(card_2);
                player2Score++;
            }


            //If it's not a tie, declare winner
            if (winner != -1) {
                System.out.println("Winner: " + RANKS[winner % 13] + " of " + SUITS[winner / 13]);

                //If not, it's a tie!
            } else {
                System.out.println("Tie!");
            }


            //Print points to console
            System.out.println("\nPlayer #1's score: " + player1Score + " points.");
            System.out.println("Player #2's score: " + player2Score + " points.");
            System.out.println("******************************************************************");

        }

        //Final results!
        System.out.println("FINAL RESULTS:");
        System.out.println("******************************************************************");

        //Declare winner of game
        if (player1Score > player2Score) {
            System.out.println("The winner is Player #1!");
        } else if (player2Score > player1Score) {
            System.out.println("The winner is Player #2!");
        } else {
            System.out.println("The match results in a tie!");      //or tie
        }

        //Print final scores
        System.out.println("\nPlayer #1's final score: " + player1Score + " points.");
        System.out.println("Player #2's final score: " + player2Score + " points.");

        System.out.println();

        //Accumulator to count number of cards printed to console to keep output tidy
        int cardCounter = 0;

        //Print player one's cards they've taken for winning to console
        System.out.println("Player #1's winnings: ");

        while (!player1winnings.isEmpty()) {

            int card = (int) player1winnings.pop();

            if (player1winnings.peek() != null) {
                System.out.print(RANKS[card % 13] + " of " + SUITS[card / 13] + ", ");
            } else {
                System.out.print(RANKS[card % 13] + " of " + SUITS[card / 13]); //if last card, no comma

            }

            cardCounter++;

            //Newline every eight cards
            if (cardCounter % 8 == 0) {
                System.out.println();
            }
        }

        System.out.println("\n");
        cardCounter = 0;                    //reset card counter

        //Print player two's cards to console they've taken for winning
        System.out.println("Player #2's winnings: ");
        while (!player2winnings.isEmpty()) {

            int card = (int) player2winnings.pop();

            if (player2winnings.peek() != null) {
                System.out.print(RANKS[card % 13] + " of " + SUITS[card / 13] + ", ");
            } else {
                System.out.print(RANKS[card % 13] + " of " + SUITS[card / 13]);//if last card, no comma

            }
            cardCounter++;

            //Newline every eight cards
            if (cardCounter % 8 == 0) {
                System.out.println();
            }

        }
        System.out.println();


    }

    /**
     * This method shuffles the deck, using an array and the Fisher-Yates shuffle
     *
     * @param deck the deck
     */
    static void shuffle(Stack deck) {

        //Build out array same size as stack of cards
        Integer[] array = new Integer[DECK_SIZE];
        int card = 0;                                   //Accumulator to count cards for array

        //Pop cards from stack to array
        while (!deck.isEmpty()) {

            array[card] = (Integer) deck.pop();
            card++;

        }

        //New random
        Random rand = ThreadLocalRandom.current();

        //Fisher-Yates shuffle
        for (Integer i : array) {
            int index = rand.nextInt(i + 1);

            //Swap
            int swap = array[index];        //value to swap
            array[index] = array[i];        //assign int @ i to swap's index
            array[i] = swap;                //assign i the value of swap
        }

        //Push cards back onto stack
        for (Integer i : array) {
            deck.push(i);
        }

    }

    /**
     * This method simply fills a stack with integers
     *
     * @param deck the deck
     */
    static void fillDeck(Stack deck) {

        for (int i = 0; i < DECK_SIZE; i++) {
            deck.push(i);
        }

    }

    /**
     * This method deals the cards from the deck to the player's hands
     *
     * @param deck   the deck
     * @param hand_1 player one's hand
     * @param hand_2 player two's hand
     */
    static void deal(Stack deck, Stack hand_1, Stack hand_2) {

        int card = 0;

        while (!deck.isEmpty()) {
            hand_1.push(deck.pop());
            hand_2.push(deck.pop());
        }

    }

    /**
     * This method compares two cards of integer value, using % 13,
     * representing the progression of ranks through the suits in a deck of cards.
     *
     * @param card_1 player one's card
     * @param card_2 player 2's card
     * @return winner's card or -1 if tie
     */
    static int compare(int card_1, int card_2) {

        if (card_1 % 13 > card_2 % 13) {
            return card_1;                          //player one
        } else if (card_1 % 13 < card_2 % 13) {
            return card_2;                          //player two
        }
        return -1;                                  //tie

    }
}
