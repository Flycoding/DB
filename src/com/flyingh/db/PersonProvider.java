package com.flyingh.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
	private static final int PERSONS = 1;
	private DBOpenHelper dbOpenHelper;
	private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		uriMatcher.addURI("com.flyingh.providers.PersonProvider", "person",
				PERSONS);
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(getContext(), "flyingh.db", null, 3);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		if (uriMatcher.match(uri) == PERSONS) {
			SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
			long rowId = db.insert("person", null, values);
			return ContentUris.withAppendedId(uri, rowId);
		}
		throw new IllegalArgumentException("uknown uri:" + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
