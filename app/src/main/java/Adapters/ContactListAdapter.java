package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.contactsmap.R;

import java.util.List;

import ListEntry.ContactEntry;

/**
 * Created by Rohit on 4/7/16.
 */
public class ContactListAdapter extends ArrayAdapter<ContactEntry> {

    Activity activity;
    List<ContactEntry> contactListEntries;

    public ContactListAdapter(Activity context, List<ContactEntry> objects) {
        super(context, R.layout.item_contact, objects);
        this.activity = context;
        this.contactListEntries = objects;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null)
            itemView = activity.getLayoutInflater().inflate(R.layout.item_contact, parent, false);

        ContactEntry entry = contactListEntries.get(position);

        TextView name = (TextView) itemView.findViewById(R.id.contact_name);
        name.setText(entry.getName());

        TextView phone = (TextView) itemView.findViewById(R.id.contact_phone);
        phone.setText((entry.getPhone() == 0)?("[Not provided]"):(entry.getPhone()+""));

        return itemView;
    }
}
