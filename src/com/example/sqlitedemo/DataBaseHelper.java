package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

//	参数1：上下文，当前窗口对象。参数2：数据库的名字。参数3:游标工厂，传null。参数4:sqlite版本，传3
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

//	当创建DataBaseHelper对象时，传入的参数2数据库名称如果存在，不执行下面方法。如果不存在，执行下面。
//	创建表。
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table user(_id integer primary key autoincrement,name varchar(20),age integer)";
		db.execSQL(sql);
	}
//当sqlite版本升级时，调用该方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
