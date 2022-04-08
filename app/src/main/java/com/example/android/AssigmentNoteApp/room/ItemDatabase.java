package com.example.android.AssigmentNoteApp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ItemModel.class},version = 1,exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {

   public abstract ItemDAO mItemDAO();

   public static ItemDatabase INSTANCE;

   public static ItemDatabase getDbInstance(Context context){

      if(INSTANCE == null){
         INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ItemDatabase.class, "Item_Database")
                 .allowMainThreadQueries()
                 .build();
      }
      return INSTANCE;
   }
}
