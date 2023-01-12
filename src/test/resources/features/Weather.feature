Feature: Visit NDTV and OpenWeather and Compare Temperature And Humidity Data.

@NDTV
  Scenario: Visit NDTV
    Given I Am In NDTV Home Page
    When I Click On Menu
    And Click On Weather Option In Nav Bar
    Then I Should Be In Weather Page
    When I Enter City Name In Search Box and Also Click On the Suggested CheckBox
    Then City Name With Temperature and Humidity Must Appear
 
 
    Scenario: Visit OpenWeather
     Given I Am In OpenWeather Home Page
    When I Search the City Name In SerchBox
    And Click On The Search Button
    And Click On the Suggested Option
    Then City Name With Temperature and Humidity Must Must Be Seen
    
    Scenario: Compare Data
    Given I Visited NDTV and OpenWeather Sites
    Then Compare Data
  	And Close Driver  
