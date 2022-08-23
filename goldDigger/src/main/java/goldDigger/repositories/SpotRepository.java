package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SpotRepository implements Repository<Spot>{

    private Collection<Spot> spots;


    public SpotRepository() {
        spots = new ArrayList<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(spots);
    }

    @Override
    public void add(Spot spot) {
        spots.add(spot);
    }

    @Override
    public boolean remove(Spot spot) {
        return spots.remove(spot);
    }

    @Override
    public Spot byName(String name) {
//        for (Spot spot : spots) {
//            if (spot.getName().equals(name)) {
//                return spot;
//            }
//        }
//        return null;
        return spots.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }
}
