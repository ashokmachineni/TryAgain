package com.ashok.tryagain;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    public FirebaseRecyclerAdapter<Transaction,TransactionViewHolder> firebaseRecyclerAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Movie");
        mRecyclerView = (RecyclerView) findViewById(R.id.rcView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Transaction, TransactionViewHolder>(
                Transaction.class,
                R.layout.transcation_list,
                TransactionViewHolder.class,
                mDatabase
        ) {


            @Override
            protected void populateViewHolder(final TransactionViewHolder viewHolder, Transaction model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Transaction transaction = firebaseRecyclerAdapter.getItem(viewHolder.getAdapterPosition());
            //For play with Vitamio Library
                        /*VideoPlayer.pathToFileOrUrl = transaction.getLink();
                        Intent intent = new Intent(MainActivity.this,VideoPlayer.class);
                        startActivity(intent);*/
          //this code for open with mxplayer only
                        /*Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType( Uri.parse(transaction.getLink()), "application/x-mpegURL" );
                        intent.setPackage( "com.mxtech.videoplayer.ad" );
                        startActivity( intent );*/
               //This code for open with any player
                        /*Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(transaction.getLink()), "video*//*");
                        //startActivity(Intent.createChooser(intent, "Complete action using"));
                        startActivity(Intent.createChooser(intent, "Complete action using"));*/
        //Exoplayer
                        Intent intent = new Intent(MainActivity.this, ExoPlayerActivity.class);
                        
                        //intent.putExtra(ExoPlayerActivity.EXTRA_URL, getString(R.string.link));
                        //intent.setDataAndType(Uri.parse(transaction.getLink(),ExoPlayerActivity.EXTRA_URL))
                        startActivity(intent);



                    }
                });

            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public static class TransactionViewHolder extends RecyclerView.ViewHolder{
            View mView;
        public TransactionViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView movie_title = (TextView) mView.findViewById(R.id.movie_title);
            movie_title.setText(title);
        }
        public void setImage(Context ctx,String image){
            ImageView movie_image = (ImageView) mView.findViewById(R.id.movie_image);
            Picasso.with(ctx).load(image).into(movie_image);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }
}
