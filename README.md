# faketwitter


## Video demo

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

# Questions

## Reflect on this project, and write what you would improve if you had more time.
In case of more development time, I could develop more unit tests to improve test coverage and improve the UI of the application. Also create a build source to manager all dependencies from modules.

## Assuming you've got multiple crash reports and reviews saying the app is not working properly and is slow for specific models, what would be your strategy to tackle the problem? (assuming the app is supposed to work well for these models)

First try to identify the problem through some error management tool like Crashlitics. After analyzing the error files, I would check Proguard (code obfuscation tool) to verify  It is not changed the model name on production build. 

## Assuming your app has now thousands of users thus a lot of posts to show in the feed. What do you believe should be improved in this initial version and what strategies/proposals you could formulate for such a challenge?

A good scaling strategy would be to create a cache layer to reduce the amount of requests on the server.
