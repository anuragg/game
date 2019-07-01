package entity.character;

public class MediumCharacter implements Character {
    @Override
    public String getName() {
        return "Brain";
    }

    @Override
    public Integer getHealth() {
        return 200;
    }

    @Override
    public Integer getSpeed() {
        return 200;
    }

    @Override
    public String resource() {
        return "medium-character.txt";
    }

    @Override
    public Integer idx() {
        return 2;
    }
}
