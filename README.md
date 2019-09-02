The project is build with MVVM design pattern 

## Emergency Control 
`EmergencyStimulator.kt` Emergency events are stimulated in , a random event between "No Emergency" and "Emergency" is emmited every random 30 - 90 seconds. 

`EmergencyControlCallHandler.kt` handles the API call every 5 seconds when there is an emergency event and stops the API requests when there is no emergency. 

## Error Handler

API request error handled and displayed until the next emergency event is emmited. The error message will disappear reactively

## Potential Improvements
### Sealed class 
   Can be applied as the polynome of `Employee.kt` with threee age groups
### Unit Tests
