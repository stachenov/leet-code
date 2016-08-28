/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

public class TwitterTest {
    
    @Test(dataProvider = "actions")
    public void testTwitter(List<Action> actions, List<List<ExpectedFeed>> expected) {
        Twitter tw = new Twitter();
        for (int i = 0; i < actions.size(); ++i) {
            actions.get(i).perform(tw);
            for (ExpectedFeed exp : expected.get(i)) {
                assertThat(tw.getNewsFeed(exp.userId)).isEqualTo(exp.expectedFeed);
            }
        }
    }
    
    @DataProvider
    public Object[][] actions() {
        return new Object[][] {
//            {Arrays.asList(new PostTweet(1, 2)), Arrays.asList(
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(2))))},
//            {Arrays.asList(
//                    new PostTweet(1, 2),
//                    new Follow(1, 3),
//                    new PostTweet(3, 4),
//                    new Unfollow(1, 3)),
//                Arrays.asList(
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(2))),
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(2))),
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(4, 2)),
//                            new ExpectedFeed(3, Arrays.asList(4))),
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(2)))
//                )
//            },
//            {Arrays.asList(
//                    new PostTweet(1, 3),
//                    new PostTweet(1, 2)),
//                Arrays.asList(
//                    Arrays.asList(),
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(2, 3)))
//                )
//            },
//            {Arrays.asList(
//                    new PostTweet(1, 3),
//                    new Follow(1, 1)),
//                Arrays.asList(
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(3))),
//                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(3)))
//                )
//            },
            {Arrays.asList(
                    new PostTweet(1, 5),
                    new PostTweet(1, 3),
                    new PostTweet(1, 101),
                    new PostTweet(1, 13),
                    new PostTweet(1, 10),
                    new PostTweet(1, 2),
                    new PostTweet(1, 94),
                    new PostTweet(1, 505),
                    new PostTweet(1, 333)),
                Arrays.asList(
                    Arrays.asList(), //new PostTweet(1, 5),
                    Arrays.asList(), // new PostTweet(1, 3),
                    Arrays.asList(), // new PostTweet(1, 101),
                    Arrays.asList(), // new PostTweet(1, 13),
                    Arrays.asList(), // new PostTweet(1, 10),
                    Arrays.asList(), // new PostTweet(1, 2),
                    Arrays.asList(), // new PostTweet(1, 94),
                    Arrays.asList(), // new PostTweet(1, 505),
                    Arrays.asList(new ExpectedFeed(1, Arrays.asList(333, 505, 94, 2, 10, 13, 101, 3, 5)))
                )
            },
        };
    }
    
    private static class ExpectedFeed {
        final int userId;
        final List<Integer> expectedFeed;

        public ExpectedFeed(int userId, List<Integer> expectedFeed) {
            this.userId = userId;
            this.expectedFeed = expectedFeed;
        }
    }
    
    private abstract static class Action {
        abstract void perform(Twitter twitter);
    }
    
    private static class PostTweet extends Action {
        final int userId;
        final int tweetId;

        public PostTweet(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }

        @Override
        void perform(Twitter twitter) {
            twitter.postTweet(userId, tweetId);
        }
    }
    
    private static class Follow extends Action {
        final int followerId;
        final int followeeId;

        public Follow(int followerId, int followeeId) {
            this.followerId = followerId;
            this.followeeId = followeeId;
        }

        @Override
        void perform(Twitter twitter) {
            twitter.follow(followerId, followeeId);
        }
    }
    
    private static class Unfollow extends Action {
        final int followerId;
        final int followeeId;

        public Unfollow(int followerId, int followeeId) {
            this.followerId = followerId;
            this.followeeId = followeeId;
        }

        @Override
        void perform(Twitter twitter) {
            twitter.unfollow(followerId, followeeId);
        }
    }
    
}
