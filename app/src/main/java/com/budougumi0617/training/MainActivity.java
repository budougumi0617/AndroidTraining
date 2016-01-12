package com.budougumi0617.training;


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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView textView = (TextView) findViewById(R.id.greet_view);
        Button button = (Button) findViewById(R.id.button_change_greet);
        Button fragment_button = (Button) findViewById(R.id.button_manage_fragment);
        setSupportActionBar(toolbar);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            fragment = new MyFragment1();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int resId = textView.getText().equals(getString(R.string.good_evening))?
                        R.string.hello_world : R.string.good_evening;
                textView.setText(resId);
            }
        });
        fragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                fragment = fragment.getClass() == MyFragment1.class ? new MyFragment2() : new MyFragment1();
                FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
            }
        });
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
}
