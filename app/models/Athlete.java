package models;

import models.athlete.ChampionAffinity;
import models.athlete.LaneAffinity;
import models.athlete.SoloQueueRating;
import models.game.ChampionHelper;
import models.game.ChampionName;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Game Athlete entity.
 */
@Entity
public class Athlete extends Model {

    /**
     * Primary db key for player.
     */
    @Id
    public Long id;

    /**
     * The team the player belongs to.
     */
    @ManyToOne
    public Team team;

    /**
     * The game that this team exists in.
     */
    @ManyToOne
    public Game whichGame;

    /**
     * The solo-queue rating that the player has.
     */
    public SoloQueueRating soloQueueRating;

    /**
     * The champion affinities the athlete has.
     */
    @ManyToMany
    public List<ChampionAffinity> championAffinities = new ArrayList<ChampionAffinity>();

    /**
     * The affinities the athlete has with the lane.
     */
    @ManyToMany
    public List<LaneAffinity> laneAffinities = new ArrayList<LaneAffinity>();

    /**
     * The name of the player.
     */
    public String name;

    // begin base attributes
    public double baseReflexes;

    public double baseConcentration;

    public double baseHandEyeCoordination;

    public double basePerception;

    public double baseIntelligence;

    public double baseWits;

    public double baseResolution;
    // end base attributes

    // being special attributes
    /**
     * How high an athlete can scale up (0-1)
     */
    double potential;

    double experience;

    double luck;
    // end special attributes

    /**
     * Finder for Athlete.
     */
    public static Model.Finder<Long, Athlete> find = new Model.Finder<Long, Athlete>(Long.class, Athlete.class);

    /**
     * Basic constructor for athlete.
     * @param name The name of the athlete.
     * @param team The team the athlete belongs to.
     * @param whichGame The game that this athlete resides in.
     * @param soloQueueRating The solo queue rating of the player.
     */
    public Athlete(String name, Team team, Game whichGame, SoloQueueRating soloQueueRating) {
        this.whichGame = whichGame;
        this.name = name;
        this.team = team;
        this.soloQueueRating = soloQueueRating;

        // init random stats for the player.
        this.initRandomBaseAttributes(5, 15);

        // init special attributes for the player.
        this.initRandomSpecialAttributes();
    }

    /**
     * Simple helper method that initializes an athlete's base attributes randomly.
     * @param min The minimum stat value.
     * @param max The maximum stat value.
     */
    private void initRandomBaseAttributes(int min, int max) {
        Random random = new Random();

        this.baseReflexes = random.nextInt(max-min) + min;
        this.baseConcentration = random.nextInt(max-min) + min;
        this.baseHandEyeCoordination = random.nextInt(max-min) + min;
        this.basePerception = random.nextInt(max-min) + min;
        this.baseIntelligence = random.nextInt(max-min) + min;
        this.baseWits = random.nextInt(max-min) + min;
        this.baseResolution = random.nextInt(max-min) + min;
    }

    /**
     * Inits the special attributes randomly.
     */
    private void initRandomSpecialAttributes() {
        Random random = new Random();
        this.potential = random.nextDouble();
        this.luck = random.nextInt(10);
        this.experience = random.nextInt(99);
    }

    /**
     * Helper method that provides whether or not an athlete is on a team.
     * @return Whether or not the athlete is on a team.
     */
    public boolean isOnTeam() {
        return this.team != null;
    }

    /**
     * Setter for the team field. Saves to db.
     * @param team The team to set.
     */
    public void setTeam(Team team) {
        this.team = team;
        this.save();
    }

    /**
     * Gets all the athletes in the database.
     * @return A list of all the athletes.
     */
    public static List<Athlete> findAll() {
        return find.all();
    }

    /**
     * Finds athletes belonging to a particular team.
     * @param team The team to check the athletes in.
     * @return The list of athletes in the team.
     */
    public static List<Athlete> findAthletesOf(Team team) {
        return Athlete.find.where().eq("team", team).findList();
    }

    /**
     * Finds all athletes belonging to a particular team.
     * @param game The game to check for athletes in.
     * @return The list of athletes in the game.
     */
    public static List<Athlete> findAthletesOf(Game game) {
        return Athlete.find.where().eq("whichGame", game).findList();
    }

    /**
     * Finds all athletes that are not on any team at the moment.
     * @param game The game to check for.
     * @return The list of recruitable athletes.
     */
    public static List<Athlete> findRecruitableAthletes(Game game) {
        return Athlete.find.where().eq("whichGame", game).eq("team", null).findList();
    }

    /**
     * Creates a new athlete.
     * @param name The name of the athlete.
     * @param teamId The id of the team that this athlete is a part of.
     * @return The new athlete.
     */
    public static Athlete create(String name, Long teamId, Game game, SoloQueueRating soloQueueRating) {
        Athlete athlete = new Athlete(name, Team.find.ref(teamId), game, soloQueueRating);
        athlete.insertRandomAffinities();
        athlete.save();
        return athlete;
    }

    /**
     * Creates a new athlete that does not belong to any team.
     * @param name The name of the athlete.
     * @param game The game that the athlete resides in.
     * @return The new athlete.
     */
    public static Athlete create(String name, Game game, SoloQueueRating soloQueueRating) {
        Athlete athlete = new Athlete(name, null, game, soloQueueRating);
        athlete.insertRandomAffinities();
        athlete.save();
        return athlete;
    }

    /**
     * Initializes affinity map.
     */
    private void insertRandomAffinities() {
        Random random = new Random();

        for (String championName : ChampionHelper.singleton.getAllChampions()) {
            this.championAffinities.add(ChampionAffinity.create(championName, random.nextDouble() * 99));
        }
    }

    /**
     * Gets the top three champions for this set of champion affinities.
     * @param numTop The number of champions to take from the top.
     * @return The top three champions.
     */
    public List<ChampionAffinity> getTopChampions(int numTop) {
        Collections.sort(this.championAffinities, new Comparator<ChampionAffinity>() {
            @Override
            public int compare(ChampionAffinity o1, ChampionAffinity o2) {
                return o2.getExactStrength().compareTo(o1.getExactStrength());
            }
        });

        return this.championAffinities.subList(0, numTop);
    }

}
