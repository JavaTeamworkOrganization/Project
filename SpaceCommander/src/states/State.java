package states;

import contracts.Updateable;
import game.GameEngine;

public abstract class State implements Updateable {
    protected GameEngine gameEngine;

    public State(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}
