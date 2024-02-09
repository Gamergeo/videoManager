# Presentation

Video bookmarks management application. The goal is to have a simple application for organizing videos by tags.

# Technologies
  - Spring Boot (Data, Log...)
  - JavaFX
  - Lombok
  - Maven

This work is in progress. Architecture, code, functionnalities will change a lot from one version to another. This is for personal use but everyone can't fork it and enhance it at will.

I am not a JavaFX expert. Coming from the world of web development, I will try my best to adapt my principles to create a complete and simple project, with a clear and extensible architecture.

This project is meant to be used with [ViewModelFx](https://github.com/Gamergeo/ViewModelFx), a library I've created to adapt my vision of MVVM
I know some other framework or libraries exists, but I wan't to do it by myself

# Versionning

- 0.3: Linking Database / App and Improving JavaFX Architecture
    - 0.3.1: Link all videos with the app
    - 0.3.2: Integration Test
    - 0.3.3: Basic Title Search
    - 0.3.4: Annotation, simplification, improvement of lazy dependencies
    - 0.3.5: Architecture trial
    - 0.3.6: FXMLView / Controller annotation and integration of 0.3.1 from gamlib
    - 0.3.7: General architecture improvement
    - 0.3.8: Architecture rollback to 0.3.7
    - 0.3.9: Random video and clickable link
- 0.4 : Layout Improvement
    - 0.4.1 : Layout general improvements, add css management.
    - 0.4.2 : CSS Generated by openAI, add folder management in fxmlview
    - 0.4.3 : Drag & drop skeleton
    - 0.4.4 : Multi drag & drop and search (gui only)
    - 0.4.5 : Tag search ok
- 0.5 : Features
    - 0.5.1 : Video Rating
    - 0.5.2 : Properly Link rating / tag between video pane and table view. Search looks ok
    - 0.5.3 : Reset video / disable video
    - 0.5.4 : UrlPattern / crtl click on link
- 0.6 : MVVM Refactoring
		Refactoring to properly implement MVVM pattern.
    - 0.6.1 : New skeleton, match with gamlib-0.4.1. Lot of features are not ready yet
    - 0.6.2 : Test architecture
    - 0.6.3 : MVVM pattern
    - 0.6.4 : Minor improvements
    - 0.6.5 : Test architecture. May revert all 0.6
- 0.7 : MVVM Pattern with java fx properties in model  
Oh god i've tried... But it seems that it's far too complicated to avoid having JavaFX properties in the model at all costs. Doing so 		requires creating (or using an existing library for) mapping between the model's properties and the ViewModel's, but this leads to 		unnecessary complexity of the process, numerous problems, and very redundant code. I have therefore decided to stick with the strict MVVM 		pattern (in the sense that views do not have direct access to the model, other than through a ViewModel), but I am now using JavaFX 		properties directly in the API part. Unfortunately, this means that this API would not be usable without JavaFX. A solution to this 		problem, unnecessary here, would be to have an interface for the model and two defining classes, one with the JavaFX properties and the other without.
     - 0.7.1 : Simplicity
     - 0.7.2 : Tag List
So, View are forced into 1-1 relation with ViewModel. Not all viewmodels have to be linked with views, but views has to be linked. Viewmodel are linked to parent viewmodel if any. View can only talk to one viewmodel (viewmodels know hierarchy, not views)
     - 0.7.3 : Save video / Tag video
     - 0.7.4 : Improve engine and features
Not ok yet, there is some architecture problems. We need to have proper interface for each viewmodel to simplify
    - 0.7.5 : CrudService / Refactoring
    - 0.7.6 : Drag&drop tag on video


