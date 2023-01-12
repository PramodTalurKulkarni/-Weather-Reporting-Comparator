# -Weather-Reporting-Comparator
Problem Statement - Weather Reporting Comparator using TestNG Framework

Problem Statement - Weather Reporting Comparator 

Create a test automation framework with following capabilities : 

1. Can interact with web applications 

2. Can compare objects retrieved from 2 different UI Websites  

Focus area : The framework should compare weather information from different sources 

as under: 

Source 1 : The website https://www.ndtv.com/ 

Source 2 : The public weather https://openweathermap.org/ 

What to do? 

Phase 1 

1. Use any UI automation tool to automate https://www.ndtv.com/ 

2. Reach the weather section of the website 

3. Use the `Pin your city` section on the left of the screen to search & select any 

given city (for your case select Bangalore) 

4. Validate that the corresponding city is available on the map with  

Information like temperature & humidity 

Phase 2 

Use the https://openweathermap.org/  

In Search City put “Bangalore” and click on search. 

It will display temperature & humidity 

 

Phase 3 

1. Create a comparator that matches the temperature information from the UI in 

phase 1 against the UI response in phase 2 (ensure that comparison is done 

using same temperature unit and humidity unit) 

2. Build a variance logic, which should be configurable using an external file, that 

returns a success if temperature difference is within a specified range, else return 

a matcher exception 

Example Workflow(Just for reference) 

1. Visit ndtv website’s weather page and search for Bangalore 

2. Store weather object 1 w.r.t this Bangalore (e.g. temp value as 33 degree 

Celsius and humidity as 55%) 

3. Get response from the open weather  for Bangalore 

4. Store the response and build the weather object 2 

5. Specify the variance logic - for e.g. 5 degree celsius for temperature and 5 % for humidity 

6. Compare weather objects 1 and 2 along with the variance and mark tests as 

pass or fail based on comparator response 
