package com.flyingh.service.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.flyingh.db.DBOpenHelper;
import com.flyingh.service.PersonService;
import com.flyingh.vo.Person;

public class PersonServiceImpl implements PersonService {
	private DBOpenHelper dbOpenHelper;

	public PersonServiceImpl(Context context) {
		super();
		this.dbOpenHelper = new DBOpenHelper(context, "flyingh.db", null, 2);
	}

	@Override
	public void save(Person person) {
		dbOpenHelper.getWritableDatabase().execSQL(
				"insert into person(name,age,amount) values(?,?,?)",
				new Object[] { person.getName(), person.getAge(),
						person.getAmount() });
	}

	@Override
	public void update(Person person) {
		dbOpenHelper.getWritableDatabase().execSQL(
				"update person set name=?,age=?,amount=? where id=?",
				new Object[] { person.getName(), person.getAge(),
						person.getAmount(), person.getId() });
	}

	@Override
	public void delete(int id) {
		dbOpenHelper.getWritableDatabase().execSQL(
				"delete from person where id=?", new Object[] { id });
	}

	@Override
	public Person get(int id) {
		Cursor cursor = dbOpenHelper.getReadableDatabase().rawQuery(
				"select * from person where id=?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToFirst()) {
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
		Cursor cursor = dbOpenHelper.getReadableDatabase().rawQuery(
				"select * from person", new String[] {});
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
		Cursor cursor = dbOpenHelper.getReadableDatabase()
				.rawQuery(
						"select * from person order by id limit ?,?",
						new String[] { String.valueOf(first),
								String.valueOf(maxResult) });
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

}
