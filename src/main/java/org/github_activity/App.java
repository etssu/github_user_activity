package org.github_activity;

public class App {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Usage: ./github-activity <username>");
            return;
        }
        GithubActivity g = new GithubActivity(args[0]); // send username
        g.getActivity();

    }
}
