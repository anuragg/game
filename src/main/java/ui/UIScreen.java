package ui;

import entity.GameSession;
import entity.LoadGameSession;
import entity.character.Character;
import entity.character.FastCharacter;
import entity.character.MediumCharacter;
import entity.character.SlowCharacter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UIScreen {
    public void banner() {
        clearScreen();
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("banner.txt");
        try (Scanner scanner = new Scanner(inputStream, "utf-8")) {
            System.out.println(scanner.useDelimiter("\\A").next());
        }
    }

    public void loadGame(String username, String character, String score, String power) {
        showHitMiss("load-game.txt", username, character, score, power);
    }

    public void hit(String username, String character, String score, String power) {
        showHitMiss("hit.txt", username, character, score, power);
    }

    public void miss(String username, String character, String score, String power) {
        showHitMiss("miss.txt", username, character, score, power);
    }

    private void showHitMiss(String page, String username, String character, String score, String power) {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(page);
        this.clearScreen();
        try (Scanner scanner = new Scanner(inputStream, "utf-8")) {
            String string = scanner.useDelimiter("\\A").next();
            string = string.replace("{username}", username);
            string = string.replace("{character}", character);
            string = string.replace("{score}", score);
            string = string.replace("{power}", power);
            System.out.println(string);
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("header.txt");
        try (Scanner scanner = new Scanner(inputStream, "utf-8")) {
            System.out.println(scanner.useDelimiter("\\A").next());
        }
    }

    public void login() {
        this.clearScreen();
        System.out.println("Enter username!");
    }

    public void load(List<LoadGameSession> sessions) {
        this.clearScreen();
        System.out.println("Load Saved Game!");
        System.out.println();
        System.out.printf("%10s %10s %10s %10s %10s", "Press", "Username", "Score", "Health", "Character");
        System.out.println();
        sessions.stream().forEach((session) -> {
            System.out.printf("%10s %10s %10s %10s %10s", session.getIdx(), session.getUsername(),
                    session.getScore().toString(), session.getHealth().toString(), session.getCharacter().getName());
            System.out.println();
        });
        System.out.println();
        System.out.println("Press b to Go back.");
    }

    public void highScores(List<GameSession> highScores) {
        this.clearScreen();
        System.out.println("High Scores!");
        System.out.println();
        System.out.printf("%10s %10s %10s ", "Username", "Score", "Character");
        System.out.println();
        highScores.stream().forEach((highScore) -> {
            System.out.printf("%10s %10s %10s ", highScore.getUsername(),
                    highScore.getScore().toString(), highScore.getCharacter().getName());
            System.out.println();
        });
        System.out.println();
        System.out.println("Press b to Go back.");
    }

    public Map<Integer, Character> chooseCharacter() {
        this.clearScreen();
        Map<Integer, Character> map = new HashMap<>();
        System.out.println("Choose your character!!");
        Integer i = 1;
        for (Character character : new Character[] { new SlowCharacter(), new MediumCharacter(), new FastCharacter() }) {
            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream(character.resource());
            try (Scanner scanner = new Scanner(inputStream, "utf-8")) {
                String display = scanner.useDelimiter("\\A").next();
                display = display.replace("{name}", character.getName());
                display = display.replace("{power}", character.getHealth().toString());
                display = display.replace("{speed}", character.getSpeed().toString());
                display = display.replace("{action}", i.toString());
                System.out.println(display);
            }
            map.put(i, character);
            i = i + 1;
        }
        return map;
    }

    public void gameover(String username, String character, String score, String power) {
        showHitMiss("gameover.txt", username, character, score, power);
    }

    public void playScreen(Integer xAxisMax, Integer xAxisPosition, Integer yAxisMax, Integer yAxisPosition, Integer xAxisPointWhereYmeet) {
        clearScreen();
        Boolean[] targetArr = new Boolean[xAxisMax];
        targetArr[xAxisPosition] = true;

        Boolean[] arrowArr = new Boolean[yAxisMax];
        arrowArr[yAxisPosition] = true;

        for(Boolean b : targetArr) {
            if(null != b && b) {
                System.out.printf( "%5s", "<>");
            } else {
                System.out.printf( "%5s", "");
            }
        }
        System.out.println();

        for(int i=0; i<yAxisMax; i++) {
            System.out.printf( "%"+xAxisPointWhereYmeet*5+"s %5s", "", null != arrowArr[yAxisMax-i-1] && arrowArr[yAxisMax-i-1] ? "^" : "");
            System.out.println();
        }
    }
}
