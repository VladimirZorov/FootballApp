package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseField implements Field{

   private String name;
   private int capacity;
   private Collection<Supplement> supplements;
   private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    @Override
    public int sumEnergy() {
        return 0;
    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void removePlayer(Player player) {

    }

    @Override
    public void addSupplement(Supplement supplement) {

    }

    @Override
    public void drag() {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public Collection<Player> getPlayers() {
        return null;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
