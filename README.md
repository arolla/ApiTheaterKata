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

## Iteration 2
The business wants to introduce a waiting list (something brand new, never done before here) via a new REST API; 
How to design it good enough upfront? 

## Iteration 3
Competitor offers an endpoint to ```/recent``` to list the 10 most recent bookings and the business wants the same on top of our existing system.

## Iteration 4
Competitor offers a multi-criteria search, so we need to do it too (but better for SEO) 

## Iteration 5
Identify the booking seats workflow to implement Hypermedia Controls (HATEOAS)

## Iteration 6
The business wants to sell through external retaillers (B2B2C opportunity): Open existing application to external consumers 
* as a macro-service (split endpoints by bounded context)
* How to mature our API faster?

## Iteration 7
The business requests one more booking type -> extend the enum in the JSON response (STANDARD, SUPERIOR, VIP, and now PREMIER). A developer suggests to remove the 'originID' field in the response because now it's always the same as another field (dossierID now always the same as originID).

## Next iterations
## Iteration 7
A competitor offers data for sale and our data are better, so we have an opportunity to sell our data through an API -> offer direct access as well to our dataset without coupling.

## Iteration 8
The business wants to use our API for the mobile app but asks to make lots of changes of backend behaviors (editorial content) and of structure to reduce the bandwidth, eg remove 6 fields in the API request and remove 17 new fields to the API response -> Introduce a new (redundant) API for a different audience with different needs.

