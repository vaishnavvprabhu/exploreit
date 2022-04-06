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
import in.exploreit.slc.data.models.Event;
import in.exploreit.slc.data.models.ListItem;

public class CommonListAdapter extends ListAdapter<ListItem, CommonListAdapter.ListItemViewHolder> {

    ListItemClickInterface listItemClickCallback;

    public CommonListAdapter(ListItemClickInterface ListItemClickInterface) {
        super(CommonListAdapter.itemDiffCallback);
        this.listItemClickCallback = ListItemClickInterface;
    }

    @NonNull @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        ListItem ListItem = getItem(position);
        holder.bind(ListItem);
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView hours;
        ImageView image;
        CardView rootCardView;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            desc = itemView.findViewById(R.id.item_desc);
            hours = itemView.findViewById(R.id.hours);
            image = itemView.findViewById(R.id.item_img);
            rootCardView = (CardView) itemView.getRootView();
        }

        public void bind(ListItem listItem) {
            title.setText(listItem.getTitle());
            title.setSelected(true); //for ellip marquee to work
            desc.setText(listItem.getDescription());
            desc.setMaxLines(listItem.getNumberOfDescLines());

            String imageUrl = String.valueOf(listItem.getImageUrl());
            Glide.with(image).load(imageUrl).centerCrop().into(image);

            if(listItem instanceof Event) {
                hours.setText(hours.getContext().getString(R.string.hours_template, listItem.getTimeStamp()));
            } else {
                hours.setText(String.valueOf(listItem.getTimeStamp()));
            }

            rootCardView.setOnClickListener(v -> {
                listItemClickCallback.onListItemClicked(listItem.getTargetUrl(), getAbsoluteAdapterPosition());
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
