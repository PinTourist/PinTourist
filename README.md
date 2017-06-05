# *PinTourist*

> "Let's Pin the world"

##  

PinTourist is a project of 2 Computer and Electrical Engineering students of La Sapienza University that are trying to make a smarter Rome #PinableRome

Imagine a smart phone application that can
- Tell you where the monuments and hot spots in your city are 
- Give you short blurbs of information about the monuments when you are near them
- Give you information on bars and restaurants near the monument you're closest to
- Challenge your friends by visiting the most monuments with mini bonus quizes pertaining to the monument you're near

##  
    


![PinTourist](https://github.com/PinTourist/PinTourist/blob/master/images/logo.png?raw=true)


Main functionalities
==

The main functionalities offered by the application are:
- GPS/NFC enabled scavenger hunt game based on the landmarks of Rome and the “hidden” locations around them
- Game wide leaderboards
- Elementary user profile  
- Detailed explanations of popular landmarks in Rome with a quiz that leads you to search for some of the more nuanced aspects of each landmark

Architecture
=

![architecture](https://cloud.githubusercontent.com/assets/26323785/26791900/e5254a3c-4a18-11e7-9c9a-69b2ea660184.PNG)

Our app uses Google Play-Services Maps to generate the map. It uses Firebase for authetification to facebook and google along with Firebase Database to store all the information about the landmark pins including the pictures, description, location, and quiz questions that are associated with each pin. These can be updated through Firebase and also allow possiblity to add user provided content through Firebase. NFC chips were designated per pin which opens the pin dialogue shown in the screenshots below.   

Video
-----------

Here you can find a video of the application: https://www.youtube.com/watch?v=ziXin6-1wGc

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/ziXin6-1wGc/0.jpg)](https://www.youtube.com/watch?v=ziXin6-1wGc)



Screenshot
=

In this section, the screenshots of the most important applications sections are shown.

Home
-------

![map](https://cloud.githubusercontent.com/assets/26323785/26789550/e96ee506-4a10-11e7-8f92-397b6982c34d.PNG)   

  
Profile
-------

![profile](https://cloud.githubusercontent.com/assets/26323785/26789612/22fc33fa-4a11-11e7-9e54-8aa9db60b44e.png)


Leaderboard
------------

![leaderboard](https://cloud.githubusercontent.com/assets/26323785/26789634/2ed8a690-4a11-11e7-930a-5774ed72c287.png)

   
Pin
-------

| Landmark                                                                                                            | 
|---------------------------------------------------------------------------------------------------------------------|
|![pinlandmark](https://cloud.githubusercontent.com/assets/26323785/26790084/c471d310-4a12-11e7-9119-ae2aafb67b4e.png)|

| Loading                                                                                                             | 
|---------------------------------------------------------------------------------------------------------------------|
|![pinloading](https://cloud.githubusercontent.com/assets/26323785/26790160/0f2796a6-4a13-11e7-9bc8-7b852a12edb7.png)|

| Quiz |
|-----------------------------------------------------------------------------------------------------------------|
|![pinquiz](https://cloud.githubusercontent.com/assets/26323785/26790181/1fdb7378-4a13-11e7-96bd-e6b39f834821.png)|


## Developed by
* **Federico Bacci**
  - [Linkedin](https://www.linkedin.com/in/federico-bacci/)
  - [Facebook](https://www.facebook.com/fedebyes)
  - [GitHub](https://www.github.com/fedebyes)

* **Louis Milia**
  - [Linkedin](https://www.linkedin.com/in/louis-milia)
  - [Facebook](https://www.facebook.com/louis.milia)
  - [GitHub](https://github.com/AilimiSoul)

The project was developed and has been presented within the course of "Pervasive Systems", held by Prof. Ioannis Chatzigiannakis within the Master of Science in Computer Science (MSE-CS), at University of Rome "La Sapienza". 
Informations about the course are available in the following page: http://ichatz.me/index.php/Site/PervasiveSystems2017.

## Presentation

* [First Milestone](http://www.slideshare.net/FedericoBacci/pin-tourist-0-74245888)
* [Second Milestone](http://www.slideshare.net/FedericoBacci/pin-tourist-1-74245895)
* [Third Milestone](https://www.slideshare.net/LouisMilia/pin-tourist-3)

