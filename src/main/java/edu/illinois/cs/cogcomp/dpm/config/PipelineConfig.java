package edu.illinois.cs.cogcomp.dpm.config;

import java.util.List;

import edu.illinois.cs.cogcomp.dpm.config.bean.RepositoryBean;
import edu.illinois.cs.cogcomp.dpm.config.bean.ViewBean;

public interface PipelineConfig {
    String getProjectDirectoryPath();

    List<RepositoryBean> getRepositories();

    List<ViewBean> getViews();
}
