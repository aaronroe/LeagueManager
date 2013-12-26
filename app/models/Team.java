package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Game team entity.
 */
@Entity
public class Team extends Model {

    /**
     * The User that owns this team.
     */
    @OneToOne
    public User owner;

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
    public String logoName;

    /**
     * Finder for team.
     */
    public static Model.Finder<Long,Team> find = new Model.Finder<Long,Team>(Long.class, Team.class);

    /**
     * Constructor for team that includes an owner.
     * @param owner The User that owns the team.
     * @param name The name of the team.
     * @param logoName The name of the logo that the team uses.
     */
    public Team(User owner, String name, String logoName) {
        // init owner.
        if(owner != null) {
            this.owner = owner;
        }

        this.name = name;
        this.logoName = logoName;
    }

    /**
     * Method to get a team from a user.
     * @param user The user whose team we are looking up.
     * @return The team of user
     */
    public static Team findTeamOf(User user) {
        return find.where().eq("owner", user).findUnique();
    }

    /**
     * Creates an instance of Team and saves it to the db.
     * @param ownerId The id corresponding to the owner's user id.
     * @param name The name of the team.
     * @param logoName The name of the logo that the team uses.
     * @return The team object.
     */
    public static Team create(Long ownerId, String name, String logoName) {
        Team team = new Team(User.find.ref(ownerId), name, logoName);
        team.save();
        return team;
    }

}
