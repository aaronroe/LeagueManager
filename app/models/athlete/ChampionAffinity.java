package models.athlete;

import controllers.Assets;
import models.game.Champion;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class that represents a (Champion Name, Value) affinity pair.
 */
@Entity
public class ChampionAffinity extends Model {

    /**
     * The db key for a champion affinity.
     */
    @Id
    private Long id;

    /**
     * The name of the champion that the affinity is for.
     * This is not a Champion object because we do not want to persist that in the database.
     */
    private String championName;

    /**
     * The strength of the affinity.
     */
    private Double strength;

    /**
     * Gets the name of the champion that the affinity is for.
     * @return the name of the champion.
     */
    public String getChampionName() {
        return championName;
    }

    /**
     * Gets the name of the champion icon image.
     * @return The name of the champion icon image.
     */
    public String getChampionIcon() {
        return "/assets/images/champions/icon_" + this.getChampionName().toLowerCase() + ".png";
    }

    /**
     * Gets the rounded strength of the champion affinity.
     * @return The strength of the champion affinity.
     */
    public Long getRoundedStrength() {
        return Math.round(strength);
    }

    /**
     * Gets the precise strength of the champion affinity.
     * @return The precise strength of the champion affinity.
     */
    public Double getExactStrength() {
        return strength;
    }

    /**
     * Setter for the strength of the affinity.
     * @param strength The new strength of the champion affinity.
     */
    public void setStrength(Double strength) {
        this.strength = strength;
        this.save();
    }

    /**
     * Constructor for champion affinity.
     * @param championName The name of the champion that the affinity is for.
     * @param strength The strength of the affinity.
     */
    public ChampionAffinity(String championName, Double strength) {
        this.championName = championName;
        this.strength = strength;
    }

    /**
     * Static method for encapsulating construction of a champion affinity.
     * @param championName The name of the champion the affinity is for.
     * @param strength The strength of the affinity.
     * @return A constructed champion affinity.
     */
    public static ChampionAffinity create(String championName, Double strength) {
        ChampionAffinity championAffinity = new ChampionAffinity(championName, strength);
        championAffinity.save();
        return championAffinity;
    }
}