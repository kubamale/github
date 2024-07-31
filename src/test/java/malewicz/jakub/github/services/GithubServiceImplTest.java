package malewicz.jakub.github.services;

import malewicz.jakub.github.dtos.RepositoryDetailsResponse;
import malewicz.jakub.github.dtos.github.Branch;
import malewicz.jakub.github.dtos.github.Commit;
import malewicz.jakub.github.dtos.github.GithubRepo;
import malewicz.jakub.github.dtos.github.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubServiceImplTest {

    @Mock
    GithubCommunicator githubCommunicator;
    @InjectMocks
    GithubServiceImpl githubService;


    @Test
    void getRepositoryDetails_success() {
        GithubRepo notForkedRepo = GithubRepo.builder().fork(false).name("repo1").owner(Owner.builder().login("user").build()).build();
        Branch branch = Branch.builder().name("branch1").commit(Commit.builder().sha("adadadaijifjeije").build()).build();

        when(githubCommunicator.getNonForkedRepos(anyString())).thenReturn(List.of(notForkedRepo));
        when(githubCommunicator.getBranches(anyString(), anyString())).thenReturn(List.of(branch));

        List<RepositoryDetailsResponse> repositoryDetails = githubService.getRepositoryDetails("user");
        assertThat(repositoryDetails).hasSize(1);
        assertThat(repositoryDetails.getFirst().branchDetails()).hasSize(1);
    }
}