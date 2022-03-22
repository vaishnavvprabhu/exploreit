package in.exploreit.slc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import in.exploreit.slc.data.models.Events;
import in.exploreit.slc.utils.ListItemClickCallback;

public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsAdapter.eventsViewholder> {

    //Author : VVP
    ListItemClickCallback clickCallback;
    public EventsAdapter(
            @NonNull FirebaseRecyclerOptions<Events> options, ListItemClickCallback clickCallback) {
        super(options);
        this.clickCallback = clickCallback;
    }

    //Function to bind the view in Card View (events.xml) with data in the model class - events.class
    @Override
    protected void onBindViewHolder(@NonNull EventsAdapter.eventsViewholder holder, int position, @NonNull Events model)
    {
        //Data Entry
        holder.evname.setText(model.getName());
        String eximgview = String.valueOf(model.getImg());
        Glide.with(holder.image).load(eximgview).centerCrop().into(holder.image);
        holder.rootCardView.setOnClickListener(v -> {
            // TODO change this hardcoded url with page url which needs to be added in Events class
            clickCallback.onListItemClicked("https://exploreit.in/");
        });
    }

    @NonNull
    @Override
    public EventsAdapter.eventsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events, parent, false);
        return new EventsAdapter.eventsViewholder(view);
    }

    //Sub Class for Reference
    class eventsViewholder extends RecyclerView.ViewHolder{
        TextView evname;
        ImageView image;
        CardView rootCardView;

        public eventsViewholder(@NonNull View itemView) {
            super(itemView);
            evname = itemView.findViewById(R.id.ev_name);
            evname.setSelected(true); //for ellip marquee to work
            image = itemView.findViewById(R.id.ev_imageview);
            rootCardView = (CardView) itemView.getRootView();
        }
    }
}
