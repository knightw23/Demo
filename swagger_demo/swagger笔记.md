# swagger笔记
1. 第一步：添加jar包
```xml
	<!-- swagger2 文档 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
 	</dependency>
```

2. 第二步：添加配置代码
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration //让Spring来加载该类配置
@EnableSwagger2 //启用Swagger2
public class Swagger2 {

    //如果需要多个文档，多加该方法就行
    @Bean
    public Docket test() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swagger测试接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rock"))//配置需要扫描的包
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Rock工作文档")
                //创建人信息
                .contact(new Contact("rock","","knightw23@163.com"))
                //描述
                .description("工作中遇到的需求记录，如果你有好的例子，请联系：knightw23@163.com")
                //版本
                .version("1.0").build();
    }
}
```

3. 第三步：测试代码
实体类代码
```java
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class TestInfo {

    @ApiModelProperty(value = "自增主键")
    private long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
controller代码：
```java
import com.rock.req.TestInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
@Api(value = "swagger测试接口")
@RestController
@RequestMapping(value = "/Swagger")
public class SwaggerController {

    @ApiOperation(value = "查询全部")
    @RequestMapping(value = "/getByName/name/{name}", method = RequestMethod.GET)
    public String getByName(@PathVariable("name") String name) {
        System.out.println(name);
        return "/getByName";
    }

    //@ApiIgnore//使用该注解忽略这个API
    @ApiOperation(value = "删除全部")
    @RequestMapping(value = "/getTestInfo", method = RequestMethod.POST)
    public TestInfo getTestInfo(@RequestBody TestInfo testInfo) {
        System.out.println(testInfo.toString());
        return testInfo;
    }
}
```

4. 第四步：启动项目
访问：http://127.0.0.1:8080/项目访问路径（没有配置就不用加）/swagger-ui.html

5. Swagger常用注解
| 注解 | <center>用法</center> |
|--------|--------|
|@Api|用在类上，说明该类的作用。可以标记一个Controller类做为swagger 文档资源     |
|@ApiOperation|用在方法上，说明方法的作用，每一个url资源的定义|
|@ApiModel|用在实体类上，描述一个实体|
|@ApiModelProperty|用在实体的属性上，描述一个属性|






















