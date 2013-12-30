package models.athlete;

import models.game.Champion;
import models.game.ChampionName;

import java.util.*;

/**
 * Represents champion affinities of a player.
 */
public class ChampionAffinities {

    /**
     * Hashmap that maps a champion to the affinity, 0-99.
     */
    private HashMap<ChampionName, Double> affinityMap;

    /**
     * Empty constructor for champion affinities.
     * For now just initializes the affinityMap.
     */
    public ChampionAffinities() {
        this.affinityMap = new HashMap<ChampionName, Double>();
        this.insertRandomAffinities();
    }

    /**
     * Initializes affinity map.
     */
    private void insertRandomAffinities() {
        Random random = new Random();

        for (ChampionName championName : ChampionName.values()) {
            this.affinityMap.put(championName, random.nextDouble() * 99);
        }
    }

    /**
     * Gets the top three champions for this set of champion affinities.
     * @param numTop The number of champions to take from the top.
     * @return The top three champions.
     */
    public List<Champion> getTopChampions(int numTop) {
        List entryList = new ArrayList(affinityMap.entrySet());
        Collections.sort(entryList, Collections.reverseOrder());

        return entryList.subList(0, numTop);
    }
}
