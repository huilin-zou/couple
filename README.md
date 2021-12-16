Original App Design Project
===

# couple

## User Stories

The following **required** functionality is completed:

- [x] Users can sign up to create a new account using Parse authentication.
- [x] Users can log in and log out of their account.
- [x] Users can take a photo, add a caption, and post it to "couple".
- [x] Users can like each others post and be added to a Like class.
- [x] Users can filter posts by gender of other users.
- [x] Users can match with other users if they both liked each other.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://user-images.githubusercontent.com/56774880/146314927-c8c6137f-c92f-41aa-ac34-23d34dbbe13c.gif' title='Video Walkthrough' width='300' alt='Video Walkthrough' />


## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
couple is a geosocial networking and online dating application that allows users to anonymously swipe to like or dislike other users' posted profiles, which generally comprise their photo, a short bio, and a list of their personal interests. 

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Lifestyle
- **Mobile:** Convinience 
- **Story:** Users can post pictures like other users and communicate with those users.
- **Market:** Global
- **Habit:** New likes, new matches, Daily
- **Scope:** Dating

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Users can post a picture of themselves
* Users can like or dislike another user
* If the user dislikes another user that user is removed from the feed
* User can see who liked their photo
* Users can send a private message to other user
* User can use filter to alter their feed

**Optional Nice-to-have Stories**

* If users match they can open up a messaging window between the two users
* Users can make a comment for the post


### 2. Screen Archetypes

* Welcome page
  * The first page user sees when they first opens up the app
* Login screen
   * Users can log in
* Registration Screen
   * Users can create a new account
* Stream
    * User can view a feed of photos
    * User can like or dislike the photo
* Filter
    * User can search advance using gender filter
* Creation
    * User can post a photo
* Chat
    * Matched users can message each other

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Profile
* Chat
* post compose

**Flow Navigation** (Screen to Screen)

* Login
* Main Screen
   * Main feed
   * Filter
   * Your Profile
   * Your Matches feed
* Main feed
   * Press to see details

## Wireframes

<img src="https://user-images.githubusercontent.com/56774880/139128185-64e6181f-64dc-4d25-85c5-c010ae293cbc.png" width=300>
<img src="https://user-images.githubusercontent.com/56774880/139128221-cc3d3cf7-c490-4048-947f-a04ae91d4b11.png" width=300>
<img src="https://user-images.githubusercontent.com/56774880/139128253-7bc57f7f-c749-436a-b5e4-a11e40a9ddc2.png" width=300>
<img src="https://user-images.githubusercontent.com/56774880/139128309-a476abb3-f418-4f02-8c22-50699eb8f58b.png" width=300>


## Schema 
### Models
Feed
| Property | Type | Description|
| -------- | -------- | -------- |
| ObejctId     | String     | unique id for the user post (default field)     |
|author|pointer to pointer|image author|
|image|file|image that user posts
caption|string|	image caption by author
createdAt|	DateTime|	date when post is created (default field)
likesCount| Number | number of likes that has been posted to an image



Signup
| Property | Type |Description |
| -------- | -------- | -------- |
| username     | String     | Username     |
|password| String| Password
|gender|String|User's gender|
|age|int| User's age

### Networking
  - Home Feed Screen
      - (Read/GET) Query all posts where user's post was liked by another user
         ```swift
         let query = PFQuery(className:"Post")
         query.whereKey("Liked", equalTo: True)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let posts = posts {
               print("Successfully retrieved \(posts.count) posts.")
           // TODO: Do something with posts...
            }
         }
         ```
      - (Create/POST) Create a new like/dislike on a post
      - (Delete) Delete existing like/dislike
   - Create Post Screen
      - (Create/POST) Create a new post object
   - Private Chat Screen
      - (Create/POST) Create a new comment on a post

##licenses

[chat-logo-license.pdf](https://github.com/CodePath-Project404/Dating_Project_2021/files/7693808/chat-logo-license.pdf)

[OFL.txt](https://github.com/CodePath-Project404/Dating_Project_2021/files/7695476/OFL.txt)

