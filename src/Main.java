import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static final int DECK_SIZE = 52;

    public static void main(String[] args) {

        final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};

        int player1Score = 0;
        int player2Score = 0;

        Stack<Node> deck = new Stack<>();
        Stack<Node> player1hand = new Stack<>();
        Stack<Node> player2hand = new Stack<>();
        Stack<Node> player1winnings = new Stack<>();
        Stack<Node> player2winnings = new Stack<>();

        fillDeck(deck);
        shuffle(deck);
        deal(deck, player1hand, player2hand);

        while (!(player1hand.isEmpty() && player2hand.isEmpty())) {
            int card_1 = (int) player1hand.pop();
            int card_2 = (int) player2hand.pop();

            System.out.println("******************************************************************");
            System.out.println("Player #1's card: " + RANKS[card_1 % 13] + " of " + SUITS[card_1 / 13]);
            System.out.println("Player #2's card: " + RANKS[card_2 % 13] + " of " + SUITS[card_2 / 13]);

            int winner = compare(card_1, card_2);

            if (winner == card_1) {
                player1winnings.push(card_1);
                player1winnings.push(card_2);
                player1Score++;

            } else if (winner == card_2) {
                player2winnings.push(card_1);
                player2winnings.push(card_2);
                player2Score++;
            }


            if (winner != -1) {
                System.out.println("Winner: " + RANKS[winner % 13] + " of " + SUITS[winner / 13]);
            } else {
                System.out.println("Tie!");
            }


            System.out.println("\nPlayer #1's score: " + player1Score + " points.");
            System.out.println("Player #2's score: " + player2Score + " points.");
            System.out.println("******************************************************************");

        }

        System.out.println("******************************************************************");

        if (player1Score > player2Score) {
            System.out.println("The winner is Player #1!");
        } else if (player2Score > player1Score) {
            System.out.println("The winner is Player #2!");
        } else {
            System.out.println("The match results in a tie!");
        }

        System.out.println();

        int cardCounter = 0;

        System.out.println("Player #1's winnings: ");

        while (!player1winnings.isEmpty()) {
            int card = (int)player1winnings.pop();
            System.out.print(RANKS[card % 13] + " of " + SUITS[card / 13] + ", ");
            cardCounter++;

            if (cardCounter % 8 == 0) {
                System.out.println();
            }
        }

        System.out.println("\n");
        cardCounter = 0;

        System.out.println("Player #2's winnings: ");
        while (!player2winnings.isEmpty()) {

            int card = (int)player2winnings.pop();
            System.out.print(RANKS[card % 13] + " of " + SUITS[card / 13] + ", ");
            cardCounter++;

            if (cardCounter % 8 == 0) {
                System.out.println();
            }

        }
        System.out.println();



    }

    static void shuffle(Stack deck) {

        Integer[] array = new Integer[DECK_SIZE];
        int card = 0;

        while (!deck.isEmpty()) {

            array[card] = (Integer) deck.pop();
            card++;

        }

        Random rand = ThreadLocalRandom.current();

        for (Integer i : array) {
            int index = rand.nextInt(i + 1);

            //Swap
            int swap = array[index];
            array[index] = array[i];
            array[i] = swap;
        }

        for (Integer i : array) {
            deck.push(i);
        }

    }

    static void fillDeck(Stack deck) {

        for (int i = 0; i < DECK_SIZE; i++) {
            deck.push(i);
        }

    }

    static void deal(Stack deck, Stack hand_1, Stack hand_2) {

        int card = 0;

        while (!deck.isEmpty()) {
            hand_1.push(deck.pop());
            hand_2.push(deck.pop());
        }

    }

    static int compare(int card_1, int card_2) {

        if (card_1 % 13 > card_2 % 13) {
            return card_1;
        } else if (card_1 % 13 < card_2 % 13) {
            return card_2;
        }
        return -1;

    }
}
