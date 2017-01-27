# ThousandTwitters

![N|Solid](https://cdn3.tnwcdn.com/wp-content/blogs.dir/1/files/2015/03/0318_twitter.jpg)

A demo of this app is hosted [here](https://thousandtwitters.herokuapp.com/):
### Available endpoints
* [/newsfeed](https://thousandtwitters.herokuapp.com/newsfeed) Shows the newsfeed for the currently logged in user.
* [/newsfeed?search=139](https://thousandtwitters.herokuapp.com/newsfeed?search=139) Will filter the newsfeed to contain the String "139".
* [/follows](https://thousandtwitters.herokuapp.com/follows) Shows the list of users the currently logged in user is following
* [/followedBy](https://thousandtwitters.herokuapp.com/followedBy) Shows the list of users the currently logged in user is followed by.
* [/follow?userId=2](https://thousandtwitters.herokuapp.com/follow?userId=2) The currently logged in user will unfollow user with id 2.
* [/unfollow?userId=2](https://thousandtwitters.herokuapp.com/unfollow?userId=2) The currently logged in user will unfollow user with id 2.

### Example user credentials
You can use one of the following users to login into the demo app.
```
User: Cartman     pw: user1
User: Stan        pw: user2
User: Kyle        pw: user3
User: Tweek       pw: user4
User: Butters     pw: user5
User: Ike         pw: user6
User: Wendy       pw: user7
```

The whole database initialization can be seen [here](https://github.com/A1m7331/thousandTwitters/blob/master/src/main/resources/data.sql) (This may not be the current database state)