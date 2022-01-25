package com.common.controller;

import com.common.module.user.dto.User;
import com.common.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/all")
    public String viewMessage()
    {
        return "Public Content";
    }

    @GetMapping("/json")
    public Object jsonObject()
    {
        Map<String,String> map = new HashMap<>();
        map.put("Test","Json");
        return map;
    }

    @GetMapping("/users")
    public Collection<User> users()
    {
       Collection<User> userList = new ArrayList<>();
       for(int i=1;i<=10;i++)
       {
           User user = new User();
           user.setId(i);
           user.setFirstName("demo_first_"+i);
           user.setEmail("demo_first_"+i+"@gmail.cm");
           userList.add(user);
       }
       return userList;
    }

}
