package it.alessandraferrua.recyclerviewheaderfooter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alessandraferrua on 15/06/15.
 */
public class RecyclerAdapterHeaderFooter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerAdapterHeaderFooter";
    public static Context mContext;
    private final List list;


    public RecyclerAdapterHeaderFooter(Context context, List list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPES.Header) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false);
            return new ViewHolderHeader(v);
        } else if (viewType == VIEW_TYPES.Footer) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer, viewGroup, false);
            return new ViewHolderFooter(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.normal, viewGroup, false);
            return new ViewHolderNormal(v);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolderNormal) {
            TextView textView = (TextView) ((ViewHolderNormal) viewHolder).layout.findViewById(R.id.text_item);
            textView.setText(list.get(position).toString());
        }

    }

    @Override
    public int getItemCount() {
        int count = list.size();
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == VIEW_TYPES.Header)
            return VIEW_TYPES.Header;
        if (position == getItemCount()-1)
            return VIEW_TYPES.Footer;
        return VIEW_TYPES.Normal;
    }

    public static class ViewHolderNormal extends RecyclerView.ViewHolder {
        public LinearLayout layout;

        public ViewHolderNormal(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.normal);
        }
    }

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {
        public LinearLayout layout;

        public ViewHolderHeader(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.header);
        }
    }

    public static class ViewHolderFooter extends RecyclerView.ViewHolder {
        public LinearLayout layout;

        public ViewHolderFooter(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.footer);
        }
    }

    private class VIEW_TYPES {
        public static final int Header = 0;
        public static final int Normal = 1;
        public static final int Footer = 2;

    }
}

