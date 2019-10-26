import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Declare Variables
        Scanner scanner = new Scanner(System.in);
        String nickname;
        String input;

        // Ask for name
        System.out.println("What is your name?");
        input = scanner.nextLine();
        nickname = input;

        // Start of Blackjack Game
        do {

            System.out.println("Welcome To Blackjack " + nickname);

            // initialize players and cards
            Player player = new Player(nickname);
            Player dealer = new Player("Dealer");
            Deck deck = new Deck();
            deck.shuffle();
            boolean gameOver = false;

            // give cards to the player
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            System.out.println(player.getHandAsString());

            // give cards to the dealer
            dealer.addCard(deck.draw());
            dealer.addCard(deck.draw());
            System.out.println(dealer.getHandAsString());

            // player's turn
            do {
                System.out.println("Would you like to hit or stay? Please type 'Hit' or 'Stay'");
                do {
                    input = scanner.nextLine();
                } while (!input.equalsIgnoreCase("Hit") && !input.equalsIgnoreCase("Stay"));

                // Hit
                if (input.equalsIgnoreCase("Hit")) {
                    player.addCard(deck.draw());
                    System.out.println(player.getNickname() + " drew a card.");
                    System.out.println();
                    System.out.println(player.getHandAsString());
                    if (player.getHandSum() > 21) {
                        System.out.println(
                                "BUST! You Hit and got a total of " + player.getHandSum() + ". Dealer wins!");
                        gameOver = true;
                    }
                }
                // STAY
                if (input.equalsIgnoreCase("stay")) {
                    System.out.println("You have chosen to stay. Your hand: " + player.getHandSum());
                }

            } while (input.equalsIgnoreCase("Hit") && !gameOver);

            // dealer's turn
            if (!gameOver) {
                System.out.println();
                System.out.println("Dealers turn:");
                System.out.println();
                System.out.println(dealer.getHandAsString());
            }

            while (!gameOver) {

                if (dealer.getHandSum() <= 16) {
                    // DRAW CARD
                    dealer.addCard(deck.draw());
                    System.out.println(dealer.getNickname() + " drew another card");
                    System.out.println(dealer.getHandAsString());
                    if (dealer.getHandSum() == 21) {
                        System.out.println("Blackjack! Dealer won.");
                        gameOver = true;
                    }
                    if (dealer.getHandSum() > 21) {
                        System.out.println("BUST! Dealer Hit and got a total of " + dealer.getHandSum() + ". "
                                + "Congrats! You win!");
                        gameOver = true;
                    }

                } else {
                    // STAY
                    System.out.println("Dealer has chosen to stay!");
                    System.out.println();
                    int totalDealerSum = dealer.getHandSum();
                    int totalPlayerSum = player.getHandSum();

                    if (totalDealerSum > totalPlayerSum) {
                        System.out.println("Both players has decided to stay. The winner is " + dealer.getNickname()
                                + " with a total of " + totalDealerSum + ".");
                    } else {
                        System.out.println("Both players has decided to stay. The winner is " + player.getNickname()
                                + " with a total of " + totalPlayerSum + ".");
                    }
                    gameOver = true;
                }

            }

            // ask for new game
            System.out.println();
            System.out.println("Would you like to start a new game?  'Yes/No' :");
            do {
                input = scanner.nextLine();
            } while (!input.equalsIgnoreCase("Yes") && !input.equalsIgnoreCase("No"));

        } while (input.equalsIgnoreCase("Yes"));

        // tidy up
        scanner.close();
    }

}