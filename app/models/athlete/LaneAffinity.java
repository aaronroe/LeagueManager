package models.athlete;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A Player's affinity for a certain lane.
 */
@Entity
public class LaneAffinity extends Model {

    /**
     * The db key for a lane affinity.
     */
    @Id
    public Long id;

    /**
     * The name of the lane.
     */
    private String laneName;

    /**
     * The strength of the affinity to the lane.
     */
    private Double strength;

    /**
     * Gets the rounded strength of the lane affinity.
     * @return The strength of the lane affinity.
     */
    public Long getRoundedStrength() {
        return Math.round(strength);
    }

    /**
     * Gets the precise strength of the lane affinity.
     * @return The precise strength of the lane affinity.
     */
    public Double getExactStrength() {
        return strength;
    }

    /**
     * Setter for the strength of the affinity.
     * @param strength The new strength of the lane affinity.
     */
    public void setStrength(Double strength) {
        this.strength = strength;
        this.save();
    }

    /**
     * Constructor for lane affinity.
     * @param laneName The name of the lane.
     * @param strength The strength of the lane affinity.
     */
    public LaneAffinity(String laneName, Double strength) {
        this.laneName = laneName;
        this.strength = strength;
    }

    /**
     * Static method for encapsulating construction of a lane affinity
     * @param laneName The name of the lane.
     * @param strength The strength of the affinity with the lane.
     * @return
     */
    public static LaneAffinity create(String laneName, Double strength) {
        LaneAffinity laneAffinity = new LaneAffinity(laneName, strength);
        laneAffinity.save();
        return laneAffinity;
    }

}
