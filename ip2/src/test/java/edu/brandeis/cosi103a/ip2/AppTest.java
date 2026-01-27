package edu.brandeis.cosi103a.ip2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testGetCost() {
        assertEquals(2, App.getCost("Method"));
        assertEquals(5, App.getCost("Module"));
        assertEquals(8, App.getCost("Framework"));
        assertEquals(0, App.getCost("Bitcoin"));
        assertEquals(3, App.getCost("Ethereum"));
        assertEquals(6, App.getCost("Dogecoin"));
        assertEquals(Integer.MAX_VALUE, App.getCost("Invalid"));
    }

    @Test
    public void testInitializeDeck() {
        Deck deck = App.initializeDeck();
        assertTrue(deck.hasFrameworks());
        // Check that we can draw some cards
        Card method = deck.drawCard("Method");
        assertEquals("Method", method.getName());
        assertEquals(Card.Type.AUTOMATION, method.getType());
    }

    @Test
    public void testBuy() {
        Deck deck = App.initializeDeck();
        Player player = new Player(deck);
        player.hand.clear();
        player.hand.add(new Card("Bitcoin", Card.Type.CRYPTOCURRENCY, 0, 1));
        player.hand.add(new Card("Bitcoin", Card.Type.CRYPTOCURRENCY, 0, 1));

        // Try to buy a Method (cost 2) - should succeed
        boolean bought = App.buy(deck, player, "Method");
        assertTrue(bought);
        
        
        // Player deck should have the bought Method
        boolean hasMethod = false;
        for (Card c : player.playerDeck) {
            if ("Method".equals(c.getName())) {
                hasMethod = true;
                break;
            }
        }
        assertTrue(hasMethod);
    }

    @Test
    public void testBuyInsufficientFunds() {
        Deck deck = App.initializeDeck();
        Player player = new Player(deck);
        
        // Clear some crypto to reduce value
        player.hand.clear();
        // Add only 1 Bitcoin
        player.hand.add(new Card("Bitcoin", Card.Type.CRYPTOCURRENCY, 0, 1));
        
        // Try to buy Module (cost 5) - should fail
        boolean bought = App.buy(deck, player, "Module");
        assertFalse(bought);
    }

    @Test
    public void testCleanup() {
        Deck deck = App.initializeDeck();
        Player player = new Player(deck);
        
        // Add some cards to hand
        player.hand.add(new Card("Test", Card.Type.AUTOMATION, 0, 0));
        
        // Cleanup: return all to deck, shuffle, draw 5
        App.cleanup(player);
        
        
        // drawToHand adds to hand, so hand should have up to 5 cards
        assertTrue(player.hand.size() <= 5);
    }
}
