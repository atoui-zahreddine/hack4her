package com.example.hackforher.User;

import com.example.hackforher.Exception.ApiResponse;
import com.example.hackforher.Exception.BadCredentialsException;
import com.example.hackforher.Exception.NotFoundException;
import com.example.hackforher.Exception.ResourceExistException;
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

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    @Value("${auth.expiration}")
    private String tokenExpiration;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user=userRepository.getUserByEmail(email);

        if(!user.isPresent())
            throw new NotFoundException(150,"no user with this email= "+email);

        return user.get();
    }

    public ResponseEntity<?> login(SignInRequest request) throws BadCredentialsException,NotFoundException{
        Authentication authentication;
        try{
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
            );
        }
        catch (org.springframework.security.authentication.BadCredentialsException ex){
            throw new BadCredentialsException(152,"bad credentials");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String,String> responseData=new HashMap<>();
        responseData.put("token", jwtUtils.generateToken((User)authentication.getPrincipal()));
        responseData.put("expiresIn",tokenExpiration);
        var response=new ApiResponse<>(Status.SUCCESS,responseData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<?> createUser(SignUpRequest signUpRequest) {

        if(userRepository.getUserByEmail(signUpRequest.getEmail()).isPresent()){
            throw new ResourceExistException(115,"email already exist !");
        }
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        var user =new User(signUpRequest);
        user =userRepository.save(user);
        var response=new ApiResponse<>(Status.SUCCESS,user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
