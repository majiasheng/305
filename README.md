# 305
# TODO
0 Because Programmers Start with 0 and we should do this first (move up as needed)
	[x]	notify user to login when they try to book a flight (in handler for booking form submission: get session, check if session("user")==null)
	[x]	add registration validation (use javax validation + hibernate validator)
	[x]	create a different registration portal for admin, i.e. use different registraion handler, and set the priviledge bit manually in db 
	[]	check email and password using jquery in front end
	[x]	customers - search flights
	[]	***query db for direct or indirect flights between a pair of cities for a given date and "approximate" time.***
	[]	reverse auctions - price customers are willing to pay for a seat
	[]	customers cancel an existing reservation

1 System Users
	[]	customers - make flight reservation
	[]	customer may partake in any number of flight reservation transactions
	[]	customer's rating should reflect how active a ticket buyer he or she is (+1 to customer's rating)

3.1 Manager Transactions
	[\]	Add, Edit and Delete information for an employee
	[]	Obtain a sales report for a particular month
	[]	Produce a comprehensive listing of all flights
	[]	Produce a list of reservations by flight number or by customer name
	[]	Produce a summary listing of revenue generated by a particular flight, destination city, or customer
	[]	Determine which customer representative generated most total revenue
	[]	Determine which customer generated most total revenue
	[]	Produce a list of most active flights
	[]	Produce a list of all customers who have seats reserved on a given flight
	[]	Produce a list of all flights for a given airport
	[]	Produce a list of all flights whose arrival and departure times are on-time/delayed 

3.2 Customer-Representative-Level Transactions
	[]	Record a reservation
	[]	Add, Edit and Delete information for a customer
	[]	Produce customer mailing lists
	[]	Produce a list of flight suggestions for a given customer (based on that customer's past reservations)

3.3 Customer-Level Transactions
	[x]	One-Way
	[]	Round-Trip
	[]	Multi-City
	[]	Domestic or International
	[]	Flexible Date/time
	[]	A customer's current reservations
	[]	Travel itinerary for a given reservation
	[]	A customer's current bid on a given reverse auction
	[]	The bid history for a given reverse auction
	[]	A history of all current and past reservations a customer has made
	[]	Best-Seller list of flights
	[]	Personalized flight suggestion list 

4 User Access Control
	[x]	Customer Representatives cannot perform manager-level transactions, but can read employee info except pay rate.
	[]	Customer Representatives should be able to record the receipt of an order from a customer.
	[x]	A customer cannot access to other customers' account information, or to any employee information. 

5 Utilities
	[x]	Allowing the manager to add and delete users
	[]	Backing up the database files
	[]	A comprehensive Help facility, including a topic-driven pull-down Help menu 

6 Other
    [] Prompt user to select customer representative after auction (default if no selection)

99 Notes
	[]	Reservation have booking fees, which is how your company makes money, and an associated customer representative


# Setup (Accurate as of November 13)
- Download maven and place the bin directory into the environment variables
- Download 'MySQL Community Server' from 'MySQL Community Downloads' (not MySQL Workbench)
- Proceed through download process until you hit the 'Select Product and Features Page'
	- Select 'MySql Servers' -> 'MySQL Server' -> latest version
	- Feel free to select any other products you may deem useful
	- Proceed to fill in user details and finish
- Setting up the database
	- log into thr mysql account and run the following commands (high-tech security)
		- create user 'ajaxadmin'@'localhost' identified by 'passwordajax305';
		- grant all privileges on ajax305.* to 'ajaxadmin'@'localhost';
		- CREATE DATABASE ajax305;
		- use ajax305;
		- source .\src\main\resources\localdump.sql
	- For Powershell users, may also run
		- cmd.exe /c "mysql -u ajaxadmin -p ajax305 < .\src\main\resources\localdump.sql"
	- For macOS or Linux, run the following command
		- mysql -u ajaxadmin -p ajax305 < .\src\main\resources\localdump.sql


# Using AJAX Program
- git clone out project at 'https://github.com/jiashengma/305'
	- To easily view our code -> Open Intellij and open the 'pom.xml' which will auto import everything
- Once again, we are using maven so to run
	- mvn clean tomcat7:run
	- head over to localhost / server and run gui application


# Possible error messages and corresponding solutions
	- 'Cannot establish database connection.'
		- Refer to # Setup -> 'Setting up the database'
	- Project does not compile under maven
		- Consider adding this block into plugins section of the pom.xml file
		    <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>

 # logining into db
 	mysql -u ajaxadmin -P ajax305
 	password: passwordajax305

 	verify tables with 'show tables;'
