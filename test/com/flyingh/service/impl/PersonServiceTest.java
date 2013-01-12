package com.flyingh.service.impl;

import java.util.List;
import java.util.Random;

import android.test.AndroidTestCase;
import android.util.Log;

import com.flyingh.service.PersonService;
import com.flyingh.vo.Person;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "com.flyingh.service.impl.PersonServiceTest";
	private static final int COUNT = 37;

	public void testSave() {
		PersonService personService = new PersonServiceImpl(getContext());
		for (int i = 0; i < COUNT; i++) {
			personService.save(new Person("haha" + i, new Random().nextInt(100)
					+ i));
		}
	}

	public void testUpdate() {
		Person person = new Person("wangwu", 25);
		person.setId(1);
		PersonService personService = new PersonServiceImpl(getContext());
		personService.update(person);
	}

	public void testDelete() {
		PersonService personService = new PersonServiceImpl(getContext());
		personService.delete(1);
	}

	public void testGetAll() {
		PersonService personService = new PersonServiceImpl(getContext());

		List<Person> persons = personService.getAll();
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}

	public void testGetPager() {
		PersonService personService = new PersonServiceImpl(getContext());
		List<Person> persons = personService.getPager(5, 7);
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}

	public void testGet() {
		PersonService personService = new PersonServiceImpl(getContext());

		Person person = personService.get(1);
		Log.i(TAG, person.toString());
	}
}
