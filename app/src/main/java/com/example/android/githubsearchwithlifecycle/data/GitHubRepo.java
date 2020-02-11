package com.example.android.githubsearchwithlifecycle.data;

import java.io.Serializable;

public class GitHubRepo implements Serializable {
    public String full_name;
    public String html_url;
    public String description;
    public int stargazers_count;
}
