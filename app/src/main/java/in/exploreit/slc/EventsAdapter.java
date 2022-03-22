package in.exploreit.slc;

//public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsAdapter.eventsViewholder> {
//
//    //Author : VVP
//    ListItemClickInterface clickCallback;
//    public EventsAdapter(
//            @NonNull FirebaseRecyclerOptions<Events> options, ListItemClickInterface clickCallback) {
//        super(options);
//        this.clickCallback = clickCallback;
//    }
//
//    //Function to bind the view in Card View (events.xml) with data in the model class - events.class
//    @Override
//    protected void onBindViewHolder(@NonNull EventsAdapter.eventsViewholder holder, int position, @NonNull Events model)
//    {
//        //Data Entry
//        holder.evname.setText(model.getName());
//        String eximgview = String.valueOf(model.getImg());
//        Glide.with(holder.image).load(eximgview).centerCrop().into(holder.image);
//        holder.rootCardView.setOnClickListener(v -> {
//            // TODO change this hardcoded url with page url which needs to be added in Events class
//            clickCallback.onListItemClicked("https://exploreit.in/");
//        });
//    }
//
//    @NonNull
//    @Override
//    public EventsAdapter.eventsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events, parent, false);
//        return new EventsAdapter.eventsViewholder(view);
//    }
//
//    //Sub Class for Reference
//    class eventsViewholder extends RecyclerView.ViewHolder{
//        TextView evname;
//        ImageView image;
//        CardView rootCardView;
//
//        public eventsViewholder(@NonNull View itemView) {
//            super(itemView);
//            evname = itemView.findViewById(R.id.ev_name);
//            evname.setSelected(true); //for ellip marquee to work
//            image = itemView.findViewById(R.id.ev_imageview);
//            rootCardView = (CardView) itemView.getRootView();
//        }
//    }
//}
