package controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Athlete;
import models.Game;
import models.User;
import models.athlete.ChampionAffinity;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import java.util.ArrayList;

/**
 * Controller that exposes a JSON API.
 */
public class JsonAPI extends Controller {

    /**
     * Gets a JSON of all recruitable athletes.
     * @return The JSON of all recruitable athletes.
     */
    @Restrict(@Group(Application.USER_ROLE))
    @BodyParser.Of(BodyParser.Json.class)
    public static Result jsonRecruitables(Integer page) {
        final User localUser = Application.getLocalUser(session());
        Game localGame = Game.findGameOf(localUser);

        ArrayNode resultList = Json.newObject().arrayNode();

        // for each recruitable athlete, build the json.
        for (Athlete recruitable : Athlete.findRecruitableAthletes(localGame)) {
            ObjectNode singleAthlete = Json.newObject();

            // id
            singleAthlete.put("id", recruitable.id);

            // name
            singleAthlete.put("name", recruitable.name);

            // solo queue ranking
            singleAthlete.put("division", recruitable.soloQueueRating.toString());
            singleAthlete.put("division_image", recruitable.soloQueueRating.getImageName());

            // create the list of top champ affinities.
            ArrayNode champAffList = Json.newObject().arrayNode();
            for (ChampionAffinity affinity : recruitable.getTopChampions(3)) {
                ObjectNode singleChampAff = Json.newObject();
                singleChampAff.put("name", affinity.getChampionName());
                singleChampAff.put("strength", affinity.getRoundedStrength());
                singleChampAff.put("image", affinity.getChampionIcon());

                champAffList.add(singleChampAff);
            }
            singleAthlete.put("champion_affinities", champAffList);

            // lane
            singleAthlete.put("lane", recruitable.getTopLanes(1).get(0).getLaneName());

            // salary
            singleAthlete.put("salary", recruitable.getSalary());

            resultList.add(singleAthlete);
        }

        return ok(resultList);
    }

}
