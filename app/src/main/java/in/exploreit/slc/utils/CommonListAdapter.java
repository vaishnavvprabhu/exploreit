package in.exploreit.slc.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import in.exploreit.slc.R;
import in.exploreit.slc.data.models.ListItem;

public class CommonListAdapter extends ListAdapter<ListItem, CommonListAdapter.ListItemViewHolder> {

    ListItemClickInterface listItemClickCallback;

    public CommonListAdapter(ListItemClickInterface ListItemClickInterface) {
        super(CommonListAdapter.itemDiffCallback);
        this.listItemClickCallback = ListItemClickInterface;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        ListItem ListItem = getItem(position);
        holder.bind(ListItem);
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        CardView rootCardView;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ev_name);
            title.setSelected(true); //for ellip marquee to work
            image = itemView.findViewById(R.id.ev_imageview);
            rootCardView = (CardView) itemView.getRootView();
        }

        public void bind(ListItem ListItem) {
            // TODO add other data types once they are added in the layout
            title.setText(ListItem.getTitle());
            String imageUrl = String.valueOf(ListItem.getImageUrl());
            Glide.with(image).load(imageUrl).centerCrop().into(image);
            rootCardView.setOnClickListener(v -> {
                // TODO change this hardcoded url with page url which needs to be added in Event class
                listItemClickCallback.onListItemClicked("https://exploreit.in/");
            });
        }
    }

    public static DiffUtil.ItemCallback<ListItem> itemDiffCallback = new DiffUtil.ItemCallback<ListItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getTimeStamp() == newItem.getTimeStamp()
                    && oldItem.getImageUrl().equals(newItem.getImageUrl());
        }
    };
}
