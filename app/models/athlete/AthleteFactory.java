package models.athlete;

import models.Athlete;
import models.Game;

import java.util.*;

/**
 * A class that generates athletes.
 */
public class AthleteFactory {

    /**
     * Singleton instance for creating athletes.
     */
    public static final AthleteFactory singleton = new AthleteFactory();

    /**
     * Constructor should not be called.
     */
    private AthleteFactory() {
        words = new String[NUM_WORDS];
        words[0] = "Blaze";
        words[1] = "Frost";
        words[2] = "Aqua";
        words[3] = "Samurai";
        words[4] = "Bear";
        words[5] = "Guardian";
        words[6] = "Smash";
        words[7] = "Flash";
        words[8] = "Hot";
        words[9] = "GG";
        words[10] = "Swag";
        words[11] = "Lord";
        words[12] = "Zephyr";
        words[13] = "Fuzzy";
    }

    /**
     * Constant for the number of words in the dictionary.
     */
    private final static int NUM_WORDS = 14;

    /**
     * Array containing modifiers for use in an athlete name.
     */
    private String[] words;

    /**
     * Generates athletes and saves them to the database.
     * @param game The game to generate the athletes for.
     * @param numAthletes The number of athletes to generate.
     * @return The list of generated athletes.
     */
    public List<Athlete> generateAthletes(Game game, int numAthletes) {
        List<Athlete> athleteList = new ArrayList<Athlete>();

        Random random = new Random(System.currentTimeMillis());

        // generate athletes.
        for (int i = 0; i < numAthletes; i++) {
            // get a unique, random athlete name.
            String first = words[random.nextInt(NUM_WORDS)];
            String second = words[random.nextInt(NUM_WORDS)];
            Integer num = random.nextInt(100);
            String athleteName = first + second + num;

            // keep generating until a unique name is found.
            while (Athlete.existsForGame(athleteName, game)) {
                first = words[random.nextInt(NUM_WORDS)];
                second = words[random.nextInt(NUM_WORDS)];
                num = random.nextInt(100);
                athleteName = first + second + num;
            }

            // generate a division.
            Double divisionDecider = random.nextDouble();

            SoloQueueRating rating;
            if (divisionDecider < 0.6) {
                rating = SoloQueueRating.DiamondII;
            }
            else if (divisionDecider < 0.9) {
                rating = SoloQueueRating.DiamondI;
            }
            else {
                rating = SoloQueueRating.Challenger;
            }

            // create the athlete and add to list.
            Athlete generatedAthlete = Athlete.create(athleteName, game, rating);

            athleteList.add(generatedAthlete);
        }

        return athleteList;
    }
}
