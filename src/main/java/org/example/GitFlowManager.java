package org.example;

import java.util.ArrayList;
import java.util.List;

// Cuidador
public class GitFlowManager {
    private List<CommitMemento> commitHistory = new ArrayList<>();

    public void saveCommit(Commit commit) {
        commitHistory.add(commit.saveCommit());
    }

    public void rollback(Commit commit, int index) {
        if (index >= 0 && index < commitHistory.size()) {
            commit.restoreCommit(commitHistory.get(index));
        } else {
            throw new IndexOutOfBoundsException("Índice de commit inválido.");
        }
    }

    public void showHistory() {
        for (int i = 0; i < commitHistory.size(); i++) {
            CommitMemento memento = commitHistory.get(i);
            System.out.println("[" + i + "] Branch: " + memento.getBranch() + ", Commit Hash: " + memento.getCommitHash() + ", Message: " + memento.getMessage());
        }
    }

    public int getCommitHistorySize() {
        return commitHistory.size();
    }

    public CommitMemento getCommitAtIndex(int index) {
        if (index >= 0 && index < commitHistory.size()) {
            return commitHistory.get(index);
        } else {
            throw new IndexOutOfBoundsException("Índice de commit inválido.");
        }
    }
}
