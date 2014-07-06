package models.game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private static final String CHAMPIONS_RESOURCE_PATH = "app/resources/Champions.json";

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

        final File championsFile = Play.getFile(CHAMPIONS_RESOURCE_PATH, Play.current());

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
            final StringBuilder sb = new StringBuilder();
            String championsLine;
            while ((championsLine = championsBufferedReader.readLine()) != null) {
                sb.append(championsLine);
            }

            final String championJSON = sb.toString();
            final ObjectMapper mapper = new ObjectMapper();

            final List<Champion> parsedChampions = mapper.readValue(championJSON, new TypeReference<List<Champion>>() {});

            for (Champion champion : parsedChampions) {
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
