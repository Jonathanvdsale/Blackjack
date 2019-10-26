//TODO: Put ACE functionality into a Dealer subclass and add JAVADOC style comments

import java.util.Scanner;


public class Main {
    //Allows player to choose between 1 or 11 for the value of ACE
    int userChoiceForAV;
    static int aceValueforPlayer=0;
    static int aceValueforDealer=0;
    private static void ACEValueForPlayer(Card card){
    Face face=card.getFace();
    Scanner read=new Scanner(System.in);
    if(face.getValue()==1){
        System.out.println("You got an ACE. What value of ACE would you like to have, 1 or 11? ");
        aceValueforPlayer+=read.nextInt();
        aceValueforPlayer--;
    }
    }
    private static void ACEValueForDealer(Card card){
    Face face=card.getFace();
    if(face.getValue()==1)
            aceValueforDealer+=11;
    }
    
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
            Card card=deck.draw();
            ACEValueForPlayer(card);
            player.addCard(card);
            card=deck.draw();
            ACEValueForPlayer(card);
            player.addCard(card);
            System.out.println(player.getHandAsString());
            
            // give cards to the dealer
            card=deck.draw();
            ACEValueForDealer(card);
            dealer.addCard(card);
            card=deck.draw();
            ACEValueForDealer(card);
            dealer.addCard(card);
            System.out.println(dealer.getHandAsString());
            if ((player.getHandSum()+aceValueforPlayer) == 21) {
                        System.out.println("Blackjack! You won.");
                        gameOver = true;
                    }
            else if ((dealer.getHandSum()+aceValueforDealer) == 21) {
                        System.out.println("Blackjack! Dealer won.");
                        gameOver = true;
                    }
            
            // player's turn
            do {
                System.out.println("Would you like to hit or stay? Please type 'Hit' or 'Stay'");
                do {
                    input = scanner.nextLine();
                } while (!input.equalsIgnoreCase("Hit") && !input.equalsIgnoreCase("Stay"));
               
                // Hit
                if (input.equalsIgnoreCase("Hit")) {
                    card=deck.draw();
                    ACEValueForPlayer(card);
                    player.addCard(card);
                    System.out.println(player.getNickname() + " drew a card.");
                    System.out.println();
                    System.out.println(player.getHandAsString());
                    if ((player.getHandSum()+aceValueforPlayer) == 21) {
                        System.out.println("Blackjack! You won.");
                        gameOver = true;
                    }
                    if ((player.getHandSum()+aceValueforPlayer) > 21) {
                        System.out.println(
                                "BUST! You Hit and got a total of " + (player.getHandSum()+aceValueforPlayer) + ". Dealer wins!");
                        gameOver = true;
                    }
                }
                // Stay
                if (input.equalsIgnoreCase("stay")) {
                    System.out.println("You have chosen to stay. Your hand: " + (player.getHandSum()+aceValueforPlayer));
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
                if ((dealer.getHandSum()+aceValueforDealer) <= 16) {
                    // DRAW CARD
                    dealer.addCard(deck.draw());
                    System.out.println(dealer.getNickname() + " drew another card");
                    System.out.println(dealer.getHandAsString());
                    if ((dealer.getHandSum()+aceValueforDealer) == 21) {
                        System.out.println("Blackjack! Dealer won.");
                        gameOver = true;
                    }
                    if ((dealer.getHandSum()+aceValueforDealer) > 21) {
                        System.out.println("BUST! Dealer Hit and got a total of " + (dealer.getHandSum()+aceValueforDealer) + ". "
                                + "Congrats! You win!");
                        gameOver = true;
                    }
                } else {
                    // STAY
                    System.out.println("Dealer has chosen to stay!");
                    System.out.println();
                    int totalDealerSum = dealer.getHandSum()+aceValueforPlayer;
                    int totalPlayerSum = player.getHandSum()+aceValueforDealer;

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
