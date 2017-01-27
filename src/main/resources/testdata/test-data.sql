INSERT INTO user (Username, Password, Email, enabled)
VALUES
  ('Cartman', '$2a$10$PwrVS2oXfp6k4.3iOQF2I.jGl3hQBLxyf362VqEB66cqco2FCNrvm', 'cartman@1ktwitter.com', true),
  ('Stan', '$2a$10$hif0VqrdDs8/yEp0bt4Zx.T8nYQDerdBagqVEtmMgNRGWonFrzcO2', 'stan@1ktwitter.com', true),
  ('Kyle', '$2a$10$gbQ9JJUeTRDNnYifDx.KAeH4NoWBwqlPs7z8EMikni5OSw0hGmADK', 'kyle@1ktwitter.com', true),
  ('Tweek', '$2a$10$VwOYInlhiPCCi2PIuMFba.olDiThSMb8/uqzBwN6fb0bLv7WzjAo2', 'tweek@1ktwitter.com', true),
  ('Butters', '$2a$10$kGpgEdDoPd1sbi7h4Ntrqu7fnijEkteT4Bq/kI7WLQtRV6WmU1GWK', 'leopoldstotch@1ktwitter.com', true),
  ('Ike', '$2a$10$EnwJk5DXYCyH5zN7dOi7Q.oNsHE7Bg2cwPOqQYXnYKGlSGmoKaMIi', 'ike@1ktwitter.com', true),
  ('Wendy', '$2a$10$Nn5ZBfo1D58cnOvOvnRO8OLGeYuXg9U14PJDpUq1qoxaONX7FKhFm', 'wendy@1ktwitter.com', true);

INSERT INTO authorities (User, Authority)
VALUES
  (1, 'User'),
  (2, 'User'),
  (3, 'User'),
  (4, 'User'),
  (5, 'User'),
  (6, 'User'),
  (7, 'User');

INSERT INTO follows (Follower, Followed)
VALUES
  ('1', '2'),
  ('1', '4'),
  ('1', '5'),
  ('1', '6'),
  ('1', '7'),
  ('2', '1'),
  ('2', '4'),
  ('2', '5'),
  ('2', '6'),
  ('3', '1');

INSERT INTO tweet (User, Text)
VALUES
  ('1', 'Limiting tweets here to 139 characters was just brilliant, always disliked 140 @ old Twitter!'),
  ('3', 'Finally a fresh Twitter, always disliked the old rusty one'),
  ('1', 'I have never understood the timeline of twitter anyways'),
  ('1', 'STOP SPAMMING'),
  ('2', 'STOP SPAMMING'),
  ('1', 'STOP SPAMMING'),
  ('2', 'SO HAPPY :). Those features here are amazing!'),
  ('4', 'Please dont read my tweets, i want to be private');