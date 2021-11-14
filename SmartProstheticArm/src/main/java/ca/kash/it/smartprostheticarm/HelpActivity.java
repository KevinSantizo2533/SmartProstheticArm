package ca.kash.it.smartprostheticarm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class HelpActivity extends AppCompatActivity {

    public ImageView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageView img = (ImageView)findViewById(R.id.contact_phone);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9058746663"));
                startActivity(intent);
            }
        });
        ImageView img = (ImageView)findViewById(R.id.contact_email);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setData(Uri.parse("hybridprosthetics@gmail.com"));
                startActivity(intent);
            }
        });
    }
}

