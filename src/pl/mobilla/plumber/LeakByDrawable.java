package pl.mobilla.plumber;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class LeakByDrawable extends Activity {

	private static Drawable leakingBackground;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_leak_by_drawable);

		TextView label = (TextView) findViewById(R.id.textViewDrawable);

		if (leakingBackground == null) {
			leakingBackground = this.getResources().getDrawable(R.drawable.cog);
		}
		label.setBackgroundDrawable(leakingBackground);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_leak_by_drawable, menu);
		return true;
	}

}
