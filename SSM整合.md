# 实现spring+springmvc+mybatis的整合
## 1.这是我搭建的完整目录结构
![](https://github.com/zuojunZzz/ssm/blob/master/%E5%9B%BE%E7%89%87/SSM%E6%95%B4%E5%90%88%E7%BB%93%E6%9E%84.png)

## 2.具体代码
### 2.1整合所需要引入的依赖有
<!-- 单元测试 -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
    </dependency>

    <!-- 1.日志 -->
    <!-- 实现slf4j接口并整合 -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.1.1</version>
    </dependency>

    <!-- 2.数据库 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.37</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>c3p0</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.1.2</version>
    </dependency>

    <!-- DAO: MyBatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.3</version>
    </dependency>

    <!-- 3.Servlet web -->
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.5.4</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.6</version>
    </dependency>

    <!-- 4.Spring -->
    <!-- 1)Spring核心：spring-core，spring-beans，spring-context
           spring-context-support ,spring-expression-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>5.1.5.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-expression</artifactId>
        <version>5.1.5.RELEASE</version>
    </dependency>

    <!-- 2)Spring DAO层 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <!-- 3)Spring web -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <!-- 4)Spring test -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>

    <!-- Map工具类 -->
    <dependency>
      <groupId>commons-collections</groupId>
      <artifactId>commons-collections</artifactId>
      <version>3.2</version>
    </dependency>
### 2.2前端界面代码(很简单的)
```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <a href="account/findAll">测试</a>
<h1>测试表单</h1>
<form action="
account/save" method="post">
    姓名:<input type="text" name="name">
    金额:<input type="text" name="money">
    <input type="submit" value="保存">
</form>
</body>
</html>
```

### 2.3 domain(实体层)
<div style="color:red">在pom.xml中引入lombok依赖，然后在实体类上添加@Data注解，可省略set/get方法和toString方法</div>
```
import lombok.Data;

import java.io.Serializable;
/*
* 实体类
* */
@Data
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Double money;
}
```
### 2.4 controller(web层)
<div style="color:red">这是前后端数据交互的层，主要用到springMvc框架技术</div>
这里我只实现了插入数据和查询数据，修改数据和删除数据方法类似
```
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
/*
* 查询数据
* */
    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有账户。。。");
        //调用service层的方法，完成整合springmvc
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }
    /*
    * 插入数据
    * */
    @RequestMapping("/save")
        public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("表现层：插入信息到账户。。。");
            //调用service层的方法，完成整合springmvc
            accountService.saveAccount(account);
            response.sendRedirect(request.getContextPath()+"/account/findAll");
            return;
        }

}
```
### 2.5 dao层(也就是mapper层)
<div style="color:red">这里是实现后端与数据库连接的方法，主要运用到mybatis框架知识</div>
可以使用注解的方式写SQL代码(查询语句简单,且量少)，也可以在.xml文件中写SQL代码(推荐)
```
/*
* Mybatis框架
* */
@Repository
public interface AccountDao {

    /*查询账户的所有信息有*/
    //@Select("select * from account")
    public List<Account> findAll();

    /*保存账户信息*/
    //通过注解形式查询
  //  @Insert("insert into account (name,money) values(#{name},#{money})")
    public void saveAccount(Account account);
}
```
### 2.5.1 在.xml文件中编写SQL代码
#### 1.以下是sql代码的.xml文件的位置
<div style="color:red">注:SQL代码的.xml文件名要与dao中对应的类名一致,如：上述dao层是AccountDao,则要以AccountDao.xml命名</div>

