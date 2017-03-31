package lcam.stockquotes.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import lcam.stockquotes.R;
import lcam.stockquotes.model.Company;
import lcam.stockquotes.model.Quote;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder>{

    private List<Quote> mItems;
    private final Context mContext;

    public ItemsAdapter(Context context, List<Quote> items) {
        mItems = items;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_quote, parent, false);

        final ItemsViewHolder viewHolder = new ItemsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Quote quote = mItems.get(position);
        if(quote==null) {
            Log.d("Info", "quote shouldn't be null at this point, but it is ):");
            return;
        }
        TextView compName = holder.companyName;
        compName.setText(quote.getName());
        TextView compSymbol = holder.companySymbol;
        compSymbol.setText(quote.getSymbol());
        TextView compLastPrice = holder.companyLastPrice;
        compLastPrice.setText(String.format (Locale.ENGLISH,"%f", quote.getLastPrice()));
        TextView compTimeStamp = holder.companyTimestamp;
        compTimeStamp.setText(quote.getTimestamp());
        TextView compHigh = holder.companyHigh;
        compHigh.setText(String.format(Locale.ENGLISH,"%f",quote.getHigh()));
        TextView compLow = holder.companyLow;
        compLow.setText(String.format(Locale.ENGLISH,"%f",quote.getLow()));
        TextView compOpen = holder.companyOpen;
        compOpen.setText(String.format(Locale.ENGLISH,"%f",quote.getOpen()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
