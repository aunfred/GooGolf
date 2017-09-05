package comsic.fred.googolf;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
       // initialWidget();

        //Connected SQLite
        connectedSQLite();
        Cdatabase();
        //Test Add Value
        //testAddValue();

        //Delete All Data
       // deleteAllData();

        //Synchronize JSON to SQLite
       synJSONtoSQLite();

    }   // onCreate

    private void synJSONtoSQLite() {
        //Setup Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode
                .ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Loop 2 Times
        int intTimes = 0;
        while (intTimes <= 1) {
            //Variable & Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strUserURL = "http://5711020660034.sci.dusit.ac.th/userTable.php";
            String strDetailURL = "http://5711020660034.sci.dusit.ac.th/DetailTABLE.php";
            HttpPost objHttpPost = null;
            //1. Create InputStream
            try {
                HttpClient objHttpClient = new DefaultHttpClient();

                switch (intTimes) {
                    case 0:
                        objHttpPost = new HttpPost(strUserURL);
                        break;

                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            } catch (Exception e) {
                Log.d("5711020660034", "InputStream ==> " + e.toString());
            }
//2.           Create strJSON
            try {
                BufferedReader objBufferedReader = new BufferedReader
                        (new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            } catch (Exception e) {
                Log.d("5711020660034", "strJSON ==> " + e.toString());
            }
//3. Update to SQLite
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes) {
                        case 0:
                            //Update userTABLE
                            String strid = jsonObject.getString("id");
                            String strname = jsonObject.getString("name");
                            String strlastname = jsonObject.getString("lname");
                            String struser = jsonObject.getString("user");
                            String strpass = jsonObject.getString("pass");
                            String strage = jsonObject.getString("age");
                            String strmail = jsonObject.getString("mail");
                            objUserTABLE.addNewUser(strid,strname,strlastname,struser,strpass,strage,strmail);
                        break;


                    }
                }
            } catch (Exception e) {
                Log.d("5711020660034", "Update SQLite ==> " + e.toString());
            }





        }
    }


    private void Cdatabase() {

        objUserTABLE = new UserTABLE(this);
    }

    private void connectedSQLite() {
        objUserTABLE = new UserTABLE(this);
        //objCarTABLE = new CarTABLE(this);
       // objOrderTABLE = new OrderTABLE(this);
        //objDetailTABLE = new DetailTABLE(this);

    }


    //connected SQLite
}















