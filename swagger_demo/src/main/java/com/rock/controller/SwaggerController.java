package com.rock.controller;

import com.rock.req.TestInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Swagger")
@Api(value = "/Swagger", description = "swagger测试接口")
public class SwaggerController {

    @ApiOperation(value = "查询全部", httpMethod = "GET")
    @RequestMapping(value = "/getByName/name/{name}", method = RequestMethod.GET)
    public String getByName(@PathVariable("name") String name) {
        System.out.println(name);
        return "/getByName";
    }

    //@ApiIgnore//使用该注解忽略这个API
    @ApiOperation(value = "删除全部", httpMethod = "POST")
    @RequestMapping(value = "/getTestInfo", method = RequestMethod.POST)
    public TestInfo getTestInfo(@RequestBody TestInfo testInfo) {
        System.out.println(testInfo.toString());
        return testInfo;
    }
}
