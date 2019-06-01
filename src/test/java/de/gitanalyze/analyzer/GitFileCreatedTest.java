package de.gitanalyze.analyzer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class GitFileCreatedTest extends GitRepositoryGenerator
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GitFileCreated.class);

    @Test
    public void collectResults() throws Exception
    {
        Instant instant = Instant.now();
        writeFile("testfile", "first line    \n      23  \n \n eee\n eee ");
        git.add().addFilepattern("testfile").call();
        git.commit().setMessage("Added testfile").call();

        GitFileCreated gitFileCreated = new GitFileCreated();
        Map<String, Integer> results = gitFileCreated.collectResults(git);

        assertThat(results, is(notNullValue()));
        assertThat(results.size(), is(1));
        assertThat(results.get("testfile"), is(notNullValue()));
        assertThat((int) instant.getEpochSecond(), allOf(greaterThan(results.get("testfile") - 1), lessThan(results.get("testfile") + 1)));
    }


    @Test
    public void collectResultsWithEdit() throws Exception
    {
        Instant instant = Instant.now();
        Files.createFile(Paths.get(repository.getDirectory().getParent()).resolve("testfile"));
        Files.createFile(Paths.get(repository.getDirectory().getParent()).resolve("testfile2"));
        git.add().addFilepattern(".").call();
        git.commit().setMessage("Added multiple files").call();

        GitFileCreated gitFileCreated = new GitFileCreated();
        Map<String, Integer> results = gitFileCreated.collectResults(git);

        assertThat(results, is(notNullValue()));
        assertThat(results.size(), is(2));
        assertThat(results.get("testfile2"), is(notNullValue()));
        assertThat((int) instant.getEpochSecond(), allOf(greaterThan(results.get("testfile2") - 1), lessThan(results.get("testfile2") + 1)));
    }
}
