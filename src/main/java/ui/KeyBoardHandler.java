package ui;

import engine.GameListenerInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyBoardHandler {
    private GameListenerInterface listener;

    public void setListener(GameListenerInterface listener) {
        this.listener = listener;
    }

    public void waitForCharecter() {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            Integer i = reader.read();
            listener.character(i-48);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenWelcomeForm() {
        try {
            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            int i = reader.read();
            handleWelcomeInput(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForLoginName() {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            String username = reader.readLine();
            listener.username(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleWelcomeInput(Integer input) {
        switch (input) {
            //press q or Q to quit
            case 113:
            case 81:
                listener.end();
                break;
            //press n or N to start a new game.
            case 110:
            case 78:
                listener.login();
                break;
            //press l or L to load saved games.
            case 108:
            case 76:
                listener.load();
                break;
            //press h or H to see high scores.
            case 104:
            case 72:
                listener.high();
                break;
        }
    }

    public void handleLoadGamesScreen() {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            int i = reader.read();
            switch (i) {
                //press b or B to go back
                case 66:
                case 98:
                    listener.welcome();
                    break;
                default:
                    listener.resume(i-48);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleHighScoresScreen() {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            switch (reader.read()) {
                //press b or B to go back
                case 66:
                case 98:
                    listener.welcome();
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleContinue() {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            int key = reader.read();
            switch (key) {
                //press s or S to save
                case 83:
                case 115:
                    listener.save();
                    break;
                case 87:
                case 119:
                    listener.welcome();
                    break;
                default:
                    listener.play();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleGameOver() {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            int key = reader.read();
            switch (key) {
                //press w or W to go to welcome screen.
                case 87:
                case 119:
                    listener.welcome();
                    break;
                default:
                    listener.welcome();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void asyncWaitForShoot() {
        new Thread(() -> {
            try {
                InputStream in = System.in;
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                reader.read();
                listener.shoot();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
