package malewicz.jakub.github.dtos.github;

import lombok.*;

import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepo {
    private String name;
    private Owner owner;
    private boolean fork;
    @Setter
    private List<Branch> branches;
}
