package post;

import java.time.LocalDate;

public class UserAccount {
    private final String email;
    private final String userName;
    private final UserAccount[] followers = new UserAccount[20];
    private int numberOfFollowers;
    private final Post[] myPosts;
    private int numberOfPosts;
    private final Post[] myTimeline = new Post[10];
    private int numberOfPostsTimeline;

    UserAccount(String email, String userName) {
        this.email = email;
        this.userName = userName;
        myPosts = new Post[10];
    }

    public void publish(String quote) {
        Post newPost = new Post(this, quote);

        myPosts[numberOfPosts] = newPost;
        numberOfPosts++;

        for (int i = 0; i < numberOfFollowers; i++)
            followers[i].updateTimeline(newPost);
    }

    public void updateTimeline(Post newPost) {
        if (numberOfPostsTimeline == 10) {
            LocalDate oldDate = myTimeline[0].getDate();
            int auxIndex = 0;
            for (int i = 1; i < myTimeline.length; i++) {
                if (myTimeline[i].getDate().isBefore(oldDate)) {
                    oldDate = myTimeline[i].getDate();
                    auxIndex = i;
                }
            }

            myTimeline[auxIndex] = newPost;
        } else {
            myTimeline[numberOfPostsTimeline] = newPost;
            numberOfPostsTimeline++;
        }
    }

    public boolean delete (int postIndex) {
        if (numberOfPosts == 0 || postIndex > numberOfPosts-1  || postIndex < 0 ) return false;

        if (postIndex == numberOfPosts-1) {
            myPosts[numberOfPosts-1] = null;
            return true;
        }

        Post removedPost = myPosts[postIndex];
        for (int i = 0; i < numberOfPosts - 1; i++) {
            if (myPosts[i] == removedPost) {
                //Post aux = myPosts[i];
                myPosts[i] = myPosts[numberOfPosts - 1];
                //myPosts[i+1] = aux;
            }
        }

        myPosts[numberOfPosts-1] = null;
        numberOfPosts--;

        return true;
    }

    public String getTimelineAsString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfPostsTimeline; i++) {
            result.append("\n").append(myTimeline[i].getPostAsString());
        }
        return result.toString();
    }

    public String getPostsAsString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfPosts; i++) {
            result.append("/n").append(myPosts[i].getPostAsString());
        }
        return result.toString();
    }

    public String getFriendsAsString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfFollowers; i++) {
            result.append("/").append(followers[i].getUserAsString());
        }
        return result.toString();
    }

    public void clapPost(int postIndex) {
        if ( (numberOfPostsTimeline == 0) || (postIndex > numberOfPostsTimeline-1)  || (postIndex < 0) ) return;
        myTimeline[postIndex].clap();
    }

    public void booPost(int postIndex) {
        if ( (numberOfPostsTimeline == 0) || (postIndex > numberOfPostsTimeline-1)  || (postIndex < 0) ) return;
        myTimeline[postIndex].boo();
    }

    private boolean existsFollower(UserAccount newFollower) {
        for (int i = 0; i < numberOfFollowers; i++) {
            if (followers[i] == newFollower) {
                return true;
            }
        }

        return false;
    }

    public void acceptFollower(UserAccount newFollower) {
        if (!existsFollower(newFollower)) {
            followers[numberOfFollowers] = newFollower;
            numberOfFollowers++;
        }
    }

    public void blockFollower(UserAccount follower) {
        if (existsFollower(follower)) {
            for (int i = 0; i < followers.length; i++) {
                if (followers[i] == follower) {
                    followers[i] = followers[i+1];
                    followers[i+1] = followers[i];
                }
            }

            followers[numberOfFollowers-1] = null;
            numberOfFollowers--;
        }
    }

    public String getUserName() {
        return userName;
    }

    private String getUserAsString() {
        return String.format("E-mail: %s | User name: %s", email, userName);
    }
}
