# Parcel API
- this is a sample api to highlight key practices when writing api's in spring boot
## Objective
- create Java Spring Boot API that calculates the cost of delivery of a parcel based on weight and volume
- rules can be added ie:
  - when weight exceeds 50kg: reject
  - When weight > 10kg: PHP 20 * Weight (kg)
  - rules have a priority name condition and cost

## Solution 
### Considerations
- basically the solution would be to create a parcel submission api
- a key point to consider is the Parcel Model because two of its key values would be derived 
  - and we don't need to save them in the db (namely volume and cost)
  - if we need to track transactions we can create a ledger for the payments 
  - but since we just compute based on rules we can just compute things on the fly
### Simplifying the solution
- the api will return a cost of 0 for those that will be rejected ( so I don't need to track status)
- let's not make get endpoints for routes that are not specifically needed

## TODO
1. create a seeder for the application for the initial rules
