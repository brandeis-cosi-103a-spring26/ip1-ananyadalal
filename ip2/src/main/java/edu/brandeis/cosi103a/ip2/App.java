package edu.brandeis.cosi103a.ip2;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Deck deck = initializeDeck();
        Player p1 = new Player(deck);
        Player p2 = new Player(deck);

        boolean turn = Math.random() < 0.5; // Random starting player
        System.out.println((turn ? "Player 1" : "Player 2") + " starts the game.");

        while(deck.hasFrameworks()){
            Player current = turn ? p1 : p2;
            List<String> affordableCards = new ArrayList<>();
            int cryptoValue = current.getCryptoValueInHand();
            
            if (cryptoValue >= getCost("Method")) affordableCards.add("Method");
            if (cryptoValue >= getCost("Module")) affordableCards.add("Module");
            if (cryptoValue >= getCost("Framework")) affordableCards.add("Framework");
            if (cryptoValue >= getCost("Bitcoin")) affordableCards.add("Bitcoin");
            if (cryptoValue >= getCost("Ethereum")) affordableCards.add("Ethereum");
            if (cryptoValue >= getCost("Dogecoin")) affordableCards.add("Dogecoin");
            
            if (!affordableCards.isEmpty()) {
                int index = (int)(Math.random() * affordableCards.size());
                String cardToBuy = affordableCards.get(index);
                
                if(buy(deck, current, cardToBuy)) {
                    System.out.println((turn ? "Player 1" : "Player 2") + " bought " + cardToBuy);
                }
            }
            
            cleanup(current);
            turn = !turn;
        }

        System.out.println("Game over: No more frameworks left.");

        // Calculate scores
        int p1Score = 0;
        for (Card c : p1.playerDeck) {
            if (c.getType() == Card.Type.AUTOMATION) {
                p1Score += c.getValue();
            }
        }
        int p2Score = 0;
        for (Card c : p2.playerDeck) {
            if (c.getType() == Card.Type.AUTOMATION) {
                p2Score += c.getValue();
            }
        }

        // Determine winner
        if (p1Score > p2Score) {
            System.out.println("Player 1 wins with " + p1Score + " points!");
        } else if (p2Score > p1Score) {
            System.out.println("Player 2 wins with " + p2Score + " points!");
        } else {
            System.out.println("It's a tie with " + p1Score + " points!");
        }
    }

    public static Deck initializeDeck() {
        return new Deck();
    }

    public static boolean buy(Deck deck, Player player, String cardName) {
        int cost = getCost(cardName);
        if (cost == Integer.MAX_VALUE) {
            return false; // invalid card name
        }

        int totalValue = 0;
        List<Card> cryptoCards = new ArrayList<>();
        for (Card c : player.hand) {
            if (c.getType() == Card.Type.CRYPTOCURRENCY) {
                totalValue += c.getValue();
                cryptoCards.add(c);
            }
        }

        if (totalValue >= cost) {
            Card bought = deck.drawCard(cardName);
            if (bought != null) {
                player.playerDeck.add(bought);
                return true;
            }
        }
        return false;
    }

    private static int getCost(String cardName) {
        switch (cardName) {
            case "Method":
                return 2;
            case "Module":
                return 5;
            case "Framework":
                return 8;
            case "Bitcoin":
                return 0;
            case "Ethereum":
                return 3;
            case "Dogecoin":
                return 6;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static void cleanup(Player player) {
        player.returnAllToDeck();
        player.shuffleDeck();
        for (int i = 0; i < 5; i++) {
            player.drawToHand();
        }
    }
}
