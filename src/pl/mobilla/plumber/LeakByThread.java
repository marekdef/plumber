package pl.mobilla.plumber;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LeakByThread extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plumber_leak_by_thread);
		
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ignore) {
				}
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_plumber_leak_by_thread, menu);
		return true;
	}

}
