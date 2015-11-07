package aikeda.chatapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    //private TextView mUserMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //xmlのViewを取得
        mInputMessage = (EditText) findViewById(R.id.input_message);
        mSendMessage = (Button) findViewById(R.id.send_message);
        mMessageLog = (LinearLayout) findViewById(R.id.message_log);
        //mCpuMessage = (TextView) findViewById(R.id.cpu_message);
        //mUserMessage = (TextView) findViewById(R.id.user_message);
        mSendMessage.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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

    @Override
    public void onClick(View v) {
        if (v.equals(mSendMessage)) {
            Toast.makeText(this, "onClick()", Toast.LENGTH_SHORT).show();
            String inputText = mInputMessage.getText().toString();
            String answer;

            TextView userMessage = new TextView(this);
            int messageColor = getResources().getColor(R.color.message_color);
            userMessage.setTextColor(messageColor);

            userMessage.setText(inputText);
            userMessage.setBackgroundResource(R.drawable.user_message);

            //コメントサイズに合わせる
            LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            userMessageLayout.gravity = Gravity.END;
            final int marginSize = getResources().getDimensionPixelOffset(R.dimen.message_margin);
            userMessageLayout.setMargins(0, marginSize, 0, marginSize);
            mMessageLog.addView(userMessage, 0, userMessageLayout);

            if (inputText.contains("元気ですか")) {
                answer = "元気です";
            } else if(inputText.contains("おみくじ")) {
                double random = Math.random() * 5.1d;
                if ( random < 1d) {
                    answer = "大凶";
                } else if( random < 2d) {
                    answer = "吉";
                } else if( random < 3d) {
                    answer = "小吉";
                } else if( random < 4d) {
                    answer = "中吉";
                } else if( random < 5d) {
                    answer = "大吉";
                } else {
                    answer = "超大吉";
                }
            } else if(inputText.contains("時間")) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                answer = String.format("ただいま%1$d時%2$d分%3$d秒",hour,minute,second);
            } else {
                answer = "そうなんですね";
            }
            mInputMessage.setText("");

            TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(),0,0,0);
            userMessageAnimation.setDuration(1000);

            final TextView cpuMessage = new TextView(this);
            cpuMessage.setTextColor(messageColor);
            cpuMessage.setBackgroundResource(R.drawable.cpu_message);
            cpuMessage.setText(answer);
            //mMessageLog.addView(cpuMessage, 0);
            cpuMessage.setGravity(Gravity.START);

            userMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                   // コメントサイズに合わせる
                    LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    cpuMessageLayout.gravity = Gravity.START;

                    //隙間を設定する
                    cpuMessageLayout.setMargins(marginSize, marginSize, marginSize, marginSize);

                    mMessageLog.addView(cpuMessage, 0, cpuMessageLayout);

                    TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(), 0, 0, 0);

                    cpuAnimation.setDuration(1000);
                    cpuMessage.setAnimation(cpuAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            userMessage.startAnimation(userMessageAnimation);
        }
    }
}