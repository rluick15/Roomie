package com.richluick.android.roomie.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.richluick.android.roomie.R;
import com.richluick.android.roomie.ui.adapters.ChatListAdapter;
import com.richluick.android.roomie.utils.Constants;

import java.util.List;

public class ChatActivity extends ActionBarActivity {

    private ParseUser mCurrentUser;
    private ListView mListView;
    private ChatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mCurrentUser = ParseUser.getCurrentUser();
        mListView = (ListView) findViewById(R.id.chatList);

        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.RELATION);
        query.whereEqualTo(Constants.USER1, mCurrentUser);
        query.whereEqualTo(Constants.USER2, mCurrentUser);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                mAdapter = new ChatListAdapter(ChatActivity.this, parseObjects);
                mListView.setAdapter(mAdapter);
            }
        });
    }
}
