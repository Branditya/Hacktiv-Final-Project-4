package id.branditya.hacktivfinalproject4.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject4.R;
import id.branditya.hacktivfinalproject4.database.Bus;
import id.branditya.hacktivfinalproject4.database.SQLiteDatabaseHandler;
import id.branditya.hacktivfinalproject4.ui.BusDetailActivity;

public class BusAdapter extends BaseAdapter {
    private final Activity context;
    ArrayList<Bus> busses;
    SQLiteDatabaseHandler db;
    int passengerCount;

    public BusAdapter(Activity context, ArrayList<Bus> busses, SQLiteDatabaseHandler db, int passengerCount) {
        this.context = context;
        this.busses = busses;
        this.db = db;
        this.passengerCount = passengerCount;
    }

    public static class ViewHolder {
        TextView tvBusName;
        TextView tvBusDepartureCity;
        TextView tvBusDepartureTime;
        TextView tvBusArrivalCity;
        TextView tvBusArrivalTime;
        TextView tvBusRating;
        TextView tvBusPrice;
        Button btnBook;
    }

    @Override
    public int getCount() {
        return busses.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.item_bus_info, null, true);

            vh.tvBusName = row.findViewById(R.id.tv_bus_name);
            vh.tvBusDepartureTime = row.findViewById(R.id.tv_departure_time);
            vh.tvBusDepartureCity = row.findViewById(R.id.tv_departure_city);
            vh.tvBusArrivalTime = row.findViewById(R.id.tv_arrival_time);
            vh.tvBusArrivalCity = row.findViewById(R.id.tv_arrival_city);
            vh.tvBusRating = row.findViewById(R.id.tv_bus_rating);
            vh.tvBusPrice = row.findViewById(R.id.tv_bus_price);
            vh.btnBook = row.findViewById(R.id.btn_bus_book);

            row.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.tvBusName.setText(busses.get(position).getName());
        vh.tvBusDepartureTime.setText(busses.get(position).getDepartureTime());
        vh.tvBusDepartureCity.setText(busses.get(position).getDepartureCity());
        vh.tvBusArrivalTime.setText(busses.get(position).getArrivalTime());
        vh.tvBusArrivalCity.setText(busses.get(position).getArrivalCity());
        vh.tvBusRating.setText(busses.get(position).getRating());
        vh.tvBusPrice.setText(String.valueOf(busses.get(position).getPrice()));

        vh.btnBook.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, BusDetailActivity.class);
            intent.putExtra("KEY_PASSENGER_COUNT", passengerCount);
            intent.putExtra("KEY_BUS_ID", busses.get(position).getId());
            context.startActivity(intent);
        });
        return row;
    }
}
