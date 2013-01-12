package com.flyingh.db;

import android.test.AndroidTestCase;

public class DBOpenHelperTest extends AndroidTestCase {
	public void testDB() {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext(),
				"flyingh.db", null, 2);
		dbOpenHelper.getWritableDatabase();
	}
}
