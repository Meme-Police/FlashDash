package com.example.drivetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class fileTestActivity extends AppCompatActivity {

    private static final int CREATE_REQUEST_CODE = 40;
    private static final int OPEN_REQUEST_CODE = 41;
    private static final int SAVE_REQUEST_CODE = 42;

    EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);

        textView = (EditText) findViewById(R.id.editText);
    }

    public void newFile(View v)
    {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "newfile.txt");
        startActivityForResult(intent, CREATE_REQUEST_CODE);
    }

    // All of this is basicly magic, so i'm going to do my best to explain it.
    public void openFile(View v)
    {
        //creates a new intent to open a doccument, and it looks for doccuments that are txt files which are openable
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        // Starts a new activity, OnActivityResult will get called when its done.
        startActivityForResult(intent, OPEN_REQUEST_CODE);
    }

    public String readFileContent(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String currentline;
        while ((currentline = reader.readLine()) != null)
        {
            stringBuilder.append(currentline + "\n");
        }
        inputStream.close();
        return stringBuilder.toString();
    }

    protected void onActivityResult (int requestCode,
                                        int resultCode,
                                        Intent resultData) {
        /***************************************
        I SHOULD PASS ALL OF THIS OFF TO A THREAD
         **************************************/

        // I don't know what a Uri is
        Uri currentURI;
        super.onActivityResult(requestCode, resultCode, resultData);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CREATE_REQUEST_CODE) {
                if (resultData != null) {
                    textView.setText("");
                }
            }
            //handles an open request
            else if (requestCode == OPEN_REQUEST_CODE);
            {
                if (resultData != null)
                {
                    currentURI = resultData.getData();

                    String content = null;
                    try {
                        content = readFileContent(currentURI);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    textView.setText(content);
                }
            }
        }
    }
}
