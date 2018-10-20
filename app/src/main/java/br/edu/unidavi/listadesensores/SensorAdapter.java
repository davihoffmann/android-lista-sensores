package br.edu.unidavi.listadesensores;

import android.hardware.Sensor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    private final List<Sensor> sensorList;
    private final OnClickListener listener;

    public SensorAdapter(List<Sensor> sensorList, OnClickListener listener) {
        this.sensorList = sensorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_sensor, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Sensor sensor = sensorList.get(i);
        viewHolder.bind(sensor, listener);
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    interface OnClickListener {
        void onClick(Sensor sensor);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView vendor;
        private TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sensor_item_name);
            vendor = itemView.findViewById(R.id.sensor_item_vendor);
            type = itemView.findViewById(R.id.sensor_item_type);
        }

        public void bind(final Sensor sensor, final OnClickListener listener) {
            name.setText(sensor.getName());
            vendor.setText(sensor.getVendor());
            if(Build.VERSION.SDK_INT > 19) {
                type.setText(sensor.getStringType());
            } else {
                type.setText(String.valueOf(sensor.getType()));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(sensor);
                }
            });
        }
    }

}
