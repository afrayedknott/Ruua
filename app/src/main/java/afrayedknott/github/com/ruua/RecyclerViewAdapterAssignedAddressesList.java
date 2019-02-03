package afrayedknott.github.com.ruua;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterAssignedAddressesList extends RecyclerView.Adapter<RecyclerViewAdapterAssignedAddressesList.ViewHolder> {

    private ArrayList<String> assignedAddressList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapterAssignedAddressesList(Context context, ArrayList<String> inputAddressList) {
        this.mInflater = LayoutInflater.from(context);
        this.assignedAddressList = inputAddressList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_row_assigned_address_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String fullName = assignedAddressList.get(position);
        holder.textViewAssignedAddressList.setText(fullName);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return assignedAddressList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewAssignedAddressList;

        ViewHolder(View itemView) {
            super(itemView);
            textViewAssignedAddressList = itemView.findViewById(R.id.textview_assigned_address_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return assignedAddressList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}