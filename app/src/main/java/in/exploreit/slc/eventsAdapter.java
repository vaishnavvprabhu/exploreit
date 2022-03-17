package in.exploreit.slc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import in.exploreit.slc.data.models.events;

public class eventsAdapter extends FirebaseRecyclerAdapter<events, eventsAdapter.eventsViewholder> {

    //Author : VVP

    public eventsAdapter(
            @NonNull FirebaseRecyclerOptions<events> options)
    {
        super(options);
    }

    //Function to bind the view in Card View (events.xml) with data in the model class - events.class
    @Override
    protected void onBindViewHolder(@NonNull eventsAdapter.eventsViewholder holder, int position, @NonNull events model)
    {
        //Data Entry
        holder.evname.setText(model.getName());



        String eximgview = String.valueOf(model.getImg());
        //Picasso loads image into recycler from firebase link
        //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
        //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
        Picasso.get().load(eximgview).into(holder.image);

    }

    @NonNull
    @Override
    public eventsAdapter.eventsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events, parent, false);
        return new eventsAdapter.eventsViewholder(view);
    }

    //Sub Class for Reference
    class eventsViewholder extends RecyclerView.ViewHolder{
        TextView evname;
        ImageView image;

        public eventsViewholder(@NonNull View itemView) {
            super(itemView);

            evname = (TextView) itemView.findViewById(R.id.ev_name);

            //for ellip marquee to work
            evname.setSelected(true);

            image = (ImageView) itemView.findViewById(R.id.ev_imageview);

        }
    }
}
