package models.athlete;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class that represents a solo queue division.
 */
@Entity
public class SoloQueueRating extends Model {
    /**
     * Primary db key for player.
     */
    @Id
    public Long id;

    /**
     * Division included in the rating.
     */
    public Division division;

    /**
     * Number within the division.
     */
    public int divisionNum;

    /**
     * Division enum to represent divisions bronze, silver, ...
     */
    public enum Division {
        Bronze, Silver, Gold, Platinum, Diamond, Challenger
    }

    /**
     * Constructor for a SoloQueue Rating. Takes in a division and a division number.
     * @param division The division (e.g. Division.bronze)
     * @param divisionNum The division number (e.g. 2)
     */
    public SoloQueueRating(Division division, int divisionNum) {
        this.division = division;
        this.divisionNum = divisionNum;
    }

    /**
     * Creates a solo queue rating and saves it to the database.
     * @param division The division to create the rating for.
     * @param divisionNum The number within the division.
     * @return The new solo queue rating.
     */
    public static SoloQueueRating create(Division division, int divisionNum) {
        SoloQueueRating rating = new SoloQueueRating(division, divisionNum);
        rating.save();
        return rating;
    }

    /**
     * Gets a string representation of a solo queue rating.
     * @return The string representation of a solo queue rating.
     */
    public String toString() {
        return "" + this.division + divisionNum;
    }
}
