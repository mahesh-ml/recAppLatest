**RecipeApp**

This application allows to manange recipe services. 

User can perform below CRUD opeartions.

    create recipe
    update an existing recipe
    delete a recipe
    and list all the recipe

**OpenAPI definition**

http://{HOST}:{PORT}/swagger-ui/index.html

Environment :

    Jdk 17 
    Maven 
    Spring boot
    Docker

Run locally:

**mvn spring-boot:run** 

This command would launch application and the database would be local h2 db.

To access the recipe app

1) first we need to create a user using below end points using postman or via curl request.

**http://{HOST}:{PORT}/api/auth/register** 

with payload

{
"email":"test@gmail.com",
"password":"test1234"
} 

where email is the email id of the user and password.

on successful request we must get a response with JWT token such as below 

**{
"jwt-token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJSZWNpcGUgQVBQL1JlY2lwZS9BQk4iLCJpYXQiOjE2Njc1NzUwNDEsImVtYWlsIjoidGVzdEBnbWFpbC5jb20ifQ.de197h3U86dURUe-BCaoGjBZqnUh-PiuK5iUJ-vxeI4"
}** 

2) Using this JWT token as authorization header we can access below end points

**GET** ->  http://{HOST}:{PORT}/recipe would list down all the available recipe.

**POST** -> http://{HOST}:{PORT}/recipe with below sample request payload 

**{
"vegetarian":false,
"ingredientList": [
"eggs",
"chicken"
],
"cookingInstructions":"heat for 10 min in cooker",
"suitableFor":22
}**

**PUT** -> http://{HOST}:{PORT}/recipe/{recipeid} 
and payload request we can update a particular recipe

**{
        "vegetarian": true,
        "suitableFor": 3,
        "ingredientList": [
            "rice",
            "lentils"
        ],
        "cookingInstructions": "boil and eat"
}**

**DELETE** http://{HOST}:{PORT}/recipe/{recipeid}

would delete a recipe with the given recipe id.

Please note that the auth header must contain the JWT token.


**SEARCH**  http://{HOST}:{PORT}/recipe/search

{
"searchCriteria": [
{
"key": "vegetarian",
"value": "true",
"operation": "EQUAL"
}
]

}


**Running in test environment**
1) mvn clean install
2) docker build . -t recipe  .
   alternatively  run mvn spring-boot:build-image -Dmaven.test.skip=true ( using buildpacks create docker image) 
2) docker-compose up &


**Prod Deployment :**

Docker containers can be deployed in kuberneter clusture or deployed to servers using CI/CD pipelines.

