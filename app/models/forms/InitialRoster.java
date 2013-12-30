package models.forms;

import models.Athlete;
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
}
