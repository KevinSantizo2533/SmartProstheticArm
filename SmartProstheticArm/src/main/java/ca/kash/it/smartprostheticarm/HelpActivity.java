/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageView img = (ImageView)findViewById(R.id.contact_phone);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getString(R.string.dialnum)));
                startActivity(intent);
            }
        });
        ImageView img2 = (ImageView)findViewById(R.id.contact_email);
        img2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(getString(R.string.plain));
                intent.putExtra(Intent.EXTRA_EMAIL,  new String[]{getString(R.string.hybridAddress)});
                Toast.makeText(getApplicationContext(), R.string.emailpro, Toast.LENGTH_SHORT).show();
                startActivity(Intent.createChooser(intent, getString(R.string.sendEmail)));
            }
        });
    }
}

