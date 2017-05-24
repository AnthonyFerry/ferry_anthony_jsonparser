package com.example.anthonyferry.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        String[] myDataSet = new String[10];

        MyAdapter adapter = new MyAdapter(myDataSet);
        myRecyclerView.setAdapter(adapter);

        GetContacts getContacts = new GetContacts(this);
        getContacts.execute();

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        String [] myDataSet;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView email;
            public TextView address;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        public MyAdapter(String[] data) {
            myDataSet = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(myDataSet[position]);
            holder.email.setText(myDataSet[position]);
            holder.address.setText(myDataSet[position]);

        }

        @Override
        public int getItemCount() {
            return myDataSet.length;
        }
    }



}

