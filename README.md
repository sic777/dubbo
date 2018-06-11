# 工程结构说明

    base-framework                                              基础框架父工程
        |---common                                              通用工程(框架无关)
        |---database                                            数据库父工程
                |---database-redis                              Redis工程(框架无关)
                |---database-springboot-mybatis                 Mybatis工程
        |---demo                                                Demo父工程
                |---dubbo-springboot-demo                       Dubbo集成Springboot例子
                            |---dubbo-springboot-demo-api       Dubbo-SpringBoot公用Api包
                            |---dubbo-springboot-demo-client    Dubbo-SpringBoot服务消费者
                            |---dubbo-springboot-demo-server    Dubbo-SpringBoot服务提供者
        |---dubbo                                               Dobbo框架父工程
                |---dubbo-bean                                  Dubbo基本实体定义(框架无关)
                |---dubbo-common                                Dubbo通用工程(框架无关)
                |---dubbo-consumer-common                       Dubbo服务消费者通用工程(框架无关)
                |---dubbo-provider-common                       Dubbo服务提供者通用工程(框架无关)
                |---dubbo-springboot-common                     Dubbo-SpringBoot通用工程
                |---dubbo-springboot-consumer                   Dubbo-SpringBoot服务消费者
                |---dubbo-springboot-provider                   Dubbo-SpringBoot服务提供者
        |---logger                                              日志工程
        |---restful                                             Restful基础框架父工程
                |---restful-base                                Restful基本封装(框架无关)
                |---restful-netty                               基于Netty的Restful框架
                |---restful-restexpress                         基于restexpress的Restful框架(本身也基于Netty)
                |---restful-springboot                          基于SpringBoot的Restful框架
        |---utils                                               工具类(框架无关)


# 框架使用说明


**common工程说明**
    
    该工程是框架无关的
    
    主要是封装一些常见的，通用的枚举常量等。
    
    目前提供的有：
    1.Environment    环境枚举
    2.BaseConstant   系统基础常量,主要是默认配置文件的文件夹名称、文件名称等   
                     默认Map配置文件：application.properties
                     默认Json配置文件：configure.json
                     默认文件夹名称：config
                     默认的环境JVM参数变量：environment
    3.ErrorMsg       提供基本的错误信息语句（客户端服务端通用）
    4.CurrentEnvironment 初始化当前环境/获取当前环境 *重要

**database工程说明**
   
   
1. **database-redis**
     
     
        该工程是框架无关的
     
        一、操作入口:
        1. Redis.STRINGS
        2. Redis.SORTSETS
        3. Redis.SETS
        4. Redis.LIST
        5. Redis.KEYS
        6. Redis.HASH
        
        二、初始化入口：
        1. Redis.init();//系统根据环境加载默认的Json配置文件或者Map配置文件初始化Redis
        2. Redis.init(Properties props);
           配置文件模板：
           redis.host=127.0.0.1
           redis.port=6379
           redis.password=testPwd
           redis.poolMaxTotal=100
           redis.poolMaxIdle=10
           redis.poolMaxWaitMillis=10000
           redis.poolTestOnBorrow=false
           redis.poolTestOnReturn=false
           redis.poolTestWhileIdle=false
        3. Redis.init(JSONObject json);
           配置文件模板：
           {
             "redis" : {
               "host" : "127.0.0.1",
               "port" : 6379,
               "password" : "testPwd",
               "poolMaxTotal" : 100,
               "poolMaxIdle" : 10,
               "poolMaxWaitMillis" : 10000,
               "poolTestOnBorrow" : false,
               "poolTestOnReturn" : false,
               "poolTestWhileIdle" : false
             }
           }
           
  
2. **database-springboot-mybatis**
   
   
       该工程基于SpringBoot框架

       集成springboot与mybatis,主要是引入jar包。



