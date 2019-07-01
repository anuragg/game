package service;

import entity.GameSession;
import entity.LoadGameSession;
import entity.character.MediumCharacter;

import java.util.ArrayList;
import java.util.List;

public class LoadGameService {

    private final List<LoadGameSession> sessions;
    private Integer identifier;

    public LoadGameService() {
        this.sessions = new ArrayList<>();
        identifier = 1;
    }

    public void save(GameSession session) {
        sessions.add(new LoadGameSession(identifier, session.getUsername(), session.getScore(), session.getHealth(), session.getCharacter()));
        identifier += 1;
    }

    public List<LoadGameSession> getSessions() {
        return sessions;
    }

    public LoadGameSession get(Integer idx) {
        for (LoadGameSession session : sessions) {
            if (session.getIdx() == idx) {
                return session;
            }
        }
        return null;
    }
}
