# Test-Plan-Pipeline
A centralized system to track a project's system test cases

How it works:
Running the program will present the user with an empty dashboard with a panel for test plans on the left and one for test cases on the right.
In the top left corner, there is a small button labeled "File" that the user clicks to access a dropdown menu to load projects' test plans. 
Clicking the "Load Test Plan(s)" button will allow the user to browse to their input CSV file. For demonstration I recommend loading 
"test-files/Projects.txt". The initial tests being displayed will be the set of all failing tests across all the loaded-in projects.
The test cases field will be populated with their ID, type, and which project they belong to. Both passing and failing tests from a particular project
can be viewed by clicking the dropdown box next to the label "Current Test Plan" and then selecting the project's name. "Wolfscheduler" for example, if 
the user is following the demonstration. Test cases can be selected by clicking on their row in the test plans panel which will then populate the
test case panel with all the case's details. At the bottom of the test case panel updated actual results can be recorded as testing is done and the status of 
the test case can be updated to passing or failing. From the test plans panel, the selected case can be moved up, down, to the front, or to the back of 
the list within the project to reflect priority. It can also be removed or more cases can be added as needed. The user can continue to switch between projects and their test cases or
work from the list of all failing cases until they reach a stopping point. At this point, the current version of the project test cases can be output to a CSV file from the same "File"
dropdown menu as they were loaded in from. This saves the user's progress so that it can be reloaded into the program to continue work another day.

How to run it:
Download the project in an IDE and run the SystemTestPlanGUI file found in the src/edu/ncsu/csc216/stp/view/ui directory.
