package com.sample.UrlShortener;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

@SpringBootApplication
@RestController
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}
	@Autowired private StringRedisTemplate redis;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void redirect(@PathVariable String id, HttpServletResponse resp) throws Exception {
        final String url = redis.opsForValue().get(id);
        if (url != null)
            resp.sendRedirect(url);
        else
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
 @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest req) {
        final String queryParams = (req.getQueryString() != null) ? "?" + req.getQueryString() : "";
        final String url = (req.getRequestURI() + queryParams).substring(1);
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(url)) {
            final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            redis.opsForValue().set(id, url);
            return new ResponseEntity<>("http://mydomain.com/" + id, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
