package com.example.authentication.Controller;

import com.example.authentication.Common.ResponseStatus;
import com.example.authentication.Constant.Constant;
import com.example.authentication.Request.UserRequest;
import com.example.authentication.Response.UserResponse;
import com.example.authentication.Security.CookieUtil;
import com.example.authentication.Security.JwtUtil;
import com.example.authentication.domain.User;
import com.example.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) throws IOException {

        UserResponse userResponse = new UserResponse();
        ResponseStatus responseStatus = new ResponseStatus();

        String username = userRequest.getUserName();
        String password = userRequest.getPassword();
        String redirect = userRequest.getRedirect();

        userResponse.setRedirect(redirect);

        List<User> userList = userService.getAllUsers();

        Optional<User> matchAccount = userList.stream()
                .filter(userList1 -> userList1.getUserName().equalsIgnoreCase(username))
                .filter(userList2 -> userList2.getPassword().equalsIgnoreCase(password))
                .findAny();

        if (matchAccount.isPresent()){

            String token = JwtUtil.generateToken(Constant.SIGNING_KEY, username);
            ResponseCookie responseCookie = CookieUtil.create(Constant.JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

            responseStatus.setSuccess(true);
            responseStatus.setMessage("login success");
            userResponse.setStatus(responseStatus);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .body(userResponse);
        }
        responseStatus.setSuccess(false);
        responseStatus.setMessage("login failed");
        userResponse.setStatus(responseStatus);

        return ResponseEntity.ok().body(userResponse);


    }




}
