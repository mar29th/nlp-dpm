package edu.illinois.cs.cogcomp.dpm.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import edu.illinois.cs.cogcomp.dpm.config.bean.PipelineConfigBean;
import edu.illinois.cs.cogcomp.dpm.config.bean.RepositoryBean;
import edu.illinois.cs.cogcomp.dpm.config.bean.ViewBean;

public class DefaultPipelineConfig implements PipelineConfig {

    private String projectPath = null;
    private PipelineConfigBean bean = null;

    public static DefaultPipelineConfig fromJson(File inFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PipelineConfigBean bean = mapper.readValue(inFile, PipelineConfigBean.class);
        return new DefaultPipelineConfig(inFile.getPath(), bean);
    }

    public DefaultPipelineConfig() {
        this.bean = new PipelineConfigBean();
    }

    public DefaultPipelineConfig(String projectPath, PipelineConfigBean bean) {
        this.projectPath = projectPath;
        this.bean = bean;
    }

    public String getProjectDirectoryPath() {
        return projectPath;
    }

    public List<RepositoryBean> getRepositories() {
        return bean.repos;
    }

    public List<ViewBean> getViews() {
        return bean.views;
    }
}
