package th.su.finaltest.chanya1999.speedrecords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import th.su.finaltest.chanya1999.speedrecords.R;
import th.su.finaltest.chanya1999.speedrecords.model.Record;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private Context mContext;
    private Record[] mRecords;

    public RecordAdapter(Context context, Record[] records) {
        this.mContext = context;
        this.mRecords = records;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_speed_record, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Record record = mRecords[position];
        double calSpeed = Calculate(record.distance,record.duration);
        holder.speedResultTextView.setText(String.format("%.1f",calSpeed)+" KM/H");
        holder.distanceAndDurationTextView.setText(String.format("%.1f",record.distance)+ " METERS, " + String.format("%.1f",record.duration)+" SECONDS");
        if(calSpeed > 80.0 ){
            holder.redCowImageView.setImageResource(R.drawable.red_cow );
        }

    }


    @Override
    public int getItemCount() {
        return mRecords.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView speedResultTextView;
        TextView distanceAndDurationTextView;
        ImageView redCowImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speedResultTextView = itemView.findViewById(R.id.speed_result_text_view);
            this.distanceAndDurationTextView = itemView.findViewById(R.id.distance_and_duration_text_view);
            this.redCowImageView = itemView.findViewById(R.id.red_cow_image_view);
        }
    }

    double Calculate (double distance, double duration){
        return (distance/duration)*(3.6);
        //String.format("%.2f",(distance/time)*(3.6));
    }
}
