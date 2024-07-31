package malewicz.jakub.github.services;

import lombok.RequiredArgsConstructor;
import malewicz.jakub.github.dtos.github.Branch;
import malewicz.jakub.github.dtos.github.GithubRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GithubCommunicatorImpl implements GithubCommunicator {

    private final RestTemplate restTemplate;
    @Value("${github.access.token}")
    private String accessToken;


    @Override
    public List<GithubRepo> getNonForkedRepos(String username) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<List<GithubRepo>> response = restTemplate.exchange(
                "https://api.github.com/users/" + username + "/repos",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );

        return Objects.requireNonNull(response.getBody()).stream().filter(repo -> !repo.isFork()).toList();
    }

    @Override
    public List<Branch> getBranches(String username, String repo) {

        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<List<Branch>> response = restTemplate.exchange(
                String.format("https://api.github.com/repos/%s/%s/branches", username, repo),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    private HttpHeaders createHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("X-GitHub-Api-Version", "2022-11-28");

        return headers;
    }
}
