package ca.kash.it.smartprostheticarm;


import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
    private LoginActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void testForgotPass() throws Exception {
        TextView tv = (TextView) activity.findViewById( R.id.forgotpass );
        tv.performClick();
        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(ForgotPasswordActivity.class.getCanonicalName(), intent.getComponent().getClassName());

    }
}
