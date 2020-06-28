#DMTest

This Application mainly use for two purpose : 
1. Get the all repository project github for a particular user
2. See the details of a selected project

The app follows the MVVM architecture with the repository pattern, alongside Dagger for DI.

####Folder structure

There are 4 main folders: di, models, network, ui, utils.
* di: Contain Dagger 2 related files
* models: Data classes use for the network call response
* network: Retrofit network related classes present in this folder
* ui: All views related classes present in this folder. The app follows MVVM architecture coding pattern 
for this project.
* utils: Contains utilities classes.

###Design, libraries and other stuff applied

* Data binding
* Google Material Components
* ViewModel and LiveData
* RXJava
* Dagger 2 for dependency injection
* Retrofit
* MVVM architecture + Repository pattern
* Navigation Architecture Component
* Constraint Layout

Whole project written in kotlin language.


###Permission!

1. Internet permission

### Note!

This project mainly focus on coding quality and easy to test coding architecture.