**dubbo工程说明**
    
 1.**dubbo-bean工程说明**
    
    
     该工程是框架无关的
     
     1.封装响应实体：RpcResponse<T>
       所有的Rpc服务均以该实体作为返回值
     2.定义响应状态枚举：RpcResponseStatus
       SUCCESS 成功
       FAILURE 失败  
     3.定义Rpc异常：RpcException
     4.定义异常枚举RpcExceptionType，包括：
       UNKNOWN              未知错误
       PARAM_INVALID        参数校验非法
       INVALID_ACCESS       权限不足,禁止访问
       RESOURCE_NOT_FOUND   资源未找到
       SERVICE_EXCEPTION    服务端发生异常
       CLIENT_EXCEPTION     客户端发生异常
           
 2.**dubbo-common工程说明**
 
     该工程是框架无关的
      
     1.定义了Dubbo应用配置基类SuperDubboApplicationConfig,
       并且提供DubboApplicationConfigManager构造类,使子工程能够调用该方法进行配置实体构造
     2.定义了Dubbo注册中心基类SuperDubboRegistryConfig(支持多注册中心),
       并且提供DubboRegistryConfigManager构造类,使子工程能够调用该方法进行配置实体构造
     3.定义Dubbo常量:
       DubboConstant(主要是配置文件前缀)
       DubboDefaultValue(主要是Dubbo配置的一些默认值)
     4.在枚举包com.sic777.dubbo.common.enums下定义了常见的Dubbo配置可选项
     5.定义一个同步的处理接口，用于容器(可以是Spring也可以是其他框架)在进行容器初始化的时候,
       执行一些操作（执行时机在容器启动前before()和容器启动后after()）
     6.扩展了Dubbo的日志，支持log4j2
     
 3.**dubbo-consumer-common工程说明**
 
      该工程是框架无关的
        
      1.定义了Dubbo服务消费者缺省配置基类SuperDubboConsumerConfig,
        并且提供DubboConsumerConfigManager构造类,使子工程能够调用该方法进行配置实体构造
      2.修改了Dubbo原生的Mock机制(集群容错触发时)，使服务消费者在调用远程服务发生错误时，返回统一的数据并打印error日志.
        扩展类：
        MockClusterInvokerExtend
        MockClusterWrapperExtend
        返回数据：
        new RpcResponse<>(RpcExceptionType.CLIENT_EXCEPTION, e.getMessage());
        //RpcExceptionType.CLIENT_EXCEPTION标识为客户端错误
        //e.getMessage()为Dubbo远程RpcException抛出的异常错误信息
       3.扩展客户端过滤器ConsumerExceptionFilterExtend(主要是进行异常拦截)，该类暂未实现任何功能
        
 4.**dubbo-provider-common工程说明**
 
      该工程是框架无关的
      
      1.定义了Dubbo服务提供者缺省配置基类SuperDubboProviderConfig,
        并且提供DubboProviderConfigManager构造类,使子工程能够调用该方法进行配置实体构造
      2.定义了Dubbo服务提供者协议配置基类SuperDubboProtocolConfig(支持多协议),
        并且提供DubboProviderConfigManager构造类,使子工程能够调用该方法进行配置实体构造
      3.扩展服务端过滤器ProviderExceptionFilterExtend(主要是进行异常拦截)，
        主要是在服务端发生异常的时候，返回统一的数据并打印error日志.
        返回数据：
        new RpcResult(new RpcResponse<>(RpcExceptionType.SERVICE_EXCEPTION, msg));
        //RpcExceptionType.SERVICE_EXCEPTION标识位服务端错误
        //msg为服务端发生的异常错误信息
        
 5.**dubbo-springboot-common工程说明**
 
      该工程基于SpringBoot框架
      
      1.继承Dubbo应用配置基类SuperDubboApplicationConfig,并且标注为@Configuration,
        使Spring容器能够扫描到该配置并且自动注入到Bean里边。
        配置前缀：dubbo.application
      2.继承Dubbo注册中心配置基类SuperDubboRegistryConfig，并且标注为@Configuration,
        使Spring容器能够扫描到该配置并且自动注入到Bean里边。
        配置前缀：dubbo.registry
        *支持多注册中心
      3.在com.sic777.dubbo.common.springboot包定义了Spring相关的类，
        DubboConfigurationApplicationContextInitializer类，在容器初始化的时候扫描Dubbo注解的路径：dubbo.scan
        提供一个自定义Spring Bean注册的工具SpringBeanRegistry
      4.在DubboCommonInfoLogger类里将Dubbo基本配置信息打印出来
        
 6.**dubbo-springboot-consumer工程说明**
 
      该工程基于SpringBoot框架
      
      1.继承Dubbo服务消费者缺省配置基类SpringBootDubboConsumerConfig,并且标注为@Configuration,
        使Spring容器能够扫描到该配置并且自动注入到Bean里边。
        配置前缀：dubbo.consumer
      2.在DubboConsumerInfoLogger将Dubbo消费者的配置信息打印出来
      3.定义启动类DubboConsumerLauncher供ISV调用
        
 7.**dubbo-springboot-provider工程说明**
 
      该工程基于SpringBoot框架

      1.继承Dubbo服务提供者缺省配置基类SuperDubboProviderConfig,并且标注为@Configuration,
        使Spring容器能够扫描到该配置并且自动注入到Bean里边。
        配置前缀：dubbo.provider
      2.继承Dubbo服务提供者协议配置基类SuperDubboProtocolConfig,并且标注为@Configuration,
        使Spring容器能够扫描到该配置并且自动注入到Bean里边。
        配置前缀：dubbo.protocol
        *支持多协议
      3.在DubboProviderInfoLogger将Dubbo提供者的配置信息打印出来
      4.定义启动类DubboProviderLauncher供ISV调用
        
