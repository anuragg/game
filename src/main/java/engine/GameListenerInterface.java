package engine;

public interface GameListenerInterface extends WelcomeGameInterface, LoginGameInterface {
    void welcome();
    void play();

    void save();
    void resume(int i);

    void shoot();
}
