# OOSE DEA Vodagone [![Build Status](https://travis-ci.org/meronbrouwer/vodagone.svg?branch=master)](https://travis-ci.org/meronbrouwer/vodagone) [![Coverage Status](https://coveralls.io/repos/github/meronbrouwer/vodagone/badge.svg?branch=master)](https://coveralls.io/github/meronbrouwer/vodagone?branch=master)

This repository contains a front-end for the final programming assignment
of the course OOSE-DEA at the [HAN University of Applied Sciences](https://www.han.nl/).

## Hosted version

Use this version if you do not want to install it locally:

[http://ci.icaprojecten.nl/vodagone/](http://ci.icaprojecten.nl/vodagone/)

## Enabling CORS headers in your JavaEE container

To use this Vodagone Client with your own Vodagone Server, you will need to enable CORS headers
in your JavaEE container.

## API

In general the API must conform the standards of a RESTful API. It will use HTTP methods and expects HTTP statuscodes in its response.

### Methods used

* GET: In case of acquiring one, or multiple resources.
* POST: In case of creating a resource.
* PUT: In case of modifying  a resource.
* DELETE: In case of deleting a resource.

### Response codes

The client will expect the following respond codes to be used

* 200: OK. A response to a successful GET, PUT or DELETE.
* 201: Resource has been created. A response to a successful POST.
* 400: Bad Request. Something is wrong with the request. This could be due to
a missing query-parameter for the token.
* 401: Unauthorized. Authorization has failed. This can happen if the user tried to log in, but supplied an invalid username/password.
* 403: Forbidden. The request was valid, but you have requested a resource for which are not authorized. This will probably mean you have provided a token that is invalid.
* 404: Not found. You have requested an endpoint that is not available.

### HATEOAS

Those that are aware of the concept HATEOAS might notice that this API is not HATEOAS. That is not a problem, HATEOAS is not within the scope of this exercise.

### Endpoints
The following endpoints are expected:

#### Login

```
url:    /login
method: POST
```

It will perform a request with an object in the body of the form:

```json
{
  "user": "meron",
  "password": "MySuperSecretPassword12341"
}
```

It will expect a response containing an object of the form:

```json
{
  "token": "1234-1234-1234",
  "user": "Meron Brouwer"
}
```

This token is then stored in LocalStorage and used for each following
request.

#### Abonnementen

##### Get all Abonnementen from the logged in User

To acquire a list of all Abonnementen from the logged in User:

```
url:    /abonnementen
method: GET
query parameter:  token
```

It will expect a response containing the complete list of all Abonnementen and the totalPrice:

```json
{
  "abonnementen": [
    {
      "id": 0,
      "aanbieder": "vodafone",
      "dienst": "Mobiele telefonie 100"
    },
    {
      "id": 1,
      "aanbieder": "vodafone",
      "dienst": "Mobiele telefonie 250"
    },
    {
      "id": 2,
      "aanbieder": "ziggo",
      "dienst": "Kabel-internet (download 300 Mbps)"
    },
  ],
  "totalPrice"  :42.37
}
```

* The field `aanbieder` should be a string and only contain either `"vodafone"` or `"ziggo"`.
* The field `totalPrice` should be in euro's and should be point separated.

##### Add an Abonnement

To add an Abonnement:

```
url:    /abonnementen
method: POST
query parameter:  token
```

The body will contain an object of the form:
```json
{                
  "id": 3,
  "aanbieder": "vodafone",
  "dienst": "Glasvezel-internet (download 500 Mbps)"                
}
```
It will expect a response containing the updated complete list of all Abonnementen and the updated totalPrice:

```json
{
  "abonnementen": [
    {
      "id": 0,
      "aanbieder": "vodafone",
      "dienst": "Mobiele telefonie 100"
    },
    {
      "id": 1,
      "aanbieder": "vodafone",
      "dienst": "Mobiele telefonie 250"
    },
    {
      "id": 2,
      "aanbieder": "ziggo",
      "dienst": "Kabel-internet (download 300 Mbps)"
    },
    {                 
      "id": 3,
      "aanbieder": "vodafone",
      "dienst": "Glasvezel-internet (download 500 Mbps)"                
    }
  ],
  "totalPrice"  :62.37
}
```

* The field `aanbieder` should be a string and only contain either `"vodafone"` or `"ziggo"`.
* The field `totalPrice` should be in euros and should be point separated.
##### Get a specific Abonnement from the logged in User

Notice that the Abonnementen above only contain a small part of all information
available. To acquire the complete Abonnement:

```
url:    /abonnementen/:id
method: GET
query parameter:  token
```

It will expect a response containing the complete Abonnement for the given id:

```json
{
  "id": 0,
  "aanbieder": "vodafone",
  "dienst": "Mobiele telefonie 100",
  "prijs" : "€5,- per maand",
  "startDatum": "2017-01-01",
  "verdubbeling": "standaard",
  "deelbaar": false,
  "status": "actief"
}
```

* The field `startDatum` should be a date and formatted as `yyyy-MM-dd`.
* The field `status` should be a string and only contain either `"opgezegd"`,  `"actief"` or `"proef"`.
* The field `verdubbeling` should be a string and only contain either `"standaard"`, `"verdubbeld"`, `"niet-beschikbaar"`.

##### Terminate an Abonnement

To terminate an Abonnement:

```
url:    /abonnementen/:id
method: DELETE
query parameter:  token
```

It will expect a response containing the complete Abonnement for the given id:
Because the Abonnement has been terminated, the field `status` should now read `opgezegd`.

```json
{
  "id": 0,
  "aanbieder": "vodafone",
  "dienst": "Mobiele telefonie 100",
  "prijs" : "€5,- per maand",
  "startDatum": "2017-01-01",
  "verdubbeling": "standaard",
  "deelbaar": false,
  "status": "opgezegd"
}
```

##### Upgrade an Abonnement

To upgrade an Abonnement:

```
url:    /abonnementen/:id
method: POST
query parameter:  token
```

The body will contain an object of the form:
```json
{
  "verdubbeling": "verdubbeld"
}
```

It will expect a response containing the complete Abonnement that has been upgraded:
Because the Abonnement has been upgraded, the field `verdubbeling` should now read `verdubbeld`. Furthermore, the field `price` should now show an increase of 50%.

```json
{
  "id": 0,
  "aanbieder": "vodafone",
  "dienst": "Mobiele telefonie 250",
  "prijs" : "€15,- per maand",
  "startDatum": "2017-01-01",
  "verdubbeling": "verdubbeld",
  "deelbaar": false,
  "status": "actief"
}
```

##### Get all available Abonnementen

To acquire a list of all available Abonnementen:

```
url:    /abonnementen/all
method: GET
query parameter:  token
query parameter:  filter
```

It will expect a response containing an array Abonnementen:

```json
[
  {
    "id": 0,
    "aanbieder": "vodafone",
    "dienst": "Mobiele telefonie 100"
  },
  {
    "id": 1,
    "aanbieder": "vodafone",
    "dienst": "Mobiele telefonie 250"
  },
  {
    "id": 2,
    "aanbieder": "ziggo",
    "dienst": "Kabel-internet (download 300 Mbps)"
  }
]
```

* The field `aanbieder` should be a string and only contain either `"vodafone"` or `"ziggo"`.

If the query parameter `filter` is non empty, only Abonnementen that contain the given filter string in either the `aanbieder` or `dienst` should be returned.

#### Abonnees

##### Get all Abonnees

To acquire a list of all Abonnees:

```
url:    /abonnees
method: GET
query parameter:  token
```

It will expect a response containing an array of all Abonnees:

```json
[
  {
    "id": 0,
    "name": "Meron",
    "email": "Meron.Brouwer@han.nl"
  },
  {
    "id": 1,
    "name": "Dennis",
    "email": "Dennis.Breuker@han.nl"
  },
  {
    "id": 2,
    "name": "Michel",
    "email": "Michel.Portier@han.nl"
  }
]
```

##### Share an Abonnement with an Abonnee

To share an Abonnement with an Abonnee:
```
url:              /abonnees/:id
method:           POST
query parameter:  token
```

The body should contain the id of the new Abonnement:
```json
{
  "id": 1
}
```

It will expect a 200 OK in the response.
