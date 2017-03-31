package lcam.stockquotes.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lcam.stockquotes.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder{

    public TextView companySymbol;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        companySymbol = (TextView) itemView.findViewById(R.id.company_symbol);
    }
}
