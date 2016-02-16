package in.noobgames.sqliteapp;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
    ListView mListView;
    ProductAdapter mAdapter;

    ArrayList<Product> mProducts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_product_list, container, false);

        mListView = (ListView) parent.findViewById(R.id.product_list);
        mAdapter = new ProductAdapter(getActivity(), R.layout.item_product);

        mListView.setAdapter(mAdapter);

        return parent;
    }

    @Override
    public void onResume() {
        super.onResume();
        readData();
    }

    void readData() {
        if (mAdapter == null)
            return;
        mProducts.clear();
        SQLiteDatabase db = ((MainActivity) getActivity()).getDBHelper().getReadableDatabase();

        String[] projection = {
                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_NAME,
                DBHelper.COLUMN_MANUFACTURER,
                DBHelper.COLUMN_PRICE
        };
        //String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy
        Cursor cursor = db.query(DBHelper.TABLE_NAME, projection, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            String manufacturer = cursor.getString(2);
            String price = cursor.getString(3);

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setManufacturer(manufacturer);
            product.setPrice(price);
            mProducts.add(product);
            cursor.moveToNext();
        }

        mAdapter.notifyDataSetChanged();
    }

    class ProductAdapter extends ArrayAdapter<View> {
        int mResource;

        public ProductAdapter(Context context, int resource) {
            super(context, resource);
            mResource = resource;
        }

        @Override
        public int getCount() {
            return mProducts.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(mResource, null);
            }

            TextView idText = (TextView) convertView.findViewById(R.id.id);
            TextView nameText = (TextView) convertView.findViewById(R.id.name);
            TextView manufacturerText = (TextView) convertView.findViewById(R.id.manufacturer);
            TextView priceText = (TextView) convertView.findViewById(R.id.price);

            Product product = mProducts.get(position);

            idText.setText("" + product.getId());
            nameText.setText(product.getName());
            manufacturerText.setText(product.getManufacturer());
            priceText.setText(product.getPrice());

            return convertView;
        }
    }

}
