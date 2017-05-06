package edu.illinois.cs.cogcomp.dpm.config.bean;

import java.util.ArrayList;
import java.util.List;

public class PipelineConfigBean {
    private List<RepositoryBean> repos = new ArrayList<>();
    private List<ViewBean> views = new ArrayList<>();

    private String corpusPath;
    private String outputPath;

    public PipelineConfigBean() {
    }

    public PipelineConfigBean(List<RepositoryBean> repos, List<ViewBean> views) {
        this.repos = repos;
        this.views = views;
    }

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

    public String getCorpusPath() {
        return corpusPath;
    }

    public void setCorpusPath(String corpusPath) {
        this.corpusPath = corpusPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
