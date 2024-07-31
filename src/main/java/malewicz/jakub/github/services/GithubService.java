package malewicz.jakub.github.services;

import malewicz.jakub.github.dtos.RepositoryDetailsResponse;

import java.util.List;

public interface GithubService {
    List<RepositoryDetailsResponse> getRepositoryDetails(final String username);
}
