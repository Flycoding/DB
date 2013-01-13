package com.flyingh.service.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.flyingh.db.DBOpenHelper;
import com.flyingh.service.PersonService;
import com.flyingh.vo.Person;

public class PersonServiceImpl2 implements PersonService {
	private DBOpenHelper dbOpenHelper;

	public PersonServiceImpl2(Context context) {
		dbOpenHelper = new DBOpenHelper(context, "flyingh.db", null, 3);
	}

	@Override
	public void save(Person person) {
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("age", person.getAge());
		values.put("amount", person.getAmount());
		dbOpenHelper.getWritableDatabase().insert("person", null, values);
	}

	@Override
	public void update(Person person) {
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("age", person.getAge());
		values.put("amount", person.getAmount());
		dbOpenHelper.getWritableDatabase().update("person", values, "id=?",
				new String[] { String.valueOf(person.getId()) });
	}

	@Override
	public void delete(int id) {
		dbOpenHelper.getWritableDatabase().delete("person", "id=?",
				new String[] { String.valueOf(id) });
	}

	@Override
	public Person get(int id) {
		Cursor cursor = dbOpenHelper.getReadableDatabase().query("person",
				null, "id=?", new String[] { String.valueOf(id) }, null, null,
				null);
		if (cursor.moveToNext()) {
			Person person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			person.setAmount(cursor.getInt(cursor.getColumnIndex("amount")));
			return person;
		}
		return null;
	}

	@Override
	public List<Person> getAll() {
		Cursor cursor = dbOpenHelper.getReadableDatabase().query("person",
				null, null, null, null, null, "id");
		List<Person> persons = new ArrayList<Person>();
		while (cursor.moveToNext()) {
			Person person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			person.setAmount(cursor.getInt(cursor.getColumnIndex("amount")));
			persons.add(person);
		}
		return persons;
	}

	@Override
	public List<Person> getPager(int first, int maxResult) {
		Cursor cursor = dbOpenHelper.getReadableDatabase().query("person",
				null, null, null, null, null, "id", first + "," + maxResult);
		List<Person> persons = new ArrayList<Person>();
		while (cursor.moveToNext()) {
			Person person = new Person();
			person.setId(cursor.getInt(cursor.getColumnIndex("id")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			person.setAmount(cursor.getInt(cursor.getColumnIndex("amount")));
			persons.add(person);
		}
		return persons;
	}

	@Override
	public void transferAccounts(int id1, int id2, int amount) {
	}
}
