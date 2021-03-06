package gameobjects.ships;

public abstract class EnemyShip extends Ship implements contracts.EnemyShip {
    private final static int DEFAULT_X_VELOCITY = 0;

    private int shootingCounter;
    private int shootingRate;
    private int xVelocity;
    private int scorePoints;

    public EnemyShip(int x, int y, int width, int height, int velocity, int health, int shootingRate, int scorePoints) {
        super(x, y, width, height, velocity, health);
        this.setXVelocity(DEFAULT_X_VELOCITY);
        this.setShootingRate(shootingRate);
        this.setScorePoints(scorePoints);
    }

    protected int getShootingCounter() {
        return this.shootingCounter;
    }

    protected void setShootingCounter(int shootingCounter) {
        this.shootingCounter = shootingCounter;
    }

    private void setShootingRate(int shootingRate) {
        this.shootingRate = shootingRate;
    }

    protected int getShootingRate() {
        return this.shootingRate;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public int getScorePoints() {
        return this.scorePoints;
    }

    private void setScorePoints(int scorePoints) {
        this.scorePoints = scorePoints;
    }
}