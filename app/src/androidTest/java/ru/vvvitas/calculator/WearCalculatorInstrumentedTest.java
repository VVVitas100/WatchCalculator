package ru.vvvitas.calculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import android.content.pm.ActivityInfo;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WearCalculatorInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
           MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Test
    public void onSaveInstanceState() throws Exception {
        onView(withText("1")).perform(click());
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withClassName(containsString(TextView.class.getSimpleName()))).check(matches(withText("1")));

        onView(withText("+")).perform(click());
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withClassName(containsString(TextView.class.getSimpleName()))).check(matches(withText("0")));

        onView(withText("9")).perform(click());
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withClassName(containsString(TextView.class.getSimpleName()))).check(matches(withText("9")));

        onView(withText("=")).perform(click());
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withClassName(containsString(TextView.class.getSimpleName()))).check(matches(withText("10.0")));
    }
}