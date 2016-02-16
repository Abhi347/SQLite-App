package in.noobgames.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ProductListFragment mProductListFragment;
    InputFragment mInputFragment;

    private DBHelper mDBHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProductListFragment = new ProductListFragment();
        mInputFragment = new InputFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frag_container, mProductListFragment)
                .commit();
    }

    public void onAddProductClick(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, mInputFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onInputCancelClick(View view) {
        //mInputFragment.onInputCancelClick();
        onBackPressed();
    }

    public void onInputSubmitClick(View view) {
        mInputFragment.onInputSubmitClick();
        onBackPressed();
    }

    public DBHelper getDBHelper() {
        return mDBHelper;
    }
}
