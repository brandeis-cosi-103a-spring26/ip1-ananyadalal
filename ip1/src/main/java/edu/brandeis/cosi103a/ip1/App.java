package edu.brandeis.cosi103a.ip1;

import java.util.Random;
import java.util.Scanner;

public class App {
    private static final int TURNS_PER_PLAYER = 10;
    private static final int MAX_REROLLS = 2;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            playGame(in, new Random());
        }
    }

    private static int rollDie(Random rng) {
        return rng.nextInt(6) + 1;
    }

    private static void playGame(Scanner in, Random rng) {
        int[] scores = new int[2];

        for (int turn = 0; turn < TURNS_PER_PLAYER; turn++) {
            for (int player = 0; player < 2; player++) {
                takeTurn(player, turn, scores, in, rng);
            }
        }

        printFinalResults(scores);
    }

    private static void takeTurn(int player, int turn, int[] scores, Scanner in, Random rng) {
        System.out.println();
        System.out.printf("Player %d's turn (Turn %d of %d). Current scores — Player 1: %d, Player 2: %d%n",
                player + 1, turn + 1, TURNS_PER_PLAYER, scores[0], scores[1]);

        int roll = rollDie(rng);
        System.out.printf("You rolled: %d%n", roll);

        int rerolls = 0;
        while (rerolls < MAX_REROLLS) {
            if (!askReroll(in, MAX_REROLLS - rerolls)) {
                break;
            }
            roll = rollDie(rng);
            System.out.printf("You rolled: %d%n", roll);
            rerolls++;
        }

        scores[player] += roll;
        System.out.printf("End of turn: Player %d scored %d this turn. Total: %d%n", player + 1, roll, scores[player]);
    }

    private static boolean askReroll(Scanner in, int rerollsLeft) {
        System.out.printf("Rerolls left: %d. Reroll? (y/n): ", rerollsLeft);
        String line = in.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("No input received — ending turn.");
            return false;
        }
        char c = line.charAt(0);
        return c == 'y' || c == 'Y';
    }

    private static void printFinalResults(int[] scores) {
        System.out.println();
        System.out.printf("Final scores — Player 1: %d, Player 2: %d%n", scores[0], scores[1]);
        if (scores[0] > scores[1]) {
            System.out.println("Player 1 wins!");
        } else if (scores[1] > scores[0]) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
