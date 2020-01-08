package shop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import shop.bean.CartBean;
import shop.utils.DBUtils;

public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    public SQLiteHelper(Context context) {
        super(context, DBUtils.DATABASE_NAME,null,DBUtils.DATABASE_VERION);
        sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DBUtils.DATABASE_TABLE+" ( "+DBUtils.CARTPAD_ID+" integer primary key autoincrement, "+DBUtils.CARTPAD_NAME+" VARCHAR(100), "+ DBUtils.CARTPAD_PRICE+" VARCHAR(100), "+DBUtils.CARTPAD_INTRDUCTION+" VARCHAR(100)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    //添加
    public boolean insertData(String CarName, String CartPice, String CartIntroduction){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBUtils.CARTPAD_NAME,String.valueOf(CarName));
        contentValues.put(DBUtils.CARTPAD_PRICE,String.valueOf(CartPice));
        contentValues.put(DBUtils.CARTPAD_INTRDUCTION,String.valueOf(CartIntroduction));
        return
                sqLiteDatabase.insert(DBUtils.DATABASE_TABLE,null,contentValues)>0;
    }
    //查询
    public List<CartBean>query(){
        List<CartBean> list=new ArrayList<CartBean>();
        Cursor cursor=sqLiteDatabase.query( DBUtils.DATABASE_TABLE,null,null,null,null,null,DBUtils.CARTPAD_ID+" desc");
        if(cursor!=null){
            while(cursor.moveToNext()){
                CartBean cartBean=new CartBean();
                String id=String.valueOf(cursor.getInt(cursor.getColumnIndex(DBUtils.CARTPAD_ID)));
                String name=cursor.getString(cursor.getColumnIndex(DBUtils.CARTPAD_NAME));
                String price=cursor.getString(cursor.getColumnIndex(DBUtils.CARTPAD_PRICE));
                String introduction=cursor.getString(cursor.getColumnIndex(DBUtils.CARTPAD_INTRDUCTION));
                cartBean.setId(id);
                cartBean.setCar_name(name);
                cartBean.setCar_price(price);
                cartBean.setCar_introduction(introduction);
                list.add(cartBean);
            }
            cursor.close();
        }

        return list;
    }
    //删除
    public boolean deleteData(String id){
        String sql=DBUtils.CARTPAD_ID+"=?";
        String[] contentValuesArray=new String[] {String.valueOf(id)};
        return sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,sql,contentValuesArray)>0;
    }

}



















