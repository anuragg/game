package service;

import entity.GameSession;
import entity.character.FastCharacter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HighScoreServiceTest {

    @Test
    public void getHighScores() {
        HighScoreService service = new HighScoreService();
        service.save(new GameSession("username", 100, 100, new FastCharacter()));
        List<GameSession> sessions = service.getHighScores();
        Assert.assertEquals(1, sessions.size());
        Assert.assertEquals("username", sessions.get(0).getUsername());
        Assert.assertEquals(new Integer(100), sessions.get(0).getScore());
        Assert.assertEquals(new Integer(100), sessions.get(0).getHealth());
    }

    @Test
    public void save() {
        HighScoreService service = new HighScoreService();
        Assert.assertEquals(0, service.getHighScores().size());
        service.save(new GameSession("username", 100, 100, new FastCharacter()));
        Assert.assertEquals(1, service.getHighScores().size());
    }

}