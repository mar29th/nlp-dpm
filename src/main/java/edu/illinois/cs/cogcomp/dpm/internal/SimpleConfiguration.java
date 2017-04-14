package edu.illinois.cs.cogcomp.dpm.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class SimpleConfiguration implements Configuration {

    private DPMYAMLBean bean = null;

    private class DPMYAMLBean {
        public String repository;
    }

    static SimpleConfiguration fromFile(File inFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        DPMYAMLBean bean = mapper.readValue(inFile, DPMYAMLBean.class);
        return new SimpleConfiguration(bean);
    }

    SimpleConfiguration() {
        this.bean = new DPMYAMLBean();
    }

    SimpleConfiguration(DPMYAMLBean bean) {
        this.bean = bean;
    }

    public String getRepositoryAddress() {
        return bean.repository;
    }

    public void setRepositoryAddress(String value) {
        bean.repository = value;
    }
}
