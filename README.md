[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)]()


                                                                     ▄              ▄
                                                                    ▌▒█           ▄▀▒▌
                                                                    ▌▒▒█        ▄▀▒▒▒▐
                                                                   ▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
                                                                 ▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
                                                               ▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
                 ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄                         ▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌
               ▄▀░░░░░░░░░░░░▄░░░░░░░▀▄                       ▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
               █░░▄░░░░▄░░░░░░░░░░░░░░█                      ▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌
               █░░░░░░░░░░░░▄█▄▄░░▄░░░█ ▄▄▄                  ▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌
        ▄▄▄▄▄  █░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██                ▀▄▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐
        ██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██                ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌
         ▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██               ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐
           ▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██                ▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌
              ▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██                ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐
              ▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀                  ▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌
          ░░░█▀▀█████████▀▀▀▀████████████▀                      ▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀
      ░░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀                          ▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀
                                                                      ▒▒▒▒▒▒▒▒▀▀▀





# 工程结构说明

    base-framework                                              基础框架父工程
        |---common                                              通用工程(框架无关)
        |---database                                            数据库父工程
                |---database-redis                              Redis工程(框架无关)
                |---database-springboot-mybatis                 Mybatis工程
                |---database-mongo                              MongoDB工程
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

# 使用建议
    
1. **客户端**


        1.在使用restful框架的时候,建议子类也定义一个restful控制器的基类，如BaseController extends SuperRestfulController
        
        2.建议不要定义Mock，统一由MockClusterInvokerExtend处理
        
        3.在stub中如果参数校验出现错误,建议直接返回new RpcResponse<>(RpcExceptionType rpcExceptionType, String errorMsg)的方式进行抛出
        
        4.在调用Rpc服务的时,返回的数据如果不做校验一般不知道成功与否,建议使用RpcResponseUtil工具进行校验,如果服务调用失败,会自动返回相对应的异常给上层


2. **服务端**    
    
    
        1.建议在暴露服务的接口实现里不要吃掉异常，直接抛出统一由ProviderExceptionFilterExtend处理
        
        2.服务端需要抛出错误时,使用new RpcResponse<>(RpcExceptionType rpcExceptionType, String errorMsg)的方式进行抛出
        
        3.服务端需要返回数据时,使用new RpcResponse<>(T t)进行返回
        
        4.服务端需要返回boolean值时,使用new RpcResponse<>(RpcResponseStatus status)替代。

# 注意
    1.当前接口版本为：/v1
      已经定义在抽象控制器BaseController下，子控制器直接定义后面的uri即可，无需再定义/v1/...
      
    2.生成的apidoc文档不要提交到git/svn，在本地需要忽略掉。