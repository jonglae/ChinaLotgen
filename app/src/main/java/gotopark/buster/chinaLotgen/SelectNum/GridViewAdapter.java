package gotopark.buster.chinaLotgen.SelectNum;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private AppCompatActivity activity;
    private String[] strings;
    public List<Integer> selectedPositions;

    public GridViewAdapter(String[] strings, AppCompatActivity activity) {
        this.strings = strings;
        this.activity = activity;
        selectedPositions = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridItemView customView = (convertView == null) ? new GridItemView(activity) : (GridItemView) convertView;
        customView.display(strings[position], selectedPositions.contains(position));

        return customView;
    }
}
