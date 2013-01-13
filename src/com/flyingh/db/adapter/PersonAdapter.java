package com.flyingh.db.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flyingh.db.R;
import com.flyingh.vo.Person;

public class PersonAdapter extends BaseAdapter {
	private Context context;
	private List<Person> persons;
	private int resource;

	public PersonAdapter(Context context, List<Person> persons, int resource) {
		super();
		this.context = context;
		this.persons = persons;
		this.resource = resource;
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(resource, null);
			ViewCache viewCache = new ViewCache();
			viewCache.idText = (TextView) convertView.findViewById(R.id.id);
			viewCache.nameText = (TextView) convertView.findViewById(R.id.name);
			viewCache.ageText = (TextView) convertView.findViewById(R.id.age);
			viewCache.amountText = (TextView) convertView
					.findViewById(R.id.amount);
			convertView.setTag(viewCache);
		}
		ViewCache viewCache = (ViewCache) convertView.getTag();
		TextView idText = viewCache.idText;
		TextView nameText = viewCache.nameText;
		TextView ageText = viewCache.ageText;
		TextView amountText = viewCache.amountText;

		Person person = persons.get(position);
		idText.setText(String.valueOf(person.getId()));
		nameText.setText(person.getName());
		ageText.setText(String.valueOf(person.getAge()));
		amountText.setText(String.valueOf(person.getAmount()));

		return convertView;
	}

	private final class ViewCache {
		private TextView idText;
		private TextView nameText;
		private TextView ageText;
		private TextView amountText;
	}

}
