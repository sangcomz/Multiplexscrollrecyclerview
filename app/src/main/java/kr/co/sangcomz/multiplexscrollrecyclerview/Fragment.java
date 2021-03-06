package kr.co.sangcomz.multiplexscrollrecyclerview;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class Fragment extends android.support.v4.app.Fragment {
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rv = inflater.inflate(
                R.layout.fragment, container, false);
        recyclerView = (RecyclerView) rv.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        setupRecyclerView(recyclerView);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) recyclerView.getParent().getParent();
                coordinatorLayout.onStartNestedScroll(recyclerView, recyclerView, 2);
            }
        });
        return rv;
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public static class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private Context context;
        private RecyclerViewAdapterHo recyclerViewAdapterHo;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final RecyclerView recyclerView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                recyclerView = (RecyclerView) view.findViewById(R.id.images);
            }
        }
        public RecyclerViewAdapter(Context context) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_vertical, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
            linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(linearLayoutManager2);
            recyclerViewAdapterHo = new RecyclerViewAdapterHo(context);
            holder.recyclerView.setAdapter(recyclerViewAdapterHo);
        }


        @Override
        public int getItemCount() {
            return 10;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerView.removeAllViews();
    }


    public static class RecyclerViewAdapterHo
            extends RecyclerView.Adapter<RecyclerViewAdapterHo.ViewHolder> {


        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final ImageView mImageView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.cheese_image);
            }
        }

        public RecyclerViewAdapterHo(Context context) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_horizon, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            Glide.with(holder.mImageView.getContext())
                    .load(getAnimalImage())
                    .fitCenter()
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }

    public static int getAnimalImage() {
        final Random RANDOM = new Random();
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.mipmap.image_1;
            case 1:
                return R.mipmap.image_2;
            case 2:
                return R.mipmap.image_3;
            case 3:
                return R.mipmap.image_4;
            case 4:
                return R.mipmap.image_5;
        }
    }
}
