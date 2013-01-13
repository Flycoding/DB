package com.flyingh.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.flyingh.db.adapter.PersonAdapter;
import com.flyingh.service.PersonService;
import com.flyingh.service.impl.PersonServiceImpl2;
import com.flyingh.vo.Person;

public class MainActivity extends Activity {
	private ListView listView;
	private PersonService ps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Cursor cursor = (Cursor) parent.getItemAtPosition(position);
				int _id = cursor.getInt(cursor.getColumnIndex("_id"));
				int id2 = cursor.getInt(cursor.getColumnIndex("id"));
				Toast.makeText(getApplicationContext(), _id+","+id2, Toast.LENGTH_SHORT).show();
				// Person person = (Person) parent.getItemAtPosition(position);
				// Toast.makeText(getApplicationContext(), person.toString(),
				// Toast.LENGTH_SHORT).show();
			}
		});
		ps = new PersonServiceImpl2(getApplicationContext());
		// display();
		display2();
		// display3();
	}

	@SuppressWarnings("unused")
	private void display3() {
		listView.setAdapter(new PersonAdapter(getApplicationContext(), ps
				.getAll(), R.layout.item));
	}

	@SuppressWarnings({ "deprecation" })
	private void display2() {
		Cursor cursor = ps.getCursor(0, 15);
		listView.setAdapter(new SimpleCursorAdapter(getApplicationContext(),
				R.layout.item, cursor, new String[] { "id", "name", "age",
						"amount" }, new int[] { R.id.id, R.id.name, R.id.age,
						R.id.amount }));
	}

	@SuppressWarnings("unused")
	private void display() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<Person> persons = ps.getPager(0, 15);
		for (Person person : persons) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", person.getId());
			map.put("name", person.getName());
			map.put("age", person.getAge());
			map.put("amount", person.getAmount());
			data.add(map);
		}
		listView.setAdapter(new SimpleAdapter(this, data, R.layout.item,
				new String[] { "id", "name", "age", "amount" }, new int[] {
						R.id.id, R.id.name, R.id.age, R.id.amount }));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
