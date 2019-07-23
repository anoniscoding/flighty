# Flightly Documentation

## Architecture
Model-View-ViewModel

## Data package
This contains all the data models associated with fetching the schedule data

## DI package
This contains dagger setup for dependency injection within the app. The `AppModule` class contains all app wide dependencies.
The `AuthInterceptor` class manages token generation for the endpoints. The `BuilderModule` class registers fragments that
are eligible for dependency injection. The `NetworkModule` setup all the required dependency for making network requests.

The `ViewModelFactory` class takes a runtime generated map as parameter that contains the providers for all viewModels 
registered in the `DependencyModule` class. The `ViewModelFactoryModule` is responsible for providing the `ViewModelFactory`.

## Extensions package
This contains custom extension functions for classes within the codebase

## Request package
This contains all the Retrofit request interfaces within the codebase

## UI package
This contains all user interface related code. Model-View-ViewModel is used here.

## Utils package
This contains utility classes such as the `BindingUtils` class. This class is a bindable adapter responsible for populating
recycler views with data.