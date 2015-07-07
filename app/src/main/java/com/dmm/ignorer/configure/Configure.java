package com.dmm.ignorer.configure;

import android.app.Activity;
import android.content.Intent;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.dmm.ignorer.Globals;
import com.dmm.ignorer.R;
import com.dmm.ignorer.adapters.ConfigureListAdapter;
import com.dmm.ignorer.domain.CallInfo;

public class Configure extends Activity implements View.OnClickListener{
    private ListView lvList;

    private Button btnAddIgnored;
    private Button btnAddIgnoredFromContacts;
    private Button btnAddIgnoredFromCallList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        lvList = (ListView)findViewById(R.id.lvConfigure);

        btnAddIgnored = (Button)findViewById(R.id.btnAddIgnored);
        btnAddIgnoredFromContacts = (Button)findViewById(R.id.btnAddIgnoredFromContacts);
        btnAddIgnoredFromCallList = (Button)findViewById(R.id.btnAddIgnoredFromCallList);
        btnAddIgnored.setOnClickListener(this);
        btnAddIgnoredFromContacts.setOnClickListener(this);
        btnAddIgnoredFromCallList.setOnClickListener(this);


        CallInfo c1 = new CallInfo();
        c1.setActive(false);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case Globals.REQ_CODE_PICK_FROM_CALL_LOG:
                    break;
                case Globals.REQ_CODE_PICK_FROM_CONTACTS:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnAddIgnored:
                break;
            case R.id.btnAddIgnoredFromCallList:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setType(CallLog.Calls.CONTENT_TYPE);
                startActivityForResult(intent, Globals.REQ_CODE_PICK_FROM_CONTACTS);
                break;
            case R.id.btnAddIgnoredFromContacts:
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, Globals.REQ_CODE_PICK_FROM_CALL_LOG);
                break;
        }
    }


}
