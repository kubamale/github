package malewicz.jakub.github.dtos.github;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
    private String sha;
}
