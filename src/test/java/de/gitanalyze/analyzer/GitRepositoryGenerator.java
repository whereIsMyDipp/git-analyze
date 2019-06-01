package de.gitanalyze.analyzer;

import de.gitanalyze.githelper.RepositoryHelper;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GitRepositoryGenerator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GitRepositoryGenerator.class);

    public Git git;
    public Repository repository;


    @Before
    public void setup() throws Exception
    {
        repository = RepositoryHelper.createRepository();
        git = new Git(repository);

    }


    public Path createFileAndAddAndCommit(String fileName)
    {
        Path pathToFile = null;
        try
        {
            pathToFile = Files.createFile(Paths.get(repository.getDirectory().getParent()).resolve(fileName));
            git.add().addFilepattern(fileName).call();
            git.commit().setMessage("Added" + fileName).call();
        }
        catch (Exception e)
        {
            LOGGER.debug("Cant Write File:", e);
        }

        return pathToFile;
    }


    public RevCommit commitFile(String name, String content) throws IOException, GitAPIException
    {
        writeFile(name, content);
        git.add().addFilepattern(name).call();
        return git.commit().setMessage("commit message").call();
    }


    public File writeFile(String name, String content) throws IOException
    {
        File file = new File(git.getRepository().getWorkTree(), name);
        try (FileOutputStream outputStream = new FileOutputStream(file))
        {
            outputStream.write(content.getBytes(UTF_8));
        }
        return file;
    }
}
