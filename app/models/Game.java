package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Class that represents a game entity.
 */
@Entity
public class Game extends Model {

    /**
     * The user whose game this is.
     */
    @ManyToOne
    public User owner;

    /**
     * List of all athletes that exist in this game.
     */
    @OneToMany
    public List<Athlete> athleteList;

    /**
     * Primary db key for game.
     */
    @Id
    public Long id;

    /**
     * Finder for team.
     */
    public static Model.Finder<Long,Team> find = new Model.Finder<Long,Team>(Long.class, Team.class);

    /**
     * Constructor for game.
     * @param owner The User whose game this is.
     */
    public Game(User owner) {
        this.owner = owner;
    }

    /**
     * Method that creates an instance of Game and saves it to the db.
     * @param ownerId The id of the user whose game is being created.
     * @return The Game. (You just lost it hehe)
     */
    public static Game create(Long ownerId) {
        Game game = new Game(User.find.ref(ownerId));
        game.save();
        return game;
    }

}
