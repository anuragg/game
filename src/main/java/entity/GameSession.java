package entity;

import entity.character.Character;

public class GameSession {
    private String username;
    private Integer score;
    private Integer health;
    private Character character;

    public GameSession(String username, Integer score, Integer health, Character character) {
        this.username = username;
        this.score = score;
        this.health = health;
        this.character = character;
    }

    public String getUsername() {
        return username;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getHealth() {
        return health;
    }

    public Character getCharacter() {
        return character;
    }

    public void addScore(int i) {
        this.score += i;
    }

    public void addHealth(int i) {
        this.health += i;
    }

    public boolean isDead() {
        return health  <= 0;
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "username=" + username +
                ", score=" + score +
                ", health=" + health +
                ", character=" + character.log() +
                '}';
    }
}
