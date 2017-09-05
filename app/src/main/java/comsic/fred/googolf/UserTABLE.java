package comsic.fred.googolf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comsic.fred.googolf.MySQLiteOpenHelper;


public class UserTABLE {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSQLiteDataBase, readSQLiteDataBase;

    public static final String User_TABLE = "UserTABLE";
    public static final String User_ID = "id";
    public static final String User_Name = "name";
    public static final String User_Lastname = "lname";
    public static final String User_Username = "user";
    public static final String User_Pass = "pass";
    public static final String User_Age = "age";
    public static final String User_Mail = "mail";

    public UserTABLE(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQLiteDataBase = objMySQLiteOpenHelper.getWritableDatabase();
        readSQLiteDataBase = objMySQLiteOpenHelper.getReadableDatabase();
    }
    public long addNewUser(String strid,String strname,String strlastname,String struser,String strpass,String strage,String strmail){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.User_ID,strid);
        objContentValues.put(objMySQLiteOpenHelper.User_Name,strname);
        objContentValues.put(objMySQLiteOpenHelper.User_Lastname,strlastname);
        objContentValues.put(objMySQLiteOpenHelper.User_Username,struser);
        objContentValues.put(objMySQLiteOpenHelper.User_Pass,strpass);
        objContentValues.put(objMySQLiteOpenHelper.User_Age,strage);
        objContentValues.put(objMySQLiteOpenHelper.User_Mail,strmail);
        return readSQLiteDataBase.insert(objMySQLiteOpenHelper.User_TABLE,null,objContentValues);
    }
    public long AddNewUser(String struser,String strpass,String strmail){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.User_Username,struser);
        objContentValues.put(objMySQLiteOpenHelper.User_Pass,strpass);
        objContentValues.put(objMySQLiteOpenHelper.User_Mail,strmail);

        return readSQLiteDataBase.insert(objMySQLiteOpenHelper.User_TABLE,null,objContentValues);
    }

    public String[] searchUSERPASSWORD(String strUser) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSQLiteDataBase.query(User_TABLE, new String[]{User_ID , User_Name,
                    User_Lastname, User_Username, User_Pass, User_Age, User_Mail}, User_Username + "=?", new String[]{String.valueOf(strUser)},null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strResult = new String[6];
                    for (int i = 0; i < 6; i++) {
                        strResult[i] = objCursor.getString(i);
                    }
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }
    }
    public String[] readUSERPASSWORD(String strUser) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSQLiteDataBase.query(User_TABLE, new String[]{User_ID , User_Name,
                    User_Lastname, User_Username, User_Pass, User_Age, User_Mail}, User_Username + "=?", new String[]{String.valueOf(strUser)},null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strResult = new String[6];
                    for (int i = 0; i < 6; i++) {
                        strResult[i] = objCursor.getString(i);
                    }
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }
    }

}