package com.budougumi0617.training;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by budougumi0617 on 2016/01/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    private Resources mMockRes;

    @Mock
    private TextView mMockTextView;

    @InjectMocks
    private MainActivity mActivity= new MainActivity();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //@Test TODO
    public void testOnCreate() throws Exception {

    }

    //@Test TODO
    public void testOnCreateOptionsMenu() throws Exception {

    }

    //@Test TODO
    public void testOnOptionsItemSelected() throws Exception {

    }

    //@Test TODO
    public void testGetTextChangeClickListener() throws Exception {
        View.OnClickListener ocl = mActivity.getTextChangeClickListener();
        when(mMockTextView.getText()).thenReturn("Good evening World in Mock!");
        System.out.println(mActivity.textView.getText().toString());
    }

    @Test
    public void testGetTextId() throws Exception {
        //Add performance to Mock resource
        when(mMockRes.getString(R.string.good_evening)).thenReturn("Good evening World!");
        assertThat(mActivity.getTextId("Good evening World!"),
                is(R.string.hello_world));

        assertThat(mActivity.getTextId("Hello World!"),
                is(R.string.good_evening));

    }

    //@Test TODO
    public void testGetFragmentChangeClickListener() throws Exception {

    }

    @Test
    public void testChangeFragment() throws Exception {
        assertThat(mActivity.changeFragment(MyFragment1.class),
                is(Matchers.instanceOf(MyFragment2.class)));

        assertThat(mActivity.changeFragment(MyFragment2.class),
                is(Matchers.instanceOf(MyFragment1.class)));
    }
}