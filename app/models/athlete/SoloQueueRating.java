package models.athlete;

/**
 * Division enum to represent divisions bronze, silver, ...
 */
public enum SoloQueueRating {
    BronzeI("Bronze I"), BronzeII("Bronze II"), BronzeIII("Bronze III"), BronzeIV("Bronze IV"), BronzeV("Bronze V"),
    SilverI("Silver I"), SilverII("Silver II"), SilverIII("Silver III"), SilverIV("Silver IV"), SilverV("Silver V"),
    GoldI("Gold I"), GoldII("Gold II"), GoldIII("Gold III"), GoldIV("Gold IV"), GoldV("Gold V"),
    PlatinumI("Platinum I"), PlatinumII("Platinum II"), PlatinumIII("Platinum III"), PlatinumIV("Platinum IV"), PlatinumV("Platinum V"),
    DiamondI("Diamond I"), DiamondII("Diamond II"), DiamondIII("Diamond III"), DiamondIV("Diamond IV"), DiamondV("Diamond V"),
    Challenger("Challenger");

    /**
     * The textRepresentation version of the rating.
     */
    private final String textRepresentation;

    /**
     * Constructor for SoloQueueRating. Allows it to have a toString.
     * @param textRepresentation The textRepresentation representation of the rating.
     */
    private SoloQueueRating(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    /**
     * The toString method for a solo queue rating.
     * @return The String representation of a solo queue rating.
     */
    public String toString() {
        return this.textRepresentation;
    }
}