package slt.lmb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Slt extends Activity {

	private GraphicsMole im;
	private int currentMolePos = -1;
	private int scoreCurr = 0;
	private int lifeCurr = 3;
	private Handler step;
	private Handler Update;
	private MoleGame mg;
	private boolean isMole = true;

	private Mole mole = new Mole();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startgame);
		final TextView scoreTview = (TextView) findViewById(R.id.Score);
		final TextView lifeTview = (TextView) findViewById(R.id.Life);
		final GridView gw = (GridView) findViewById(R.id.gridview);
		step = new ChangeImage();
		Update = new Handler();
		im = new GraphicsMole(this);
		gw.setAdapter(im);

		// ----------------------------

		gw.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, final View v,
					int position, long id) {
				if (currentMolePos == position) {

					Update.post(new Runnable() {

						@Override
						public void run() {

							if (isMole == true) {
								scoreCurr = scoreCurr + 1;
								scoreTview.setText("Score: " + scoreCurr);
								scoreTview.refreshDrawableState();
							} else {
								lifeCurr = lifeCurr - 1;
								lifeTview.setText("Life: " + lifeCurr);
								lifeTview.refreshDrawableState();
								if (lifeCurr == 0) {
									mg.stopThread();
									
									Intent gameOverIntent = new Intent(
											Slt.this, GameOver.class);
									startActivity(gameOverIntent);
									finish();
																	
								}
							}

						}
					});
				}
			}
		});
		mg = new MoleGame(step);
		mg.start();

	}
	
	public Activity getActivity(){
		
		return this.getActivity();
	}

	// ---------------------------
	private class ChangeImage extends Handler {

		Hole hole = new Hole();
		private int oldPosition = -1;
		private final double prob = 0.7;

		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			currentMolePos = bundle.getInt("newPosition");
			if (Math.random() < prob) {
				im.setItem(currentMolePos, mole.getTalpa());
				isMole = true;
			} else {
				im.setItem(currentMolePos, mole.getFarfalla());
				isMole = false;
			}

			if (oldPosition != -1 && currentMolePos != oldPosition) {
				im.setItem(oldPosition, hole.getHole());
			}

			oldPosition = currentMolePos;
			im.notifyDataSetChanged();
		}
	}
	

}