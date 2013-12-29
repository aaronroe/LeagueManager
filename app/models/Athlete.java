package models;

import models.athlete.SoloQueueRating;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Random;

/**
 * Game Athlete entity.
 */
@Entity
public class Athlete extends Model {

    /**
     * Primary db key for player.
     */
    @Id
    public Long id;

    /**
     * The team the player belongs to.
     */
    @ManyToOne
    public Team team;

    /**
     * The game that this team exists in.
     */
    @ManyToOne
    public Game whichGame;

    /**
     * The solo-queue rating that the player has.
     */
    public SoloQueueRating soloQueueRating;

    /**
     * The name of the player.
     */
    public String name;

    // begin attributes

    public int reflexes;

    public int concentration;

    public int handEyeCoordination;

    public int perception;

    public int intelligence;

    public int wits;

    public int resolution;

    // end attributes

    /**
     * Finder for Athlete.
     */
    public static Model.Finder<Long, Athlete> find = new Model.Finder<Long, Athlete>(Long.class, Athlete.class);

    /**
     * Basic constructor for athlete.
     * @param name The name of the athlete.
     * @param team The team the athlete belongs to.
     * @param whichGame The game that this athlete resides in.
     * @param soloQueueRating The solo queue rating of the player.
     */
    public Athlete(String name, Team team, Game whichGame, SoloQueueRating soloQueueRating) {
        this.whichGame = whichGame;
        this.name = name;
        this.team = team;
        this.soloQueueRating = soloQueueRating;

        // init random stats for the player.
        this.initRandomStats(5, 15);
    }

    /**
     * Simple helper method that initializes an athlete's stats randomly.
     * @param min The minimum stat value.
     * @param max The maximum stat value.
     */
    private void initRandomStats(int min, int max) {
        Random random = new Random();

        this.reflexes = random.nextInt(max-min) + min;
        this.concentration = random.nextInt(max-min) + min;
        this.handEyeCoordination = random.nextInt(max-min) + min;
        this.perception = random.nextInt(max-min) + min;
        this.intelligence = random.nextInt(max-min) + min;
        this.wits = random.nextInt(max-min) + min;
        this.resolution = random.nextInt(max-min) + min;
    }

    /**
     * Helper method that provides whether or not an athlete is on a team.
     * @return Whether or not the athlete is on a team.
     */
    public boolean isOnTeam() {
        return this.team != null;
    }

    /**
     * Gets all the athletes in the database.
     * @return A list of all the athletes.
     */
    public static List<Athlete> findAll() {
        return find.all();
    }

    /**
     * Finds athletes belonging to a particular team.
     * @param team The team to check the athletes in.
     * @return The list of athletes in the team.
     */
    public static List<Athlete> findAthletesOf(Team team) {
        return Athlete.find.where().eq("team", team).findList();
    }

    /**
     * Finds all athletes belonging to a particular team.
     * @param game The game to check for athletes in.
     * @return The list of athletes in the game.
     */
    public static List<Athlete> findAthletesOf(Game game) {
        return Athlete.find.where().eq("whichGame", game).findList();
    }

    /**
     * Finds all the athletes belonging to the game of a certain user.
     * @param user The user whose game to lookup in.
     * @return The list of athletes in the game belonging to the user.
     */
    public static List<Athlete> findAthletesInGameOf(User user) {
        return findAthletesOf(Game.findGameOf(user));
    }

    /**
     * Creates a new athlete.
     * @param name The name of the athlete.
     * @param teamId The id of the team that this athlete is a part of.
     * @return The new athlete.
     */
    public static Athlete create(String name, Long teamId, Game game, SoloQueueRating soloQueueRating) {
        Athlete athlete = new Athlete(name, Team.find.ref(teamId), game, soloQueueRating);
        athlete.save();
        return athlete;
    }

    /**
     * Creates a new athlete that does not belong to any team.
     * @param name The name of the athlete.
     * @param game The game that the athlete resides in.
     * @return The new athlete.
     */
    public static Athlete create(String name, Game game, SoloQueueRating soloQueueRating) {
        Athlete athlete = new Athlete(name, null, game, soloQueueRating);
        athlete.save();
        return athlete;
    }

}
