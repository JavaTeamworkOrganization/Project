package states;

public class StateManager {
    private static State currentState;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }
}
