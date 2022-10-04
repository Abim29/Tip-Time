package com.example.tiptime;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CalculatorTests {
    @Rule
    public ActivityScenarioRule<MainActivity> activity = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("10000.00"));
        onView(withId(R.id.calculate_button)).perform(click());
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("Rp2.000,00"))));
    }

    @Test
    public void calculate_18_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("10000.00"));
        onView(withId(R.id.option_eighteen_percent)).perform(click());
        onView(withId(R.id.calculate_button)).perform(click());
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("Rp1.800,00"))));
    }

    @Test
    public void calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("10000.00"));
        onView(withId(R.id.option_fifteen_percent)).perform(click());
        onView(withId(R.id.calculate_button)).perform(click());
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("Rp1.500,00"))));
    }

    @Test
    public void calculate_15_percent_tip_no_rounding() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("11250.00"));
        onView(withId(R.id.option_fifteen_percent)).perform(click());
        onView(withId(R.id.round_up_switch)).perform(click());
        onView(withId(R.id.calculate_button)).perform(click());
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("Rp1.687,50"))));
    }


}
