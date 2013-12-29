package models.athlete;

import models.Game;
import models.Team;
import models.User;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

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
     * The name of the player.
     */
    public String name;

    /**
     * Finder for Athlete.
     */
    public static Model.Finder<Long, Athlete> find = new Model.Finder<Long, Athlete>(Long.class, Athlete.class);

    /**
     * Basic constructor for athlete.
     * @param name The name of the athlete.
     * @param team The team the athlete belongs to.
     * @param whichGame The game that this athlete resides in.
     */
    public Athlete(String name, Team team, Game whichGame) {
        this.whichGame = whichGame;
        this.name = name;
        this.team = team;
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
    public static Athlete create(String name, Long teamId, Game game) {
        Athlete athlete = new Athlete(name, Team.find.ref(teamId), game);
        athlete.save();
        return athlete;
    }

    /**
     * Creates a new athlete that does not belong to any team.
     * @param name The name of the athlete.
     * @param game The game that the athlete resides in.
     * @return The new athlete.
     */
    public static Athlete create(String name, Game game) {
        Athlete athlete = new Athlete(name, null, game);
        athlete.save();
        return athlete;
    }

}
