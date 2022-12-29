package football.entities.player;

public abstract class BasePlayer implements Player{

    private String name;
    private String nationality;
    private double kg;
    private int strength;

    public BasePlayer(String name, String nationality, double kg, int strength) {
        this.name = name;
        this.nationality = nationality;
        this.kg = kg;
        this.strength = strength;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void stimulation() {

    }

    @Override
    public double getKg() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getStrength() {
        return 0;
    }
}
