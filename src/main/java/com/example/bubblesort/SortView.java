package com.example.bubblesort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortView extends View {

    private Paint paint;
    private int height;
    public MainActivity mainActivity = new MainActivity();
    public SortView(Context context,AttributeSet attrs){
       super(context,attrs);
   }



    public void generateArray(int liczbaElementow){
        Random rd = new Random();
        int[] arr = new int[liczbaElementow];
        for(int i =0; i< arr.length; i++){
            arr[i] = rd.nextInt(liczbaElementow - 0);
            Log.d("TAG", "generateArray: "+ arr[i]);


        }
        bubbleSort(arr,liczbaElementow);
        invalidate();

    }
    public void bubbleSort(int[] tablica, int liczbaElementow){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int n = liczbaElementow;
        Runnable runnableTask = ()  ->{
            try{
                for(int i =0; i< n - 1; i++){
                    for(int j = 0; j<n - i -1; j++){
                        if(tablica[j] > tablica[j+1]){
                            int temp = tablica[j];
                            tablica[j] = tablica[j+1];
                            tablica[j+1] = temp;
                        }
                    }
                }
                for(int i =0; i<n; i++){
                    Log.d("TAG", "bubbleSort:  element " + i + " "  + tablica[i]);


                }

            }catch(Exception e){
                Log.e("Error", "Error sorting elements and generating image"+ e.getMessage());
            }
        };
        executorService.execute(runnableTask);

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        paint = new Paint();
        paint.setColor(Color.BLUE);


        super.onDraw(canvas);
        for(int i=0; i< mainActivity.liczbaElementow; i++){
            canvas.drawRect(10 + i, mainActivity.liczbaElementow, 30 + i, 40 + i, paint);

        }



    }

}
