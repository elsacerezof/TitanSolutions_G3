package es.unican.g3.tus;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.g3.tus.views.ActivityInicialLogo;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AceptacionBuscarParadaPorTextoTest {

    @Rule
    public ActivityTestRule<ActivityInicialLogo> mActivityTestRule = new ActivityTestRule<>(ActivityInicialLogo.class);

    @Test
    public void aceptacionBuscarParadaPorTextoTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.search_button), withContentDescription("Search"),
                        withParent(allOf(withId(R.id.search_bar),
                                withParent(withId(R.id.action_search)))),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("ojaiz"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("447"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("447")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewName), withText("Ojaiz 166"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Ojaiz 166")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewNumero), withText("446"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("446")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textViewName), withText("Ojaiz 89"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Ojaiz 89")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textViewNumero), withText("355"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("355")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textViewName), withText("Ojaiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Ojaiz")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textViewNumero), withText("54"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        3),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("54")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textViewName), withText("Barrio de Ojaiz 7"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        3),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Barrio de Ojaiz 7")));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("477"), closeSoftKeyboard());

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.textViewNumero), withText("477"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("477")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.textViewName), withText("Jose Ortega y Gasset - 2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("Jose Ortega y Gasset - 2")));

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction searchAutoComplete3 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete3.perform(replaceText("mi casita"), closeSoftKeyboard());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.textViewNumero), withText("499"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("499")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.textViewName), withText("Camarreal Peñacastillo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView12.check(matches(withText("Camarreal Peñacastillo")));

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        ViewInteraction searchAutoComplete4 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete4.perform(replaceText("domino"), closeSoftKeyboard());

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.textViewNumero), withText("481"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView13.check(matches(withText("481")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.textViewName), withText("Jeronimo Sainz de la Maza ( Mercado Mexico )"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView14.check(matches(withText("Jeronimo Sainz de la Maza ( Mercado Mexico )")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