**logger工程说明**

     该工程是框架无关的
        
        目前主要是引入了log包(主要以log4j2为主，其他日志框架暂未测试过)

**restful工程说明**
    
 1.**restful-base工程说明**
 
     该工程是框架无关的
     
     1.定义一个控制器的基类SuperRestfulController，封装一些常用的方法：
       int getPermission();//获取当前accessToken对应的权限
       String getAccessTokenString();//获取accessToken字符串
       abstract JSONObject getAccessTokenData();//获取accessToken缓存数据和接口权限，注意：该抽象方法由上层框架封装的时候进行实现，非ISV实现
       void funcValidateNotNull(Object obj, String key);//校验对象不为null
       void funcValidateNotEmpty(String str, String key);//校验字符串不为空
       JSONObject generateReturnList(List<?> dataList, int count);//构造响应列表对象
       JSONObject generateReturnId(String idKey, Object idValue);//构造id响应对象
       JSONObject generateDefaultReturnId(Object idValue);构造默认的响应id,key为"id"
       void rest200(Object obj);//默认以Rest200异常拦截的方式返回数据,上层框架可以根据不同的框架重写该方法选择返回200的方式。
       void rest400(long code, String msg);//400异常
       void rest403(long code, String msg);//403异常
       void rest404(long code, String msg);//404异常
       void exceptionParamInvalid(String details);//参数校验异常(400异常)
       void exceptionObjectOrKeyNull(String key);//key或者对象不存在异常(400异常)
       void exceptionValueEmpty(String key);//值为空异常(400异常)
       void exceptionValueNull(String key);//值为Null异常(400异常)
       void exceptionResourceNotFound(String details);//资源未找到异常(404异常)
     2.定义常量HttpConstants,主要是请求/响应的时候对头部进行封装，如Content-Type、Access-Control-Allow-Origin
       定义常量MicroConstants,主要是一些key的定义,包括：
           /**
            * 返回前端错误key
            */
           public static final String ERROR_FLAG = "error";
           /**
            * 总数标识
            */
           public static final String COUNT_FLAG = "count";
           /**
            * 列表标识
            */
           public static final String LIST_FLAG = "list";
           /**
            * accessToken对象属性标识
            */
           public static final String ACK_ATTRIBUTE_FLAG = "ACK_ATTRIBUTE";
           /**
            * 鉴权标识
            */
           public static final String ACCESS_TOKEN_FLAG = "Access-Token";
           /**
            * 接口权限标识
            */
           public static final String PERMISSION_FLAG = "REST_PERMISSION";
     3.com.sic777.microservice.exception包下主要定义了Rest异常类、Rest异常响应实体、和一些错误枚举
        错误枚举RESTFUL_ERROR，主要包括（建议ISV定义的错误码由xxx1010开始）：
            /**
             * 参数校验错误
             */
            PARAM_INVALID(4001000, ErrorMsg.PARAM_INVALID),
            /**
             * key或者对象不存在
             */
            OBJECT_NULL(4001001, ErrorMsg.OBJECT_NULL),
            /**
             * 参数值为空
             */
            VALUE_EMPTY(4001002, ErrorMsg.VALUE_EMPTY),
            /**
             * 参数值为NULL
             */
            VALUE_NULL(4001003, ErrorMsg.VALUE_NULL),
            /**
             * 禁止访问
             */
            INVALID_ACCESS(4031000, ErrorMsg.INVALID_ACCESS),
            /**
             * Access-Token 为空
             */
            ACCESS_TOKEN_VALUE_EMPTY(4031001, String.format("'%s' value must not be empty", MicroConstants.ACCESS_TOKEN_FLAG)),
            /**
             * 资源未找到
             */
            RESOURCE_NOT_FOUND(4041000, "%s"),
            /**
             * URI找不到
             */
            URI_NOT_FOUND(4041001, "requested path: '%s' not found"),
            /**
             * 请求方法不支持
             */
            METHOD_NOT_ALLOWED(4051000, "method '%s' not allowed,requested path: '%s'"),
            /**
             * 服务器发生异常
             */
            SERVICE_EXCEPTION(5031001, ErrorMsg.SERVICE_EXCEPTION);
     4.定义异常类：
             Rest200            其实为正常响应给前端,并非异常,只是以异常的方式拦截返回。
             Rest400Exception
             Rest403Exception
             Rest404Exception
             Rest503Exception
             RestException      异常基类
     5.定义异常响应实体RestExceptionResponse
             异常返回的数据格式：
             {
                "error":{
                    "code":错误码,
                    "message":"错误信息"
                }
             }
             正常响应的数据格式：
             {
                "id":"123456",
                "name":"测试"
             }
     6.定义接口鉴权拦截器基类SuperAuthInterceptor
            主要提供给上层框架在封装接口鉴权时使用
            
     7.定义了权限注解Permission
            可以使用在控制器的类上，也可以使用在控制器的方法上
            在权限控制粒度上（权限注解优先级：方法注解 > 类注解）：
                 1. 类无,方法无 无需校验
                 2. 类无,方法有 以方法为主
                 3. 类有,方法无 以类为主
                 4. 类有,方法有 以方法为主
                 
       定义了基本的权限值RestPermission：只包括
            int ANYBODY = 1;//任何人可访问该Rest接口
      8.定义了一个鉴权SPI接口，由ISV实现，由上层框架封装的时候调用
            Spring体系可以直接实现该接口，然后上层框架用@Autowired注入
            非Spring体系可以使用Java SPI服务发现机制使用
        TwoTuple<JSONObject, Integer> parse(String accessToken) throws Exception;
        //返回值可使用Tuple.tuple("缓存的AccessToken数据",权限值);
      9.定义工具类
        RestValidateUtil 校验工具类，会抛出相对应的异常给前端
        RpcResponseUtil  检验rpc响应是否存在异常，存在异常会返回相对应的通用异常给前端
             
 2.**restful-netty工程说明**

     该工程基于netty框架
     暂未实现

 3.**restful-restexpress工程说明**
 
     该工程基于restexpress(netty)框架
     未实现
     
 4.**restful-springboot工程说明**    
    
     该工程基于SpringBoot框架
  
     1.定义RestExceptionHandler统一处理异常
     2.定义RequestMethodFilter对请求头、响应头、跨域等进行处理，在FilterConfig设置为拦截/*
     3.在MicroServerLogger中将容器的配置打印出来
     4.在WebConfig中定义拦截器、及定义请求和响应的Json框架为fastJson
     5.在AuthInterceptor实现鉴权拦截器基类SuperAuthInterceptor，并将token缓存的数据和解析出来的权限携带给控制器
     6.定义RestfulController继承SuperRestfulController
       实现了基类的抽象方法JSONObject getAccessTokenData();
       重写了基类的方法rest200(Object obj);改为使用PrintWriter输出
       新增方法：
       HttpServletRequest getRequest();//获取request对象
       HttpServletResponse getResponse();//获取response对象
       Object getAttribute(String attributeKey);//获取request属性数据
       String getParameter(String key);//获取参数
     7.定义了一个统一错误响应控制器RestExceptionController,如请求方法不支持
     8.定义了启动类MicroserviceLauncher
     
**utils工程说明**
    
    该工程是框架无关的
    
    提供常用的工具类，主要包括：
    
    1.PropertiesUtil  加载配置文件
    2.StringUtil      字符串工具
    3.DateFormatTools 日期格式化工具
    4.JsonUtil        Json工具
    5.ObjectUtil      对象判空工具
    6.SystemCheckUtil 防痴呆检测工具
    7.Version         Jar包版本检测工具
    8.HostUtil        获取本机ip地址工具
    9.PermissionUtil  权限算法工具
    10.Http           Http工具
                      使用时,建议初始化连接池PoolHttpConnectionManager.instance.init(int max, int per)
    11.com.sic777.utils.generator包下的ID生成工具
    12.com.sic777.utils.encrypt包下的加密算法工具
    13.com.sic777.utils.container包下的容器生成工具
    14.com.sic777.utils.bean包下的实体转换工具
    
    
**demo工程说明**
    
    关于服务提供者/消费者需要引入什么jar包、具体怎么使用，直接参考该项目即可。
    
# 使用建议
    
1. **客户端**


    1.在使用restful框架的时候,建议子类也定义一个restful控制器的基类，如BaseController extends SuperRestfulController
    2.建议不要定义Mock，统一由MockClusterInvokerExtend处理
    3.在stub中如果参数校验出现错误,建议直接返回new RpcResponse<>(RpcExceptionType rpcExceptionType, String errorMsg)的方式进行抛出
    4.在调用Rpc服务的时,返回的数据如果不做校验一般不知道成功与否,建议使用RpcResponseUtil工具进行校验,如果服务调用失败,会自动返回相对应的异常给上层应用


2. **服务端**    
    
    
    1.建议在暴露服务的接口实现里不要吃掉异常，直接抛出统一由ProviderExceptionFilterExtend处理
    2.服务端需要抛出错误时,使用new RpcResponse<>(RpcExceptionType rpcExceptionType, String errorMsg)的方式进行抛出
    3.服务端需要返回数据时,使用new RpcResponse<>(T t)进行返回
    4.服务端需要返回boolean值时,使用new RpcResponse<>(RpcResponseStatus status)替代。

# 注意

    1.当使用到Spring体系的框架时候,ISV的扫描路径需要手动配置
