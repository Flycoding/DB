package com.flyingh.service;

import java.util.List;

import android.database.Cursor;

import com.flyingh.vo.Person;

public interface PersonService {
	void save(Person person);

	void update(Person person);

	void delete(int id);

	Person get(int id);

	List<Person> getAll();

	List<Person> getPager(int first, int maxResult);

	void transferAccounts(int id1, int id2, int amount);

	Cursor getCursor(int first, int maxResult);

}
