package service;

import entity.character.Character;
import entity.character.FastCharacter;
import entity.character.MediumCharacter;
import entity.character.SlowCharacter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterServiceTest {
    @Test
    public void testGetCharacters() {
        CharacterService characterService = new CharacterService();
        Assert.assertEquals(3, characterService.getCharacters().size());
    }

    @Test
    public void testFastCharacter() {
        CharacterService characterService = new CharacterService();
        Character expected = new FastCharacter();
        Character actual = characterService.get(1);
        Assert.assertEquals(expected.getHealth(), actual.getHealth());
        Assert.assertEquals(expected.getSpeed(), actual.getSpeed());
    }

    @Test
    public void testMediumCharacter() {
        CharacterService characterService = new CharacterService();
        Character expected = new MediumCharacter();
        Character actual = characterService.get(2);
        Assert.assertEquals(expected.getHealth(), actual.getHealth());
        Assert.assertEquals(expected.getSpeed(), actual.getSpeed());
    }

    @Test
    public void testSlowCharacter() {
        CharacterService characterService = new CharacterService();
        Character expected = new SlowCharacter();
        Character actual = characterService.get(3);
        Assert.assertEquals(expected.getHealth(), actual.getHealth());
        Assert.assertEquals(expected.getSpeed(), actual.getSpeed());
    }

}