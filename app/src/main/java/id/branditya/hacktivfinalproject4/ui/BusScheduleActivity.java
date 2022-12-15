package id.branditya.hacktivfinalproject4.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;

import id.branditya.hacktivfinalproject4.R;
import id.branditya.hacktivfinalproject4.adapter.BusAdapter;
import id.branditya.hacktivfinalproject4.database.Bus;
import id.branditya.hacktivfinalproject4.database.SQLiteDatabaseHandler;

public class BusScheduleActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;
    ArrayList<Bus> busses;
    BusAdapter busAdapter;
    TextView tvPassengerCount;
    TextView tvDepartureCity;
    TextView tvArrivalCity;
    TextView tvUnavailableBus;
    ListView listview;
    ImageView btnDropdownDepartureCity;
    ImageView btnDropdownArrivalCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);
        db = new SQLiteDatabaseHandler(this);
        Bundle bundle = getIntent().getExtras();

        tvDepartureCity = findViewById(R.id.tv_departure_city);
        tvArrivalCity = findViewById(R.id.tv_arrival_city);
        btnDropdownDepartureCity = findViewById(R.id.btn_dropdown_departure);
        btnDropdownArrivalCity = findViewById(R.id.btn_dropdown_arrival);
        tvPassengerCount = findViewById(R.id.tv_passenger);
        listview = findViewById(R.id.lv_buss_schedule);
        tvUnavailableBus = findViewById(R.id.tv_unavailable_bus);
        ImageView btnBack = findViewById(R.id.btn_back);

        tvDepartureCity.setText(bundle.getString("KEY_DEPARTURE_CITY"));
        tvArrivalCity.setText(bundle.getString("KEY_ARRIVAL_CITY"));
        tvPassengerCount.setText(bundle.getString("KEY_PASSENGER_COUNT"));

        selectDepartureCity();
        selectArrivalCity();
        selectPassengerCount();
        getBusData();

        btnBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void selectDepartureCity() {
        btnDropdownDepartureCity.setOnClickListener(view -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.layout_dialog_select_city);
            TextView tvSelectCityTitle = dialog.findViewById(R.id.tv_select_city_title);
            TextView tvCityMedan = dialog.findViewById(R.id.tv_city_medan);
            TextView tvCityPekanbaru = dialog.findViewById(R.id.tv_city_pekanbaru);
            TextView tvCityRiau = dialog.findViewById(R.id.tv_city_riau);

            tvSelectCityTitle.setText("Select Departure City");
            tvCityMedan.setOnClickListener(view2 -> {
                tvDepartureCity.setText("Medan");
                getBusData();
                dialog.dismiss();
            });
            tvCityPekanbaru.setOnClickListener(view2 -> {
                tvDepartureCity.setText("Pekanbaru");
                getBusData();
                dialog.dismiss();
            });
            tvCityRiau.setOnClickListener(view2 -> {
                tvDepartureCity.setText("Riau");
                getBusData();
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    private void selectArrivalCity() {
        btnDropdownArrivalCity.setOnClickListener(view -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.layout_dialog_select_city);
            TextView tvSelectCityTitle = dialog.findViewById(R.id.tv_select_city_title);
            TextView tvCityMedan = dialog.findViewById(R.id.tv_city_medan);
            TextView tvCityPekanbaru = dialog.findViewById(R.id.tv_city_pekanbaru);
            TextView tvCityRiau = dialog.findViewById(R.id.tv_city_riau);

            tvSelectCityTitle.setText("Select Arrival City");
            tvCityMedan.setOnClickListener(view2 -> {
                tvArrivalCity.setText("Medan");
                getBusData();
                dialog.dismiss();
            });
            tvCityPekanbaru.setOnClickListener(view2 -> {
                tvArrivalCity.setText("Pekanbaru");
                getBusData();
                dialog.dismiss();
            });
            tvCityRiau.setOnClickListener(view2 -> {
                tvArrivalCity.setText("Riau");
                getBusData();
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    private void selectPassengerCount() {
        tvPassengerCount.setOnClickListener(view -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.layout_dialog_passenger_count);

            Slider sliderPassengerCount = dialog.findViewById(R.id.slider_passenger_count);
            TextView tvDialogPassengerCount = dialog.findViewById(R.id.tv_passenger_count);
            Button btnCancel = dialog.findViewById(R.id.btn_passenger_cancel);
            Button btnSelect = dialog.findViewById(R.id.btn_passenger_select);

            if (sliderPassengerCount != null) {
                sliderPassengerCount.addOnChangeListener((slider, value, fromUser) -> {
                    if (tvDialogPassengerCount != null) {
                        tvDialogPassengerCount.setText(String.valueOf(Math.round(value)));
                    }
                });
            }
            btnCancel.setOnClickListener(view2 -> {
                dialog.dismiss();
            });
            btnSelect.setOnClickListener(view2 -> {
                if (tvDialogPassengerCount != null) {
                    tvPassengerCount.setText(tvDialogPassengerCount.getText());
                }
                getBusData();
                dialog.dismiss();
            });
            dialog.setCancelable(false);
            dialog.show();
        });
    }

    private void getBusData() {
        busses = (ArrayList<Bus>) db.getBusBySearch(
                tvDepartureCity.getText().toString(),
                tvArrivalCity.getText().toString()
        );
        if (busses.isEmpty()){
            tvUnavailableBus.setVisibility(View.VISIBLE);
        } else {
            tvUnavailableBus.setVisibility(View.GONE);
        }
        busAdapter = new BusAdapter(this, busses, db,
                Integer.parseInt(tvPassengerCount.getText().toString()));
        listview.setAdapter(busAdapter);
    }
}