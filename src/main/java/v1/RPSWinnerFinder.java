package v1;

import models.HandRoll;
import models.Player;
import models.PlayerTurnInput;
import service.WinnerFinder;

import java.util.Optional;
import java.util.logging.Logger;


public class RPSWinnerFinder implements WinnerFinder<HandRoll> {
    private static final Logger logger =  Logger.getLogger(RPSWinnerFinder.class.getName());
    public Optional<Player> find(PlayerTurnInput player1TurnInput, PlayerTurnInput player2TurnInput) {
        logger.info("Player1 turn input" + player1TurnInput + "\n Player2 turn input" + player2TurnInput);
        if(player2TurnInput.getPlayerInput().equals(player1TurnInput.getPlayerInput())){
            return Optional.empty();
        }
        if(player1TurnInput.getPlayerInput().equals(HandRoll.ROCK) && player2TurnInput.getPlayerInput()
                .equals(HandRoll.SCISSOR)){
            return Optional.of(player1TurnInput.getPlayer());
        }
        if(player1TurnInput.getPlayerInput().equals(HandRoll.SCISSOR) && player2TurnInput.getPlayerInput()
                .equals(HandRoll.PAPER)){
            return Optional.of(player1TurnInput.getPlayer());
        }
        if(player1TurnInput.getPlayerInput().equals(HandRoll.PAPER) && player2TurnInput.getPlayerInput()
                .equals(HandRoll.ROCK)){
            return Optional.of(player1TurnInput.getPlayer());
        }
        return Optional.of(player2TurnInput.getPlayer());
    }
}
