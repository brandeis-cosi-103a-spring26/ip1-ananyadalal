package edu.brandeis.cosi103a.ip2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    List<Card> hand;
    List<Card> playerDeck;

    public Player(Deck deck) {
        hand = new ArrayList<>();
        playerDeck = new ArrayList<>();
        
        // Draw 7 Bitcoins
        for (int i = 0; i < 7; i++) {
            Card card = deck.drawCard("Bitcoin");
            if (card != null) {
                playerDeck.add(card);
            }
        }
        
        // Draw 3 Methods
        for (int i = 0; i < 3; i++) {
            Card card = deck.drawCard("Method");
            if (card != null) {
                playerDeck.add(card);
            }
        }
    }

    // Draw a card from playerDeck into the hand
    public Card drawToHand() {
        if (!playerDeck.isEmpty()) {
            Card card = playerDeck.remove(0);
            hand.add(card);
            return card;
        }
        return null;
    }

    // Return all cards from the hand into the playerDeck
    public void returnAllToDeck() {
        playerDeck.addAll(hand);
        hand.clear();
    }
    

    // Shuffle the playerDeck
    public void shuffleDeck() {
        Collections.shuffle(playerDeck);
    }

    // Get the total value of cryptocurrency cards in hand
    public int getCryptoValueInHand() {
        int total = 0;
        for (Card c : hand) {
            if (c.getType() == Card.Type.CRYPTOCURRENCY) {
                total += c.getValue();
            }
        }
        return total;
    }
}
