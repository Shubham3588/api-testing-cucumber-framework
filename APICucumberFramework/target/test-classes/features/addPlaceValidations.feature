Feature: Validating Place API's

Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
  Given Add Place Playload "<name>" "<language>" "<address>"
  When User calls "AddPlaceAPI" with post http request
  Then the API call is success with status code "200"
  And the "status" in response body is "OK"
  And the "scope" in response body is "APP" 
  
 Examples:
 
  |name|language|address|
  |AaHouse|English| 1st Cross Road|
   |BBHouse|Hindi| 2nd Cross Road|
   |CcHouse|French| 3rd Cross Road|


