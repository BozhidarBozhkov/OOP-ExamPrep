package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood{

    public GangNeighbourhood() {

    }

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        for (Gun gun: mainPlayer.getGunRepository().getModels()) {

            while (gun.canFire() || !civilPlayers.isEmpty()) {

            }

        }


    }
}
