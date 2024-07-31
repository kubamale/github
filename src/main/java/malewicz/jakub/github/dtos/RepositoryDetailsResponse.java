package malewicz.jakub.github.dtos;

import java.util.List;

public record RepositoryDetailsResponse(
        String repositoryName,
        String ownerLogin,
        List<BranchDetailsInternal> branchDetails
) { }
