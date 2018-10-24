package com.example.sqlitedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
	EditText nameEdit,ageEdit,idEdit;
	ListView lv;
	
	DataBaseHelper dbHelper ;
	SQLiteDatabase db;//通过该对象可以对表执行增删改查操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = (EditText)findViewById(R.id.nameEdit);
        ageEdit = (EditText)findViewById(R.id.ageEdit);
        idEdit = (EditText)findViewById(R.id.idEdit);
        lv = (ListView)findViewById(R.id.listView1);
        
//        根据DataBaseHelper对象获取SQLiteDatabase对象，为了对表进行增删改查
        dbHelper = new DataBaseHelper(this, "mytest", null, 3);
        db = dbHelper.getWritableDatabase();
        
    }
    
    public void my(View v){
//    	获取输入的姓名和年龄
    	String name = nameEdit.getText().toString();
    	String age = ageEdit.getText().toString();
    	String id = idEdit.getText().toString();
    	ContentValues values = new ContentValues();
		values.put("name", name);//将数据封装到ContentValues中，key值必须是表中的列名。
		values.put("age", age);
    	switch (v.getId()) {
		case R.id.addBtn:
//			将输入的数据添加到数据库中
			db.insert("user", "id", values);
			break;

		case R.id.queryBtn:
			
			break;
		case R.id.updateBtn:
			db.update("user", values, "_id=?", new String[]{id});
			break;
		case R.id.delBtn:
			
			db.delete("user", "_id=?", new String[]{id});
			break;
		}
    	query();
    }
    private void query(){
//		从数据库中取值。
		Cursor c = db.rawQuery("select * from user", null);
		/*********访问Cursor中数据 start*************/
		if(c.getCount()>0){
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToNext();
				String s = "";
				for (int j = 0; j < c.getColumnCount(); j++) {
					s += c.getString(j);
				}
				Log.i("==================", s);
				
			}
		}
		/*********访问Cursor中数据 end*************/
		startManagingCursor(c);//关闭游标，用Activity管理游标。
//		使用SimpleCursorAdapter，要求表中主键必须_id
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, 
				R.layout.myitem, 
				c, //数据源
				new String[]{"_id","name","age"}, //从数据源Cursor对象c中获取数据，也就是表中的列名
				new int[]{R.id.idView,R.id.nameView,R.id.ageView});//是myitem布局文件中的ui组件的资源id
		lv.setAdapter(adapter);
    }

}
