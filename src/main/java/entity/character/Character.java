package entity.character;

public interface Character {
    String getName();
    Integer getHealth();
    Integer getSpeed();
    String resource();
    Integer idx();

    default String log(){
        return String.format("Character: %s {HP:%d; Speed:%d}", getName(), getHealth(), getSpeed());
    }
}
