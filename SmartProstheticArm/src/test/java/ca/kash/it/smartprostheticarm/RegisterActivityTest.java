package ca.kash.it.smartprostheticarm;


import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class RegisterActivityTest {

    @Test
    public void clickingButton_shouldChangeResultsViewText() throws Exception {
        Activity activity = Robolectric.setupActivity(RegisterActivity.class);

        //Button button = (Button) activity.findViewById(R.id.btnUpdateText);
        //TextView results = (TextView) activity.findViewById(R.id.tvField);

        //button.performClick();
        //assertEquals("Testing Android Rocks1!", results.getText().toString());
    }
}
