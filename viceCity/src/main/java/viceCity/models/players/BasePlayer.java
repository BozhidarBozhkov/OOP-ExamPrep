package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import static viceCity.common.ExceptionMessages.NAME_NULL;
import static viceCity.common.ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO;

public abstract class BasePlayer implements Player{

    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        setName(name);
        setLifePoints(lifePoints);
        //todo initialize repository
        this.gunRepository = gunRepository;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(NAME_NULL);
        }
        this.name = name;
    }

    public void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }

    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    public void takeLifePoints(int points) {
        this.lifePoints = Math.max(0, this.lifePoints - points);
    }
}
