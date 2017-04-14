package edu.illinois.cs.cogcomp.dpm.config.bean;

import java.util.ArrayList;
import java.util.List;

public class PipelineConfigBean {
    private List<RepositoryBean> repos = new ArrayList<>();
    private List<ViewBean> views = new ArrayList<>();

    public List<RepositoryBean> getRepos() {
        return repos;
    }

    public void setRepos(List<RepositoryBean> repos) {
        this.repos = repos;
    }

    public List<ViewBean> getViews() {
        return views;
    }

    public void setViews(List<ViewBean> views) {
        this.views = views;
    }
}
