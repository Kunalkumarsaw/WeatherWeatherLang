# WeatherWeatherLang

Description
A simple application to demonstrate knowledge of the Android framework, architectural skills,
and familiarity with common processes like testing or communicating with REST API.
The application is composed of two screens only.
Main screen
● List of weather conditions in Manila, Prague and Seoul
● Each item contains
○ Name of the location
○ Weather status
○ Current temperature
○ Favorite indicator
○ Background color based on the temperature
■ Freezing #1976D2 (-infinity, 0]
■ Cold #26C6DA (0, 15]
■ Warm #66BB6A (15, 30]
■ Hot #FF7043 (30, infinity)

● Show loading progress when data is loading
● Swipe-to-refresh functionality
● Clicking on an item navigates to the detail screen
Detail screen
● Shows detailed information for a specific location
● The screen contains
○ Name
○ Weather status
○ Current temperature
○ Low and high temperatures
○ Favorite toggle button
● When the user clicks on the favorite toggle button then its icon should toggle between
outline drawable and fill drawable
● An indicator of a favorite location should be automatically updated in the list when
navigating back to the main screen

Requirements
Use Kotlin.
Use MVVM architecture.
Use Gradle.
Use minSdk 21 and maxSdk 29.
Use only single activity. Implement each screen as a separate fragment.
App must not crash.
Present network errors to the user.
Write at least 3 unit tests that are substantially different from each other.
Download weather information from https://openweathermap.org/current
● Create your own account.
● 1000 free requests per day should be enough for this task.
● Here are the IDs of the three cities:
○ Manila - 1701668
○ Prague - 3067696
○ Seoul - 1835848
● Use /data/2.5/group endpoint for the list screen
● Use /data/2.5/weather endpoint for the detail screen
● Use ?units=metric to get temperatures in Celsius instead of Kelvin
Display current temperature with one decimal place. Low/high temperatures without a decimal
point.
Navigating back from the detail returns the user to the list. Should work with both system back
button and up navigation button in the ActionBar.
