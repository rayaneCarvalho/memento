package org.example;

import java.util.ArrayList;
import java.util.List;

public class Commit {
    private String branch;
    private String commitHash;
    private String message;
    private List<CommitMemento> mementoList = new ArrayList<>();

    public void createCommit(String branch, String commitHash, String message) {
        this.branch = branch;
        this.commitHash = commitHash;
        this.message = message;
    }

    public CommitMemento saveCommit() {
        return new CommitMemento(branch, commitHash, message);
    }

    public void restoreCommit(CommitMemento memento) {
        this.branch = memento.getBranch();
        this.commitHash = memento.getCommitHash();
        this.message = memento.getMessage();
    }

    public void addMemento(CommitMemento memento) {
        mementoList.add(memento);
    }

    public void restoreFromMemento(int index) {
        if (index < 0 || index >= mementoList.size()) {
            throw new IllegalArgumentException("Índice inválido");
        }
        restoreCommit(mementoList.get(index));
    }

    @Override
    public String toString() {
        return "Branch: " + branch + ", Commit Hash: " + commitHash + ", Message: " + message;
    }
}

