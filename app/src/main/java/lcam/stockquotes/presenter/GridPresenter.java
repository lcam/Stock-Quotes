package lcam.stockquotes.presenter;

import java.util.List;

import lcam.stockquotes.model.Company;
import lcam.stockquotes.model.Quote;
import lcam.stockquotes.services.ServiceGenerator;
import lcam.stockquotes.view.MainActivity;

public class GridPresenter {

    private MainActivity view;
    private ServiceGenerator service;

    public GridPresenter(MainActivity view, ServiceGenerator service) {
        this.view = view;
        this.service = service;
    }

    public void loadData(String tag) {
        service.loadCompanies(tag);
    }

    public void updateView(List<Quote> quotes) {
        view.updateList(quotes);
    }

    public void updateViewFailed() {
        view.loadFailed();
    }
}
