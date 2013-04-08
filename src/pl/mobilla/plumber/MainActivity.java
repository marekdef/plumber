package pl.mobilla.plumber;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	private ArrayAdapter<Class<?>> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		arrayAdapter = new ArrayAdapter<Class<?>>(this, android.R.layout.simple_list_item_1);
		setListAdapter(arrayAdapter);
		
		arrayAdapter.add(PlumberActivity.class);
		arrayAdapter.add(StringsActivity.class);
		arrayAdapter.add(LeakByThread.class);
		arrayAdapter.add(LeakByHandler.class);
		arrayAdapter.add(LeakByDrawable.class);
		arrayAdapter.add(LeakByStatic.class);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				;
				Class<?> cls = arrayAdapter.getItem(arg2);
				Intent intent = new Intent(MainActivity.this.getApplicationContext(), cls);
				startActivity(intent);
				
			}
			
		});
	}

	
	
}
