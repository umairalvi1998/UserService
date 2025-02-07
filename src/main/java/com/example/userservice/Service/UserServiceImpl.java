package com.example.userservice.Service;

import com.example.userservice.DTOs.SendEmailDto;
import com.example.userservice.Exceptions.InvalidPasswordException;
import com.example.userservice.Exceptions.SessionExpiredException;
import com.example.userservice.Exceptions.UserAlreadExistsException;
import com.example.userservice.Exceptions.UserNotFoundException;
import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import com.example.userservice.Respository.TokenRepository;
import com.example.userservice.Respository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;
    private KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper;

     public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,TokenRepository tokenRepository,KafkaTemplate kafkaTemplate,ObjectMapper objectMapper) {
         this.userRepository = userRepository;
         this.bCryptPasswordEncoder = bCryptPasswordEncoder;
         this.tokenRepository = tokenRepository;
         this.kafkaTemplate = kafkaTemplate;
         this.objectMapper = objectMapper;
     }

    @Override
    public User signUp(String name, String email, String password) throws UserAlreadExistsException, JsonProcessingException {
         Optional<User> userOptional = userRepository.findByEmail(email);
         if (userOptional.isPresent()) {
             throw new UserAlreadExistsException("User Already Exists with this email");
         }
         User user = new User();
         user.setName(name);
         user.setEmail(email);
         user.setHashedpassword(bCryptPasswordEncoder.encode(password));

        SendEmailDto emailDto = new SendEmailDto();
        emailDto.setTo(email);
        emailDto.setSubject("Welcome to our platform");
        emailDto.setBody("Welcome to our platform, we are glad to have you here.");
        emailDto.setName(name);

         //Publish an event inside kafka to send a welcome email to user.
        kafkaTemplate.send("Send-Email-Notification",
               objectMapper.writeValueAsString(emailDto));

        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
         Optional<User> userOptional = userRepository.findByEmail(email);

         if(userOptional.isEmpty()) {
             throw new UserNotFoundException("User Not Found");
         }

         User user = userOptional.get();

         if(!bCryptPasswordEncoder.matches(password, user.getHashedpassword())) {
             throw new InvalidPasswordException("Incorrect Password");
         }

         // Login Successful, generate a new Token
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);

        return savedToken;
    }

    private Token generateToken(User user) {
        LocalDateTime currentTime = LocalDateTime.now(); //issuedAt
        LocalDateTime expirationTime = currentTime.plusDays(30);
        Token token = new Token();
        token.setUser(user);
        token.setExpiredAt(expirationTime);
        token.setIssuedAt(currentTime);

        //token value is a randomly generated string of 128 characters
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        return token;
    }

    @Override
    public void logOut(String token) throws SessionExpiredException {

        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(token, false);
        if(tokenOptional.isEmpty()) {
            throw new SessionExpiredException("Session Expired");
        }

        Token tokenInDb = tokenOptional.get();
        tokenInDb.setDeleted(true);
        tokenRepository.save(tokenInDb);

    }

    public User validateToken(String token) throws SessionExpiredException {
         Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(token,false);

         if(tokenOptional.isEmpty()) {
             throw new SessionExpiredException("Session Expired");
         }

         return tokenOptional.get().getUser();
    }
}
