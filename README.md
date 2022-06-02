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
- all weight is assumed to be in kg
- all length width and height are in cm
- the api will return a cost of 0 for those that will be rejected
  - moreover reject can be a 400 error
- let's not make get endpoints for routes that are not specifically needed
- added a seeder for the initial set of rules


## TODO
1. since it is not in the base requirements the following can be quickly done to improve or add features:
   - add more functionalities to the RuleController to easily manage rules
     - for now only add is needed.
     - the seeder can also be modified for initial rules and it can be changed to read from a source

## sample parcel creation calls: 
#### (these are the scenarios considered in the seeder)
1. Reject parcel - Weight exceeds 50kg
  ```
    curl --location --request POST 'localhost:8080/api/v1/parcels' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "weight": 100,
    "height": 3,
    "width": 3,
    "length": 3
    }'
   ```
2. Heavy Parcel - Weight exceeds 10kg
  ```
    curl --location --request POST 'localhost:8080/api/v1/parcels' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "weight": 11,
    "height": 3,
    "width": 3,
    "length": 3
    }'
   ```
3. Small Parcel - Volume is less than 1500 cm^3
  ```
    curl --location --request POST 'localhost:8080/api/v1/parcels' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "weight": 9,
    "height": 5,
    "width": 2,
    "length": 100
    }'
   ```
4. Medium Parcel - Volume is less than 2500 cm^3
  ```
    curl --location --request POST 'localhost:8080/api/v1/parcels' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "weight": 9,
    "height": 5,
    "width": 1,
    "length": 100
    }'
   ```
5. Large Parcel - Volume is or exceeds 2500 cm^3 
  ```
    curl --location --request POST 'localhost:8080/api/v1/parcels' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "weight": 9,
    "height": 5,
    "width": 5,
    "length": 100
    }'
   ```


