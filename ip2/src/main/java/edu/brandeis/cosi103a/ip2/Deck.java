package edu.brandeis.cosi103a.ip2;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    public List<Card> methods;
    public List<Card> modules;
    public List<Card> frameworks;
    public List<Card> bitcoins;
    public List<Card> ethereums;
    public List<Card> dogecoins;

    public Deck() {
        methods = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            methods.add(new Card("Method", Card.Type.AUTOMATION, 2, 1));
        }

        modules = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            modules.add(new Card("Module", Card.Type.AUTOMATION, 5, 3));
        }

        frameworks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            frameworks.add(new Card("Framework", Card.Type.AUTOMATION, 8, 6));
        }

        bitcoins = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            bitcoins.add(new Card("Bitcoin", Card.Type.CRYPTOCURRENCY, 0, 1));
        }

        ethereums = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ethereums.add(new Card("Ethereum", Card.Type.CRYPTOCURRENCY, 3, 2));
        }

        dogecoins = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dogecoins.add(new Card("Dogecoin", Card.Type.CRYPTOCURRENCY, 6, 3));
        }
    }

    public Card drawCard(String cardName) {
        switch (cardName) {
            case "Method":
                if (!methods.isEmpty()) {
                    return methods.remove(0);
                }
                break;
            case "Module":
                if (!modules.isEmpty()) {
                    return modules.remove(0);
                }
                break;
            case "Framework":
                if (!frameworks.isEmpty()) {
                    return frameworks.remove(0);
                }
                break;
            case "Bitcoin":
                if (!bitcoins.isEmpty()) {
                    return bitcoins.remove(0);
                }
                break;
            case "Ethereum":
                if (!ethereums.isEmpty()) {
                    return ethereums.remove(0);
                }
                break;
            case "Dogecoin":
                if (!dogecoins.isEmpty()) {
                    return dogecoins.remove(0);
                }
                break;
        }
        return null; // No card available or invalid name
    }

    public boolean hasFrameworks() {
        return !frameworks.isEmpty();
    }
}