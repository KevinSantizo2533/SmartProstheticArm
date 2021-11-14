package ca.kash.it.smartprostheticarm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
        ImageView img2 = (ImageView)findViewById(R.id.contact_email);
        img2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,  new String[]{"hybridprosthetics@gmail.com"});
                Toast.makeText(getApplicationContext(), "Please choose your email provider", Toast.LENGTH_SHORT).show();
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }
}

