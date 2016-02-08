package com.budougumi0617.training;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Resources res;
    public TextView textView;
    public Button button;
    public Button fragment_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        res = getResources();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.greet_view);
        button = (Button) findViewById(R.id.button_change_greet);
        fragment_button = (Button) findViewById(R.id.button_manage_fragment);
        setSupportActionBar(toolbar);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            /* Write Here */
            fragment = new MyFragment1();
        }
        button.setOnClickListener(getTextChangeClickListener());
        fragment_button.setOnClickListener(getFragmentChangeClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public View.OnClickListener getTextChangeClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int resId = getTextId(textView.getText().toString());
                textView.setText(resId);
            }
        };
    }

    public int getTextId(String text){
        /* Write Here */
        Toast.makeText(MainActivity.this,"Let's implement getTextId()", Toast.LENGTH_LONG).show();
        return R.string.to_do_implement;
    }

    public View.OnClickListener getFragmentChangeClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Write here */
                Fragment fragment = new MyFragment1();
                FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                Toast.makeText(MainActivity.this,
                        "Let's implement getFragmentChangeClickListener()", Toast.LENGTH_LONG).show();
            }
        };
    }

    public Fragment changeFragment(Class<? extends Fragment> currentFragmentType){
        return currentFragmentType == MyFragment2.class ?
                new MyFragment1() : new MyFragment2();
    }
}
