package malewicz.jakub.github.services;

import lombok.RequiredArgsConstructor;
import malewicz.jakub.github.dtos.BranchDetailsInternal;
import malewicz.jakub.github.dtos.github.GithubRepo;
import malewicz.jakub.github.dtos.RepositoryDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    private final GithubCommunicator githubCommunicator;

    @Override
    public List<RepositoryDetailsResponse> getRepositoryDetails(final String username) {
        List<GithubRepo> repositories = githubCommunicator.getNonForkedRepos(username);

        for (GithubRepo repo : repositories) {
            repo.setBranches(githubCommunicator.getBranches(username, repo.getName()));
        }

        List<RepositoryDetailsResponse> repos = new ArrayList<>();

        for (GithubRepo repo : repositories) {
            repos.add(new RepositoryDetailsResponse(
                            repo.getName(),
                            repo.getOwner().getLogin(),
                            repo.getBranches().stream().map(r ->
                                    new BranchDetailsInternal(
                                            r.getName(),
                                            r.getCommit().getSha())
                            ).toList()
                    )
            );
        }

        return repos;
    }

}
