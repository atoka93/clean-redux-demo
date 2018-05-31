# clean-redux-demo
Sample Android application in Kotlin showcasing an architecture combining [Redux](https://redux.js.org/) and the [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) principles, using the [Kotlin Redux libraries](https://github.com/atoka93/redux-kotlin).

## Structure
The application contains two screens:
- a login screen: this functionality is mocked. When the `Log In` button is pressed it will (with a delay) either randomly display an error message or forward you to the next screen.
- a list screen: it displays the most popular GitHub repository names using the GitHub API (not authenticated). Also contains: paging, reload functionality and error handling.


Currently there is no RX implememtation available.

## License
Licensed under the [Apache License, Version 2.0](https://github.com/atoka93/clean-redux-demo/blob/master/LICENSE).
