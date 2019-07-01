package service;

import entity.character.Character;
import entity.character.FastCharacter;
import entity.character.MediumCharacter;
import entity.character.SlowCharacter;

import java.util.ArrayList;
import java.util.List;

public class CharacterService {

    private final List<Character> list;

    public CharacterService() {
        this.list = new ArrayList<>();
        list.add(new FastCharacter());
        list.add(new MediumCharacter());
        list.add(new SlowCharacter());
    }

    public List<Character> getCharacters() {
        return list;
    }

    public Character get(Integer i) {
        for (Character character : list) {
            if (character.idx() == i) {
                return character;
            }
        }
        return null;
    }
}
