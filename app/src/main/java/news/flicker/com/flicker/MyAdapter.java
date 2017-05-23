package news.flicker.com.flicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;

/**
 * Created by Gemy on 5/22/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private AQuery aQuery;
    public Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mTextView;
        public ViewHolder(ImageView v) {
            super(v);
            mTextView = v;
        }
    }

    public MyAdapter(String[] myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        ImageView v = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        aQuery = new AQuery(context);
        holder.mTextView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        aQuery.id(holder.mTextView).image(mDataset[position], true, true, 0, R.drawable.ic_picture, null, AQuery.FADE_IN);

    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
