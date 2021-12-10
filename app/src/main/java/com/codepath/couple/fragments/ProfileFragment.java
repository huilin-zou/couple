package com.codepath.couple.fragments;

import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.couple.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    SwipeRefreshLayout swipeContainer;

    @Override
    protected void queryPost() {


        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Like");

        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {

                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + " , username: " + post.getUser().getUsername());
                }

                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();

            }
        });
    }
}
