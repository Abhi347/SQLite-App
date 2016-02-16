package in.noobgames.sqliteapp;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {
    EditText mNameEdit, mManufacturerEdit, mPriceEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_input, container, false);

        mNameEdit = (EditText) parent.findViewById(R.id.name);
        mManufacturerEdit = (EditText) parent.findViewById(R.id.manufacturer);
        mPriceEdit = (EditText) parent.findViewById(R.id.price);

        return parent;
    }

    public void onInputSubmitClick() {
        String name = mNameEdit.getText().toString();
        String manufacturer = mManufacturerEdit.getText().toString();
        String price = mPriceEdit.getText().toString();
        SQLiteDatabase db = ((MainActivity) getActivity()).getDBHelper().getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_NAME, name);
        contentValues.put(DBHelper.COLUMN_MANUFACTURER, manufacturer);
        contentValues.put(DBHelper.COLUMN_PRICE, price);

        long result = db.insert(DBHelper.TABLE_NAME, null, contentValues);

        if (result == 1) {
            Log.d("InputFragment", "Insert Successful");
        } else {
            Log.d("InputFragment", "Insert Failed");
        }
    }
}
