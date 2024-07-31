# Github data extractor
## How to run
Please specify a environment variable with your github access token
```properties
github_access_token=<your_access_token>
```
## Endpoints
To extract users data you can call this endpoint
```http request
http://localhost:8080/github/user/{username}
```
this will return all repositories that are owned by the user with all their branches and the latest commit sha for them