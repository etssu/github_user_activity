package org.github_activity;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubActivity {
    private final String username;
    public GithubActivity(String username) {
        this.username = username;
    }

    public void getActivity() {
        String GITHUB_URL = "https://api.github.com/users/" + username + "/events";

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().
                    uri(URI.create(GITHUB_URL)).
                    header("Accept", "application/json").
                    GET().
                    build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println(response.body());
            } else {
                System.out.println(response.statusCode());
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
