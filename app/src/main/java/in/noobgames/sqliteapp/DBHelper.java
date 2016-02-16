package in.noobgames.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abhishek on 15-02-2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "product.db";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MANUFACTURER = "manufacturer";
    public static final String COLUMN_PRICE = "price";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
            + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
            COLUMN_MANUFACTURER + TEXT_TYPE + COMMA_SEP +
            COLUMN_PRICE + TEXT_TYPE +
            " );";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DBHelper", "on Create: " + CREATE_TABLE_QUERY);
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }
}
