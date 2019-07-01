package entity;

import entity.character.Character;

public class LoadGameSession extends GameSession {
    private final Integer idx;

    public LoadGameSession(Integer idx, String username, Integer score, Integer health, Character character) {
        super(username, score, health, character);
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

}
