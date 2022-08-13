package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission{


    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Collection<String> items = planet.getItems();

        for (Astronaut astronaut : astronauts) {
            while (astronaut.canBreath() && items.iterator().hasNext()) {
                astronaut.breath();
                String currentItem = items.iterator().next();
                astronaut.getBag().getItems().add(currentItem);
                items.remove(currentItem);
            }
        }
    }
}
