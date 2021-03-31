package com.bateng.web.controller;

import static com.bateng.utils.ToJsonString.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
/*import com.bateng.security.browser.entity.Role;
import com.bateng.security.browser.service.UserBiz;*/
import com.bateng.utils.ToJsonString;
import com.bateng.web.exception.UserNoException;
/*import com.bateng.security.browser.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;*/
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@CrossOrigin
@RestController
public class UserController {
   /* @Autowired
    private UserBiz userBiz;

    @GetMapping
    @ApiOperation(value = "用户查询测")
    public String query(@ApiParam(value = "用户参数") @RequestParam(name = "username", required = false) String username
            , Pageable pageable
    ) {
//        throw new UserNoException(12,"user is no");
        return JSONObject.toJSONString(userBiz.findAll(), new SerializeFilter[]{
                new PropertyPreFilter() {
                    @Override
                    public boolean apply(JSONSerializer jsonSerializer, Object o, String s) {
                        if (o.getClass() == Role.class)
                            return false;
                        return true;
                    }
                }
        }, SerializerFeature.DisableCircularReferenceDetect);
    }


    @GetMapping("/{id}")
    public User query2(@PathVariable(required = true) String id) {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");
        return user;
    }


    @PostMapping
    public String add(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(x -> {
                System.out.println(x);
                System.out.println(x.getDefaultMessage());
            });
        }


        System.out.println(toDisableCircularReferenceDetectString(user));
        user.setId(1);
        return JSONObject.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect);
    }


    @PutMapping("/{id}")
    public String update(@Valid @RequestBody User user, BindingResult errors) {
        System.out.println(toDisableCircularReferenceDetectString(user));
        return toDisableCircularReferenceDetectString(user);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        System.out.println(id);
    }*/
}
