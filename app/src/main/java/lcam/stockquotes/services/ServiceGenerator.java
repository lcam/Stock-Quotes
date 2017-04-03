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

    private List<Quote> res;
    private int listSize;

    public ServiceGenerator(MainActivity mainActivity) {
        presenter = new GridPresenter(mainActivity, this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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
                Log.d("Info", "about to load quotes");
                loadQuotes(companies, input);
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void loadQuotes(List<Company> companies, String input){
        if(companies==null){
            Log.d("Info", "company list is null, let's try again");
            loadCompanies(input);
            return;
        }
        res=new ArrayList<>(); //new request, reset res list
        listSize=companies.size(); //new request, reset list size
        for(Company company:companies){
            Call<Quote> call = stockClient.findQuote(company.getSymbol());
            // asynchronous call to API
            call.enqueue(new Callback<Quote>() {
                @Override
                public void onResponse(Call<Quote> call, Response<Quote> response) {
                    Quote quote = response.body();
                    if(quote==null){
                        Log.d("Info", "quote is null, let's try again");
                        callQuoteAPI(company.getSymbol(), res);
                        return;
                    }
                    res.add(quote);
                    Log.d("Info", "onResponse, listSize = " + res.size());
                    if(res.size()==listSize) {
                        Log.d("Info", "done building res list");
                        presenter.updateView(res);
                    }
                }

                @Override
                public void onFailure(Call<Quote> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    presenter.updateViewFailed();
                    Log.d("Info", "onFailure");
                }
            });
        }
    }

    private void callQuoteAPI(String symbol, List<Quote> res){
        Call<Quote> call = stockClient.findQuote(symbol);
        // asynchronous call to API
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                Quote quote = response.body();
                if(quote==null){
                    Log.d("Info", "quote is null, let's try again");
                    callQuoteAPI(symbol, res);
                    return;
                }
                res.add(quote);
                Log.d("Info", "onResponse, listSize = " + res.size());
                if(res.size()==listSize) {
                    Log.d("Info", "done building res list");
                    presenter.updateView(res);
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Log.d("Info", "onFailure");
            }
        });
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
