package entity.character;

public class FastCharacter implements Character {
    @Override
    public String getName() {
        return "Furios";
    }

    @Override
    public Integer getHealth() {
        return 100;
    }

    @Override
    public Integer getSpeed() {
        return 400;
    }

    @Override
    public String resource() {
        return "fast-character.txt";
    }

    @Override
    public Integer idx() {
        return 1;
    }
}
