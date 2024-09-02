package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommitTest {

    @Test
    void deveArmazenarCommits() {
        Commit commit = new Commit();
        GitFlowManager gitFlowManager = new GitFlowManager();

        commit.createCommit("main", "123abc", "Commit inicial");
        gitFlowManager.saveCommit(commit);

        commit.createCommit("main", "456def", "Adicionada nova funcionalidade");
        gitFlowManager.saveCommit(commit);

        assertEquals(2, gitFlowManager.getCommitHistorySize());
    }

    @Test
    void deveRetornarCommitInicial() {
        Commit commit = new Commit();
        GitFlowManager gitFlowManager = new GitFlowManager();

        commit.createCommit("main", "123abc", "Commit inicial");
        gitFlowManager.saveCommit(commit);

        commit.createCommit("main", "456def", "Adicionada nova funcionalidade");
        gitFlowManager.saveCommit(commit);

        gitFlowManager.rollback(commit, 0);
        assertEquals("Branch: main, Commit Hash: 123abc, Message: Commit inicial", commit.toString());
    }

    @Test
    void deveRetornarCommitAnterior() {
        Commit commit = new Commit();
        GitFlowManager gitFlowManager = new GitFlowManager();

        commit.createCommit("main", "123abc", "Commit inicial");
        gitFlowManager.saveCommit(commit);

        commit.createCommit("main", "456def", "Adicionada nova funcionalidade");
        gitFlowManager.saveCommit(commit);

        commit.createCommit("feature", "789ghi", "Mesclada branch de funcionalidade");
        gitFlowManager.saveCommit(commit);

        gitFlowManager.rollback(commit, 1); // Voltar ao segundo commit
        assertEquals("Branch: main, Commit Hash: 456def, Message: Adicionada nova funcionalidade", commit.toString());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        Commit commit = new Commit();
        GitFlowManager gitFlowManager = new GitFlowManager();

        commit.createCommit("main", "123abc", "Commit inicial");
        gitFlowManager.saveCommit(commit);

        try {
            gitFlowManager.rollback(commit, 1); // Tentativa de rollback com índice inválido
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Índice de commit inválido.", e.getMessage());
        }
    }
}
