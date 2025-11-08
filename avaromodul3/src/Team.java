public class Team {
    String teamName;
    int gamesPlayed;
    int wins;
    int losses;
    double totalPoints;
    double totalAssists;
    double totalRebounds;
    double ppg;
    double apg;
    double rpg;
    PlayerList players;
    Team next;
    Team prev;

    public Team(String teamName, int gamesPlayed, int wins, int losses, double totalPoints, double totalAssists, double totalRebounds) {
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.totalPoints = totalPoints;
        this.totalAssists = totalAssists;
        this.totalRebounds = totalRebounds;
        this.players = new PlayerList();
        this.next = null;
        this.prev = null;
    }
}