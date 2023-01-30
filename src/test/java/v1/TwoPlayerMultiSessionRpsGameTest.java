package v1;

import models.HandRoll;
import models.Player;
import models.PlayerTurnInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.WinnerFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TwoPlayerMultiSessionRpsGameTest {

    private TwoPlayerMultiSessionRpsGame<HandRoll> twoPlayerMultiSessionRpsGame;
    @Mock
    private WinnerFinder<HandRoll> winnerFinder;
    @Mock
    List<Map<Player, List<Player>>> matchStatsAgainstSessions;

    Player player1;
    Player player2;
    @Before
    public void setup(){

        player1 = new Player("player1");
        player2 = new Player("player2");
        int numberOfPlayers = 2;
        int numberOfSessions = 4;
        matchStatsAgainstSessions = Mockito.mock(List.class);
        winnerFinder = Mockito.mock(WinnerFinder.class);
        twoPlayerMultiSessionRpsGame = new TwoPlayerMultiSessionRpsGame<>(numberOfPlayers,
                numberOfSessions, winnerFinder,
                matchStatsAgainstSessions);
    }
    @Test
    public void testHasNextForTrue(){
        Mockito.when(matchStatsAgainstSessions.size()).thenReturn(1);
        Assert.assertTrue(twoPlayerMultiSessionRpsGame.hasNextSession());
    }

    @Test
    public void testHasNextForFalse(){
        Mockito.when(matchStatsAgainstSessions.size()).thenReturn(4);
        Assert.assertFalse(twoPlayerMultiSessionRpsGame.hasNextSession());
    }

    @Test
    public void testSessionPlayForDraw(){
        List<PlayerTurnInput> playerTurns = new ArrayList<>();
        PlayerTurnInput player1Turn = new PlayerTurnInput(player1, HandRoll.ROCK);
        PlayerTurnInput player2Turn = new PlayerTurnInput(player2, HandRoll.ROCK);
        playerTurns.add(player1Turn);
        playerTurns.add(player2Turn);
        Mockito.when(winnerFinder.find(player1Turn, player2Turn)).thenReturn(Optional.empty());

        Map<Player, List<Player>> winnerToLooserMap = twoPlayerMultiSessionRpsGame.sessionPlay(playerTurns);
        Assert.assertEquals(2, winnerToLooserMap.size());
        for(Player player: winnerToLooserMap.keySet()){
            Assert.assertEquals(1, winnerToLooserMap.get(player).size());
        }
    }

    @Test
    public void testSessionPlayForWinner(){
        List<PlayerTurnInput> playerTurns = new ArrayList<>();
        PlayerTurnInput player1Turn = new PlayerTurnInput(player1, HandRoll.ROCK);
        PlayerTurnInput player2Turn = new PlayerTurnInput(player2, HandRoll.SCISSOR);
        playerTurns.add(player1Turn);
        playerTurns.add(player2Turn);
        Mockito.when(winnerFinder.find(player1Turn, player2Turn)).thenReturn(Optional.of(player1));

        Map<Player, List<Player>> winnerToLooserMap = twoPlayerMultiSessionRpsGame.sessionPlay(playerTurns);
        Assert.assertEquals(1, winnerToLooserMap.size());
        for(Player player: winnerToLooserMap.keySet()){
            Assert.assertEquals("player1", player.getName());
            Assert.assertEquals(1, winnerToLooserMap.get(player).size());
            Assert.assertEquals("player2", winnerToLooserMap.get(player).get(0).getName());
        }
    }
}
