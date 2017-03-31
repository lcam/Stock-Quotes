package lcam.stockquotes.services;

import java.util.List;

import io.reactivex.Observable;
import lcam.stockquotes.model.Company;
import lcam.stockquotes.model.Quote;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockClient {

    // http://dev.markitondemand.com/MODApis/Api/v2/
    // Lookup/json?input=NFLX
    @GET("Lookup/json")
    Observable<List<Company>> findCompanies(
            @Query("input") String input
    );

    // http://dev.markitondemand.com/MODApis/Api/v2/
    // Quote/json?symbol=AAPL
    @GET("Quote/json")
    Observable<List<Quote>> findQuote(
            @Query("symbol") String symbol
    );
}
