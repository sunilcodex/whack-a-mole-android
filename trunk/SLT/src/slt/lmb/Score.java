/**
 * 
 */
package slt.lmb;



/**
 * @author lorenzo
 *
 */
public class Score extends Thread {
	


	private int score;
	
	public Score(){

		
		this.score=0;
		
	}
	
	@Override
	public void run() {
		
		super.run();
		
		//incremento il punteggio
		AddScore();
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int AddScore(){
		
		return score++;
	}
	
	
	
		
	
	
	


	
}
