package engine;

public interface GameListenerInterface extends ExploreInterface, CreateCharacterInterface, LoadGameInterface, PlayGameInterface {
    void welcome();
}
