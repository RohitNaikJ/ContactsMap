package com.echo.contactsmap;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

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
}
