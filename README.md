# ABN AMRO Assesment Description 📘:	
ABN AMRO assesment with a requirement to implement a an app that shows ABN AMRO GitHub repositories. The App contains two (2) screens, list screen and detail screen using the Github Rest API, where the CTA button in the details screen would link to an external browser to view the repository.
( ✨demo below ✨ )


# Built With 🛠	
* Kotlin
* Kotlin Coroutines
* Kotlin Flows
* Jetpack Compose
* ViewModel
* Room 
* Retrofit
* Jetpack Paging
* Coil
* Jetpack Compose Navigation 
* Mockk (mocking dependencies)
* Dagger Hilt
* Android Multi-Module


# Architecture  🏗
This app uses multi-module MVVM (Model View View-Model) architecture :
![image](https://github.com/user-attachments/assets/352b39e5-98f0-4019-b859-6fff40ccafcc)


# Multi Module Structure 💈	
The app uses a multi module setup using a hybrid modularization pattern (by layer & by feature). The app was split into the listed modules :

- App
- List UI 
- List Data
- Details UI
- Details Data
- Database
- Network

Module Graph :
![image](https://github.com/user-attachments/assets/69f52ead-afa1-4443-993b-ef5aebf82a0d)


# App Screen Flow 🎨
https://github.com/user-attachments/assets/88d8c148-f8eb-476f-b488-937578748198

