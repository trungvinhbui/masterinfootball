package com.trungvinh.masterinfootball;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class BangTinFragment extends Fragment {

    private static final String TAG = BangTinFragment.class.getSimpleName();
    private static final String URL = "https://raw.githubusercontent.com/trungvinhbui/DatabaseMasterinfootball/master/BangTin1.json";

    private RecyclerView recyclerView;
    private List<MauTin> MauTinList;
    private NewsAdapter mAdapter;

    public BangTinFragment() {
        // Required empty public constructor
    }

    public static BangTinFragment newInstance(String param1, String param2) {
        BangTinFragment fragment = new BangTinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bang_tin, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        MauTinList = new ArrayList<>();
        mAdapter = new NewsAdapter(getActivity(), MauTinList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchStoreItems();

        return view;
    }

    private void fetchStoreItems() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<MauTin> items = new Gson().fromJson(response.toString(), new TypeToken<List<MauTin>>() {
                        }.getType());

                        MauTinList.clear();
                        MauTinList.addAll(items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleyApp.getInstance().addToRequestQueue(request);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
        private Context context;
        private List<MauTin> MauTinList;

        public NewsAdapter(Context context, List<MauTin> MauTinList) {
            this.context = context;
            this.MauTinList = MauTinList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bangtin_item_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final MauTin MauTin = MauTinList.get(position);
            holder.name.setText(MauTin.getTitle());
            holder.info.setText(MauTin.getinfo());

            Glide.with(context)
                    .load(MauTin.getImage())
                    .into(holder.thumbnail);
        }

        @Override
        public int getItemCount() {
            return MauTinList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, info;
            public ImageView thumbnail;

            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.title);
                info = view.findViewById(R.id.info);
                thumbnail = view.findViewById(R.id.thumbnail);
            }
        }
    }
}
