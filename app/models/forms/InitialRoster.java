package models.forms;

import play.data.validation.Constraints;

import java.util.List;

/**
 * Represents the elements of the form for an initial roster.
 */
public class InitialRoster {
    /**
     * The list of athlete ids selected.
     */
    @Constraints.Required
    public List<Long> athletes;

    /**
     * Other validation for the initial roster.
     * @return null, if there is no error, and a message otherwise.
     */
    public String validate() {
        if (athletes.size() != 5) {
            return "You must pick exactly 5 players for your intial roster.";
        }

        return null;
    }
}
