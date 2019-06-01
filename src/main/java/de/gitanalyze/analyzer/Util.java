package de.gitanalyze.analyzer;

import com.opencsv.CSVWriter;

import de.gitanalyze.dto.GitDto;
//import de.gitanalyze.dto.WarningsDto;
//import de.gitanalyze.dto.WarningsDtoIssue;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util
{

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);


    public static List<Path> getFilesInRepository(Git git) throws IOException
    {
        List<Path> files = Files.walk(Paths.get(git.getRepository().getWorkTree().getPath()))
            .filter(path -> Files.isRegularFile(path))
            //.filter(path -> path.toString().contains(".java"))
            .collect(Collectors.toList());
        return files;
    }


    public static String getFileRelative(Git git, Path path) throws IOException
    {
        return new File(git.getRepository().getWorkTree().getAbsolutePath()).toURI()
            .relativize(new File(path.toString()).toURI()).getPath();
    }


    public static void writeWGitWarningsLineByLine(String filePath, List<GitDto> gitDtoList)
    {
        LOGGER.debug("Start export to {}", filePath);
        File file = new File(filePath);
        try
        {
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            String[] header = GitDto.getHeaderLine();
            writer.writeNext(header);
            for (GitDto gitDto : gitDtoList)
            {
                writer.writeNext(gitDto.getContentLine());
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        LOGGER.debug("Finished export to {}", filePath);
    }
}
