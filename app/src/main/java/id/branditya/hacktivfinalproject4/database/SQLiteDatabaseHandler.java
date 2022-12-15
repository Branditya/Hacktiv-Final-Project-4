package id.branditya.hacktivfinalproject4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appData";
    private static final String TABEL_BUS = "Bus";

    private static final String KEY_ID = "id";
    private static final String BUS_NAME = "BUS_NAME";
    private static final String BUS_DEPARTURE_CITY = "BUS_DEPARTURE_CITY";
    private static final String BUS_DEPARTURE_TIME = "BUS_DEPARTURE_TIME";
    private static final String BUS_ARRIVAL_CITY = "BUS_ARRIVAL_CITY";
    private static final String BUS_ARRIVAL_TIME = "BUS_ARRIVAL_TIME";
    private static final String BUS_RATING = "BUS_RATING";
    private static final String BUS_DATE = "BUS_DATE";
    private static final String BUS_TOTAL_TIME = "BUS_TOTAL_TIME";
    private static final String BUS_PRICE = "BUS_PRICE";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BUS_TABLE = "CREATE TABLE " + TABEL_BUS + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + BUS_NAME + " TEXT," + BUS_DEPARTURE_CITY + " TEXT," +
                BUS_DEPARTURE_TIME + " TEXT," + BUS_ARRIVAL_CITY + " TEXT," +
                BUS_ARRIVAL_TIME + " TEXT," + BUS_RATING + " TEXT," +
                BUS_DATE + " TEXT," + BUS_TOTAL_TIME + " TEXT," +
                BUS_PRICE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_BUS_TABLE);

        Bus bus1 = new Bus("Sempati Star", "Medan", "17:35",
                "Pekanbaru", "09:10", "9/10", "Fri, 16 Dec 2022",
                "13h 45m", 166000);
        Bus bus2 = new Bus("Pahala Kencana", "Medan", "02:30",
                "Riau", "05:20", "8/10", "Fri, 16 Dec 2022",
                "3h 50m", 80000);
        addBus(sqLiteDatabase, bus1);
        addBus(sqLiteDatabase, bus2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABEL_BUS);
        onCreate(sqLiteDatabase);
    }

    public void addBus(SQLiteDatabase sqLiteDatabase, Bus bus) {

        ContentValues values = new ContentValues();
        values.put(BUS_NAME, bus.getName());
        values.put(BUS_DEPARTURE_CITY, bus.getDepartureCity());
        values.put(BUS_DEPARTURE_TIME, bus.getDepartureTime());
        values.put(BUS_ARRIVAL_CITY, bus.getArrivalCity());
        values.put(BUS_ARRIVAL_TIME, bus.getArrivalTime());
        values.put(BUS_RATING, bus.getRating());
        values.put(BUS_DATE, bus.getDate());
        values.put(BUS_TOTAL_TIME, bus.getTotalTime());
        values.put(BUS_PRICE, bus.getPrice());

        sqLiteDatabase.insert(TABEL_BUS, null, values);
    }

    public List<Bus> getAllBus() {
        List<Bus> busList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_BUS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Bus bus = new Bus();
                bus.setId(Integer.parseInt(cursor.getString(0)));
                bus.setName(cursor.getString(1));
                bus.setDepartureCity(cursor.getString(2));
                bus.setDepartureTime(cursor.getString(3));
                bus.setArrivalCity(cursor.getString(4));
                bus.setArrivalTime(cursor.getString(5));
                bus.setRating(cursor.getString(6));
                bus.setDate(cursor.getString(7));
                bus.setTotalTime(cursor.getString(8));
                bus.setPrice(Integer.parseInt(cursor.getString(9)));
                busList.add(bus);
            } while (cursor.moveToNext());
        }
        return busList;
    }

    //getting single bus
    public Bus getBus(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABEL_BUS, new String[]{KEY_ID, BUS_NAME, BUS_DEPARTURE_CITY,
                        BUS_DEPARTURE_TIME, BUS_ARRIVAL_CITY, BUS_ARRIVAL_TIME, BUS_RATING, BUS_DATE,
                        BUS_TOTAL_TIME, BUS_PRICE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Bus bus = new Bus(cursor.getString(1), cursor.getString(2), cursor.getString(3)
                , cursor.getString(4), cursor.getString(5), cursor.getString(6)
                , cursor.getString(7), cursor.getString(8), Integer.parseInt(cursor.getString(9)));

        return bus;
    }

    public List<Bus> getBusBySearch(String departureCity, String arrivalCity, String busDate) {
        List<Bus> busList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_BUS + " WHERE " + BUS_DEPARTURE_CITY +
                "=" + "'" + departureCity + "'" + " AND " + BUS_ARRIVAL_CITY + "=" +
                "'" + arrivalCity + "'" + " AND " + BUS_DATE + "=" +
                "'" + busDate + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Bus bus = new Bus();
                bus.setId(Integer.parseInt(cursor.getString(0)));
                bus.setName(cursor.getString(1));
                bus.setDepartureCity(cursor.getString(2));
                bus.setDepartureTime(cursor.getString(3));
                bus.setArrivalCity(cursor.getString(4));
                bus.setArrivalTime(cursor.getString(5));
                bus.setRating(cursor.getString(6));
                bus.setDate(cursor.getString(7));
                bus.setTotalTime(cursor.getString(8));
                bus.setPrice(Integer.parseInt(cursor.getString(9)));
                busList.add(bus);
            } while (cursor.moveToNext());
        }
        return busList;
    }
}
