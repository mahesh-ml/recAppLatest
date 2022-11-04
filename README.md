# recAppLatest



**Environment :**


Jdk 17 
Maven 
Spring boot
Docker
**
Run locally:**

mvn spring-boot:run

This command would launch application and the database would be local h2 db.
**
To access the recipe app**

first we need to create a user using below end points using postman or via curl request.
http://localhost:{PORT}/api/auth/register

with payload

{ "email":"test@gmail.com", "password":"test1234" }

where email is the email id of the user and password.

on successful request we must get a response with JWT token such as below

{ "jwt-token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJSZWNpcGUgQVBQL1JlY2lwZS9BQk4iLCJpYXQiOjE2Njc1NzUwNDEsImVtYWlsIjoidGVzdEBnbWFpbC5jb20ifQ.de197h3U86dURUe-BCaoGjBZqnUh-PiuK5iUJ-vxeI4" }

Using this JWT token as authorization header we can access below end points
GET -> http://localhost:{PORT}/recipe would list down all the available recipe.

POST -> http://localhost:{PORT}/recipe with below sample request payload

{ "vegetarian":false, "ingredientList": [ "eggs", "chicken" ], "cookingInstructions":"heat for 10 min in cooker", "suitableFor":22 }

PUT -> http://localhost:{PORT}/recipe/{recipeid} and payload request we can update a particular recipe

{ "vegetarian": true, "suitableFor": 3, "ingredientList": [ "rice", "lentils" ], "cookingInstructions": "boil and eat" }

DELETE http://localhost:{PORT}/recipe/{recipeid}

would delete a recipe with the given recipe id.

Please note that the auth header must contain the JWT token.

**Running in test environment**

Please run docker-compose up & the above command would launch mysql instance as docker container.
use below command mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=test"
access api as mentioned in previous steps.
