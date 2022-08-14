package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplementRepository;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field = null;

        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(INVALID_FIELD_TYPE);
        }
        fields.add(field);
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement = null;

        switch (type) {
            case "Powdered":
                supplement = new Powdered();
                break;
            case "Liquid":
                supplement = new Liquid();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplementRepository.add(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement desiredSupplement = supplementRepository.findByType(supplementType);
        if (desiredSupplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        field.addSupplement(desiredSupplement);
        supplementRepository.remove(desiredSupplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player = null;

        switch (playerType) {
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        if ("ArtificialTurf".equals(field.getClass().getSimpleName()) && playerType.equals("Men")
                || ("NaturalGrass".equals(field.getClass().getSimpleName()) && playerType.equals("Women"))) {
            return FIELD_NOT_SUITABLE;
        }



        field.addPlayer(player);
        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);

        field.drag();
        int dragCount = field.getPlayers().size();
        return String.format(PLAYER_DRAG, dragCount);
    }

    @Override
    public String calculateStrength(String fieldName) {


        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);

        int sumOfAllPlayersStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        // int sum = field.getPlayers().stream().mapToInt(Player::getStrength).sum();


        return String.format(STRENGTH_FIELD, fieldName, sumOfAllPlayersStrength);
    }

    @Override
    public String getStatistics() {

        return fields.stream().map(Field::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}
