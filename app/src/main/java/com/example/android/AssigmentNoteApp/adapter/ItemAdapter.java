package com.example.android.AssigmentNoteApp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.AssigmentNoteApp.R;
import com.example.android.AssigmentNoteApp.room.ItemModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

   private List<ItemModel> items;
   private ItemClickListener mListener;

   public ItemAdapter(List<ItemModel> items, ItemClickListener mListener) {
      this.items = items;
      this.mListener = mListener;
   }

   public void setItemModel(List<ItemModel> itemModels) {
      this.items = itemModels;
      notifyDataSetChanged();
   }
   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
      return new ViewHolder(v);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      holder.tvTitle.setText(items.get(position).getTitle());
      holder.tvDescription.setText(items.get(position).getDescription());
      if(items.get(position).getImage() != null) {
         holder.mImageView.setImageURI(Uri.parse(items.get(position).getImage()));
      }
      holder.root.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            mListener.onItemClick(items.get(position).getId());
         }
      });
   }

   @Override
   public int getItemCount() {
      return items.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {

      TextView tvTitle;
      TextView tvDescription;
      ImageView mImageView;
      View root;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);

         tvTitle = itemView.findViewById(R.id.tv_title);
         tvDescription = itemView.findViewById(R.id.tv_desc);
         mImageView = itemView.findViewById(R.id.imge);
         root = itemView.findViewById(R.id.root);
      }
   }
}
