package com.sensorreader;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    
    private SensorManager sensorManager;
    Sensor accelerometer;

    TextView xValue;
    TextView yValue;
    TextView zValue;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = findViewById(R.id.xVal);
        xValue.setBackgroundColor(Color.YELLOW);
        xValue.setTextSize(18);

        yValue = findViewById(R.id.yVal);
        yValue.setBackgroundColor(Color.YELLOW);
        yValue.setTextSize(18);

        zValue = findViewById(R.id.zVal);
        zValue.setBackgroundColor(Color.YELLOW);
        zValue.setTextSize(18);

        button = findViewById(R.id.button);
        button.setBackgroundColor(Color.GREEN);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startAndStop(View view){

        if(button.getText()=="STOP"){
                button.setText("START");
                button.setBackgroundColor(Color.GREEN);
                sensorManager.unregisterListener(MainActivity.this,accelerometer);
        }else{
            xValue.setVisibility(View.VISIBLE);
            yValue.setVisibility(View.VISIBLE);
            zValue.setVisibility(View.VISIBLE);
            sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
            button.setText("STOP");
            button.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged: X:" + event.values[0] + "Y:" + event.values[1] + "Z:" + event.values[2]);
        xValue.setText("xValue : "+ event.values[0]);
        yValue.setText("yValue : "+ event.values[1]);
        zValue.setText("zValue : "+ event.values[2]);


    }
}
