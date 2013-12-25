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
     * The owner of the team.
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
     * Constructor for team that does not include an owner.
     */
    public Team(String name) {
        this(null, name);
    }

    /**
     * Finder for team.
     */
    public static Model.Finder<Long,Team> find = new Model.Finder<Long,Team>(Long.class, Team.class);

    /**
     * Constructor for team that includes an owner.
     */
    public Team(User owner, String name) {
        // init owner.
        if(owner != null) {
            this.owner = owner;
        }

        this.name = name;
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
     * @return The team object.
     */
    public static Team create(Long ownerId, String name) {
        Team team = new Team(User.find.ref(ownerId), name);
        team.save();
        return team;
    }

}
