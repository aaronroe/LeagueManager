package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
     */
    public Athlete(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    /**
     * Creates a new athlete.
     * @param name The name of the athlete.
     * @param teamId The id of the team that this athlete is a part of.
     * @return The new athlete.
     */
    public static Athlete create(String name, Long teamId) {
        Athlete athlete = new Athlete(name, Team.find.ref(teamId));
        athlete.save();
        return athlete;
    }

}
