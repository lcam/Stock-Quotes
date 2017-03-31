package lcam.stockquotes.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import lcam.stockquotes.R;
import lcam.stockquotes.model.Company;
import lcam.stockquotes.model.Quote;
import lcam.stockquotes.presenter.GridPresenter;
import lcam.stockquotes.services.ServiceGenerator;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{

    private RecyclerView rvItems;
    private GridLayoutManager layoutManager;
    private ItemsAdapter adapter;
    int numColumn = 1;

    private EditText mEditText;
    private String tagInput = "";
    private GridPresenter mGridPresenter;
    private ServiceGenerator mNetworkService;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditText = (EditText) this.findViewById(R.id.etTag);
        mEditText.setOnEditorActionListener(this);

        //suppress the soft-keyboard until the user actually touches the editText View
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        mNetworkService = new ServiceGenerator(this);
        mGridPresenter = new GridPresenter(this, mNetworkService);

        rvItems = (RecyclerView)findViewById(R.id.rvImages);
        getData(" ");

        layoutManager = new GridLayoutManager(this, numColumn);
        rvItems.setLayoutManager(layoutManager);

        // Swipe down to refresh
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            tagInput = mEditText.getText().toString();
            getData(tagInput);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miSearch:
                tagInput = mEditText.getText().toString();
                getData(tagInput);

                //hide keyboard
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getData(String tag) {
        mGridPresenter.loadData(tag);
    }

    public void updateList(List<Quote> quotes) {
        adapter = new ItemsAdapter(getApplicationContext(), quotes);
        rvItems.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false); //suppress loading spinner after refresh
    }

    public void findFailed() {
        Snackbar.make(rvItems, "!!!!!!!Symbols could not be loaded", Snackbar.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false); //suppress loading spinner after refresh
    }

    public void loadFailed() {
        //Snackbar.make(rvItems, "Stock quotes could not be loaded", Snackbar.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false); //suppress loading spinner after refresh
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            tagInput = mEditText.getText().toString();
            getData(tagInput);

            //hide keyboard
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            return true;
        }
        return false;
    }
}
