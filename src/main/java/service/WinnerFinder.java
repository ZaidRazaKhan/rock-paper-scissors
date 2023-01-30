package service;

import models.Player;
import models.PlayerTurnInput;

import java.util.Optional;

public interface WinnerFinder<E extends Enum> {
    Optional<Player> find(PlayerTurnInput player1TurnInput, PlayerTurnInput player2TurnInput);
}
