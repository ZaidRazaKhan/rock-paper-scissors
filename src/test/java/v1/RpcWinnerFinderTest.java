package v1;

import models.HandRoll;
import models.Player;
import models.PlayerTurnInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class RpcWinnerFinderTest {


    private RPSWinnerFinder rpsWinnerFinder;
    private Player player1;
    private Player player2;
    @Before
    public void setup(){
        player1 = new Player("Real");
        player2 = new Player("Computer");
        rpsWinnerFinder = new RPSWinnerFinder();
    }
    @Test
    public void testWinnerFinderWhenRockWins(){
        PlayerTurnInput player1TurnInput = new PlayerTurnInput(player1, HandRoll.ROCK);
        PlayerTurnInput player2TurnInput = new PlayerTurnInput(player2, HandRoll.SCISSOR);
        Optional<Player> winner = rpsWinnerFinder.find(player1TurnInput, player2TurnInput);
        Assert.assertTrue(winner.isPresent());
        Assert.assertEquals(player1, winner.get());
    }
    @Test
    public void testWinnerFinderWhenPaperWins(){
        PlayerTurnInput player1TurnInput = new PlayerTurnInput(player1, HandRoll.PAPER);
        PlayerTurnInput player2TurnInput = new PlayerTurnInput(player2, HandRoll.ROCK);
        Optional<Player> winner = rpsWinnerFinder.find(player1TurnInput, player2TurnInput);
        Assert.assertTrue(winner.isPresent());
        Assert.assertEquals(player1, winner.get());
    }
    @Test
    public void testWinnerFinderWhenScissorWins(){
        PlayerTurnInput player1TurnInput = new PlayerTurnInput(player1, HandRoll.SCISSOR);
        PlayerTurnInput player2TurnInput = new PlayerTurnInput(player2, HandRoll.PAPER);
        Optional<Player> winner = rpsWinnerFinder.find(player1TurnInput, player2TurnInput);
        Assert.assertTrue(winner.isPresent());
        Assert.assertEquals(player1, winner.get());
    }
    @Test
    public void testWinnerFinderWhenMatchDraw(){
        PlayerTurnInput player1TurnInput = new PlayerTurnInput(player1, HandRoll.ROCK);
        PlayerTurnInput player2TurnInput = new PlayerTurnInput(player2, HandRoll.ROCK);
        Optional<Player> winner = rpsWinnerFinder.find(player1TurnInput, player2TurnInput);
        Assert.assertFalse(winner.isPresent());
    }

}
