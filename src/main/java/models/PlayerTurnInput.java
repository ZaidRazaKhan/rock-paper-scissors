package models;


public class PlayerTurnInput {
    private final Player player;
    private final HandRoll playerInput;

    public PlayerTurnInput(Player player, HandRoll playerInput) {
        this.playerInput = playerInput;
        this.player = player;
    }

    @Override
    public String toString() {
        return "PlayerTurnInput{" +
                "player=" + player +
                ", playerInput=" + playerInput +
                '}';
    }

    public Player getPlayer() {
        return player;
    }

    public HandRoll getPlayerInput() {
        return playerInput;
    }
}
