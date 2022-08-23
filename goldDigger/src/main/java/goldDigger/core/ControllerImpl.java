package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int exploredSpots;


    public ControllerImpl() {
        discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();
        exploredSpots = 0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;

        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }

        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
     //   Collections.addAll(spot.getExhibits(), exhibits);
        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discovererToRemove = discovererRepository.byName(discovererName);
        if (discovererToRemove == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        discovererRepository.remove(discovererToRemove);
        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> suitableDiscoverers = discovererRepository.getCollection().stream()
                .filter(d->d.getEnergy() > 45).collect(Collectors.toList());

        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spotToExplore = spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spotToExplore, suitableDiscoverers);
        long excludedDiscoverers = suitableDiscoverers.stream().filter(d -> !d.canDig()).count();
        exploredSpots++;

        return String.format(INSPECT_SPOT, spotName, excludedDiscoverers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT, exploredSpots));
        sb.append(System.lineSeparator());
        sb.append(FINAL_DISCOVERER_INFO);
        sb.append(System.lineSeparator());
        sb.append(spotRepository.toString());

        return sb.toString().trim();

//String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploreStateCount) + System.lineSeparator() +
//                ConstantMessages.FINAL_EXPLORER_INFO + System.lineSeparator() +
//                explorerRepository.getCollection().stream().map(Explorer::toString).collect(Collectors.joining(System.lineSeparator()));

//      return String.format(FINAL_SPOT_INSPECT, exploredSpots) + System.lineSeparator() +
//                FINAL_DISCOVERER_INFO + System.lineSeparator() +
//                spotRepository.getCollection().stream().map(Discoverer::toString)
//                        .collect(Collectors.joining(System.lineSeparator()));
//    }
    }
}
