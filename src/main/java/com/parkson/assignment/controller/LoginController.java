package com.parkson.assignment.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
public class LoginController {
  private String clientId;
  private String secret;

  @Autowired
  public LoginController(
      @Value("${security.jwt.client-id}") final String clientId,
      @Value("${security.jwt.client-secret}") final String secret) {
    this.clientId = clientId;
    this.secret = secret;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginPage(
      @RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout,
      Model model) {
    String errorMessge = null;
    if (error != null) {
      errorMessge = "Username or Password is incorrect !!";
    }
    if (logout != null) {
      errorMessge = "You have been successfully logged out !!";
    }
    model.addAttribute("errorMessge", errorMessge);
    return "login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String fetchToken(@RequestParam String username, @RequestParam String password) {

    RestTemplate restTemplate = new RestTemplate();

    String credentials = clientId + ":" + secret;
    String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.add("Authorization", "Basic " + encodedCredentials);

    HttpEntity<String> request = new HttpEntity<String>(headers);

    String access_token_url = "http://localhost:8080/oauth/token";
    access_token_url += "?username=" + username;
    access_token_url += "&password=" + password;
    access_token_url += "&grant_type=password";
    // access_token_url += "&redirect_uri=http://localhost:8090/";
    ResponseEntity<String> response =
        restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

    System.out.println("Access Token Response ---------" + response.getBody());

    return "welcome";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout=true";
  }
}
