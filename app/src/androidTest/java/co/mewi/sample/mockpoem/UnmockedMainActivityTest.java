package co.mewi.sample.mockpoem;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.mewi.sample.mockpoem.mockwebutils.MockWebUtils;
import co.mewi.sample.mockpoem.mockwebutils.PoemistResponse;
import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class UnmockedMainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void clickFabTest_makeNetworkCallSuccess() {
        // Before any action
        onView(withId(R.id.output))
                .check(matches(withText("")));

        // Immediately after tapping the fab button
        onView(withId(R.id.fab))
                .perform(click());

        onView(withId(R.id.output))
                .check(matches(withText("LOADING")));

        // TODO: Replace the thread sleep with Idling Resource
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Once network request completes, check if response has some value
        onView(withId(R.id.output))
                .check(matches(Matchers.not(withText(MainActivity.LOADING))));
    }
}