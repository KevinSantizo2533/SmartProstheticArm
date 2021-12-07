package ca.kash.it.smartprostheticarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {
    private EditText etTo;
    private EditText etMsg;
    private EditText etSubject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        etTo = (EditText) findViewById(R.id.et_to);
        etTo.setText("hybridprosthetics@gmail.com", TextView.BufferType.EDITABLE);
        etSubject = (EditText) findViewById(R.id.et_subject);
        etMsg = (EditText) findViewById(R.id.et_message);

        Button buttonSend = (Button) findViewById(R.id.sendBtn);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEMAIL();
            }
        });
    }

    private void sendEMAIL() {
        String contactList = etTo.getText().toString();
        String [] recipient = contactList.split(",");

        String subject = etSubject.getText().toString();
        String message = etMsg.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Select Email App"));
    }
}
