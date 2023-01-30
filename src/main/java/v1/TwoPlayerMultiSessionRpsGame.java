package v1;


import models.Player;
import models.PlayerTurnInput;
import service.RpsGame;
import service.WinnerFinder;

import java.util.*;
import java.util.logging.Logger;

public class TwoPlayerMultiSessionRpsGame<T extends Enum> implements RpsGame {
    private final int numberOfPlayers;
    private final int numberOfSessions;
    private final WinnerFinder<T> winnerFinder;
    private final List<Map<Player, List<Player>>> matchStatsAgainstSessions;

    private static final Logger logger =  Logger.getLogger(TwoPlayerMultiSessionRpsGame.class.getName());
    // TODO: Use the above variable to provide match stats between sessions

    public TwoPlayerMultiSessionRpsGame(int numberOfPlayers, final int numberOfSession,
                                        final WinnerFinder<T> winnerFinder,
                                        final List<Map<Player, List<Player>>> matchStatsAgainstSessions) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfSessions = numberOfSession;
        this.matchStatsAgainstSessions = matchStatsAgainstSessions;
        this.winnerFinder = winnerFinder;
    }

    @Override
    public boolean hasNextSession() {
        return numberOfSessions > matchStatsAgainstSessions.size();
    }

    @Override
    public Map<Player, List<Player>> sessionPlay(List<PlayerTurnInput> playerTurnInputs) {
        logger.info("Session play info: " + playerTurnInputs.toString());
        assert playerTurnInputs.size() == numberOfPlayers;
        Map<Player, List<Player>> winnerAgainstLooserMap = new HashMap<>();
        for(int i = 0 ; i < playerTurnInputs.size()-1; i++){
            for(int j = i+1 ; j < playerTurnInputs.size(); j++){
                Optional<Player> winner = winnerFinder.find(playerTurnInputs.get(i), playerTurnInputs.get(j));
                if(!winner.isPresent()){
                    addIntoMap(playerTurnInputs.get(i).getPlayer(), playerTurnInputs.get(j).getPlayer(),
                            winnerAgainstLooserMap);
                    addIntoMap(playerTurnInputs.get(j).getPlayer(), playerTurnInputs.get(i).getPlayer(),
                            winnerAgainstLooserMap);
                }else{
                    addIntoMap(winner.get(), winner.get().equals(playerTurnInputs.get(i).getPlayer())
                            ?playerTurnInputs.get(j).getPlayer():playerTurnInputs.get(i).getPlayer() ,
                            winnerAgainstLooserMap);
                }
            }
        }
        matchStatsAgainstSessions.add(winnerAgainstLooserMap);
        return winnerAgainstLooserMap;
    }

    private void addIntoMap(Player key, Player value, Map<Player, List<Player>> map) {
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<>());
        }
        List<Player> oldValues = map.get(key);
        oldValues.add(value);
        map.put(key, oldValues);
    }
}
