package assignment.battleShip.models.warArtillery;

public abstract class Artillery {
    private int power;

    Artillery(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public void decreasePowerOnHit() {
        this.power--;
    }
}
