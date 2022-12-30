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
        Field field;

        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        } else {
            throw new NullPointerException(INVALID_FIELD_TYPE);
        }
        fields.add(field);
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement;

        if (type.equals("Powdered")) {
            supplement = new Powdered();
        } else if (type.equals("Liquid")) {
            supplement = new Liquid();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplementRepository.add(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement = supplementRepository.findByType(supplementType);

        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);

        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        field.addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;
        Field field=fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);

        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        } else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        boolean isPlayerWomenAndFieldTurf = field.getClass().getSimpleName().equals("ArtificialTurf")&& player.equals("Women");
        boolean isPlayerMenAndFieldGrass = field.getClass().getSimpleName().equals("ArtificialTurf")&& player.equals("Women");

        if (isPlayerWomenAndFieldTurf || isPlayerMenAndFieldGrass) {
            field.addPlayer(player);
        } else {
            return FIELD_NOT_SUITABLE;
        }

        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD,playerType,fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        int dragCount = 0;
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);
        for (Player p : field.getPlayers()){
            p.stimulation();
            dragCount++;
        }


        return String.format(PLAYER_DRAG, dragCount);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);
        int sumStrength = 0;
        for (Player p : field.getPlayer()) {
            sumStrength += p.getStrength();
        }

        return String.format(STRENGTH_FIELD, fieldName, sumStrength);
    }

}

    @Override
    public String getStatistics() {
        return null;
    }
}
