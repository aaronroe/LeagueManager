package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class that represents a game entity.
 */
@Entity
public class Game extends Model {

    /**
     * The user whose game this is.
     * Things get more complex when users can have more than one team.
     */
    @OneToOne
    private User owner;

    /**
     * The user-controlled team in this game.
     */
    @OneToOne
    private Team userTeam;

    /**
     * Primary db key for game.
     */
    @Id
    private Long id;

    /**
     * Whether or not the user's team has been init.
     */
    private boolean isTeamInit;

    /**
     * Whether or not the user's roster has been init.
     */
    private boolean isRosterInit;

    /**
     * Gets the amount of money the player owns in the game.
     * @return The amount of money the player owns in the game, in integer form.
     */
    public long getMoney() {
        return money;
    }

    /**
     * Sets money the money the player has in the game. Does not mutate game state on db.
     * @param money Amount of money for the player.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Increments player's game money by an amount. Does not mutate game state on db.
     * @param moneyIncrement Amount of money to add to the player, can be positive or negative.
     */
    public void incrementMoney(int moneyIncrement) {
        this.money += moneyIncrement;
    }

    /**
     * Gets the unix timestamp of the current date ingame.
     * @return The unix timestamp of the current date ingame, in long form.
     */
    public long getDate() {
        return date;
    }

    /**
     * Gets whether the game's initial roster has been formed.
     * @return Whether the game's initial roster has been formed.
     */
    public boolean isRosterInit() {
        return isRosterInit;
    }

    /**
     * Gets the database id of the game.
     * @return The database id of the game.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets whether the game's initial team has been created.
     * @return Whether the game's initial team has been created.
     */
    public boolean isTeamInit() {
        return isTeamInit;
    }

    /**
     * Gets the team of the user.
     * @return The team of the user.
     */
    public Team getUserTeam() {
        return userTeam;
    }

    /**
     * Gets the owner of this game.
     * @return The account associated with this game.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * The amount of money that the user has in the game.
     */
    private long money;

    /**
     * The current date in the game.
     */
    private long date;

    /**
     * Finder for team.
     */
    public static Model.Finder<Long,Game> find = new Model.Finder<Long,Game>(Long.class, Game.class);

    /**
     * Constructor for game.
     * @param owner The User whose game this is.
     */
    public Game(User owner) {
        this.owner = owner;

        this.isRosterInit = false;
        this.isTeamInit = false;

        this.money = 500;

        // initialize the date.
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(2013, 10, 5);
        this.date = cal.getTimeInMillis();
    }

    /**
     * Setter for whether the team has been init. Saves to db.
     * @param isTeamInit Whether the team has been init.
     */
    public void setTeamInit(boolean isTeamInit) {
        this.isTeamInit = isTeamInit;
        this.save();
    }

    /**
     * Setter for whether the roster has been init. Saves to db.
     * @param isRosterInit Whether the roster has been init.
     */
    public void setRosterInit(boolean isRosterInit) {
        this.isRosterInit = isRosterInit;
        this.save();
    }

    /**
     * Method to get the game object for a particular user.
     * @param user The user whose game we are looking up.
     * @return The game belonging to user
     */
    public static Game findGameOf(User user) {
        return find.where().eq("owner", user).findUnique();
    }

    /**
     * Method that creates an instance of Game and saves it to the db.
     * @param ownerId The id of the user whose game is being created.
     * @return The Game. (You just lost it hehe)
     */
    public static Game create(Long ownerId) {
        Game game = new Game(User.find.ref(ownerId));
        game.save();
        return game;
    }

    /**
     * Creates a team for the user in this game, and saves appropriately.
     * @param name The name of the team.
     * @param logo The filename of the logo to use.
     * @return The team created.
     */
    public Team createUserTeam(String name, String logo) {
        Team userTeam = Team.create(this, name, logo);
        this.userTeam = userTeam;
        this.save();

        return userTeam;
    }

    /**
     * Advances the Game date a week.
     */
    public void advanceWeek() {
        // force the long into java's date libraries, so it will handle annoyances for us.
        Date currentDate = new Date(this.date);
        GregorianCalendar calendarDate = new GregorianCalendar();
        calendarDate.setTime(currentDate);

        // add seven days
        calendarDate.add(Calendar.DATE, 7);

        this.date = calendarDate.getTimeInMillis();

        this.incrementMoney(this.getUserTeam().getNetProfit());

        this.save();
    }

}
