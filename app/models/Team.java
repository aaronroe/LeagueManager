package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Game team entity.
 */
@Entity
public class Team extends Model {

    /**
     * Primary db key for team.
     */
    @Id
    public Long id;

    /**
     * The name of the team.
     */
    public String name;

    /**
     * The name of the logo icon.
     */
    public String logo;

    /**
     * Finder for team.
     */
    public static Model.Finder<Long,Team> find = new Model.Finder<Long,Team>(Long.class, Team.class);

    /**
     * Constructor for team that includes an owner.
     * @param name The name of the team.
     * @param logo The name of the logo that the team uses.
     */
    public Team(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    /**
     * Method to get the game object for a particular user.
     * @param user The user whose game we are looking up.
     * @return The game belonging to user
     */
    public static Team findTeamOf(User user) {
        return Game.find.where().eq("owner", user).findUnique().userTeam;
    }

    /**
     * Creates an instance of Team and saves it to the db.
     * @param name The name of the team.
     * @param logo The filename of the logo that the team uses.
     * @return The team object.
     */
    public static Team create(String name, String logo) {
        Team team = new Team(name, logo);
        team.save();
        return team;
    }

}
