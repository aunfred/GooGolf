package comsic.fred.googolf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;
    //Explicit


    private static final String DATABASE_NAME = "Golf.db";
    private static final int DATABASE_VERSION = 1;

    public static final String User_TABLE = "UserTABLE";
    public static final String User_ID = "id";
    public static final String User_Name = "name";
    public static final String User_Lastname = "lname";
    public static final String User_Username = "user";
    public static final String User_Pass = "pass";
    public static final String User_Age = "age";
    public static final String User_Mail = "mail";
    public String CREATE_USER_TABLE = "create table " + User_TABLE + " (" + User_ID + " text primary key, " + " " + User_Name + " text, "
            + User_Lastname + " text, " + User_Username + " text, " + User_Pass + " text, " + User_Age + " text , " + User_Mail + " text);";


    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, CREATE_USER_TABLE);

        db.execSQL(CREATE_USER_TABLE);


        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_User_TABLE = "DROP TABLE IF EXISTS " + User_TABLE;

        db.execSQL(DROP_User_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);

    }
}   // Main Class
