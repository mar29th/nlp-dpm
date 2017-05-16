# Illinois NLP Manager

This application reads configuration file and execute a pipeline
according to parameters specified. It consists of the following modules:

* [Application](#application-module)
* [Configuration](#configuration-module)
* [Source Supply](#source-supply-module)
* [Dynamic module loading](#dynamic-module-loading)

Also, for future developments, please see
[comments](#suggestion-for-future-developments).

## Getting Started
To run in terminal:
```bash
java -jar {file}.jar {configFilePath}.json
```
where `file` is filename of compiled application JAR, and `configFilePath` is
absolute path of pipeline config path (see below).

### Configuration Schema
The configuration file must contain a valid JSON string. Complete schema is:
```json
{
  "repos": [
    {
      "name": "Any name for this repo. Must be unique.",
      "location": "https://mavencentral"
    },
    {
      "name": "MavenCentral",
      "location": "http://repo1.maven.org/maven2"
    }
  ],
  "views": [
    {
      "groupId": "Same as groupId in POM <dependency>",
      "artifactId": "Same as artifactId in POM <dependency>",
      "version": "Same as version in POM <dependency>",
      "entrypoint": "Full classpath for dynamic module loading entrypoint"
    },
    {
      "groupId": "edu.illinois.edu.cogcomp",
      "artifactId": "doesnt-matter-yet",
      "version": "3.1.11",
      "entrypoint": "edu.illinois.cs.cogcomp.dpm.test.MockPackageInterface"
    }
  ]
}
```

### Preparing for Dynamic Module Loading
Each module (i.e. annotator packages) should contain an "entrypoint class",
which handles annotator preparation for the dynamic module loader. The loader
simply get the annotator and inject into execution.

An entrypoint class can be of any name, without implementing any interface. It
__MUST__ provide a no-args method `getAnnotator()` which returns an `Annotator`.

Example:
```java
public class DemoEntrypoint {
    public getAnnotator() {
        return new DoesNotDoAnythingAnnotator();
    }
}
```

## Main Application Module
The main application reads parameters directly from config files, and on each
`run()` call, execute the pipeline for exactly once. Since currently the
project is more of a demo, `Application` serves as more of a top-level class
that interfaces directly with command line.

### Suggestion For Future Developments
Since running JARs require JVM and terminal, it might be better to provide a bash
script that can hide this step for inexperienced users. To go further,
it may be good to have a GUI, so users can operate directly
and won't even touch the command line at all. To do this, one may want to
change method interface in `Application` to return a `TextAnnotation`,
since it currently only save result to file and doesn't return anything.


## Configuration Module
This module contains POJOs (beans) of configuration format, and config provider
module interfaces.

### Global Config
Mostly same role as `~/.bashrc` and `./.vimrc`, except that currently it does not
actually read the config from any file. Values in this config is always reused
across runs. The default configurations are hardcoded, such as the
download cache directory for the application.

### Pipeline Config
The config is run-specific, which specifies the parameters of any specific run.
Config for one run is irrelevant to next run. This config provides repositories
link or needed views of this particular run. It can be read from JSON files as
shown [above](#getting-started).


## Source Supply Module
This module resolves dependencies specified in a `PipelineConfig`, and
download all packages together with their prerequisites.

### Default (Maven) Implementation
The default downloader searches from Maven repos. The work of this implementation
is very similar to dependency downloading during a normal `mvn build`. This
implementation relies on [Naether](https://github.com/mguymon/naether),
a slightly wrapped version of
[Aether](https://github.com/sonatype/sonatype-aether),
to resolve dependency graph and download.


## Dynamic Module Loading
Java has dynamic module loading but is not as easy to use as Python's.
Due to static-type nature, Java requires reflection to get a method
from a black-box (dynamic module), which means we need a defined set of
interfaces that an external module should conform. For interface definition,
please see [above](#preparing-for-dynamic-module-loading).
