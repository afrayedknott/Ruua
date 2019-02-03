package afrayedknott.github.com.ruua;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterFieldWorkerNamesList extends RecyclerView.Adapter<RecyclerViewAdapterFieldWorkerNamesList.ViewHolder> {

    private ArrayList<User> assignedFieldWorkers;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapterFieldWorkerNamesList(Context context, ArrayList<User> assignedFieldWorkers) {
        this.mInflater = LayoutInflater.from(context);
        this.assignedFieldWorkers = assignedFieldWorkers;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyvler_view_row_field_worker_name, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String fullName = assignedFieldWorkers.get(position).getFullName();
        holder.textViewFieldWorkerName.setText(fullName);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return assignedFieldWorkers.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewFieldWorkerName;

        ViewHolder(View itemView) {
            super(itemView);
            textViewFieldWorkerName = itemView.findViewById(R.id.textview_field_worker_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return assignedFieldWorkers.get(id).getFullName();
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