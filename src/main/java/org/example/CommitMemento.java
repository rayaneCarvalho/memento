package org.example;

// Memento
public class CommitMemento {
    private String branch;
    private String commitHash;
    private String message;

    public CommitMemento(String branch, String commitHash, String message) {
        this.branch = branch;
        this.commitHash = commitHash;
        this.message = message;
    }

    public String getBranch() {
        return branch;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public String getMessage() {
        return message;
    }
}

