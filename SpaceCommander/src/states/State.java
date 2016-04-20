package states;

import contracts.Updateable;
import game.GameEngine;
import game.InstructionsMenu;

public abstract class State implements Updateable {
    protected GameEngine gameEngine;
    protected InstructionsMenu instructionsMenu;

    public State(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}
