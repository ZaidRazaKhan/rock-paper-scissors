package models;

import java.util.Objects;
import java.util.UUID;



public class Player {
    private final String id;
    private final String name;

    @Override
    public boolean equals(Object o) {
        return Objects.equals(toString(), o.toString());
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Player(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
