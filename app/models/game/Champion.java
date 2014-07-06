package models.game;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a champion instance in league of legends.
 */
public class Champion {

    /**
     * The identifying name of the champion.
     */
    @JsonProperty("name")
    private String name;

    public Champion() {}

    /**
     * Constructor for a champion instance.
     * @param name The name of the champion we are initializing.
     */
    public Champion(String name) {
        this.name = name;
    }

    /**
     * Checks whether or not the champion has the same identifying name.
     * @param o The object to check against.
     * @return Whether or not o is equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Champion champion = (Champion) o;

        if (name != champion.name) return false;

        return true;
    }

    /**
     * Hashcode is calculated just by the identifying name enum.
     * @return The hashcode of the champion.
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
