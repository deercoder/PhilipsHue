README
====

Philips Hue(http://www2.meethue.com/en-us/), light strips or light that supports using RESTful API to control the lights, query the status, get and set state for the lights.

This repo is the JAVA implementation for using the RESTful API of Hue, this repo provides functions for:

* turn on/off lights
* set colors
* get light status
* parse the response of JSON object
* JSON parser/constructor for response
* PUT/POST/GET request for general HTTP request

It also depends on various `*.jar` like [okhttp](http://square.github.io/okhttp/), okio, moshi, json.

For RESTful API of Hue, check here: http://www.developers.meethue.com/documentation/getting-started
