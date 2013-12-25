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
     * Constructor for team that includes an owner.
     */
    public Team(User owner, String name) {
        // init owner.
        if(owner != null) {
            this.owner = owner;
        }

        this.name = name;
    }
}
