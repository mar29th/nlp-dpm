package edu.illinois.cs.cogcomp.dpm.internal;

import com.tobedevoured.naether.NaetherException;
import com.tobedevoured.naether.api.Naether;
import com.tobedevoured.naether.impl.NaetherImpl;

import org.sonatype.aether.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class MavenDownloader implements Downloader {

    private Configuration config = null;

    @Inject
    public MavenDownloader(Configuration config) {
        this.config = config;
    }

    public void download(List<String> packageList, String destDir) throws NaetherException {
        Naether aether = new NaetherImpl();

        Set<RemoteRepository> repos = new HashSet<RemoteRepository>();
        // This is hardcoded in mvn, so we do the same.
        repos.add(new RemoteRepository("mvnCentral", "default", "http://repo1.maven.org/maven2/"));
        repos.add(new RemoteRepository("cogcompRepo", "default", config.getRepositoryAddress()));
        aether.setRemoteRepositories(repos);

        for (String packageName : packageList) {
            aether.addDependency(packageName);
        }
        aether.setLocalRepoPath(Constants.REPO_PATH);
        aether.resolveDependencies();
        aether.downloadArtifacts(new ArrayList<String>(aether.getDependenciesNotation()));
    }
}
