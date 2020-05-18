package game;

import java.util.List;

import cases.Cases;
import warriors.characters.Characters;
import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.engine.Warriors;

public class Game implements GameState{

	private String playerName;
	private String gameId = "game_"+ Warriors.gameList.size() ;
	private GameStatus gameStatus = GameStatus.IN_PROGRESS;
	private Hero hero;
	private Map map;
	private String lastLog;
	private int currentCase;

	public Game(String playerName,Hero hero,Map map) {
		this.playerName = playerName;
		this.hero = hero;
		this.map = map;
		this.getGameId();
		this.getGameStatus();
		this.currentCase=0;
		this.lastLog="la partie commence";
	}

	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return playerName;
	}

	@Override
	public String getGameId() {
		// TODO Auto-generated method stub
		return gameId;
	}

	public GameStatus setGameStatus(GameStatus status) {
		return this.gameStatus = status;
	}

	@Override
	public GameStatus getGameStatus() {
		// TODO Auto-generated method stub
		return gameStatus;
	}

	@Override
	public Hero getHero() {
		// TODO Auto-generated method stub
		return hero;
	}

	@Override
	public Map getMap() {
		// TODO Auto-generated method stub
		return map;
	}

	public String setLastLog(String log) {
		return this.lastLog = log;
	}

	@Override
	public String getLastLog() {
		// TODO Auto-generated method stub
		return lastLog;
	}

	public int setCurrentCase(int dice) {
		return this.currentCase = getCurrentCase()+dice;
	}

	@Override
	public int getCurrentCase() {
		// TODO Auto-generated method stub
		return currentCase;
	}


	public Characters getChar() {
		// TODO Auto-generated method stub
		return (Characters)hero;
	}

	public String moveHero(int dice, List<Cases> caseList) {
		// TODO Auto-generated method stub
		String action;
		if((getCurrentCase()+dice)>=64) {
			setGameStatus(GameStatus.FINISHED);
			action="la partie est fini";
		} else {
			setCurrentCase(dice);
			caseList.get(getCurrentCase()).event(getChar());;
			if(getChar().getLife()<=0) {
				setGameStatus(GameStatus.GAME_OVER);
				action = "vous êtes mort ";
			}else {
				action=String.format( "Vous avez fait un lancé de %d \nVous etes sur la case n°%d\nil y a %s\nil vous reste %d pv\nvous avez %d pa",dice,getCurrentCase(),caseList.get(getCurrentCase()).toString(),getChar().getLife(),getChar().getAttackLevel());
			}
		}
		return action;
	}


}