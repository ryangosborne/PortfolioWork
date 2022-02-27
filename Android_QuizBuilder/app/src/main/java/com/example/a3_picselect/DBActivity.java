package com.example.a3_picselect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.view.View;
import android.widget.*;
import android.database.*;
import java.io.*;
import android.os.Bundle;

public class DBActivity extends AppCompatActivity {

    EditText et_albumName;
    EditText et_comment;
    RatingBar rb_rating;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        et_albumName = findViewById(R.id.et_albumName);
        et_comment = findViewById(R.id.et_comment);
        rb_rating = findViewById(R.id.rb_rating);
        btn_submit = findViewById(R.id.btn_submit);

        try{
            String destPath = "/data/data" + getPackageName() + "/database/MyDB"; // establishing path
            File file = new File(destPath);
            if(!file.exists()){ // if the file(and path) don't exist, copy into new DB using same path
                CopyDB(getBaseContext().getAssets().open("mydb"), new FileOutputStream(destPath));
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        DBAdapter db = new DBAdapter(this); // creating new DBAdapter, passing context as argument

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(et_albumName.getText());
                System.out.println(et_comment.getText());
                System.out.println((int)rb_rating.getRating());

                // insert record to DB when SUBMIT button is pressed -- NO VALIDATION --
                db.open();
                long id = db.insert(et_albumName.getText().toString(), (int)rb_rating.getRating(), et_comment.getText().toString());
                db.close(); // closing DB when finished for integrity

                // display confirmation with Toast
                Toast.makeText(getBaseContext(), "Entry successful. Thanks for your submission", Toast.LENGTH_LONG).show();
            }
        }); // end handler

    } // end onCreate

    // routine to copy DB
    public void CopyDB(InputStream inputStream,OutputStream outputStream) throws IOException{ // not implemented
        //copy 1k bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    } // end method CopyDB

    public void displayEntry(Cursor cursor) // not implemented -- couldn't figure how to make applicable Cursor
    {
        Toast.makeText(this,
                "id: " + cursor.getString(0) + "\n" +
                    "Album: " + cursor.getString(1) + "\n" +
                    "Rating: " + cursor.getString(2) + "\n" +
                    "Comment: " + cursor.getString(3), Toast.LENGTH_LONG).show();
    } // end displayEntry
} // end DBActivity class