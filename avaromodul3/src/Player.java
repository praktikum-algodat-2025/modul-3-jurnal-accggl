public class Player {
    String name;
    int jerseyNumber;
    double totalPoints;
    double totalAssists;
    double totalRebounds;
    int gamesPlayed;
    double ppg;
    double apg;
    double rpg;
    Player next;
    Player prev;

    public Player(String name, int jerseyNumber, double totalPoints, double totalAssists, double totalRebounds, int gamesPlayed) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.totalPoints = totalPoints;
        this.totalAssists = totalAssists;
        this.totalRebounds = totalRebounds;
        this.gamesPlayed = gamesPlayed;
        this.next = null;
        this.prev = null;
    }
}
