package id.branditya.hacktivfinalproject4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.Slider;

import id.branditya.hacktivfinalproject4.R;

public class HomeActivity extends AppCompatActivity {
    TextView tvPassengerCount;
    TextView tvDepartureCity;
    TextView tvArrivalCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnSearch = findViewById(R.id.btn_search_bus);
        tvPassengerCount = findViewById(R.id.tv_passenger_count);
        tvDepartureCity = findViewById(R.id.tv_departure_city);
        tvArrivalCity = findViewById(R.id.tv_arrival_city);

        selectDepartureCity();
        selectArrivalCity();
        selectPassengerCount();

        btnSearch.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, BusScheduleActivity.class);
            intent.putExtra("KEY_PASSENGER_COUNT", tvPassengerCount.getText());
            intent.putExtra("KEY_DEPARTURE_CITY", tvDepartureCity.getText());
            intent.putExtra("KEY_ARRIVAL_CITY", tvArrivalCity.getText());
            startActivity(intent);
        });
    }

    private void selectDepartureCity() {
        tvDepartureCity.setOnClickListener(view -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.layout_dialog_select_city);
            TextView tvSelectCityTitle = dialog.findViewById(R.id.tv_select_city_title);
            TextView tvCityMedan = dialog.findViewById(R.id.tv_city_medan);
            TextView tvCityPekanbaru = dialog.findViewById(R.id.tv_city_pekanbaru);
            TextView tvCityRiau = dialog.findViewById(R.id.tv_city_riau);

            tvSelectCityTitle.setText("Select Departure City");
            tvCityMedan.setOnClickListener(view2->{
                tvDepartureCity.setText("Medan");
                dialog.dismiss();
            });
            tvCityPekanbaru.setOnClickListener(view2->{
                tvDepartureCity.setText("Pekanbaru");
                dialog.dismiss();
            });
            tvCityRiau.setOnClickListener(view2->{
                tvDepartureCity.setText("Riau");
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    private void selectArrivalCity() {
        tvArrivalCity.setOnClickListener(view -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.layout_dialog_select_city);
            TextView tvSelectCityTitle = dialog.findViewById(R.id.tv_select_city_title);
            TextView tvCityMedan = dialog.findViewById(R.id.tv_city_medan);
            TextView tvCityPekanbaru = dialog.findViewById(R.id.tv_city_pekanbaru);
            TextView tvCityRiau = dialog.findViewById(R.id.tv_city_riau);

            tvSelectCityTitle.setText("Select Arrival City");
            tvCityMedan.setOnClickListener(view2->{
                tvArrivalCity.setText("Medan");
                dialog.dismiss();
            });
            tvCityPekanbaru.setOnClickListener(view2->{
                tvArrivalCity.setText("Pekanbaru");
                dialog.dismiss();
            });
            tvCityRiau.setOnClickListener(view2->{
                tvArrivalCity.setText("Riau");
                dialog.dismiss();
            });;
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
                dialog.dismiss();
            });
            dialog.setCancelable(false);
            dialog.show();
        });
    }
}