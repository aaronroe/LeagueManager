package models.forms;

import play.data.validation.Constraints;

/**
 * Represents the elements of the form for an initial team.
 */
public class InitialTeam {

    /**
     * Name of the team.
     */
    @Constraints.Required
    @Constraints.MaxLength(50)
    public String name;

    /**
     * Icon for the team.
     */
    @Constraints.Required
    @Constraints.MaxLength(100)
    public String logo;

}
