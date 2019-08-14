## Root Sample
Designed to solve problem from: https://gist.github.com/dan-manges/1e1854d0704cb9132b74

built using:

java version    1.8.0_211

maven version   3.6.1

### Usage
build/test:
 
 `mvn clean build`

to run built code:

`java -jar root-sample-1.0.jar <input file>`


### discussion

Though it isn't necessary to solve the problem, I thought it was important to perform compilation via a 
package manager so that running/testing/building the program was easily repeatable, and all dependancies could be easily
accounted for in the pom.xml for the program. 

For modeling the problem's data, I chose to create a list of Driver objects, which each model a single driver's 
historical activity. A driver can have many Trip objects, which each model one trip's duration and distance. In this way 
the Trip object loses the information of start and end time after being created, but that information wasn't needed to 
solve the problem and could easily be changed if needed. Similarly, implementation for each of these units was also 
separated into the Trip and Driver classes, as well as the DriverDataReader class which is responsible for parsing the 
input strings into the list of drivers mentioned above.     

The goal of these separations was to make my code modular, allowing for much more specific unit testing and 
troubleshooting while building the program; as well as making the code's framework easier to extend as most concerns 
are logically separated from each other. For example,  Driver and Trip objects don't need to be changed if we choose to 
change the program to accept times in AM/PM format instead of 24 hour, that implementation is only present in the 
DataReader. Another example of modularity I decided on was removing the file opening implementation  from 
DriverDataReader and having that exist in the programs entry point, Main. This allows the data source to more easily be 
swapped for another depending on requirements.    

For testing, I tried to cover any obvious edge cases of each object's methods. I probably could have done more examples 
for different cases or sets of data, but I didn't want to be redundant or over think the problem. While testing 
DriverDataReader, I learned a bit about redirecting System.out and capturing the output and was very happy with the 
results. It was important to me to integrate testing into the build process to provide immediate feedback if 
a unit test is to find a bug, or break in the future, preventing problems from traveling downstream.

Other specific notes:

 * I used IntelliJ formatting and a bit of boilerplate generation from the IDE as well. 
 * In `DriverDataReader.loadData()`, I wanted to avoid traversing the list of drivers each time I needed to record a 
        new trip, so I used a Map with driver name as the key, value being the corresponding driver object. After data 
        loading is done the driver objects are saved to a list.