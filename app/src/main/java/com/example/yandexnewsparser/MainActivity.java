package com.example.yandexnewsparser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<PostValue> postValueArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.wrap); // find another  3 param
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Toast.makeText(MainActivity.this,"Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        new MyTask().execute();

    }
    class MyTask extends AsyncTask<Void,Void,Void>{
        ProgressBar progressBar;
        ParsingClass pc;

        @Override
        protected Void doInBackground(Void... voids) {
            pc = new ParsingClass();
            pc.get();
            postValueArrayList = pc.getPostsList();


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = new ProgressBar(MainActivity.this);
            progressBar.setIndeterminate(true);
            progressBar.isShown();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            PostBaseAdapter postBaseAdapter = new PostBaseAdapter(MainActivity.this, postValueArrayList);
            listView.setAdapter(postBaseAdapter);
            progressBar.setVisibility(View.GONE);

        }
    }
}
