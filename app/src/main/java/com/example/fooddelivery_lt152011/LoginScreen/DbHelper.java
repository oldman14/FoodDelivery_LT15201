package com.example.fooddelivery_lt152011.LoginScreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN1 = "UserID";
    private static final String COLUMN2 = "UserName";
    private static final String COLUMN3 = "UserPhone";
    private static final String COLUMN4 = "UserMail";
    private static final String COLUMN5 = "UserBirthday";
    private static final String COLUMN6 = "UserImage";
    private static final String COLUMN7 = "Token";
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME+"("+COLUMN1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN2+" TEXT,"+COLUMN3+" INTEGER,"+COLUMN4+" TEXT,"+COLUMN5+" TEXT,"+COLUMN6+" TEXT,"+COLUMN7+" TEXT"+")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private String sql = "SELECT * FROM " + TABLE_NAME;
    private String delete = "DELETE FROM " + TABLE_NAME;


    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addUser(ModelUser user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1,user.getUserID());
        values.put(COLUMN2,user.getUserName());
        values.put(COLUMN3,user.getUserPhone());
        values.put(COLUMN4,user.getUserMail());
        values.put(COLUMN5, String.valueOf(user.getUserBirthday()));
        values.put(COLUMN6,user.getUserImage());
        values.put(COLUMN7,user.getToken());
        database.insert(TABLE_NAME,null,values);
        database.close();
    }

    public ModelUser getUser(String...a){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(sql,a);
        ModelUser user = new ModelUser();
        while (c.moveToNext()){
            user.setUserID(c.getInt(c.getColumnIndex(COLUMN1)));
            user.setUserName(c.getString(c.getColumnIndex(COLUMN2)));
            user.setUserPhone(c.getInt(c.getColumnIndex(COLUMN3)));
            user.setUserMail(c.getString(c.getColumnIndex(COLUMN4)));
            user.setUserBirthday(c.getString(c.getColumnIndex(COLUMN5)));
            user.setUserImage(c.getString(c.getColumnIndex(COLUMN6)));
            user.setToken(c.getString(c.getColumnIndex(COLUMN7)));
        }
        return user;
    }

    public void delete(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(delete);
    }

    public void update(String phone, String name, String mail,String birthday, String image){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN2,name); //These Fields should be your String values of actual column names
        cv.put(COLUMN4,mail);
        cv.put(COLUMN5,birthday);
        cv.put(COLUMN6,image);
        database.update(TABLE_NAME, cv, "UserPhone = ?", new String[]{phone});
    }
}
