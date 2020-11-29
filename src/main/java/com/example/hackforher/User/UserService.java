package com.example.hackforher.User;

import com.example.hackforher.Exception.*;
import com.example.hackforher.User.Models.SignInRequest;
import com.example.hackforher.User.Models.SignUpRequest;
import com.example.hackforher.Utils.Enums.Status;
import com.example.hackforher.Utils.JwtUtils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Value("${auth.expiration}")
    private String tokenExpiration;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.getUserByUsername(username);

        if(!user.isPresent())
            throw new NotFoundException(150,"no user with this username= "+username);

        return user.get();
    }

    public ResponseEntity<?> login(SignInRequest request) throws BadCredentialsException,NotFoundException{
        Authentication authentication;
        try{
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        }
        catch (org.springframework.security.authentication.BadCredentialsException ex){
            throw new BadCredentialsException(152,"bad credentials");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user =(User)authentication.getPrincipal();
        return fillResponseWithUserData(user);
    }
    public ResponseEntity<?> createUser(SignUpRequest signUpRequest) {

        if(userRepository.getUserByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new ResourceExistException(115,"username already exist !");
        }

        if(userRepository.getUserByPhone(signUpRequest.getPhone()).isPresent())
            throw  new ResourceExistException(116,"phone already exist !");

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        var user =new User(signUpRequest);
        user =userRepository.save(user);
        return fillResponseWithUserData(user);
    }

    private ResponseEntity<?> fillResponseWithUserData(User user) {
        Map<String,Object> responseData=new HashMap<>();
        responseData.put("token", jwtUtils.generateToken(user));
        responseData.put("expiresIn",tokenExpiration);
        responseData.put("user",user);
        var response=new ApiResponse<>(Status.SUCCESS,responseData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> updateUser(User updatedUser,User user) {

        if(updatedUser.getPassword() != null ){
            if(updatedUser.getPassword().length()<8)
                throw new BadRequestException("password should be 8+ characters", HttpStatus.BAD_REQUEST.value());
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        user.merge(updatedUser);
        userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    public ResponseEntity<?> getUserByID(UUID userId) {
        var user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(404,"no user with this id"));

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
