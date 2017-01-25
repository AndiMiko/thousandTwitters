INSERT INTO user (Username, Password, Email, enabled)
VALUES ('Alice', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'alice@thousandtwitters.com', true);

INSERT INTO user (Username, Password, Email, enabled)
VALUES ('Bob', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'bob@gmail.com', true);

INSERT INTO user (Username, Password, Email, enabled)
VALUES ('John', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'john@johnsdomain.com', true);

INSERT INTO authorities (User, Authority)
VALUES (1, 'User');

INSERT INTO authorities (User, Authority)
VALUES (2, 'User');

INSERT INTO follows (Follower, Followed)
VALUES ('1', '2');

INSERT INTO follows (Follower, Followed)
VALUES ('1', '3');

INSERT INTO follows (Follower, Followed)
VALUES ('2', '1');

INSERT INTO tweet (User, Text)
VALUES ('1', 'Finally a fresh Twitter, always disliked the old rusty one');

INSERT INTO tweet (User, Text)
VALUES ('1', 'I have never understood the timeline of twitter anyways');

INSERT INTO tweet (User, Text)
VALUES ('1', 'SO HAPPY :). Those features here are amazing!');

INSERT INTO tweet (User, Text)
VALUES ('1', 'Limiting tweets here to 139 characters was just brilliant, always disliked 140 @ old Twitter!');

INSERT INTO tweet (User, Text)
VALUES ('3', 'Please dont read my tweets, i want to be private');