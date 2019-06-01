package de.gitanalyze.dto;

import java.time.LocalDateTime;

public class GitDto
{
    private String path;

    private LocalDateTime localDateTime = LocalDateTime.now();
    private String versionHash;
    private int buildNumber;
    private String jobName;

    private int gitAmountOfChanges;
    private int gitContributorsToFile;
    private int gitFileCreated;
    private int gitFileUpdated;
    private int loc;
    private int cyclomaticComplexity;


    public static String[] getHeaderLine()
    {
        String[] header = {
            "path", "localDateTime", "versionHash", "buildNumber", "jobName", " gitAmountOfChanges",
            "gitContributorsToFile", "gitFileCreated", "gitFileUpdated", "loc", "cyclomaticComplexity",
        };
        return header;
    }


    @Override public String toString()
    {
        return "GitDto{" +
            "path='" + path + '\'' +
            ", localDateTime=" + localDateTime +
            ", versionHash='" + versionHash + '\'' +
            ", buildNumber=" + buildNumber +
            ", jobName='" + jobName + '\'' +
            ", gitAmountOfChanges=" + gitAmountOfChanges +
            ", gitContributorsToFile=" + gitContributorsToFile +
            ", gitFileCreated=" + gitFileCreated +
            ", gitFileUpdated=" + gitFileUpdated +
            ", loc=" + loc +
            ", cyclomaticComplexity=" + cyclomaticComplexity +
            '}';
    }


    public LocalDateTime getLocalDateTime()
    {
        return localDateTime;
    }


    public void setLocalDateTime(LocalDateTime localDateTime)
    {
        this.localDateTime = localDateTime;
    }


    public String getVersionHash()
    {
        return versionHash;
    }


    public void setVersionHash(String versionHash)
    {
        this.versionHash = versionHash;
    }


    public int getBuildNumber()
    {
        return buildNumber;
    }


    public void setBuildNumber(int buildNumber)
    {
        this.buildNumber = buildNumber;
    }


    public String getJobName()
    {
        return jobName;
    }


    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }


    public String getPath()
    {
        return path;
    }


    public void setPath(String path)
    {
        this.path = path;
    }


    public int getGitAmountOfChanges()
    {
        return gitAmountOfChanges;
    }


    public void setGitAmountOfChanges(int gitAmountOfChanges)
    {
        this.gitAmountOfChanges = gitAmountOfChanges;
    }


    public int getGitContributorsToFile()
    {
        return gitContributorsToFile;
    }


    public void setGitContributorsToFile(int gitContributorsToFile)
    {
        this.gitContributorsToFile = gitContributorsToFile;
    }


    public int getGitFileCreated()
    {
        return gitFileCreated;
    }


    public void setGitFileCreated(int gitFileCreated)
    {
        this.gitFileCreated = gitFileCreated;
    }


    public int getGitFileUpdated()
    {
        return gitFileUpdated;
    }


    public void setGitFileUpdated(int gitFileUpdated)
    {
        this.gitFileUpdated = gitFileUpdated;
    }


    public int getLoc()
    {
        return loc;
    }


    public void setLoc(int loc)
    {
        this.loc = loc;
    }


    public int getCyclomaticComplexity()
    {
        return cyclomaticComplexity;
    }


    public void setCyclomaticComplexity(int cyclomaticComplexity)
    {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }


    public String[] getContentLine()
    {
        String[] fields = {
            path,
            "" + localDateTime,
            versionHash,
            "" + buildNumber,
            jobName,
            "" + gitAmountOfChanges,
            "" + gitContributorsToFile,
            "" + gitFileCreated,
            "" + gitFileUpdated,
            "" + loc,
            "" + cyclomaticComplexity,
        };
        return fields;
    }
}
