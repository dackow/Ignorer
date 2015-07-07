package com.dmm.ignorer.configure;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.dmm.ignorer.R;
import com.dmm.ignorer.adapters.ConfigureListAdapter;
import com.dmm.ignorer.domain.CallInfo;

public class Configure extends ActionBarActivity {
    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        lvList = (ListView)findViewById(R.id.lvConfigure);

        CallInfo c1 = new CallInfo();
        c1.setActive(true);
        c1.setCategory("P");
        c1.setId(0);
        c1.setComment("Comment");
        c1.setPhone_number("509808144");

        CallInfo c2 = new CallInfo();
        c2.setActive(true);
        c2.setCategory("G");
        c2.setId(1);
        c2.setComment("Comment 2");
        c2.setPhone_number("502304426");

        CallInfo[] data = new CallInfo[]{c1, c2};

        ConfigureListAdapter adapter = new ConfigureListAdapter(this, R.layout.configure_list_adapter, data);
        lvList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configure, menu);
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
