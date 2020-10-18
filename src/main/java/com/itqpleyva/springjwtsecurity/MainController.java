package com.itqpleyva.springjwtsecurity;

import com.itqpleyva.springjwtsecurity.JwtManagement.Jwt;
import com.itqpleyva.springjwtsecurity.Models.AuthenticationRequest;
import com.itqpleyva.springjwtsecurity.Models.AuthenticationResponse;
import com.itqpleyva.springjwtsecurity.Services.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myuserdatilService;

    @Autowired
    private Jwt jwt;
    
    @RequestMapping("/home")
    public String home() {

        return "Hello from MainController";
    }

    @RequestMapping(value="/authenticate", method=RequestMethod.POST)
    public ResponseEntity<?> requestMethodName( @RequestBody AuthenticationRequest authrequest) throws Exception {

   
        try {
            authenticationManager.authenticate( 
            
                new UsernamePasswordAuthenticationToken(authrequest.getUser(), authrequest.getPassword())
                
                );
        } catch (BadCredentialsException e) {
            
            throw new Exception("username or password incorrect");
        }
    
        final UserDetails userDetails = myuserdatilService.loadUserByUsername(authrequest.getUser());
        final String token = jwt.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
    }
    
}