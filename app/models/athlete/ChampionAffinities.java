package models.athlete;

import models.game.Champion;
import models.game.ChampionName;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

/**
 * Represents champion affinities of a player.
 */
@Entity
public class ChampionAffinities extends Model {

    /**
     * Primary db key for Champion Affinities.
     */
    @Id
    public Long id;

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
    public List<ChampionName> getTopChampions(int numTop) {
        // sort the entries in the map.
        List<Map.Entry<ChampionName, Double>> entryList =
                new ArrayList<Map.Entry<ChampionName, Double>>(affinityMap.entrySet());
        // use collections.sort with custom comparator. Notice that o2 is before o1, this is to make it descending.
        Collections.sort(entryList, new Comparator<Map.Entry<ChampionName, Double>>() {
            @Override
            public int compare(Map.Entry<ChampionName, Double> o1, Map.Entry<ChampionName, Double> o2) {
                return ((Comparable) ( (o2)).getValue())
                        .compareTo(((o1)).getValue());
            }
        });

        // add the top "numTop" champions to the return list.
        List<ChampionName> returnList = new ArrayList<ChampionName>();
        for (int i = 0; i < numTop; i++) {
            returnList.add(entryList.get(i).getKey());
        }

        return returnList;
    }

    /**
     * Creates a champion affinities object and saves it to db.
     * @return The champion affinities object.
     */
    public static ChampionAffinities create() {
        ChampionAffinities championAffinities = new ChampionAffinities();
        championAffinities.save();

        return championAffinities;
    }
}
