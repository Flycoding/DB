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
	private static final int PERSON = 2;
	private DBOpenHelper dbOpenHelper;
	private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		uriMatcher.addURI("com.flyingh.providers.PersonProvider", "person",
				PERSONS);
		// uriMatcher.addURI("com.flyingh.providers.PersonProvider", "person/#",
		// PERSON);
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(getContext(), "flyingh.db", null, 3);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if (uriMatcher.match(uri) == PERSONS) {
			return dbOpenHelper.getReadableDatabase()
					.query("person", projection, selection, selectionArgs,
							null, null, sortOrder);
		} else if (uriMatcher.match(uri) == PERSON) {
			return dbOpenHelper.getReadableDatabase().query("person",
					projection, getWhereClause(uri, selection), selectionArgs,
					null, null, sortOrder);
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		if (uriMatcher.match(uri) == PERSONS) {
			return "vnd.android.cursor.dir/person";
		} else if (uriMatcher.match(uri) == PERSON) {
			return "vnd.android.cursor.item/person";
		}
		throw new IllegalArgumentException("uknown uri:" + uri);
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
		if (uriMatcher.match(uri) == PERSONS) {
			return dbOpenHelper.getWritableDatabase().delete("person",
					selection, selectionArgs);
		} else if (uriMatcher.match(uri) == PERSON) {
			return dbOpenHelper.getWritableDatabase().delete("person",
					getWhereClause(uri, selection), selectionArgs);
		}
		return 0;
	}

	private String getWhereClause(Uri uri, String selection) {
		String where = "id=" + ContentUris.parseId(uri);
		if (isEmpty(selection)) {
			return where;
		}
		return where + " and " + selection;
	}

	private boolean isEmpty(String selection) {
		return selection == null || selection.trim().length() > 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if (uriMatcher.match(uri) == PERSONS) {
			return dbOpenHelper.getWritableDatabase().update("person", values,
					selection, selectionArgs);
		} else if (uriMatcher.match(uri) == PERSON) {
			return dbOpenHelper.getWritableDatabase().update("person", values,
					getWhereClause(uri, selection), selectionArgs);
		}
		return 0;
	}

}
