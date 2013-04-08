package pl.mobilla.plumber;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class LeakByStatic extends Activity {
	private static Context leakingContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plumber_leak_by_static);
		
		leakingContext = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_plumber_leak_by_static, menu);
		return true;
	}

}
