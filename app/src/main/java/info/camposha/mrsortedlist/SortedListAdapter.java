package info.camposha.mrsortedlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.List;

public class SortedListAdapter extends RecyclerView.Adapter<SortedListAdapter.ViewHolder> {

    SortedList<Star> stars;
    private int LAYOUT = R.layout.model;

    public SortedListAdapter(int layout) {
        this.LAYOUT = layout;
        sort(true,"NAME");
    }

    public void sort(final Boolean ascending, final String property){
        stars = new SortedList<>(Star.class, new SortedList.Callback<Star>() {
            @Override
            public int compare(Star star1, Star star2) {
                if(ascending){
                    if(property.equalsIgnoreCase("COMMENTS")){
                        return String.valueOf(star1.getComments()).compareTo(String.valueOf(star2.getComments()));
                    }else if(property.equalsIgnoreCase("FAVORITES")){
                        return String.valueOf(star1.getFavorites()).compareTo(String.valueOf(star2.getFavorites()));
                    }else if(property.equalsIgnoreCase("VIEWS")){
                        return String.valueOf(star1.getViews()).compareTo(String.valueOf(star2.getViews()));
                    }
                    return star1.getName().compareTo(star2.getName());
                }else{
                    if(property.equalsIgnoreCase("COMMENTS")){
                        return String.valueOf(star2.getComments()).compareTo(String.valueOf(star1.getComments()));
                    }else if(property.equalsIgnoreCase("FAVORITES")){
                        return String.valueOf(star2.getFavorites()).compareTo(String.valueOf(star1.getFavorites()));
                    }else if(property.equalsIgnoreCase("VIEWS")){
                        return String.valueOf(star2.getViews()).compareTo(String.valueOf(star1.getViews()));
                    }
                    return star2.getName().compareTo(star1.getName());
                }
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Star star1, Star star2) {
                return star1.getName().equals(star2.getName());
            }

            @Override
            public boolean areItemsTheSame(Star star1, Star star2) {
                return star1.getName().equals(star2.getName());
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });

    }

    public void addAll(List<Star> starList) {
        stars.beginBatchedUpdates();
        for (int i = 0; i < starList.size(); i++) {
            stars.add(starList.get(i));
        }
        stars.endBatchedUpdates();
    }

    public Star get(int position) {
        return stars.get(position);
    }

    public void clear() {
        stars.beginBatchedUpdates();
        //remove items at end, to avoid unnecessary array shifting
        while (stars.size() > 0) {
            stars.removeItemAt(stars.size() - 1);
        }
        stars.endBatchedUpdates();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(LAYOUT, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Star star = stars.get(position);
        holder.nameTxt.setText(star.getName());
        holder.commentsTxt.setText(String.valueOf(star.getComments()));
        holder.favoritesTxt.setText(String.valueOf(star.getFavorites()));
        holder.viewsTxt.setText(String.valueOf((int)(Math.ceil(star.getViews()/1000)))+"K");
    }

    @Override
    public int getItemCount() {
        return stars.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt,commentsTxt,favoritesTxt,viewsTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            commentsTxt = itemView.findViewById(R.id.commentsTxt);
            favoritesTxt = itemView.findViewById(R.id.favoritesTxt);
            viewsTxt = itemView.findViewById(R.id.viewsTxt);

        }

    }

}
//end
