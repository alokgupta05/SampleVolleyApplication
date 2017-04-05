package com.example.samplevolleyapplication.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.samplevolleyapplication.R;


public class MainActivity extends AppCompatActivity {

    private Button mBtnPost;
    private Button mBtnGet;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnGet=   (Button)findViewById(R.id.getbutton);
        mBtnPost = (Button) findViewById(R.id.postbutton);
      /*  mSearchView = (SearchView)findViewById(R.id.search_view);
        mSearchView.setIconifiedByDefault(false);*/
        mBtnGet.setOnClickListener(mGetOnClickListener);
        mBtnPost.setOnClickListener(mPostOnClickListener);

       /* mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
// do something on text submit
                Toast.makeText(MainActivity.this,query,Toast.LENGTH_SHORT).show();
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                return false;
            }
        });*/

    }

    View.OnClickListener mGetOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            SampleServiceClass sampleServiceClass = new SampleServiceClass();
            sampleServiceClass.getGithubData(new RequestEventListener() {
                @Override
                public void OnError(Object data) {
                    Toast.makeText(MainActivity.this,"weather api acall failed",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(Object data) {
                    Toast.makeText(MainActivity.this,"weather api call successful",Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    View.OnClickListener mPostOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            SampleServiceClass sampleServiceClass = new SampleServiceClass();
            sampleServiceClass.postData(new RequestEventListener() {
                @Override
                public void OnError(Object data) {
                    Toast.makeText(MainActivity.this,"google api acall failed",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(Object data) {
                    Toast.makeText(MainActivity.this,"google api call successful",Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
