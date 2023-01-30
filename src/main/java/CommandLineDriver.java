import models.HandRoll;
import models.Player;
import models.PlayerTurnInput;
import service.RpsGame;
import service.WinnerFinder;
import v1.RPSWinnerFinder;
import v1.TwoPlayerMultiSessionRpsGame;

import java.util.*;

public class CommandLineDriver {
    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        // Game rules
        System.out.println("Game rules: ");
        System.out.println("0 for ROCK");
        System.out.println("1 for PAPER");
        System.out.println("2 for SCISSOR");
        System.out.println("Please enter the number of sessions you want to play with computer!");
        int numberOfSession = scanner.nextInt();
        System.out.println("Game started!!");

        Map<Integer, HandRoll> integerToHandRollMap = new HashMap<>();
        integerToHandRollMap.put(0, HandRoll.ROCK);
        integerToHandRollMap.put(1, HandRoll.PAPER);
        integerToHandRollMap.put(2, HandRoll.SCISSOR);


        Player realPlayer = new Player("realPlayer");
        Player computerPlayer = new Player("computer");

        WinnerFinder<HandRoll> rpsWinnerFinder = new RPSWinnerFinder();

        List<Map<Player, List<Player>>> matchStatsAgainstSessions = new ArrayList<>();
        int numberOfPlayers = 2;
        RpsGame rpsGame = new TwoPlayerMultiSessionRpsGame<>(numberOfPlayers, numberOfSession, rpsWinnerFinder,
                matchStatsAgainstSessions);
        while(rpsGame.hasNextSession()){
            System.out.println("Please provide your handroll!!");
            int userInput = scanner.nextInt();
            while(userInput > 2 || userInput < 0){
                System.out.println("please enter a valid handroll in range [0,2]");
                userInput = scanner.nextInt();
            }


            HandRoll playerInput = getHandRoll(integerToHandRollMap, userInput);
            HandRoll computerInput = getHandRoll(integerToHandRollMap, (int)(Math.random()*3));
            System.out.println("Real player input: " + playerInput.name());
            System.out.println("Computer input: " + computerInput.name());
            PlayerTurnInput player1TurnInput = new PlayerTurnInput(realPlayer, playerInput);
            PlayerTurnInput computerTurnInput = new PlayerTurnInput(computerPlayer, computerInput);
            List<PlayerTurnInput> playerTurnInputs = new ArrayList<>();
            playerTurnInputs.add(player1TurnInput);
            playerTurnInputs.add(computerTurnInput);

            Map<Player, List<Player>> winnerAgainstLooserMap = rpsGame.sessionPlay(playerTurnInputs);

            if(winnerAgainstLooserMap.size() == 2){
                System.out.println("Match draw!!");
            }else{
                for(Player winner: winnerAgainstLooserMap.keySet()){

                    System.out.println("Winner: " + winner.getName());
                    System.out.print("Loosers: ");
                    for(Player looser: winnerAgainstLooserMap.get(winner)){
                        System.out.print(looser.getName() + "\t");
                    }
                }
            }
            System.out.println("\nSession end");

        }

    }
    private static HandRoll getHandRoll(Map<Integer, HandRoll> integerToHandRollMap, int input) {
        return integerToHandRollMap.get(input);
    }}
