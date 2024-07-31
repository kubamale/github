package malewicz.jakub.github.services;

import malewicz.jakub.github.dtos.github.Branch;
import malewicz.jakub.github.dtos.github.Commit;
import malewicz.jakub.github.dtos.github.GithubRepo;
import malewicz.jakub.github.dtos.github.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubCommunicatorImplTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    GithubCommunicatorImpl githubCommunicatorImpl;

    @Test
    void getNonForkedRepos() {
        GithubRepo notForkedRepo = GithubRepo.builder().fork(false).name("repo1").owner(Owner.builder().login("user").build()).build();
        GithubRepo forkedRepo = GithubRepo.builder().fork(true).name("repo1").owner(Owner.builder().login("user").build()).build();
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class))
        ).thenReturn(ResponseEntity.ok(List.of(notForkedRepo, forkedRepo)));

        List<GithubRepo> repos = githubCommunicatorImpl.getNonForkedRepos("user");
        assertThat(repos).hasSize(1);
    }

    @Test
    void getBranches() {
        Branch branch = Branch.builder().name("branch1").commit(Commit.builder().sha("adadadaijifjeije").build()).build();
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(List.of(branch)));

        List<Branch> branches = githubCommunicatorImpl.getBranches("user", "repo");
        assertThat(branches).hasSize(1);
    }
}