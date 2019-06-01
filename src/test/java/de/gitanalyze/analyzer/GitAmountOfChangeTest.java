package de.gitanalyze.analyzer;

import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GitAmountOfChangeTest extends GitRepositoryGenerator
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GitAmountOfChangeTest.class);


    @Test
    public void collectResults() throws Exception
    {
        Instant instant = Instant.now();
        writeFile("filea.txt", "first line    \n      23  \n \n eee\n eee ");
        writeFile("fileb.txt", "first line    \n      23  \n \n eee\n eee ");
        git.add().addFilepattern(".").call();
        RevCommit newCommit = git.commit().setMessage("commit message").call();

        writeFile("filea.txt", "first line211 \n      23  \n    eee\n eee \n 44445555");
        writeFile("fileb.txt", "first line \n      23  \n    eee\n eee \n 44445555");
        git.add().addFilepattern(".").call();
        RevCommit newCommit2 = git.commit().setMessage("commit message").call();

        GitAmountOfChange gitAmountOfChange = new GitAmountOfChange();

        Map<String, Integer> results = gitAmountOfChange.collectResults(git, "master");
        assertThat(results.size(), is(2));
        assertThat(results.get("filea.txt"), is(3));
        assertThat(results.get("fileb.txt"), is(2));

    }
}
