package service;

import entity.GameSession;
import entity.LoadGameSession;
import entity.character.FastCharacter;
import entity.character.SlowCharacter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LoadGameServiceTest {

    @Test
    public void save() {
        LoadGameService service = new LoadGameService();
        Assert.assertEquals(0, service.getSessions().size());
        service.save(new GameSession("username", 100, 100, new FastCharacter()));
        Assert.assertEquals(1, service.getSessions().size());
    }

    @Test
    public void getSessions() {
        LoadGameService service = new LoadGameService();
        Assert.assertEquals(0, service.getSessions().size());
        service.save(new GameSession("username", 100, 100, new FastCharacter()));
        List<LoadGameSession> sessions = service.getSessions();
        Assert.assertEquals(1, sessions.size());
        Assert.assertEquals("username", sessions.get(0).getUsername());
        Assert.assertEquals(new Integer(100), sessions.get(0).getScore());
        Assert.assertEquals(new Integer(100), sessions.get(0).getHealth());
        Assert.assertEquals(new Integer(1), sessions.get(0).getIdx());
    }

    @Test
    public void get() {
        LoadGameService service = new LoadGameService();
        service.save(new GameSession("username1", 100, 100, new FastCharacter()));
        service.save(new GameSession("username2", 200, 200, new SlowCharacter()));
        LoadGameSession session = service.get(2);
        Assert.assertEquals("username2", session.getUsername());
        Assert.assertEquals(new Integer(200), session.getScore());
        Assert.assertEquals(new Integer(200), session.getHealth());
        Assert.assertEquals(new Integer(2), session.getIdx());
    }

}