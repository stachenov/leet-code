/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class Twitter {
    private final Map<Integer, List<Tweet>> tweets = new HashMap<>();
    private final Map<Integer, Set<Integer>> followees = new HashMap<>();
    private int time = 0;
    
    /** Initialize your data structure here. */
    public Twitter() {
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, u -> new ArrayList<>()).add(new Tweet(tweetId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> reversed = new PriorityQueue<>((tw1, tw2) -> Integer.compare(tw2.time, tw1.time));
        List<Tweet> twThis = tweets.computeIfAbsent(userId, u -> new ArrayList<>());
        addLast10(twThis, reversed);
        for (int f : followees.computeIfAbsent(userId, u -> new HashSet<>())) {
            addLast10(tweets.computeIfAbsent(f, u -> new ArrayList<>()), reversed);
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 10 && !reversed.isEmpty(); ++i)
            answer.add(reversed.remove().id);
        return answer;
    }

    private void addLast10(List<Tweet> twThis, PriorityQueue<Tweet> reversed) {
        IntStream.range(twThis.size() - 10, twThis.size())
                .filter(i -> i >= 0)
                .mapToObj(i -> twThis.get(i))
                .forEachOrdered(tw -> reversed.add(tw));
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId)
            return;
        followees.computeIfAbsent(followerId, id -> new HashSet<>()).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        followees.computeIfAbsent(followerId, id -> new HashSet<>()).remove(followeeId);
    }
    
    private class Tweet {
        final int id;
        final int time;

        Tweet(int id) {
            this.id = id;
            this.time = Twitter.this.time++;
        }
    }
}
