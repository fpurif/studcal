package paulinefran.studentcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import paulinefran.studentcalendar.R;
import paulinefran.studentcalendar.Course;

public class ListCoursesAdapter extends BaseAdapter {

    public static final String TAG = "ListCoursesAdapter";

    private List<Course> mItems;
    private LayoutInflater mInflater;

    public ListCoursesAdapter(Context context, List<Course> listCompanies) {
        this.setItems(listCompanies);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Course getItem(int position) {
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
            v = mInflater.inflate(R.layout.list_item_course, parent, false);
            holder = new ViewHolder();
            holder.txtCourseName = (TextView) v.findViewById(R.id.txt_course_name);
            holder.txtSemesterName = (TextView) v.findViewById(R.id.txt_semester_name);
            holder.txtLocation = (TextView) v.findViewById(R.id.txt_location);
            holder.txtInstructor = (TextView) v.findViewById(R.id.txt_instructor);
            holder.txtInformation = (TextView) v.findViewById(R.id.txt_information);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Course currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtCourseName.setText(currentItem.getCName());
            holder.txtLocation.setText(currentItem.getLocation());
            holder.txtInformation.setText(currentItem.getInfo());
            holder.txtInstructor.setText(currentItem.getInstructor());
            holder.txtSemesterName.setText(currentItem.getmSemester().getName());
        }

        return v;
    }

    public List<Course> getItems() {
        return mItems;
    }

    public void setItems(List<Course> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtCourseName;
        TextView txtInformation;
        TextView txtInstructor;
        TextView txtLocation;
        TextView txtSemesterName;
    }

}
