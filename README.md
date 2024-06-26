# ApiTheaterKata
How to design pragmatic APIs

Let us consider an online theater booking system, composed of existing systems, as described below.
![Legacy system](./LegacySystem.jpg?raw=true "Legacy system")

* A legacy system done in C# on top of a SQL DB, ASP.Net (equivalent to Java with good old JSP's) 
* An existing Importer component can import external csv & Excel files directly to the DB with the same validations as the screens 
* We have some big DB tables that contain valuable sets of data about customers' behaviors
* We have competitors, some of which already have public API's
* From now, we have decided to extend preferably by adding new microservices 

# Iterations
## Iteration 1
Create a mobile application. Reuse the existing services.
New features: get all events & book a show

## Iteration 2
Duplication of business rules between "Legacy Importer" and "Legacy backend"
How to refactor that?

## Iteration 3
The business wants to introduce a waiting list (something brand new, never done before here) via a new REST API; 
How to design it good enough upfront? 

## Iteration 4
Competitor offers an endpoint to ```/recent``` to list the 10 most recent bookings and the business wants the same on top of our existing system.

## Iteration 5
Identify the booking seats workflow to implement Hypermedia Controls (HATEOAS)

## Iteration 6
The business wants to sell through external retaillers (B2B2C opportunity): 
* Open existing application to external consumers as a macro-service (split endpoints by bounded context)
* How to mature our API faster?

## Iteration 7
* The business requests one more booking type -> extend the enum in the JSON response (STANDARD, SUPERIOR, VIP, and now PREMIER).
* A developer suggests to remove the 'originID' field in the response because now it's always the same as another field (dossierID now always the same as originID).
* Someone put a 5MB text file in a text field (BOOM!) + After the 5MB text field, requirement to limit the text fields to <= 50 chars in requests.

## Iteration 8
We find a bug : Someone posted twice the same booking and it booked twice!

## Iteration 9
* DDoS: 1000 request per second! (BOOM!)
* Someone tells you they were able to access someone else's booking by typing random booking number in the path (OUCH!) 

## Other cases
## Case 1
A competitor offers data for sale and our data are better, so we have an opportunity to sell our data through an API -> offer direct access as well to our dataset without coupling.

## Case 2
The business wants to use our API for the mobile app but asks to make lots of changes of backend behaviors (editorial content) and of structure to reduce the bandwidth, eg remove 6 fields in the API request and remove 17 new fields to the API response -> Introduce a new (redundant) API for a different audience with different needs.

## Case 3 
Now that we are to launch another country, Legal says that we need to always have a currency and suggest we make the field 'currency' no more optional in request

## Case 4
Rename typo in 'cutsomer' in response (OUCH!)

## Case 5
Add 'affiliate link' field to request

## Case 6 
Remove a field in the request
