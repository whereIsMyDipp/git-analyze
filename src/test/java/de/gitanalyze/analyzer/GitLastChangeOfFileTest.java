package de.gitanalyze.analyzer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class GitLastChangeOfFileTest extends GitRepositoryGenerator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GitFileCreated.class);


    @Test
    public void collectResults() throws Exception
    {

        Path pathToFile = Files.createFile(Paths.get(repository.getDirectory().getParent()).resolve("testfile"));

        git.add().addFilepattern("testfile").call();
        git.commit().setMessage("Added testfile").call();
        String str = "writing";
        Thread.sleep(3 * 1000);
        Instant instant = Instant.now();
        Files.write(pathToFile, str.getBytes());
        git.add().addFilepattern("testfile").call();
        git.commit().setMessage("Added testfile").call();

        GitLastChangeOfFile gitLastChangeOfFile = new GitLastChangeOfFile();
        Map<String, Integer> results = gitLastChangeOfFile.collectResults(git);

        assertThat(results, is(notNullValue()));
        assertThat(results.size(), is(1));
        assertThat(results.get("testfile"), is(notNullValue()));
        assertThat(
            (int) instant.getEpochSecond(),
            allOf(
                greaterThan(results.get("testfile") - 1),
                lessThan(results.get("testfile") + 1)));
    }
}
