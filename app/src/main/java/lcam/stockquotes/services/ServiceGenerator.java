package lcam.stockquotes.services;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lcam.stockquotes.model.Company;
import lcam.stockquotes.model.Quote;
import lcam.stockquotes.presenter.GridPresenter;
import lcam.stockquotes.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String API_BASE_URL = "http://dev.markitondemand.com/MODApis/Api/v2/";
    private StockClient stockClient;
    private GridPresenter presenter;

    public ServiceGenerator(MainActivity mainActivity) {
        presenter = new GridPresenter(mainActivity, this);

        //RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(rxAdapter)
                .build();

        stockClient = retrofit.create(StockClient.class);
    }

    public void loadCompanies(String input) {
        Call<List<Company>> call = stockClient.findCompanies(input);
        // asynchronous call to API
        call.enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                List<Company> companies = response.body();
                loadQuotes(companies);
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.d("Error", t.getMessage());
                presenter.updateViewFailed();
            }
        });
    }

    public void loadQuotes(List<Company> companies){
        List<Quote> res=new ArrayList<>();
        for(Company company:companies){
            Call<Quote> call = stockClient.findQuote(company.getSymbol());
            // asynchronous call to API
            call.enqueue(new Callback<Quote>() {
                @Override
                public void onResponse(Call<Quote> call, Response<Quote> response) {
                    Quote quote = response.body();
                    res.add(quote);
                }

                @Override
                public void onFailure(Call<Quote> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    presenter.updateViewFailed();
                }
            });
        }

        presenter.updateView(res);
    }

//    public void loadCompanies(String input) {
//        Call<List<Company>> call = stockClient.findCompanies(input);
//
//        // asynchronous call to API
//        call.enqueue(new Callback<List<Company>>() {
//            @Override
//            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
//                List<Company> companies = response.body();
//                presenter.updateView(companies);
//            }
//
//            @Override
//            public void onFailure(Call<List<Company>> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//                presenter.updateViewFailed();
//            }
//        });
//    }

}
