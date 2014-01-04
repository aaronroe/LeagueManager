package models.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that helps with constants for lanes.
 */
public class LaneHelper {

    /**
     * Singleton instance for lane helper usage.
     */
    public static LaneHelper singleton = new LaneHelper();

    /**
     * Array of the lanes.
     */
    private String[] lanes;

    /**
     * The number of lanes in the game.
     */
    private final int NUM_LANES = 4;

    // lane name constants.
    public static final String TOP = "Top";
    public static final String MID = "Middle";
    public static final String BOT = "Bottom";
    public static final String JUNGLE = "Jungle";

    /**
     * Private constructor, as this should never be directly instantiated..
     */
    private LaneHelper() {
        lanes = new String[this.NUM_LANES];
        lanes[0] = TOP;
        lanes[1] = MID;
        lanes[2] = BOT;
        lanes[3] = JUNGLE;
    }

    /**
     * Gets an iterable collection of lanes, represented by Strings.
     * @return An iterable of lanes, represented by Strings.
     */
    public final Iterable<String> getAllLanes() {
        List<String> lanesList = new LinkedList<String>(Arrays.asList(lanes));

        return lanesList;
    }

}
