package co.mewi.sample.mockpoem;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        MockWebUtils.callOnSetup();
    }

    @After
    public void tearDown() throws Exception {
        MockWebUtils.callOnTearDown();
    }

    @Test
    public void clickFabTest_makeNetworkCallSuccess() {
        // CHECK if LOADING is shown at first, then after click, it should show some text

        // Set api response - success
        MockWebUtils.getMockWebServer().enqueue(new MockResponse().setResponseCode(200).setBody(PoemistResponse.RANDOM_POEMS));

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
                .check(matches(Matchers.not(withText("LOADING"))));
    }

    @Test
    public void beforeClickFabTest() {
        // Before any action, the screen should be blank
        onView(withId(R.id.output))
                .check(matches(withText("")));
    }

    @Test
    public void clickFabTest_makeMockNetworkCallLoading() {
        // Set api response - delayed enough to test the loading state
        MockResponse mockResponse = new MockResponse();
        mockResponse.setBodyDelay(2, TimeUnit.SECONDS);

        // Immediately after tapping the fab button
        onView(withId(R.id.fab))
                .perform(click());

        // Show LOADING
        onView(withId(R.id.output))
                .check(matches(withText(MainActivity.LOADING)));
    }

    @Test
    public void clickFabTest_makeMockNetworkCallSuccess() {
        // Set api response - success
        MockWebUtils.getMockWebServer().enqueue(new MockResponse().setResponseCode(200).setBody(PoemistResponse.RANDOM_POEMS));

        // Before any action
        onView(withId(R.id.output))
                .check(matches(withText("")));

        // Immediately after tapping the fab button
        onView(withId(R.id.fab))
                .perform(click());

        // Since it's a mocked response, I canc
        onView(withId(R.id.output))
                .check(matches(not(MainActivity.LOADING)));
    }

    @Test
    public void clickFabTest_makeMockNetworkCallFailure() {
        // Set api response - failure
        MockWebUtils.getMockWebServer().enqueue(new MockResponse().setResponseCode(500).setBody(""));

        // Before any action
        onView(withId(R.id.output))
                .check(matches(withText("")));

        // Immediately after tapping the fab button
        onView(withId(R.id.fab))
                .perform(click());

        // Once network request completes, check if response has some value
        onView(withId(R.id.output))
                .check(matches(withText(MainActivity.FAIL)));
    }
}