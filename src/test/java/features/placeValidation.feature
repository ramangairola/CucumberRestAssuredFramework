Feature: Validating Place API's

@AddPlaceAPI @Regression
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
  Given Add Place Payload with "<Name>" "<Address>" "<Language>"
  When user calls "AddPlaceAPI" with "Post" http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_id created maps to "<Name>" using "GetPlaceAPI"

Examples:
    | Name      | Address              | Language   |
    | Chandler  | 15 Yemen road, Yemen | English-EN |
    | Monica    | New York             | English-EN |
    | Joey      | California           | English-EN |
    | Phoebe    | Manhattan            | English-EN |
    | Rachael   | New Jersey           | English-EN |
    | Ross      | Texas                | English-EN |

@DeletePlaceAPI @Regression
Scenario: Verify if delete place functionality is working
  Given DeletePlace Payload
  When user calls "DeletePlaceAPI" with "Delete" http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"
