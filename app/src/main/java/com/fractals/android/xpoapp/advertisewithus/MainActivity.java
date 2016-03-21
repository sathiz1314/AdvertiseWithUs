package com.fractals.android.xpoapp.advertisewithus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Spinner duration,frequency;
    String durationdata[];
    String frequencydata[];
    private static final int SELECT_PHOTO = 100;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        duration = (Spinner) findViewById(R.id.dura_spinner);
        frequency = (Spinner) findViewById(R.id.freq_spinner);
        imageView = (ImageView)findViewById(R.id.imageView);

        durationMethod();
        frequencyMethod();

    }

    private void frequencyMethod() {
        durationdata = new String[]{"1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> durationadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,durationdata);
        duration.setAdapter(durationadapter);
        duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void durationMethod() {
        frequencydata = new String[]{"1.0","2.0","3.0","4.0","5.0","6.0","7.0","8.0","9.0","10.0"};
        ArrayAdapter<String> durationadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,frequencydata);
        frequency.setAdapter(durationadapter);
       frequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //   Toast.makeText(getApplicationContext(),"Clicked"+position,Toast.LENGTH_SHORT).show();
               Toast.makeText(getApplicationContext(),"Clicked - - - >"+position,Toast.LENGTH_SHORT).show();


           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }

    public void upload(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent,"Select Picture"), SELECT_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                e.printStackTrace();
                }
            }
    }
}
