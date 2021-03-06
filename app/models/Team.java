package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Game team entity.
 */
@Entity
public class Team extends Model {

    /**
     * Primary db key for team.
     */
    @Id
    private Long id;

    /**
     * The game that this team exists in.
     */
    @ManyToOne
    private Game whichGame;

    /**
     * The name of the team.
     */
    private String name;

    /**
     * Gets the name of the logo corresponding to this team.
     * @return The name of the logo corresponding to this team.
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Gets the name of the team.
     * @return The name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the game that this team belongs to.
     * @return The game this team belongs to.
     */
    public Game getWhichGame() {
        return whichGame;
    }

    /**
     * Gets the database id of the team.
     * @return The database id of the team.
     */
    public Long getId() {
        return id;
    }

    /**
     * The name of the logo icon.
     */
    private String logo;

    /**
     * Finder for team.
     */
    public static Model.Finder<Long,Team> find = new Model.Finder<Long,Team>(Long.class, Team.class);

    /**
     * Constructor for team that includes an owner.
     * @param whichGame The game this team is being created in.
     * @param name The name of the team.
     * @param logo The name of the logo that the team uses.
     */
    public Team(Game whichGame, String name, String logo) {
        this.whichGame = whichGame;
        this.name = name;
        this.logo = logo;
    }

    /**
     * Method to get the game object for a particular user.
     * @param user The user whose game we are looking up.
     * @return The game belonging to user
     */
    public static Team findTeamOf(User user) {
        return Game.find.where().eq("owner", user).findUnique().getUserTeam();
    }

    /**
     * Method to get the list of Teams that belong to a particular game.
     * @param game The game to look up.
     * @return The list of teams belonging to a game.
     */
    public static List<Team> findTeamsOf(Game game) {
        return Team.find.where().eq("whichGame", game).findList();
    }

    /**
     * Creates an instance of Team and saves it to the db.
     * @param whichGame The game this team is being created in.
     * @param name The name of the team.
     * @param logo The filename of the logo that the team uses.
     * @return The team object.
     */
    public static Team create(Game whichGame, String name, String logo) {
        Team team = new Team(whichGame, name, logo);
        team.save();
        return team;
    }

    /**
     * Gets the compound salary of the team.
     * @return The compound salary of the team.
     */
    public int getTotalPlayerSalary() {
        int totalSalary = 0;

        List<Athlete> athleteList = Athlete.findAthletesOf(this);

        for (Athlete athlete : athleteList) {
            totalSalary += athlete.getSalary();
        }

        return totalSalary;
    }

    public int getStreamingProfits() {
        return 400;
    }

    public int getNetProfit() {
        return getStreamingProfits() - getTotalPlayerSalary();
    }

}
