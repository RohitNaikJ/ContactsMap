package com.echo.contactsmap;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Fragments.ContactsFragment;
import Fragments.MapsFragment;
import ListEntry.ContactEntry;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    public static List<ContactEntry> contactEntries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("contacts").setIndicator("All Contacts", null),
                ContactsFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("maps").setIndicator("Contacts Map", null),
                MapsFragment.class, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sync_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                finish();
                return true;

            case R.id.action_sync:
                Toast.makeText(MainActivity.this, "Syncing Contacts..", Toast.LENGTH_SHORT).show();
                Contacts syncContacts = new Contacts(contactEntries, this);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
