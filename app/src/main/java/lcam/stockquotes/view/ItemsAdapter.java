package lcam.stockquotes.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_quote, parent, false);

        // Return a new holder instance
        final ItemsViewHolder viewHolder = new ItemsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Quote quote = mItems.get(position);
        TextView compSymbol = holder.companySymbol;
        compSymbol.setText(quote.getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
