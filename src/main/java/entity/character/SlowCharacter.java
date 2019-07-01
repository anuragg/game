package entity.character;

public class SlowCharacter implements Character {
    @Override
    public String getName() {
        return "Stable";
    }

    @Override
    public Integer getHealth() {
        return 400;
    }

    @Override
    public Integer getSpeed() {
        return 100;
    }

    @Override
    public String resource() {
        return "slow-character.txt";
    }

    @Override
    public Integer idx() {
        return 3;
    }
}
