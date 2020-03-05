package edu.gatech.seclass.wordfind6300;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

import static androidx.test.espresso.matcher.ViewMatchers.*;


/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 */


@RunWith(AndroidJUnit4.class)
public class AutomatedTesting {

    @Rule
    public ActivityTestRule<MainActivity> tActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    //try to validate the game setting by changing the game time and game size - 1
    @Test
    public void Test1() {
        onView(withId(R.id.settingsButton)).perform(click());
        onView(withId(R.id.gameMinutesSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("5"))).perform(click());
        onView(withId(R.id.gameMinutesSpinner)).check(matches(withSpinnerText(containsString("5"))));
        onView(withId(R.id.boardSizeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("6(x6)"))).perform(click());
        onView(withId(R.id.boardSizeSpinner)).check(matches(withSpinnerText(containsString("6(x6)"))));
        Espresso.closeSoftKeyboard();
        pressBack();
    }

    //try more values for game time and game size
    @Test
    public void Test2() {
        onView(withId(R.id.settingsButton)).perform(click());
        onView(withId(R.id.gameMinutesSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("4"))).perform(click());
        onView(withId(R.id.gameMinutesSpinner)).check(matches(withSpinnerText(containsString("4"))));
        onView(withId(R.id.boardSizeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("4(x4)"))).perform(click());
        onView(withId(R.id.boardSizeSpinner)).check(matches(withSpinnerText(containsString("4(x4)"))));
        Espresso.closeSoftKeyboard();
        pressBack();
    }

    //test out the reroll button by pressing it three times and check if the score is -15
    @Test
    public void Test3() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.rerollBtn)).perform(click());
        onView(withId(R.id.rerollBtn)).perform(click());
        onView(withId(R.id.rerollBtn)).perform(click());
        onView(withId(R.id.scoreText)).check(matches(withText(containsString("-15"))));
    }


    //start a game and enter a word to see if the score is correct
    @Test
    public void Test4() {
        onView(withId(R.id.playButton)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("H"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("U"))).perform(click());
        onView(withId(R.id.enterBtn)).perform(click());
        onView(withId(R.id.scoreText)).check(matches(withText(containsString("2"))));


//        onView(withId(R.id.boardGrid)).perform(click(1));
//        onView(withId(R.id.scoreText)).check(matches(withText(containsString("-15"))));
    }

    @Test
    public void Test5() {}
    @Test
    public void Test6() {}
    @Test
    public void Test7() {}
    @Test
    public void Test8() {}
    @Test
    public void Test9() {}
    @Test
    public void Test10() {}
    @Test
    public void Test11() {}
    @Test
    public void Test12() {}



}