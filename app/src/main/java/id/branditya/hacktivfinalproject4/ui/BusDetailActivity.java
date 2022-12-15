package id.branditya.hacktivfinalproject4.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.branditya.hacktivfinalproject4.database.Bus;
import id.branditya.hacktivfinalproject4.R;
import id.branditya.hacktivfinalproject4.database.SQLiteDatabaseHandler;

public class BusDetailActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);
        db = new SQLiteDatabaseHandler(this);


        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("KEY_BUS_ID");
        int passengerCount = bundle.getInt("KEY_PASSENGER_COUNT");
        Bus bus = db.getBus(id);

        TextView tvPassengerCount = findViewById(R.id.tv_bus_passenger);
        TextView tvBusName = findViewById(R.id.tv_bus_name);
        TextView tvBusRating = findViewById(R.id.tv_bus_rating);
        TextView tvDate = findViewById(R.id.tv_bus_date);
        TextView tvDepartureCity = findViewById(R.id.tv_departure_city);
        TextView tvDepartureTime = findViewById(R.id.tv_departure_time);
        TextView tvArrivalCity = findViewById(R.id.tv_arrival_city);
        TextView tvArrivalTime = findViewById(R.id.tv_arrival_time);
        TextView tvTotalTime = findViewById(R.id.tv_bus_total_time);
        TextView tvBusPrice = findViewById(R.id.tv_bus_price);
        TextView tvTotalPrice = findViewById(R.id.tv_bus_total_price);
        ImageView btnBack = findViewById(R.id.btn_back);

        tvBusName.setText(bus.getName());
        tvBusRating.setText(bus.getRating());
        tvDate.setText(bus.getDate());
        tvDepartureCity.setText(bus.getDepartureCity());
        tvDepartureTime.setText(bus.getDepartureTime());
        tvArrivalCity.setText(bus.getArrivalCity());
        tvArrivalTime.setText(bus.getArrivalTime());
        tvTotalTime.setText(bus.getTotalTime());
        tvBusPrice.setText(String.valueOf(bus.getPrice()));
        tvTotalPrice.setText(String.valueOf(bus.getPrice() * passengerCount));
        tvPassengerCount.setText(String.valueOf(passengerCount));

        btnBack.setOnClickListener(view-> {
            finish();
        });
    }
}