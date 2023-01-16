package com.api.battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void main(String[] args) {
      var bcrypt = new BCryptPasswordEncoder();
      System.out.println(bcrypt.encode("stella"));


    }
}

// marcelo -> $2a$10$z0Agwe00cbQuOuPyFiN1VOJ9eZVmKzK4OZES00v.kMmdv3xYy4WPS
// stella -> $2a$10$NOmuR/Re8/rA.fLcet7zX./ryVyi3pJ4w7rfqq8VH3lD0BREyeycG
