package com.flyingh.service.impl;

import java.util.List;

import com.flyingh.vo.Person;

import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest2 extends AndroidTestCase {

	private static final int COUNT = 18;
	private static final String TAG = "PersonServiceTest2";
	PersonServiceImpl2 ps = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ps = new PersonServiceImpl2(getContext());
	}

	public void testSave() {
		PersonServiceImpl2 ps = new PersonServiceImpl2(getContext());
		for (int i = 0; i < COUNT; i++) {
			Person person = new Person();
			person.setName("haa" + i);
			person.setAge(18 + i);
			ps.save(person);
		}
	}

	public void testUpdate() {
		PersonServiceImpl2 ps = new PersonServiceImpl2(getContext());

		Person person = new Person("hee", 22, 2000);
		person.setId(2);
		ps.update(person);

	}

	public void testDelete() {
		PersonServiceImpl2 ps = new PersonServiceImpl2(getContext());
		ps.delete(1);
	}

	public void testGet() {
		PersonServiceImpl2 ps = new PersonServiceImpl2(getContext());
		Person person = ps.get(2);
		Log.i(TAG, person.toString());
	}

	public void testGetAll() {
		// PersonServiceImpl2 ps = new PersonServiceImpl2(getContext());
		List<Person> persons = ps.getAll();
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}

	public void testPager() {
		PersonServiceImpl2 ps = new PersonServiceImpl2(getContext());
		List<Person> persons = ps.getPager(3, 10);
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}

}
