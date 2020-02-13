package com.example.android.githubsearchwithlifecycle;

import com.example.android.githubsearchwithlifecycle.data.GitHubRepo;
import com.example.android.githubsearchwithlifecycle.data.GitHubSearchRepository;
import com.example.android.githubsearchwithlifecycle.data.Status;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GitHubSearchViewModel extends ViewModel {
    private GitHubSearchRepository mRepository;
    private LiveData<List<GitHubRepo>> mSearchResults;
    private LiveData<Status> mLoadingStatus;

    public GitHubSearchViewModel() {
        mRepository = new GitHubSearchRepository();
        mSearchResults = mRepository.getSearchResults();
        mLoadingStatus = mRepository.getLoadingStatus();
    }

    public void loadSearchResults(String query) {
        mRepository.loadSearchResults(query);
    }

    public LiveData<List<GitHubRepo>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }
}
