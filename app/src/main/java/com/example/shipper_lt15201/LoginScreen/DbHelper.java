package com.example.shipper_lt15201.LoginScreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "shipper.db";
    private static final String TABLE_NAME = "shipper";
    private static final String COLUMN1 = "ShipID";
    private static final String COLUMN2 = "ShipName";
    private static final String COLUMN3 = "ShipImage";
    private static final String COLUMN4 = "ShipPhone";
    private static final String COLUMN5 = "ShipNumberCar";
    private static final String COLUMN6 = "Status";
    private static final String COLUMN7 = "Token";
    private static final String COLUMN8 = "StoreID";
    private String   CREATE_SHIPPER_TABLE = "CREATE TABLE " + TABLE_NAME+"("+COLUMN1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN2+" TEXT,"+COLUMN3+" INTEGER,"+COLUMN4+" TEXT,"+COLUMN5+" TEXT,"+COLUMN6+" TEXT,"+COLUMN7+" BOOLEAN"+","+COLUMN8+" INTEGER"+")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private String sql = "SELECT * FROM " + TABLE_NAME;
    private String delete = "DELETE FROM " + TABLE_NAME;


    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SHIPPER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addUser(ModelShipper shipper){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1,shipper.getShipID());
        values.put(COLUMN2,shipper.getShipName());
        values.put(COLUMN3,shipper.getShipPhone());
        values.put(COLUMN4,shipper.getShipImage());
        values.put(COLUMN5, shipper.getShipNumberCar());
        values.put(COLUMN6,shipper.getStatus());
        values.put(COLUMN7,shipper.getToken());
        database.insert(TABLE_NAME,null,values);
        database.close();
    }

    public ModelShipper getUser(String...a){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(sql,a);
        ModelShipper shipper = new ModelShipper();
        while (c.moveToNext()){
            shipper.setShipID(c.getInt(c.getColumnIndex(COLUMN1)));
            shipper.setShipName(c.getString(c.getColumnIndex(COLUMN2)));
            shipper.setShipPhone(c.getInt(c.getColumnIndex(COLUMN3)));
            shipper.setShipImage(c.getString(c.getColumnIndex(COLUMN4)));
            shipper.setShipNumberCar(c.getString(c.getColumnIndex(COLUMN5)));
            shipper.setStatus(c.getString(c.getColumnIndex(COLUMN6)));
            shipper.setToken(c.getString(c.getColumnIndex(COLUMN7)));
            shipper.setStoreID(c.getInt( c.getColumnIndex( COLUMN8 ) ));
        }
        return shipper;
    }

    public void delete(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(delete);
    }
}
