package edu.brandeis.cosi103a.ip2;

public class Card {
    public enum Type {
        AUTOMATION,
        CRYPTOCURRENCY
    }

    private String name;
    private Type type;
    private int cost;
    private int value;

    public Card(String name, Type type, int cost, int value) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " (" + type + ", cost: " + cost + ", value: " + value + ")";
    }
}