![](https://github.com/zuojunZzz/ssm/blob/master/%E5%9B%BE%E7%89%87/SQLCode.png)
### 2.6 service层(springMVC框架技术)
####1.如下图<div style="color:red">接口部分编写方法，实现接口的类主要调用接口的方法，通过这样降低代码之间的耦合度，便于增加或减少方法</div>
![](https://github.com/zuojunZzz/ssm/blob/master/%E5%9B%BE%E7%89%87/servciceCode.png)

#### 2.AccountService代码如下
```
public interface AccountService {
    /*查询账户的所有信息有*/
    public List<Account> findAll();

    /*保存账户信息*/
    public void saveAccount(Account account);
}

```
#### 3.AccountServiceImpl代码如下
```
@Service("accountService")//将该类交给springIOC容器保管
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("(service)业务层：查询所有账户信息。。。");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("(service)业务层：保存账户信息。。。");
        accountDao.saveAccount(account);
    }
}
```
### 2.7 application.xml中的配置如下
<div style="color:red">注：该文件内主要写spring相关配置以及spring整合mybatis的相关配置</div>
```
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-3.0.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--
    spring就会去自动扫描base-package对应的路径或者该路径的子包下面的java文件，
    如果扫描到文件中带有@Service,@Component,@Repository,@Controller等这些注解的类，
    则把这些类注册为bean
    -->
    <context:component-scan base-package="com.jgsu">
        <!--配置哪些注解不扫描-->
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--spring整合mybatis框架-->

    <!-- .配置数据库相关参数properties的属性：${url} -->
    <context:property-placeholder location="classpath:config.properties"/>
    <!--1.配置连接池-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${driver}" />
        <property name="jdbcUrl" value="${url}" />
        <property name="user" value="${username}" />
        <property name="password" value="${password}" />
    </bean>
    <!--2.配置sqlSession工厂-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sessionFactory">
        <property name="dataSource" ref="dataSource"/>
        <!-- 配置MyBaties全局配置文件:SqlMapConfig.xml -->
        <property name="configLocation" value="classpath:SqlMapConfig.xml"/>
        <!--将mapper文件中的(所有.xml文件)AccountDao.xml映射进去-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <!--3.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中-->
    <bean id="mapperScanner" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--给出需要扫描Dao包-->
        <property name="basePackage" value="com.jgsu.dao"/>
        <!--注入SqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sessionFactory"/>
    </bean>

    <!--4.配置spring声明式事务管理器-->
      <!--配置事务管理器-->
        <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
            <property name="dataSource" ref="dataSource"/>
        </bean>
      <!--配置事务通知-->
       <tx:advice id="txAdvice" transaction-manager="transactionManager">
           <tx:attributes>
               <!--find方法打头的表示只读-->
               <tx:method name="find*" read-only="true"/>
               <tx:method name="*" isolation="DEFAULT"/>
           </tx:attributes>
       </tx:advice>
      <!--配置AOP增强-->
    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.jgsu.service.impl.*ServiceImpl.*(..))"/>
    </aop:config>
</beans>

```
### 2.7 log4j.properties
<div style="color:red">注:配置日志文件，便于打印日志</div>
```
log4j.rootLogger=INFO,Console,File  
#定义日志输出目的地为控制台
log4j.appender.Console=org.apache.log4j.ConsoleAppender  
log4j.appender.Console.Target=System.out  
#可以灵活地指定日志输出格式，下面一行是指定具体的格式
log4j.appender.Console.layout = org.apache.log4j.PatternLayout  
log4j.appender.Console.layout.ConversionPattern=[%c] - %m%n  

#文件大小到达指定尺寸的时候产生一个新的文件
log4j.appender.File = org.apache.log4j.RollingFileAppender  
#指定输出目录
log4j.appender.File.File = logs/ssm.log  
#定义文件最大大小
log4j.appender.File.MaxFileSize = 10MB  
# 输出所以日志，如果换成DEBUG表示输出DEBUG以上级别日志
log4j.appender.File.Threshold = ALL  
log4j.appender.File.layout = org.apache.log4j.PatternLayout  
log4j.appender.File.layout.ConversionPattern =[%p] [%d{yyyy-MM-dd HH\:mm\:ss}][%c]%m%n
```

### 2.8 config.properties
<div style="color:red">连接数据库的基本信息</div>
```
//config.properties中创建数据库连接路线
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/ssm
username=root
password=xxxx
```

### 2.9 springmvc.xml
<div style="color:red">编写springmvc相关配置</div>
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd
            http://www.springframework.org/schema/mvc
            http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

    <!--1.开启注解扫描，只扫描Controller注解-->
    <context:component-scan base-package="com.jgsu">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--2.配置视图解析器对象-->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--3.过滤静态资源-->
    <mvc:resources mapping="/css/**" location="/css/"/>
    <mvc:resources mapping="/images/**" location="/images/"/>
    <mvc:resources mapping="/js/**" location="/js/"/>

    <!--4.开启springmvc注解支持-->
    <mvc:annotation-driven/>
</beans>
```
  
<div style="color:red">源码地址:https://github.com/zuojunZzz/ssm</div>
========================


#### <p align="right" style="color:red">座右铭:日积月累才能拥有真本领，多思考，多实践</p>

##### <p align="right" style="color:red">作者:zuojunZzz</p>
