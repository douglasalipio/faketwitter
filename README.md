# faketwitter


## Video demo

<img src ="https://github.com/douglasalipio/faketwitter/blob/master/demo_resources/demo_image_1.png"  width="360"/>&nbsp;&nbsp;
<img src ="https://github.com/douglasalipio/faketwitter/blob/master/demo_resources/demo_image_2.png" width="360" />&nbsp;&nbsp;

[[Video]](https://github.com/douglasalipio/faketwitter/blob/master/demo_resources/full_demo_smallest.mp4)

## Relevant feature developed
- [x] Reposting content [Long press action on item list]
- [x] Quote posting and [click on item list]
- [x] Posting content
- [x] Limit 5 posts in one day 
- [x] Count of number of posts the user has made per type
- [x] Limit 770 characters when write a post. 


## Project architecture
The project was developed based on clean architecture and separated by modules such as domain, presentation, and data.

* Presentation - MVVM was developed to communicate with the UI 
* Domain - Usecase pattern to handle business logic
* Data - Repository pattern to handle local data

## Relevant 3rd party libraries
* Livedata
* Viewmodel
* Mockk
* Junit
* Circleimageview
* Navigation
* Koin
