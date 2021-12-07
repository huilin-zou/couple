package com.codepath.couple.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepath.couple.FilterActivity;
import com.codepath.couple.Post;
import com.codepath.couple.PostsAdapter;
import com.codepath.couple.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    public static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;
    private SwipeRefreshLayout swipeContainer;
    private Button btnFilter;
    public String createdAt;
    ActivityResultLauncher<Intent> getFilter;
    public String gender = "everyone";
    public int min_age=18;
    public int max_age=100;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFilter = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (Activity.RESULT_OK == result.getResultCode()) {
                            Intent data = result.getData();

                            gender = data.getStringExtra("gender");
                            if(gender == null)
                                gender = "everyone";
                            min_age=data.getIntExtra("min_age", 18);
                            max_age=data.getIntExtra("max_age", 100);
                            queryPost();
                        }
                    }
                }
        );
        String value = getActivity().getIntent().getStringExtra("value");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_posts, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);


        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);


        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryPost();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);


        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPost();

        btnFilter = view.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {

                getFilter.launch(new Intent(getActivity(), FilterActivity.class));

            }
        });

    }
    protected void queryPost() {

        ParseUser user = ParseUser.getCurrentUser();

        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        /*
        try {
            user.fetch();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int y = user.getInt("Age");*/
        query.include(Post.KEY_USER);
      //  query.include("user.Age");
        query.setLimit(20);
        if (!(gender.equals("everyone")))
            query.whereEqualTo("gender", gender);


        query.whereLessThanOrEqualTo("age",max_age);
        query.whereGreaterThanOrEqualTo("age",min_age);

        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);

                    return;
                }
                swipeContainer.setRefreshing(false);
                for (Post post : posts) {
                   // if(post.getUser().getInt("Age") >= min_age)
                   // allPosts.add(post);
                    Log.i(TAG, "Post: " + post.getDescription() + " , username: " + post.getUser().getUsername());
                }
                allPosts.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();

            }
        });
    }

}