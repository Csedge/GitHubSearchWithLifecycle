package com.example.android.githubsearchwithlifecycle.data;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.githubsearchwithlifecycle.utils.GitHubUtils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GitHubSearchRepository implements GitHubSearchAsyncTask.Callback {
    private static final String TAG = GitHubSearchRepository.class.getSimpleName();
    private MutableLiveData<List<GitHubRepo>> mSearchResults;
    private MutableLiveData<Status> mLoadingStatus;
    private String mCurrentQuery;

    public GitHubSearchRepository() {
        mSearchResults = new MutableLiveData<>();
        mSearchResults.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);

        mCurrentQuery = null;
    }

    public LiveData<List<GitHubRepo>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    @Override
    public void onSearchFinished(List<GitHubRepo> searchResults) {
        mSearchResults.setValue(searchResults);
        if (searchResults != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }

    private boolean shouldExecuteSearch(String query) {
        return !TextUtils.equals(query, mCurrentQuery);
    }

    public void loadSearchResults(String query) {
        if (shouldExecuteSearch(query)) {
            mCurrentQuery = query;
            String url = GitHubUtils.buildGitHubSearchURL(query);
            mSearchResults.setValue(null);
            Log.d(TAG, "executing search with url: " + url);
            mLoadingStatus.setValue(Status.LOADING);
            new GitHubSearchAsyncTask(this).execute(url);
        } else {
            Log.d(TAG, "using cached search results");
        }
    }
}
