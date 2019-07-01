package service;

import entity.GameSession;

import java.util.ArrayList;
import java.util.List;

public class HighScoreService {

    private final List<GameSession> highScores;

    public HighScoreService() {
        this.highScores = new ArrayList<>();
    }

    public List<GameSession> getHighScores() {
        return highScores;
    }

    public void save(GameSession session) {
        highScores.add(session);
    }
}
