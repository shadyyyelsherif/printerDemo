package com.example.shadi.printerdemo.Nav_Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shadi.printerdemo.MainActivity;
import com.example.shadi.printerdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    ListView listView;
    int [] img = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d };

    String [] names = {"Letter a", "Letter b", "Letter c", "Letter d"};
    String [] descrption = {"Description a", "Description b", "Description c", "Description d"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //custom listView
        ListView lv2 = view.findViewById(R.id.listView2);
        CustomAdapter customAdapter = new CustomAdapter();
        lv2.setAdapter(customAdapter);

    return view;

    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return img.length ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = getLayoutInflater().inflate(R.layout.custom_list, null);
            ImageView imageView = view.findViewById(R.id.imageView1);
            TextView textView = view.findViewById(R.id.textView_name);
            TextView textView1 = view.findViewById(R.id.textView_description);

            imageView.setImageResource(img[position]);
            textView.setText(names[position]);
            textView1.setText(descrption[position]);

            return view;
        }
    }

}
