package slt.lmb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MoleGame extends Thread {

	final static public int LOWERBOUND = 0;
	final static public int UPPERBOUND = 8;

	private int upperBound;
	
	private Handler handler;

	public enum GameState{RUNNING, STOPPED};

	private GameState gstate;

	public MoleGame(Handler handler) {
		super();
		this.upperBound = UPPERBOUND;
		gstate = GameState.RUNNING;
		this.handler=handler;
	}

	@Override
	public void run() {

		while(gstate == GameState.RUNNING) {

			int newPosition;
			// genero posizione random
			newPosition=(int) (Math.random()*(upperBound+1));
			//setto la nuova posizione
			nextStep(newPosition);

			//attendo tot tempo
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	private void nextStep(int newPosition){
		Message msg = handler.obtainMessage();
		Bundle b = new Bundle();
		b.putInt("newPosition", newPosition);
		msg.setData(b);
		handler.sendMessage(msg);
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public String getGameState() {
		return gstate.name();
	}

	public void setState(String state) {
		if (state.equals(GameState.RUNNING.name()))	this.gstate = GameState.RUNNING;
		else this.gstate = GameState.STOPPED;
			
	}
	
	

}
