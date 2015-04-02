package paulinefran.studentcalendar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import paulinefran.studentcalendar.R;
import paulinefran.studentcalendar.Semester;

public class ListSemestersAdapter extends BaseAdapter {

    public static final String TAG = "ListCompnaiesAdapter";

    private List<Semester> mItems;
    private LayoutInflater mInflater;

    public ListSemestersAdapter(Context context, List<Semester> listSemesters) {
        this.setItems(listSemesters);
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Semester getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_semester, parent, false);
            holder = new ViewHolder();
            holder.txtSemesterName = (TextView) v.findViewById(R.id.txt_semester_name);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Semester currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtSemesterName.setText(currentItem.getName());
        }


        return v;
    }

    public List<Semester> getItems() {
        return mItems;
    }

    public void setItems(List<Semester> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtSemesterName;
        TextView txtWebsite;
        TextView txtPhoneNumber;
        TextView txtAddress;
    }

}
