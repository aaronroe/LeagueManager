package models.game;

import play.Logger;
import play.api.Play;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that helps with constants for the champions.
 */
public class ChampionHelper {

    /**
     * Singleton instance to use for champion helper.
     */
    public final static ChampionHelper singleton = new ChampionHelper();

    /**
     * Champions in the game.
     */
    private final List<Champion> champions;

    /**
     * private constructor, as this class should be used with the singleton instance.
     */
    private ChampionHelper() {
        champions = new ArrayList<Champion>();

        final File championsFile = Play.getFile("app/resources/Champions", Play.current());

        FileReader championsFileReader = null;
        try {
            championsFileReader = new FileReader(championsFile);
            if (Logger.isDebugEnabled()) {
                Logger.debug("Found the champions file!");
            }
        } catch (FileNotFoundException e) {
            if (Logger.isErrorEnabled()) {
                Logger.error("Failure finding champion file name");
            }
        }

        final BufferedReader championsBufferedReader = new BufferedReader(championsFileReader);

        try {
            String championName;
            while ((championName = championsBufferedReader.readLine()) != null) {
                final Champion champion = new Champion(championName);
                champions.add(champion);
            }

            Logger.info("Loading champion names from file was successful.");
        } catch (IOException e) {
            if (Logger.isErrorEnabled()) {
                Logger.error("Failure loading champions from file");
            }
        }
    }

    /**
     * Returns an iterable of Strings, which represent champions.
     * @return An iterable of champions, in String format.
     */
    public final Iterable<Champion> getAllChampions() {
        return this.champions;
    }
}
