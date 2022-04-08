package com.example.android.AssigmentNoteApp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ItemDAO {

    @Query("select * from item_table")
    public List<ItemModel> getAllItem();

    @Query("SELECT * FROM item_table WHERE id =:id")
    public ItemModel getItemById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(ItemModel itemModel);

    @Delete
    public void deleteAll(ItemModel itemModel);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateAll(ItemModel itemModel);

}
