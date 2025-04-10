package com.avinash.SequirityApp.SequirityApp.advices;


import com.avinash.SequirityApp.SequirityApp.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestControllerAdvice
public class GlobalExceptionHandler {

    //After apiError and customExcepion(after ApiResponse)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourseNotFound(ResourceNotFoundException exception)
    {

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();


        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> hanleAuthenticationException( AuthenticationException ex){

        List<String> subErrors = new ArrayList<>();
//        ApiError apiError = new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED,subErrors);
        ApiError apiError = new ApiError(ex.getMessage(),HttpStatus.UNAUTHORIZED,subErrors);

        System.out.println("===========================apierror=====================================");
        System.out.println(apiError);
        System.out.println("===========================apierror=====================================");
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> hanleJwtException( JwtException ex){

        List<String> subErrors = new ArrayList<>();
//        ApiError apiError = new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED,subErrors);
        ApiError apiError = new ApiError(ex.getMessage(),HttpStatus.UNAUTHORIZED,subErrors);

        System.out.println("===========================apierror=====================================");
        System.out.println(apiError);
        System.out.println("===========================apierror=====================================");
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }









    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception)           // => Here we are returning ErrorResponse as instance of ApiResponse.
    {

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();


        return  buildErrorResponseEntity(apiError);
    }












//listing all errors in an array, all errors come in single array

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception)
//    {
//
//        List<String> errors = exception
//                .getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(error -> error.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message(errors.toString())
//                .build();
//
//
//        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//    }






    //listing all errors in an array, all errors come in different  array
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception)
//    {
//
//        List<String> errors = exception
//                .getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(error -> error.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message("Input validation failed")
//                .subErrors(errors)
//                .build();
//
//
//        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception)
    {

        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .subErrors(errors)
                .build();


        return buildErrorResponseEntity(apiError);
    }








    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {

        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }



}

