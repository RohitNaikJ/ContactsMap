package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.echo.contactsmap.ContactInfoActivity;
import com.echo.contactsmap.MainActivity;
import com.echo.contactsmap.R;

import org.json.JSONArray;
import org.json.JSONException;

import Adapters.ContactListAdapter;
import ListEntry.ContactEntry;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    ListView listView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView_contacts);

        String url = "http://private-b08d8d-nikitest.apiary-mock.com/contacts";

        if(MainActivity.contactEntries.size()==0) {
            JsonArrayRequest req = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            JSONArray contacts = null;
                            try {
                                contacts = response.getJSONObject(0).getJSONArray("contacts");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < contacts.length(); i++) {
                                String name = "[Not Provided]";
                                String email = "[Not Provided]";
                                long phone = 0;
                                long officePhone = 0;
                                double lat = 0;
                                double longi = 0;
                                try {
                                    name = contacts.getJSONObject(i).getString("name");
                                    email = contacts.getJSONObject(i).getString("email");
                                    phone = contacts.getJSONObject(i).getLong("phone");
                                    officePhone = contacts.getJSONObject(i).getLong("officePhone");
                                    lat = contacts.getJSONObject(i).getDouble("latitude");
                                    longi = contacts.getJSONObject(i).getDouble("longitude");
//                                    LatLng ll = new LatLng(lat, longi);
//                                    MainActivity.coords.add(ll);
                                } catch (JSONException e) {
                                    Toast.makeText(getActivity(), e.getMessage() + " for ID:" + i, Toast.LENGTH_SHORT).show();
                                }
                                MainActivity.contactEntries.add(new ContactEntry(name, email, phone, officePhone, lat, longi));
                            }

                            ContactListAdapter adapter = new ContactListAdapter(getActivity(), MainActivity.contactEntries);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ContactEntry contact = MainActivity.contactEntries.get(position);
                                    Intent intent = new Intent(getActivity(), ContactInfoActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", contact.getName());
                                    bundle.putString("email", contact.getEmail());
                                    bundle.putLong("phone", contact.getPhone());
                                    bundle.putLong("officePhone", contact.getOphone());
                                    bundle.putDouble("latitude", contact.getLat());
                                    bundle.putDouble("longitude", contact.getLonge());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "ErrorResponse: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
            Volley.newRequestQueue(getActivity()).add(req);
        }else{
            ContactListAdapter adapter = new ContactListAdapter(getActivity(), MainActivity.contactEntries);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ContactEntry contact = MainActivity.contactEntries.get(position);
                    Intent intent = new Intent(getActivity(), ContactInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", contact.getName());
                    bundle.putString("email", contact.getEmail());
                    bundle.putLong("phone", contact.getPhone());
                    bundle.putLong("officePhone", contact.getOphone());
                    bundle.putDouble("latitude", contact.getLat());
                    bundle.putDouble("longitude", contact.getLonge());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        return rootView;
    }

}
