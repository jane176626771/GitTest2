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
	SQLiteDatabase db;//ͨ���ö�����ԶԱ�ִ����ɾ�Ĳ����
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = (EditText)findViewById(R.id.nameEdit);
        ageEdit = (EditText)findViewById(R.id.ageEdit);
        idEdit = (EditText)findViewById(R.id.idEdit);
        lv = (ListView)findViewById(R.id.listView1);
        
//        ����DataBaseHelper�����ȡSQLiteDatabase����Ϊ�˶Ա������ɾ�Ĳ�
        dbHelper = new DataBaseHelper(this, "mytest", null, 3);
        db = dbHelper.getWritableDatabase();
        
    }
    
    public void my(View v){
//    	��ȡ���������������
    	String name = nameEdit.getText().toString();
    	String age = ageEdit.getText().toString();
    	String id = idEdit.getText().toString();
    	ContentValues values = new ContentValues();
		values.put("name", name);//�����ݷ�װ��ContentValues�У�keyֵ�����Ǳ��е�������
		values.put("age", age);
    	switch (v.getId()) {
		case R.id.addBtn:
//			�������������ӵ����ݿ���
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
//		�����ݿ���ȡֵ��
		Cursor c = db.rawQuery("select * from user", null);
		/*********����Cursor������ start*************/
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
		/*********����Cursor������ end*************/
		startManagingCursor(c);//�ر��α꣬��Activity�����αꡣ
//		ʹ��SimpleCursorAdapter��Ҫ�������������_id
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, 
				R.layout.myitem, 
				c, //����Դ
				new String[]{"_id","name","age"}, //������ԴCursor����c�л�ȡ���ݣ�Ҳ���Ǳ��е�����
				new int[]{R.id.idView,R.id.nameView,R.id.ageView});//��myitem�����ļ��е�ui�������Դid
		lv.setAdapter(adapter);
    }

}
