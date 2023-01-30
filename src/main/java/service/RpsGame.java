package service;


import models.Player;
import models.PlayerTurnInput;

import java.util.List;
import java.util.Map;

public interface RpsGame {
    boolean hasNextSession();

    /*
    This takes list of player input to make it a multiplayer game
    and in case of draw between 2 players, it adds looser to both the values against player
     */
    Map<Player, List<Player>> sessionPlay(List<PlayerTurnInput> playerTurnInputs);
}
