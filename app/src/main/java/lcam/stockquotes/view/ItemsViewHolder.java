package lcam.stockquotes.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lcam.stockquotes.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder{
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public TextView companySymbol;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ItemsViewHolder(View itemView) { //NOTICE: NO LONGER PASSING IMyViewHolderClicks INTERFACE AS A PARAMETER
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);
        companySymbol = (TextView) itemView.findViewById(R.id.company_symbol);
    }
}
