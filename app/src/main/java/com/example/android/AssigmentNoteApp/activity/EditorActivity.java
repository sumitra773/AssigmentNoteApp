package com.example.android.AssigmentNoteApp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.AssigmentNoteApp.R;
import com.example.android.AssigmentNoteApp.room.ItemDatabase;
import com.example.android.AssigmentNoteApp.room.ItemModel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class EditorActivity extends AppCompatActivity {

    private EditText mTitle, mDescription;
    private Button mSave;
    ImageView mImageView;
    Button mAddImage;
    String imagePath;
    Boolean isEdit = false;
    int id = -1;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer);

        mTitle = findViewById(R.id.edit_title);
        mDescription = findViewById(R.id.edit_description);
        mSave = findViewById(R.id.btn_save);

        mAddImage = findViewById(R.id.btn_choose);
        mImageView = findViewById(R.id.add_img);

        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();

                }
            }
        });


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save(mTitle.getText().toString().trim(), mDescription.getText().toString().trim());

            }
        });

        loadData(getIntent().getIntExtra("_id", -1));
    }

    private void loadData(int id) {
        if(id == -1) {
            return;
        }
        ItemDatabase db = ItemDatabase.getDbInstance(this.getApplicationContext());

        ItemModel itemModel = db.mItemDAO().getItemById(id);
        mTitle.setText(itemModel.getTitle());
        mDescription.setText(itemModel.getDescription());
        if(itemModel.getImage() != null) {
            imagePath = itemModel.getImage();
            mImageView.setImageURI(Uri.parse(itemModel.getImage()));
        }
        this.id = id;
        isEdit = true;
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();

                } else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imagePath = getPath(data.getData());
            mImageView.setImageURI(data.getData());
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }


    private void save(String mTitle, String mDescription) {
        final ItemDatabase db = ItemDatabase.getDbInstance(getApplicationContext());
        ItemModel itemModel = new ItemModel();
        String title = mTitle;
        String description = mDescription;

        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setImage(imagePath);

        if(isEdit) {
            itemModel.setId(id);
            db.mItemDAO().updateAll(itemModel);
        } else {
            db.mItemDAO().insertAll(itemModel);
        }
        setResult(Activity.RESULT_OK);
        finish();
    }


}