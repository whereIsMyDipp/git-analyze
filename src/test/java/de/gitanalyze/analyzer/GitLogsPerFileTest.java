package de.gitanalyze.analyzer;

import com.google.common.collect.Iterables;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GitLogsPerFileTest extends GitRepositoryGenerator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GitFileCreated.class);


    @Test
    public void collectResults() throws Exception
    {
        Path pathToFile = createFileAndAddAndCommit("testfile");

        Files.write(pathToFile, new String("writing").getBytes());
        git.add().addFilepattern(".").call();
        git.commit().setMessage("Added multiple files").call();

        GitLogsPerFile gitLogsPerFile = new GitLogsPerFile();
        Map<String, Iterable<RevCommit>> results = gitLogsPerFile.collectResults(git);
        assertThat(Iterables.size(results.get("testfile")), is(2));

        Files.write(pathToFile, new String("writing123").getBytes());
        git.add().addFilepattern(".").call();
        git.commit().setMessage("Added multiple files").call();

        gitLogsPerFile = new GitLogsPerFile();
        results = gitLogsPerFile.collectResults(git);

        assertThat(Iterables.size(results.get("testfile")), is(3));

    }

}
