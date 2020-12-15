

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

 class MyDBHandler extends SQLiteOpenHelper {
    //information  of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB.db";
    public static final String TABLE_NAME = "Doctor";
    public static final String COLUMN_ID = "DoctorID";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_FEES = "Fees";

    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select*FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, doctor.getid());
        values.put(COLUMN_TYPE, doctor.gettype());
        values.put(COLUMN_ADDRESS, doctor.getlocation());
        values.put(COLUMN_FEES, doctor.getfees());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public doctor findHandler(String location) {

                String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_ADDRESS + " = " + "'" + location + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        doctor doctor = new doctor();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            doctor.setid(Integer.parseInt(cursor.getString(0)));
            doctor.settype(cursor.getString(1));
            doctor.setlocation(cursor.getString(1));
            doctor.setfees(Integer.parseInt(cursor.getString(0)));
            cursor.close();
        } else {
            doctor = null;
        }
        db.close();
        return doctor;
}



    public boolean deleteHandler(int docid) {

        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(docid) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        doctor doctor = new doctor();
        if (cursor.moveToFirst()) {
            doctor.setid(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",new String[] {
                String.valueOf(doctor.getid())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(int docid, String type,String location,int fees) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, docid);
        args.put(COLUMN_TYPE, type);
        args.put(COLUMN_ADDRESS, location);
        args.put(COLUMN_FEES, fees);

        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + docid, null) > 0;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE="CREATE TABLE"+TABLE_NAME+"("+COLUMN_ID+"INTEGER PRIMARY KEY,"+COLUMN_TYPE+"TEXT,"+COLUMN_ADDRESS+"TEXT,"+COLUMN_FEES+"INTEGER )";
        db.execSQL(CREATE_TABLE);
    }
}
