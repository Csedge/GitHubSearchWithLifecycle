package com.example.android.githubsearchwithlifecycle.data;

import android.util.Log;

import com.example.android.githubsearchwithlifecycle.utils.GitHubUtils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GitHubSearchRepository {
    private static final String TAG = GitHubSearchRepository.class.getSimpleName();
    private MutableLiveData<List<GitHubRepo>> mSearchResults;

    public GitHubSearchRepository() {
        mSearchResults = new MutableLiveData<>();
        mSearchResults.setValue(null);
    }

    public LiveData<List<GitHubRepo>> getSearchResults() {
        return mSearchResults;
    }

    public void loadSearchResults(String query) {
        String url = GitHubUtils.buildGitHubSearchURL(query);
        mSearchResults.setValue(null);
        Log.d(TAG, "executing search with url: " + url);
        // Execute HTTP query...
    }
}
