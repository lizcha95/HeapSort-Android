package com.example.curso.heapsort;

 import android.app.IntentService;
 import android.content.Intent;
 import android.os.Bundle;
 import android.os.ResultReceiver;
 import android.util.Log;

 import java.lang.reflect.Array;
 import java.util.Random;
 import java.util.concurrent.TimeUnit;


 public class BackgroundIntent extends IntentService {

 public static final int STATUS_RUNNING = 0;
 public static final int STATUS_FINISHED = 1;
 public static final int STATUS_ERROR = 2;

 private static final String TAG = "BackgroundIntent";

 public BackgroundIntent() {
 super(BackgroundIntent.class.getName());
 }

     private static int N;
     /* Sort Function */
     public static void sort(int arr[])
     {
         heapify(arr);
         for (int i = N; i > 0; i--)
         {
             swap(arr,0, i);
             N = N-1;
             maxheap(arr, 0);
         }
     }
     /* Function to build a heap */
     public static void heapify(int arr[])
     {
         N = arr.length-1;
         for (int i = N/2; i >= 0; i--)
             maxheap(arr, i);
     }
     /* Function to swap largest element in heap */
     public static void maxheap(int arr[], int i)
     {
         int left = 2*i ;
         int right = 2*i + 1;
         int max = i;
         if (left <= N && arr[left] > arr[i])
             max = left;
         if (right <= N && arr[right] > arr[max])
             max = right;

         if (max != i)
         {
             swap(arr, i, max);
             maxheap(arr, max);
         }
     }
     /* Function to swap two numbers in an array */
     public static void swap(int arr[], int i, int j)
     {
         int tmp = arr[i];
         arr[i] = arr[j];
         arr[j] = tmp;
     }

 private void heapSort() {

    int i;
    int n = 1000000;
    int arrayInt[] = new int[n];

    Random randomNumber = new Random();
    for(i = 0; i < n; i++)
        arrayInt[i] = randomNumber.nextInt(n);
    sort(arrayInt);

 }

 static final int MAX_VALUE = 100000;
 int data[] = new int[MAX_VALUE];


 public void generarNuevoArreglo() {
 Random random = new Random();

 for (int i = 0; i < data.length; i++){
 data[i] = random.nextInt(MAX_VALUE);
 }
 }


 @Override
 protected void onHandleIntent(Intent intent) {

 Log.d(TAG, "Service Started!");

 final ResultReceiver receiver = intent.getParcelableExtra("receiver");

 Bundle bundle = new Bundle();

 /* Service Started */
receiver.send(STATUS_RUNNING, Bundle.EMPTY);
        try {

        long start = System.nanoTime();
        generarNuevoArreglo();
        heapSort();
        long end = System.nanoTime();

        long elapsedTime = end - start;
        String res = "trascurrieron: " + elapsedTime + "nano seconds\n" +
        "lo cual es " + TimeUnit.NANOSECONDS.toSeconds(elapsedTime) + " segundos";

        bundle.putString("exectime",res);
        }
        catch (Error err)
        {
        bundle.putString(Intent.EXTRA_TEXT, err.getMessage());
        receiver.send(STATUS_ERROR, bundle);
        }

        /* Status Finished */
        receiver.send(STATUS_FINISHED, bundle);
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
        }
        }