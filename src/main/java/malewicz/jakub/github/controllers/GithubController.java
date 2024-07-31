package malewicz.jakub.github.controllers;

import lombok.RequiredArgsConstructor;
import malewicz.jakub.github.dtos.RepositoryDetailsResponse;
import malewicz.jakub.github.services.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github/user")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDetailsResponse>> get(@PathVariable final String username) {
        return ResponseEntity.ok(githubService.getRepositoryDetails(username));
    }
}
