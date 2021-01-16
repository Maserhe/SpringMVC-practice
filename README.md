# SpringMVC

**springMVC**

**博客地址**[传送门](https://maserhe.top/articles/2021/01/15/1610702633293.html)

MVC: 模型 视图 控制器

- MVC是模型（Mode、视图view）、控制器（Controller）的简写，是一种软件设计规范

- 是将业务逻辑、数据、显示分离的方法来组织代码。
- MVC主要作用是**降低了视图与业务逻辑间的双向偶合。**
- MVC不是一种设计模式，**MVC是一种架构模式**。当然不同的MVC存在差异

Jsp: 职责不单一，即负责页面展示，也负责部分逻辑控制，**不好**

## 1.**职责分析：**

### 1.1Controller：控制器 

- 取得表单数据 

- 调用业务逻辑 

- 转向指定的页面 

### 1.2 Model：模型 

- 业务逻辑 
- 保存数据的状态 

### 1.3 View：视图

- 显示页面

## 2. 开始 (TMD 环境搭建了好久 )

### 2.1 依赖

```xml
<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.2</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
        </dependency>

    </dependencies>
```

### 2.2 配置springmvc-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-4.2.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
        http://www.springframework.org/schema/task
        http://www.springframework.org/schema/task/spring-task-4.2.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="controller"/>
    <!-- 让Spring MVC不处理静态资源 -->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
    在spring中一般采用@RequestMapping注解来完成映射关系
    要想使@RequestMapping注解生效
    必须向上下文中注册DefaultAnnotationHandlerMapping
    和一个AnnotationMethodHandlerAdapter实例
    这两个实例分别在类级别和方法级别处理。
    而annotation-driven配置帮助我们自动完成上述两个实例的注入。
    -->
    <mvc:annotation-driven />
    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>


</beans>
```

### 2.3 web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--1.注册DispatcherServlet-->
    <servlet>

        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <!--关联一个springmvc的配置文件:【servlet-name】-servlet.xml-->
        <init-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>

        <!--启动级别-1-->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--/ 匹配所有的请求；（不包括.jsp）-->
    <!--/* 匹配所有的请求；（包括.jsp）-->
    <servlet-mapping>
      <servlet-name>springmvc</servlet-name>
      <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

## 3. 使用注解

**这个就没那么麻烦了，也不用实现什么接口了，直接注解**

web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
          http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--1.注册DispatcherServlet-->
    <servlet>

        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <!--关联一个springmvc的配置文件:【servlet-name】-servlet.xml-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>

        <!--启动级别-1-->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--/ 匹配所有的请求；（不包括.jsp）-->
    <!--/* 匹配所有的请求；（包括.jsp）-->
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

springmvc-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-4.2.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
        http://www.springframework.org/schema/task
        http://www.springframework.org/schema/task/spring-task-4.2.xsd">

    <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
    <context:component-scan base-package="controller"/>

    <!-- 让Spring MVC不处理静态资源  css, js, mp3 等等-->
    <mvc:default-servlet-handler />
    <!--
    支持mvc注解驱动
    在spring中一般采用@RequestMapping注解来完成映射关系
    要想使@RequestMapping注解生效
    必须向上下文中注册DefaultAnnotationHandlerMapping
    和一个AnnotationMethodHandlerAdapter实例
    这两个实例分别在类级别和方法级别处理。
    而annotation-driven配置帮助我们自动完成上述两个实例的注入。
    -->
    <mvc:annotation-driven />

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>


</beans>
```

**测试类**

```xml
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 2:12
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model) {

        // 封装数据
        model.addAttribute("msg", "Hello,SpringMVCAnnocation, Maserhe!!!!");

        return "hello"; // 会被视图解析器处理
    }
}
```

### 3.1 必须配置的三大件

- 处理器
- 映射器
- 视图解析器

## 4、Controller 及 RestFul 

### 4.1、控制器Controller 

- 控制器复杂提供访问应用程序的行为，通常通过接口定义或注解定义两种方法实现。 
- 控制器负责解析用户的请求并将其转换为一个模型。 
- 在Spring MVC中一个控制器类可以包含多个方法 
- 在Spring MVC中，对于Controller的配置方式有很多种

### 4.2、实现Controller接口 

Controller是一个接口，在org.springframework.web.servlet.mvc包下，接口中只有一个方法；

```java
//实现该接口的类获得控制器功能 
public interface Controller { 
//处理请求且返回一个模型与视图对象 
    ModelAndView handleRequest(HttpServletRequest var1, HttpServletResponse var2) throws Exception; 
}
```

**缺点**

一个控制器只有一个方法，如果多个方法，则需要定义多个Controller,定义的方式比较麻烦。

### 4.3 RestFul风格

**概念** Restful就是一个资源定位及资源操作的风格。不是标准也不是协议，只是一种风格。基于这个风格 设计的软件可以更简洁，更有层次，更易于实现缓存等机制。 

**功能** 资源：互联网所有的事物都可以被抽象为资源 资源操作：使用POST、DELETE、PUT、GET，使用 不同方法对资源进行操作。 分别对应 添加、 删除、修改、查询。 

**传统方式操作资源** ：通过不同的参数来实现不同的效果！方法单一，post 和 get

http://127.0.0.1/item/queryItem.action?id=1 查询,GET

 http://127.0.0.1/item/saveItem.action 新 增,POST

 http://127.0.0.1/item/updateItem.action 更新,POST

 http://127.0.0.1/item/deleteItem.acti on?id=1 删除,GET或POST 

**使用RESTful操作资源 **： 可以通过不同的请求方式来实现不同的效果！如下：

**请求地址一样，但是功能 可以不同！**

 http://127.0.0.1/item/1 查询,GET

 http://127.0.0.1/item 新增,POST 

http://127.0.0.1/item 更新,PUT 

http://127.0.0.1/item/1 删除,DELETE

**在Spring MVC中可以使用 @PathVariable 注解，让方法参数的值对应绑定到一个URI模板变量上**

```java
@Controller
public class ControllerTest03 {

    @RequestMapping("add/{a}/{b}")
    public String add(@PathVariable int a, @PathVariable int b, Model model) {

        model.addAttribute("msg", "结果是" + String.valueOf(a + b));
        return "test";
    }
}
```

请求就变成了 add/1/2 不用再使用原来的那种

**优点**

- 使路径变得更加简洁； 
- 获得参数更加方便，框架会自动进行类型转换。 
- 通过路径变量的类型可以**约束访问参数**，如果类型不一样，则访问不到对应的请求方法，如这 里访问是的路径是/commit/1/a，则路径与方法不匹配，而不会是参数转换失败。

Spring MVC 的 @RequestMapping 注解能够处理 HTTP 请求的方法, 比如 GET, PUT, POST, DELETE 以 及 PATCH。 

**所有的地址栏请求默认都会是 HTTP GET 类型的。**

 方法级别的注解变体有如下几个： 组合注解

```java
@GetMapping 
@PostMapping 
@PutMapping 
@DeleteMapping 
@PatchMapping
```

@GetMapping 是一个组合注解 它所扮演的是 **@RequestMapping(method =RequestMethod.GET) 的一个快捷方式。**

## 5. 跳转与重定向

- 可以自己设定，请求和跳转的页面，那样就不需要**视图解析器了**

```java
@Controller
public class ControllerTest01 {

    @RequestMapping("/maserhe/t1")
    public String test0(Model model) {

        model.addAttribute("msg", "这里是直接跳转的");
        return "/WEB-INF/jsp/test.jsp";
    }
    @RequestMapping("/maserhe/t2")
    public String test1(Model model) {

        model.addAttribute("msg", "这里是也是直接跳转的");

        return "forward:/WEB-INF/jsp/test.jsp";
    }

    @RequestMapping("/maserhe/t3")
    public String test2(Model model) {

        model.addAttribute("msg", "这里是也是直接跳转的");

        return "redirect:/test1.jsp";
    }

}
```

### 5.1 处理前端数据 （对象或者字段）

```java
@Controller
@RequestMapping("/user")
public class ControllerTest04 {

    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model) {
        // 1. 接受前端的参数
        System.out.println("输入的用户名是： " + name);
        // 2. 将结果返回给前端
        model.addAttribute("msg", name);

        return "test";
    }

    // 传参是一个对象，表单域必须要和对象中的中的属性名一致，不然参数会为null
    @RequestMapping("/t2")
    public String test2(User user) {

        System.out.println(user);
        
        return "test";
    }
}
```

**数据显示到前端**

- ModelAndView 实现Controller接口,喜欢用这个。
- Model                 用注解时比较喜欢用这个。
- ModelMap         继承了LinkedHashMap,所以他又LInkedHashMap的所有功能。

### 5.2 处理乱码

SpringMVC中，再web.xml中配置，不用自己写过滤器了。

**警告** 别忘了过滤器的 / 和 / * 的区别

```xml
<filter>
    <filter-name>encoding</filter-name>
    <filterclass>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
    <param-name>encoding</param-name>
    <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <!--如果处理jsp 需要替换成/ -->
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

## 6. JSON

前后端分离时代

后端部署后端：提供接口，提供数据

前端独立部署：负责渲染后端的数据

**JSON 语法是 JavaScript 对象表示法语法的子集。**

- 对象表示为键值对，数据由逗号分隔 

-  花括号保存对象 

- 方括号保存数组  

**JSON 键值对**是用来保存 JavaScript 对象的一种方式，和 JavaScript 对象的写法也大同小异，键/值对组 合中的键名写在前面并用双引号 "" 包裹，使用冒号 : 分隔，然后紧接着值：

```js
var user = {
    name:"maserhe",
    age:20,
    pwd:"123456"
}
```

Controller返回数据， 比较好用的工具

Jackson 和 fastJson

Jackson 最新依赖

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.1</version>
</dependency>
```

### 6.1**使用JackSon**

```java
@Controller
public class UserController {

    @RequestMapping(value = "/t1", produces = "application/json;charset=utf-8")
    @ResponseBody // 不会走视图解析器， 直接返回字符串。，
    public String json1() throws JsonProcessingException {

        // Jackson中 有一个 ObjectMapper
        // 创建一个对象
        User user = new User("Maserhe", 21, "男");

        return new ObjectMapper().writeValueAsString(user);

    }
```

上面还是有点麻烦，可以在spingmvc的配置文件上添加一端消息处理JackSon乱码

```xml
 <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean
                    class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean
                    class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean
                            class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

**@Controller** 是需要经历视图解析器的。

**@RestController**  会直接返回字符串。

@Responsebody 是配合 @Controller 来使用的

### 6.2 使用 FastJson (阿里巴巴)

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.75</version>
</dependency>
```

## 7. 全部配置

### 7.1 web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
          http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--Dispatcher-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>

        <!--启动级别-->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>


    <!--乱码过滤-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>

        <init-param>
           <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>

    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
</web-app>
```

### 7.2 applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="spring-dao.xml"/>
    <import resource="spring-service.xml"/>
    <import resource="spring-mvc.xml"/>
    
</beans>
```

### 7.3 spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置整合mybatis -->
    <!-- 1.关联数据库文件 与database建立连接-->
    <context:property-placeholder location="classpath:database.properties"/>


    <!--2. 连接池
        dbcp: 半自动化操作， 不能自动连接
        c3p0: 自动化操作，
        druid: hikari
    -->

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--关闭连接后 不自动commit-->
        <property name="autoCommitOnClose" value="false"/>
        <property name="checkoutTimeout" value="10000"/>
        <!--连接失败， 重尝试次数-->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>


    <!--3. SqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>

    </bean>

    <!--配置 dao 接口的 扫描 包-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入 sqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--需要新的包-->
        <property name="basePackage" value="dao"/>
    </bean>

</beans>
```

### 7.4 spring-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">


    <!--1. 扫描 service 下面的 所有包-->
    <context:component-scan base-package="service"/>

    <!--2. 将我们的 所有 业务类 注入到 Spring, 可以通过 配置 或者 注解来实现-->
    <bean id="BookServiceImpl" class="service.BookServiceImpl">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <!--3. 事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>

    </bean>

    <!--4. aop 事务 支持！！ -->



</beans>
```

### 7.5 spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-4.2.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
        http://www.springframework.org/schema/task
        http://www.springframework.org/schema/task/spring-task-4.2.xsd">


    <!--1, 注解驱动-->
    <mvc:annotation-driven/>
    <!--2. 静态资源过滤-->
    <mvc:default-servlet-handler/>
    <!--3. 扫描包: controller -->
    <context:component-scan base-package="controller"/>
    <!--4. 视图解析器-->

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

### 7.6 mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    
    <typeAliases>
        <package name="pojo"/>
    </typeAliases>

    <mappers>
        <mapper class="dao.BookMapper"/>
    </mappers>
</configuration>
```

### 7.7 databse.properties

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSl=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=123456
```

### 7.8 Maven依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>ssmBuild</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <!--依赖 junit 数据库驱动 连接池 mybatis mybatis-spring jsp servlet springmvc -->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.22</version>
        </dependency>

        <!--数据库连接池-->
        <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.5</version>
        </dependency>

        <!--servlet-api-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>


        <!--jsp-->

        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <!--jstl-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!--spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.2</version>
        </dependency>

        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>

        <!--mybatis 与 spring 整合-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>

        <!--spring jdbc-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.2</version>
        </dependency>
    </dependencies>

    <!--静态资源导出-->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>

                <filtering>true</filtering>
            </resource>


            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>

        </resources>

    </build>


</project>
```

## 8. 拦截器

自定义拦截器： 实现HandlerInterceptor

```java
public class MyInterceptor implements HandlerInterceptor {

    // return true 执行下一个拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 放行判断
        // 判断用户是否已经登陆
        // 如果session中没有 就转发到 登陆界面
        if (request.getRequestURI().contains("login")) return true;
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) return true;
        else request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截后---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("清理-------------");
    }
}
```

applicationContext.xml

```xml
<!--配置拦截器-->

<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <bean class="config.MyInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```

## 9 .文件上传

maven依赖

```xml
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

from表单

```xml
<%--二进制文件流的形式进行上传文件--%>
<form enctype="multipart/form-data" method="post">
  <input type="file" name="file">
  <input type="submit" value="上传">
</form>
```

applicationContext.xml

```xml
<!--注册文件上传bean-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">

</bean>
```

MVC为文件上传提供了直接的支持，这种支持是依赖 即插即用 的MultipartResolver实现类

CommonsMultipartResolver

```java
@RequestMapping("/upload")
public String upload(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest request) {

    // 上传文件
    try {
        file.transferTo(new File(file.getOriginalFilename()));
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("文件上传失败");
    }
    System.out.println("上传成功");
    return "redirect:/index.jsp";
}
```