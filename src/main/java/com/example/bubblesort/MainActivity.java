package com.example.bubblesort;

import static java.lang.Integer.parseInt;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();
    private Button mButton;
    private EditText mEdit;
    public int liczbaElementow;
    private boolean  isClicked = false;
    public SortView  sortView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        linearLayout = findViewById(R.id.linearLayout);
        mButton =  findViewById(R.id.btn);
        mEdit = findViewById(R.id.wartosc);
        sortView = findViewById(R.id.sortView);
        mButton.setOnClickListener(
                view -> {
                    Log.v("EditText", mEdit.getText().toString());
                    String etString =  mEdit.getText().toString();
                    int etInt = parseInt(etString);
                    liczbaElementow = etInt;
                    isClicked = !isClicked;
                    Log.d("TAG", "onClick: " + liczbaElementow + isClicked);
                    sortView.generateArray(liczbaElementow);
                    setProgressValue(liczbaElementow);

                }

        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void setProgressValue(final int liczbaElementow){
        progressBar.setProgress(progressStatus);
        progressBar.setMax(liczbaElementow);
        Log.d("TAG", "setProgressValue: START" );
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < liczbaElementow) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    public void createRectangle(int height){
          int width = 2;

    }
}