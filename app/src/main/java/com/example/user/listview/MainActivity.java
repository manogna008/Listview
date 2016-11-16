package com.example.user.listview;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView l;
    ListView mDynamicListView;
    int bt2;
    Button bt1;
    ImageView i1;
    String[] memetitles;
    String[] memediscription;
    int[] images = {R.drawable.sonali, R.drawable.sonali, R.drawable.share, R.drawable.like, R.drawable.send};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        memetitles = res.getStringArray(R.array.titles);
        memediscription = res.getStringArray(R.array.description);
        l = (ListView) findViewById(R.id.ListView);
        l.setItemsCanFocus(true);


        vivzadapter adapter = new vivzadapter(this, memetitles, images, memediscription);
        l.setAdapter(adapter);
    }


    class vivzadapter extends ArrayAdapter<String> {
        Context context;
        int[] images;
        String[] titleArray;
        String[] dispArray;

        vivzadapter(Context c, String[] titles, int ims[], String[] dispArray) {
            super(c, R.layout.single_row, R.id.textView, titles);
            this.context = c;
            this.images = ims;
            titleArray = titles;
            this.dispArray = dispArray;
        }


        @NonNull
        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            View row=convertView;
            if(row==null) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 row = inflater.inflate(R.layout.single_row, parent, false);
            }
            row.setClickable(true);
            row.setFocusable(true);

            ImageView myimage = (ImageView) row.findViewById(R.id.imageView);
            TextView mytitles = (TextView) row.findViewById(R.id.textView);
            bt1 = (Button) row.findViewById(R.id.button1);
            i1 = (ImageView) row.findViewById(R.id.tick);
            TextView mydiscription = (TextView) row.findViewById(R.id.textView3);
            myimage.setImageResource(images[position]);
            mytitles.setText(titleArray[position]);
            mydiscription.setText(dispArray[position]);
            if (bt1.getTag() == null)
                bt1.setTag("");
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSelection(view);
                    if (view.getId()==R.id.button1) {

                       bt1.setVisibility(view.GONE);
                        i1.setVisibility(view.VISIBLE);
                       Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                   }

                }
            });

            i1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.tick) {
                        bt1.setVisibility(v.VISIBLE);
                        i1.setVisibility(v.GONE);
                    }
                }
            });


            return row;
        }

        private void btnSelection(View button) {
            if (button.getTag().equals("no")) {
                button.setTag("yes");
                bt1.setVisibility(button.GONE);
                i1.setVisibility(button.VISIBLE);
            }


        }


    }
}



