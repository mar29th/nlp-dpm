package edu.illinois.cs.cogcomp.dpm.config;

import java.util.List;

import edu.illinois.cs.cogcomp.dpm.config.bean.RepositoryBean;
import edu.illinois.cs.cogcomp.dpm.config.bean.ViewBean;

/**
 * Provides pipeline-specific configuration parameters. Should be implemented (populated) by subclasses.
 *
 * Pipeline configuration provides parameters for a specific sequence of annotator execution (e.g. views to be added).
 */
public interface PipelineConfig {

    /**
     * Provide absolute path of a project's root directory (defined as the location of the configuration file).
     *
     * @return Absolute path of a projects's root directory (definition see above).
     */
    String getProjectDirectoryPath();

    /**
     * Provide list of repositories that downloader should search from.
     *
     * Similar to POM's {@code <dependency/>} When dependencies graph is resolved and downloader is about to download JARs,
     * it consults this list as possible maven repositories.
     *
     * @return List of {@link RepositoryBean} that downloader uses as Maven source.
     */
    List<RepositoryBean> getRepositories();

    /**
     * Give a list of views that the user wishes to be added to a run.
     *
     * @return List of {@link ViewBean} that represent views added and executed.
     */
    List<ViewBean> getViews();

    /**
     * Provide absolute location of input corpus file.
     *
     * @return Absolute location of input corpus file.
     */
    String getCorpusPath();

    /**
     * Provide absolute path of annotation serialization.
     *
     * @return Absolute path of annotation serialization.
     */
    String getOutputPath();
}
