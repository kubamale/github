package malewicz.jakub.github.services;


import malewicz.jakub.github.dtos.github.Branch;
import malewicz.jakub.github.dtos.github.GithubRepo;

import java.util.List;

public interface GithubCommunicator {
    List<GithubRepo> getNonForkedRepos(String username);
    List<Branch> getBranches(String username, String repo);
}
