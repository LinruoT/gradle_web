# gradle_web
这是[《Spring实战》第四版](https://book.douban.com/subject/26767354/)spring web的学习项目
## 部署
 * 克隆项目，用idea打开
 * 等待gradle下载依赖
 * 

## 技术
###SpringMVC
###Hibernate
###Spring Data JPA
###JWT
####缺点：不能jwt的token不能主动禁用
 ####1. 创建jwt类
 * JWTConfigurer: 为了添加filter，它将被应用到security config中
 * JWTFilter: jwt转换为Authentication， 并且用SecurityContextHolder.getContext().setAuthentication
 * CORSFilter: 允许跨域，添加Access-Control系列HTTP头
 * TokenProvider: （是Component）提供token和authentication互相转换的方法，配置secret等参数

####2. 修改security config
                 .csrf()
                 .disable()
                 
                 .and()
                 .apply(jwtConfigurer());
####3. 添加controller
 * JWTController(TokenProvider, AuthenticationManager在securityconfig配置passwordEncoder等): /api/auth POST，登录获取token
 * LoginVM: @RequestBody LoginVM 用户名、密码、记住我
###external configuration 外部化配置
配置保存在 /src/main/resources/billy.properties
在component类中获取配置 BillyServiceImpl @Value
在configuration类中获取配置 MongoConfig, RabbitMQConfig
###spring AOP
Encoreable 定义新功能接口， GdhAspect 定义切面
