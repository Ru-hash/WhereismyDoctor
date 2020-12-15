package com.rurab.whereismydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView t; EditText docid,type,location,fees,appointment,password;
    //public void open(View v){
      //  Intent i=new Intent(getApplicationContext(),.class);
        //    startActivity(i);

    //}

    public void login(View v){
        docid=findViewById(R.id.editText3);
        password=findViewById(R.id.editText5);
        MyDBHandler db=new MyDBHandler(this,null,null,1);

        doctor doc=db.findHandler2(docid.getText().toString(),password.getText().toString());
        if(doc==null) {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
        }
        else {
            String appointment="Today's appointment:";
            Intent i=new Intent(getApplicationContext(),Docres.class);
            Bundle b=new Bundle();
            b.putString("key",appointment);
            b.putString("user","doc2");
            i.putExtras(b);
            startActivity(i);
        }

    }

    public void book(View v)
    {

       String r="WERTYIOPLKJHGFDAZVBNM123456789";
       StringBuilder salt= new StringBuilder();
       Random ran=new Random();
       while (salt.length()<6){
           int index=(int)(ran.nextFloat()*r.length());
           salt.append(r.charAt(index));}
           String bookid=salt.toString();

        Toast.makeText(this, "Note down your Booking id!! ", Toast.LENGTH_SHORT).show();
        Bundle b=new Bundle();
        b.putString("key",bookid);

        Intent i=new Intent(getApplicationContext(),Docres.class);
        i.putExtras(b);
        startActivity(i);

    }

   public void load(View v){

       MyDBHandler db=new MyDBHandler(this,null,null,1);
       t=findViewById(R.id.t);
       docid=findViewById(R.id.editText3);
       type=findViewById(R.id.editText4);
       location=findViewById(R.id.editText);
       fees=findViewById(R.id.editText2);
       appointment=findViewById(R.id.editText6);
       password=findViewById(R.id.editText5);
       String pass=db.loadHandler();
       Intent i=new Intent(getApplicationContext(),Docres.class);
       Bundle b=new Bundle();
       b.putString("key",pass);
       i.putExtras(b);
       startActivity(i);
       //t.setText(db.loadHandler());
       docid.setText("");
       type.setText("");
       location.setText("");
       fees.setText("");
       appointment.setText("");
       password.setText("");
   }

   public void add(View v){
       try {
           MyDBHandler db=new MyDBHandler(this,null,null,1);
           t=findViewById(R.id.t);
           docid=findViewById(R.id.editText3);
           type=findViewById(R.id.editText4);
           location=findViewById(R.id.editText);
           fees=findViewById(R.id.editText2);
           appointment=findViewById(R.id.editText6);
           password=findViewById(R.id.editText5);
           int id=Integer.parseInt(docid.getText().toString());
           String ty=type.getText().toString();
           String loc=location.getText().toString();
           int fee=Integer.parseInt(fees.getText().toString());
           int app=Integer.parseInt(appointment.getText().toString());
           String pass=password.getText().toString();
           doctor doc =new doctor(id,ty,loc,fee,app,pass);
           db.addHandler(doc);
           docid.setText("");
           type.setText("");
           location.setText("");
           fees.setText("");
           appointment.setText("");
           password.setText("");
       } catch (NumberFormatException e) {
           e.printStackTrace();
       }
   }
   public void find(View v){
       String pass;
       MyDBHandler db=new MyDBHandler(this,null,null,1);
       t=findViewById(R.id.t);
       docid=findViewById(R.id.editText3);
       type=findViewById(R.id.editText4);
       location=findViewById(R.id.editText);
       fees=findViewById(R.id.editText2);
       appointment=findViewById(R.id.editText6);
       password=findViewById(R.id.editText5);
       doctor doc=db.findHandler(location.getText().toString(),type.getText().toString());
       if(doc!=null){
            pass="Doctor ID:"+doc.getid()+" Location: "+doc.getlocation()+" Fees: "+doc.getfees();
           Intent i=new Intent(getApplicationContext(),patres2.class);
           Bundle b=new Bundle();
           b.putString("key",pass);
           i.putExtras(b);
           startActivity(i);
           //t.setText(doc.getid()+" "+doc.getlocation()+" "+doc.getfees());
           docid.setText("");
           type.setText("");
           location.setText("");
           fees.setText("");
           appointment.setText("");
           password.setText("");
       }
       else {

           t.setText("No match found");
           docid.setText("");
           type.setText("");
           location.setText("");
           fees.setText("");
           appointment.setText("");
           password.setText("");

       }
   }
    public void delete(View v){
        try {
            MyDBHandler db=new MyDBHandler(this,null,null,1);
            t=findViewById(R.id.t);
            docid=findViewById(R.id.editText3);
            type=findViewById(R.id.editText4);
            location=findViewById(R.id.editText);
            fees=findViewById(R.id.editText2);
            appointment=findViewById(R.id.editText6);
            password=findViewById(R.id.editText5);
            boolean result=db.deleteHandler(Integer.parseInt(docid.getText().toString()));
            if(result){
                docid.setText("");
                type.setText("");
                location.setText("");
                fees.setText("");
                appointment.setText("");
                password.setText("");
                t.setText("Record Removed");
            }
            else {
                docid.setText("No Match");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }
    public void update(View v){
        try {
            MyDBHandler db=new MyDBHandler(this,null,null,1);
            t=findViewById(R.id.t);
            docid=findViewById(R.id.editText3);
            type=findViewById(R.id.editText4);
            location=findViewById(R.id.editText);
            fees=findViewById(R.id.editText2);
            appointment=findViewById(R.id.editText6);
            password=findViewById(R.id.editText5);
            boolean result=db.updateHandler(Integer.parseInt(docid.getText().toString()),type.getText().toString(),location.getText().toString(),Integer.parseInt(fees.getText().toString()),Integer.parseInt(appointment.getText().toString()),password.getText().toString());
            if(result){
                docid.setText("");
                type.setText("");
                location.setText("");
                fees.setText("");
                appointment.setText("");
                password.setText("");
                t.setText("Record Updated");
            }
            else {
                docid.setText("No Match");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            docid=findViewById(R.id.editText3);
            type=findViewById(R.id.editText4);
            location=findViewById(R.id.editText);
            fees=findViewById(R.id.editText2);
            appointment=findViewById(R.id.editText6);
            password=findViewById(R.id.editText5);
            Button f=findViewById(R.id.button9);
            Button a=findViewById(R.id.button6);
            Button u=findViewById(R.id.button5);
            Button d=findViewById(R.id.button4);
            Button l=findViewById(R.id.button8);
            Button bo=findViewById(R.id.button11);
            Button lo=findViewById(R.id.button12);
            Bundle b=getIntent().getExtras();
            String user=b.getString("ur");


            if(user.equals("doc") ){ f.setVisibility(View.INVISIBLE); bo.setVisibility(View.INVISIBLE);  lo.setVisibility(View.INVISIBLE);  }
            else if(user.equals("pat")){
                docid.setVisibility(View.INVISIBLE);
                fees.setVisibility(View.INVISIBLE);
                appointment.setVisibility(View.INVISIBLE);
                password.setVisibility(View.INVISIBLE);
                a.setVisibility(View.INVISIBLE);
                d.setVisibility(View.INVISIBLE);
                l.setVisibility(View.INVISIBLE);
                u.setVisibility(View.INVISIBLE);
                bo.setVisibility(View.INVISIBLE);
                lo.setVisibility(View.INVISIBLE);
            }
            else if(user.equals("pat2")){
                location.setVisibility(View.INVISIBLE);
                fees.setVisibility(View.INVISIBLE);
                type.setVisibility(View.INVISIBLE);
                appointment.setVisibility(View.INVISIBLE);
                password.setVisibility(View.INVISIBLE);
                a.setVisibility(View.INVISIBLE);
                d.setVisibility(View.INVISIBLE);
                l.setVisibility(View.INVISIBLE);
                u.setVisibility(View.INVISIBLE);
                f.setVisibility(View.INVISIBLE);
                lo.setVisibility(View.INVISIBLE);
            }
            else if(user.equals("doc2")){
                location.setVisibility(View.INVISIBLE);
                fees.setVisibility(View.INVISIBLE);
                type.setVisibility(View.INVISIBLE);
                appointment.setVisibility(View.INVISIBLE);
                bo.setVisibility(View.INVISIBLE);
                a.setVisibility(View.INVISIBLE);
                d.setVisibility(View.INVISIBLE);
                l.setVisibility(View.INVISIBLE);
                u.setVisibility(View.INVISIBLE);
                f.setVisibility(View.INVISIBLE);

            }
    }



}

class MyDBHandler extends SQLiteOpenHelper {
    //information  of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB.db";
    public static final String TABLE_NAME = "Doctor";
    public static final String COLUMN_ID = "DoctorID";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_FEES = "Fees";
    public static final String COLUMN_APPOINTMENT = "Appointment";
    public static final String COLUMN_PASSWORD = "Password";

    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +System.getProperty("line.separator");
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
        values.put(COLUMN_APPOINTMENT, doctor.getapointment());
        values.put(COLUMN_PASSWORD, doctor.getpassword());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public doctor findHandler(String location,String type) {

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ADDRESS + " = " + "'" + location + "'"+" AND "+COLUMN_TYPE+"=" + "'"+type+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        doctor doctor = new doctor();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            doctor.setid(Integer.parseInt(cursor.getString(0)));
            doctor.settype(cursor.getString(1));
            doctor.setlocation(cursor.getString(2));
            doctor.setfees(Integer.parseInt(cursor.getString(3)));
            doctor.setappointment(Integer.parseInt(cursor.getString(4)));
            doctor.setpassword(cursor.getString(5));
            cursor.close();
        } else {
            doctor = null;
        }
        db.close();
        return doctor;
    }
    public doctor findHandler2(String id,String password) {

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + "'" + id + "'"+" AND "+COLUMN_PASSWORD+"=" + "'"+password+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        doctor doctor = new doctor();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            doctor.setid(Integer.parseInt(cursor.getString(0)));
            doctor.settype(cursor.getString(1));
            doctor.setlocation(cursor.getString(2));
            doctor.setfees(Integer.parseInt(cursor.getString(3)));
            doctor.setappointment(Integer.parseInt(cursor.getString(4)));
            doctor.setpassword(cursor.getString(5));
            cursor.close();
        } else {
            doctor = null;
        }
        db.close();
        return doctor;
    }



    public boolean deleteHandler(int docid) {

        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + String.valueOf(docid) + "'";
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
    public boolean updateHandler(int docid, String type,String location,int fees,int appointment,String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, docid);
        args.put(COLUMN_TYPE, type);
        args.put(COLUMN_ADDRESS, location);
        args.put(COLUMN_FEES, fees);
        args.put(COLUMN_APPOINTMENT,appointment);
        args.put(COLUMN_PASSWORD, password);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + docid, null) > 0;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+COLUMN_ID+" INTEGER PRIMARY KEY,"+COLUMN_TYPE+" TEXT,"+COLUMN_ADDRESS+" TEXT,"+COLUMN_FEES+" INTEGER ,"+COLUMN_APPOINTMENT+" INTEGER, "+COLUMN_PASSWORD+" TEXT )";
        db.execSQL(CREATE_TABLE);
    }
}


 class doctor {
     private String type, location, password;
     private int docid, fees, appointment;

     public doctor() {
     }

     public doctor(int docid, String type, String location, int fees, int appointment, String password) {
         this.docid = docid;
         this.type = type;
         this.location = location;
         this.fees = fees;
         this.appointment = appointment;
         this.password = password;
     }

     public void setid(int id) {
         this.docid = id;
     }

     public int getid() {
         return this.docid;
     }

     public void settype(String type) {
         this.type = type;
     }

     public String gettype() {
         return this.type;
     }

     public void setfees(int fees) {
         this.fees = fees;
     }

     public int getfees() {
         return this.fees;
     }

     public void setlocation(String location) {
         this.location = location;
     }

     public String getlocation() {
         return this.location;
     }

     public void setappointment(int appointment) {
         this.appointment = appointment;
     }

     public int getapointment() {
         return this.appointment;
     }

     public void setpassword(String password) {
         this.password = password;
     }

     public String getpassword() {
         return this.password;
     }
 }



