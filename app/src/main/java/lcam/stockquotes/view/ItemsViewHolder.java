package lcam.stockquotes.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lcam.stockquotes.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder{

    public TextView companyName;
    public TextView companySymbol;
    public TextView companyLastPrice;
    public TextView companyTimestamp;
    public TextView companyHigh;
    public TextView companyLow;
    public TextView companyOpen;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        companyName = (TextView) itemView.findViewById(R.id.company_name);
        companySymbol = (TextView) itemView.findViewById(R.id.company_symbol);
        companyLastPrice = (TextView) itemView.findViewById(R.id.company_last_price);
        companyTimestamp = (TextView) itemView.findViewById(R.id.company_timestamp);
        companyHigh = (TextView) itemView.findViewById(R.id.company_high);
        companyLow = (TextView) itemView.findViewById(R.id.company_low);
        companyOpen = (TextView) itemView.findViewById(R.id.company_open);
    }
}
