package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

//	����1�������ģ���ǰ���ڶ��󡣲���2�����ݿ�����֡�����3:�α깤������null������4:sqlite�汾����3
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

//	������DataBaseHelper����ʱ������Ĳ���2���ݿ�����������ڣ���ִ�����淽������������ڣ�ִ�����档
//	������
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table user(_id integer primary key autoincrement,name varchar(20),age integer)";
		db.execSQL(sql);
	}
//��sqlite�汾����ʱ�����ø÷���
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
