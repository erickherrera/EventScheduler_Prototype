# EventScheduler_Prototype
## Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?
The goal of this application is to create a program that provides user with the ability to add events to their user profile which are stored in a list layout in the home screen. The event scheduler will also notify user

## What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
The application is composed of a 'Home' screen with a scroll view that contains the events that have been added via FAB. When user presses the FAB, the event creation fargment appears where user must enter an even title to add the event to the list or simply press the back button to cancel the event creating fragment. The app has a light and dark theme and very simple UI to accomplish the goal. 
## How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?
The application is using Material Desdign for the UI, and the aquitecture is to always start at the log in screen. At the Log In Screen the user will either press log in button to log in or enter their information to register. If the user already has an active session then when the user presses log in it will automatically navigate them to their home screen. The home screen is considered the main activity the event creation fragment is tied to this activity. I approach activity and fragments as activities being the head of a thread and the fragments being the nodes of that thread.
## How did you test to ensure your code was functional? Why is this process important and what did it reveal?
I tested my code on my own android device and it helped reveal that instead of a scroll view a recycler view was a better choice for the type of functionality I was trying to build.
