Sample **UrlShortener** in Java with the help of SpringBoot and Redis.

Step 1: Install Redis Server
use the following url and instructions to install redis on OS platforms 
[Redis Server](https://redis.io/download) download latest one.

Step 2: Clone this Repository. using `https://github.com/sreebasavamanju/UrlShortener.git`

Step 3: Navigate to cloned repository and run below commands.

           mvn clean install
           mvn eclipse:clean eclipse:eclipse
           mvn package
          
Step  4: run spring boot application using `mvn spring-boot:run`
            spring boot will run on default port 8080.

Step 5:now use `postman` or use `curl` to perform below operations.

      Sample Url Shorting:
      
      >"$ curl -vX POST localhost:8080/https://www.google.pl/search?q=tomasz+nurkiewicz"
      > POST /https://www.google.pl/search?q=tomasz+nurkiewicz HTTP/1.1
      > User-Agent: curl/7.30.0
      > Host: localhost:8080
      > Accept: */*
      >
      < HTTP/1.1 200 OK
      < Server: Apache-Coyote/1.1
      < Content-Type: text/plain;charset=ISO-8859-1
      < Content-Length: 28
      < Date: Sat, 23 Aug 2014 20:47:40 GMT
      <"http://mydomain.com/50784f51"
      
      Sample url Redirecting type below Url in browser you wil be redirected to the target url
      
Step 6: run the redirect url in your browser,

>http://mydomain.com/50784f51 replace `mydomain.com` to `localhost:8080`
        url will be like http://localhost:8080/50784f51

