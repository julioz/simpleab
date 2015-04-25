package br.com.zynger.simpleabsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.zynger.simpleab.ABTestPerformer;
import br.com.zynger.simpleab.ABTestVariant;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.main_text);

        ABTestPerformer performer = new ABTestPerformer();

        TextViewBackgroundTest abTest = new TextViewBackgroundTest(performer, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_red_dark);
                textView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "A";
            }
        }, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_green_dark);
                textView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "B";
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
