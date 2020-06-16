package com.example.drivetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hello FlashDash!!
        //take 2
    }

    public void driveActivity(View view)
    {
        Purpose purpose = new Purpose();
        purpose.whatIsMyPurpose();
        Intent intent = new Intent(this, fileTestActivity.class);
        startActivity(intent);
    }
}
