import java.util.*;

class Player {
    private String name;
    private List<Integer> scores;

    public Player(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
    }

    public int rollDice() {
        int roll = new Random().nextInt(6) + 1;
        scores.add(roll);
        return roll;
    }

    public int getTotalScore() {
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getScores() {
        return scores;
    }
}

class DiceGame {
    private int numPlayers;
    private int numRounds;
    private List<Player> players;

    public DiceGame(int numPlayers, int numRounds) {
        this.numPlayers = numPlayers;
        this.numRounds = numRounds;
        this.players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i));
        }
    }

    public void playGame() {
        System.out.println("\n🎲 Starting Dice Game with " + numPlayers + " players for " + numRounds + " rounds...\n");

        for (int round = 1; round <= numRounds; round++) {
            System.out.println("-- Round " + round + " --");
            for (Player player : players) {
                int roll = player.rollDice();
                System.out.println(player.getName() + " rolled a " + roll);
            }
            System.out.println();
        }
    }

    public void showScoreboard() {
        System.out.println("📊 Final Scoreboard:");
        System.out.printf("%-10s", "Player");
        for (int i = 1; i <= numRounds; i++) {
            System.out.printf(" Round%-3d", i);
        }
        System.out.printf(" Total\n");

        for (Player player : players) {
            System.out.printf("%-10s", player.getName());
            for (int score : player.getScores()) {
                System.out.printf(" %-8d", score);
            }
            System.out.printf(" %-5d\n", player.getTotalScore());
        }
    }

    public void declareWinner() {
        int maxScore = players.stream().mapToInt(Player::getTotalScore).max().orElse(0);

        List<String> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getTotalScore() == maxScore) {
                winners.add(player.getName());
            }
        }

        System.out.println("\n🏆 Winner(s): " + String.join(", ", winners) + " with " + maxScore + " points!");
    }
}

public class DiceGameApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();

        System.out.print("Enter number of rounds: ");
        int numRounds = scanner.nextInt();

        DiceGame game = new DiceGame(numPlayers, numRounds);
        game.playGame();
        game.showScoreboard();
        game.declareWinner();

        scanner.close();
    }
}
