package de.gitanalyze.githelper;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class RepositoryHelper
{

    public static Repository openRepository() throws IOException
    {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
            .readEnvironment() // scan environment GIT_* variables
            .findGitDir() // scan up the file system tree
            .build();
    }


    public static Repository createRepository() throws IOException
    {
        // prepare a new folder
        File localPath = File.createTempFile("TestGitRepository", "");
        if (!localPath.delete())
        {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();

        return repository;
    }


    public static Repository cloneRepository(String remote, File localPath) throws GitAPIException
    {
        System.out.println("Cloning from " + remote + " to " + localPath);
        Git result = Git.cloneRepository()
            .setURI(remote)
            .setDirectory(localPath)
            .call();
        return result.getRepository();
    }


    public static File createLocalPath() throws IOException
    {
        File localPath = File.createTempFile("TestGitRepository", "");
        if (!localPath.delete())
        {
            throw new IOException("Could not delete temporary file " + localPath);
        }
        return localPath;
    }
}
