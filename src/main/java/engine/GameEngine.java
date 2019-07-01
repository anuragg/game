package engine;

import entity.GameSession;
import entity.LoadGameSession;
import entity.character.Character;
import service.CharacterService;
import service.HighScoreService;
import service.LoadGameService;
import ui.KeyBoardHandler;
import ui.UIScreen;

import static service.GameConstants.*;

public class GameEngine implements GameListenerInterface {
    private UIScreen ui;
    private KeyBoardHandler keyBoardHandler;
    private LoadGameService loadGameService;
    private HighScoreService highScoreService;
    private CharacterService characterService;
    private String username;
    private GameSession session;

    public GameEngine() {
        ui = new UIScreen();
        keyBoardHandler = new KeyBoardHandler();
        keyBoardHandler.setListener(this);
        loadGameService = new LoadGameService();
        highScoreService = new HighScoreService();
        characterService = new CharacterService();
    }

    public void start() {
        this.welcome();
    }

    @Override
    public void welcome() {
        ui.banner();
        keyBoardHandler.listenWelcomeForm();
    }

    @Override
    public void login() {
        ui.login();
        keyBoardHandler.waitForLoginName();
    }

    @Override
    public void end() {
        System.exit(0);
    }

    @Override
    public void load() {
        ui.load(loadGameService.getSessions());
        keyBoardHandler.handleLoadGamesScreen();
    }

    @Override
    public void high() {
        ui.highScores(highScoreService.getHighScores());
        keyBoardHandler.handleHighScoresScreen();
    }

    @Override
    public void username(String username) {
        this.username = username;
        System.out.println("Username is : " + this.username);
        ui.chooseCharacter(characterService.getCharacters());
        keyBoardHandler.waitForCharecter();
    }

    @Override
    public void character(Integer i) {
        Character character = characterService.get(i);
        this.session = new GameSession(username,0, character.getHealth(), character);
        System.out.println("Character chosen is : " + this.session.getCharacter().log());
        this.play();
    }

    @Override
    public void save() {
        loadGameService.save(session);
        welcome();
    }

    @Override
    public void resume(int i) {
        LoadGameSession session = loadGameService.get(i);
        if (null != session) {
            this.session = new GameSession(session.getUsername(), session.getScore(), session.getHealth(), session.getCharacter());
            ui.loadGame(session.getUsername(), session.getCharacter().getName(), session.getScore().toString(), session.getHealth().toString());
            keyBoardHandler.handleContinue();
        }
    }

    private Boolean shoot = false;

    @Override
    public void shoot() {
        shoot = true;
    }

    @Override
    public void play() {
        ui.clearScreen();
        keyBoardHandler.asyncWaitForShoot();

        int shootTime = 0;
        int xAxisMax = X_MAX;
        int yAxisMax = Y_MAX;
        int xAxisPointWhereYmeet = X_SUBSET;

        for (int time=0; time<TIME_MAX; time++) {
            if(shoot) {
                shootTime = time;
                shoot = false;
            }

            int xAxisPosition = time >= xAxisMax ? xAxisMax-1 : time;
            int yAxisPosition = shootTime > 0 ? time-shootTime >= yAxisMax ? yAxisMax-1 : time-shootTime : 0;

            ui.playScreen(xAxisMax, xAxisPosition, yAxisMax, yAxisPosition, xAxisPointWhereYmeet);

            if( xAxisMax == xAxisPosition + 1 ) { //target reaches front
                processMiss();
            } else if ( yAxisMax == yAxisPosition + 1 ) { //arrow reaches top
                if ( xAxisPosition == xAxisPointWhereYmeet ) { //target meets arrow
                    processHit();
                } else {
                    processMiss();
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //fallback if nothing happens.
        processMiss();
    }

    private void processMiss() {
        session.addHealth(MISS_POINT);
        if (session.isDead()) {
            highScoreService.save(session);
            ui.gameover(session.getUsername(), session.getCharacter().getName(), session.getScore().toString(), session.getHealth().toString());
            keyBoardHandler.handleGameOver();
        } else {
            ui.miss(session.getUsername(), session.getCharacter().getName(), session.getScore().toString(), session.getHealth().toString());
            keyBoardHandler.handleContinue();
        }
    }

    private void processHit() {
        session.addScore(HIT_POINT);
        session.addHealth(HIT_HP);
        ui.hit(session.getUsername(), session.getCharacter().getName(), session.getScore().toString(), session.getHealth().toString());
        keyBoardHandler.handleContinue();
    }

}
