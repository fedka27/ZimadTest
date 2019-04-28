package club.bambilla.zimadtest.tabs.list.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import club.bambilla.zimadtest.R;
import club.bambilla.zimadtest.models.ListItem;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {
    private List<ListItem> listItems = new ArrayList<>();

    @Nullable
    private ListItemClickListener listItemClickListener;

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListItemViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, int position) {
        listItemViewHolder.bind(listItems.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void setListItems(List<ListItem> items) {
        listItems.clear();
        listItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setListItemClickListener(@Nullable ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView titleTextView, positionTextView;

        public ListItemViewHolder(ViewGroup parent) {
            super(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false));

            iconImageView = itemView.findViewById(R.id.image_view_icon);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            positionTextView = itemView.findViewById(R.id.text_view_position);
        }

        public void bind(ListItem listItem, int position) {
            Picasso.get()
                    .load(listItem.getUrl())
                    .placeholder(android.R.drawable.stat_notify_sync)
                    .error(android.R.drawable.stat_notify_error)
                    .fit()
                    .centerCrop()
                    .into(iconImageView);

            titleTextView.setText(listItem.getTitle());
            positionTextView.setText(String.valueOf(position));

            itemView.setOnClickListener(v -> {
                if (listItemClickListener != null) {
                    listItemClickListener.onItemClick(listItem, position);
                }
            });
        }
    }
}
