package br.com.zynger.simpleabsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import br.com.zynger.simpleab.ABTestPerformer;
import br.com.zynger.simpleab.ABTestVariant;


public class MainActivity extends ActionBarActivity {

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.main_text);
        mButton = (Button) findViewById(R.id.main_button);

        ABTestPerformer performer = new ABTestPerformer();

        performTextViewTest(performer);
        performButtonTest(performer);
    }

    private void performButtonTest(ABTestPerformer performer) {

    }

    private void performTextViewTest(ABTestPerformer performer) {
        TextViewBackgroundTest abTest = new TextViewBackgroundTest(performer, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_red_dark);
                mTextView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "A";
            }
        }, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_green_dark);
                mTextView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "B";
            }
        }, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_blue_bright);
                mTextView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "C";
            }
        });

        abTest.perform();
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
