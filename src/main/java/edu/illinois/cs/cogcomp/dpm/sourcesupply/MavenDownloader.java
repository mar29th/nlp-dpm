package edu.illinois.cs.cogcomp.dpm.sourcesupply;

import com.tobedevoured.naether.NaetherException;
import com.tobedevoured.naether.api.Naether;
import com.tobedevoured.naether.impl.NaetherImpl;

import edu.illinois.cs.cogcomp.dpm.listener.OnDownloaderStatusUpdateListener;
import edu.illinois.cs.cogcomp.dpm.listener.StatusUpdateEvent;
import org.sonatype.aether.graph.Dependency;
import org.sonatype.aether.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import edu.illinois.cs.cogcomp.dpm.config.GlobalConfig;
import edu.illinois.cs.cogcomp.dpm.config.PipelineConfig;
import edu.illinois.cs.cogcomp.dpm.config.bean.RepositoryBean;

public class MavenDownloader implements Downloader {

    private GlobalConfig globalConfig = null;
    private PipelineConfig pipelineConfig = null;
    private OnDownloaderStatusUpdateListener listener = null;

    @Inject
    public MavenDownloader(GlobalConfig globalConfig, PipelineConfig pipelineConfig) {
        this.globalConfig = globalConfig;
        this.pipelineConfig = pipelineConfig;
    }

    public void download(List<Dependency> dependencyList, String destDir) throws NaetherException {
        Naether aether = new NaetherImpl();
        listener.onUpdate(new StatusUpdateEvent(StatusUpdateEvent.Type.DOWNLOADER_STARTED));

        Set<RemoteRepository> remoteRepos = new HashSet<>();
        List<RepositoryBean> pipelineRepos = pipelineConfig.getRepositories();
        for (RepositoryBean pipelineRepo : pipelineRepos) {
            remoteRepos.add(new RemoteRepository(
                pipelineRepo.getName(),
                "default", pipelineRepo.getLocation()));
        }
        aether.setRemoteRepositories(remoteRepos);

        for (Dependency dependency : dependencyList) {
            aether.addDependency(dependency);
        }
        aether.setLocalRepoPath(globalConfig.getMavenRepoPath());
        aether.resolveDependencies(); // Block on current thread and is time consuming
        List<String> resolvedDependencies = new ArrayList<>(aether.getDependenciesNotation());

        StatusUpdateEvent event = new StatusUpdateEvent(StatusUpdateEvent.Type.DOWNLOADER_UPDATE);
        event.putValue("dependencies", resolvedDependencies);
        listener.onUpdate(event);

        aether.downloadArtifacts(resolvedDependencies);

        event = new StatusUpdateEvent(StatusUpdateEvent.Type.DOWNLOADER_COMPLETED);
        listener.onUpdate(event);
    }

    @Override
    public void setOnDownloaderStatusUpdateListener(OnDownloaderStatusUpdateListener listener) {
        this.listener = listener;
    }

    @Override
    public OnDownloaderStatusUpdateListener getOnDownloaderStatusUpdateListener() {
        return listener;
    }
}
