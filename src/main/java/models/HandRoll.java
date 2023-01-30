package models;

public enum HandRoll {
    ROCK("ROCK"),
    PAPER("PAPER"),
    SCISSOR("SCISSOR");
    private final String value;

    HandRoll(final String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return "HandRoll{" +
                "value='" + value + '\'' +
                '}';
    }
}
