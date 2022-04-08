package com.example.android.AssigmentNoteApp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.AssigmentNoteApp.R;
import com.example.android.AssigmentNoteApp.adapter.ItemAdapter;
import com.example.android.AssigmentNoteApp.adapter.ItemClickListener;
import com.example.android.AssigmentNoteApp.room.ItemDatabase;
import com.example.android.AssigmentNoteApp.room.ItemModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ItemAdapter mAdapter;
    private List<ItemModel> mItemModels = new ArrayList<>();

    public static final int REQUEST_CODE = 100;

    FloatingActionButton mFeb;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFeb = findViewById(R.id.btn_feb);

        mFeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,EditorActivity.class);
                startActivityForResult(intent,REQUEST_CODE);

            }
        });

        mRecyclerView = findViewById(R.id.rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemAdapter(this.mItemModels, new ItemClickListener() {
            @Override
            public void onItemClick(int id) {

                Intent intent = new Intent(HomeActivity.this,EditorActivity.class);
                intent.putExtra("_id", id);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        loadItemModel();

    }

    private void loadItemModel(){
        ItemDatabase db = ItemDatabase.getDbInstance(this.getApplicationContext());

        List<ItemModel> itemModels = db.mItemDAO().getAllItem();
        mAdapter.setItemModel(itemModels);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                loadItemModel();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}