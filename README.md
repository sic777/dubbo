[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)]()


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

# 数据格式说明

    ​		数据返回格式有两种:

1. FIXED

    ```
       当code为0时候为成功，其他情况为失败，
       除了鉴权失败(403)、服务不可用(503)、URL NOT FOUND(404)、Method Not Allow(405)等错误，
       其他业务异常的HTTP状态码均为200
    ```
         
   
   ```json
     {
     "code" : "状态码",
     "message" : "描述信息",
     "data" : "响应数据"
     }
   ```

2. DYNAMIC

  ```
         只有当HTTP 状态码为200时，返回正确的数据
         当HTTP为非200时，抛出错误信息
   ```
       
   ```json
     1.HTTP 200
     {
     "id" : "",
     "字段1" : "",
     "字段2" : ""
     }
     
     2.非HTTP 200
     {
     "error" :
    	 {
     		"message" : "错误信息",
     		"code" : "错误码"
     	 }
     }
   ```


# 错误码集合

###  **参数校验相关错误码**

| 错误码     |       错误描述                        |
| ------- | ----------------------------------|
| 10000   |       param invalid,details:'%s'  |
| 10001   |       '%s' must not be null       |
| 10002   |       '%s' value must not be empty|
| 10003   |       '%s' value must not be null |

### **权限相关错误码**

| 错误码         | 错误描述                                   |
| ----------- | -------------------------------------- |
| 4031021     | invalid access                         |
| 20001       | 'Access-Token' value must not be empty |

### **资源未找到相关错误码**

| 错误码           | 错误描述                           |
| ------------- | ------------------------------ |
| 30000         | requested path: '%s' not found |
| 30001         |                                |

### **不支持的操作相关错误码**

| 错误码             | 错误描述                                   |
| --------------- | -------------------------------------- |
| 40000           | Method {%s} Not Allowed,URI {'%s'}     |


### **服务不可用相关错误码**

| 错误码  | 错误描述                |
| -----| ------------------- |
| 50000| service unavailable |



# 使用建议
    
1. **客户端**


        1.在使用restful框架的时候,建议子类也定义一个restful控制器的基类，如BaseController extends SuperRestfulController
        
        2.建议不要定义Mock，统一由MockClusterInvokerExtend处理
        
        3.在stub中如果参数校验出现错误,建议直接返回new RpcResponse<>(RpcExceptionType rpcExceptionType, String errorMsg)的方式进行抛出
         4.在调用Rpc服务的时,返回的数据如果不做校验一般不知道成功与否,建议使用RpcResponseUtil工具进行校验,如果服务调用失败,会自动返回相对应的异常给上层


2. **服务端**    
    
    
        1.建议在暴露服务的接口实现里不要吃掉异常，直接抛出统一由ProviderExceptionFilterExtend处理
        
        2.服务端需要抛出错误时,使用new RpcResponse<>(RpcExceptionType rpcExceptionType, String errorMsg)的方式进行抛出
        
        3.服务端需要返回数据时,使用new RpcResponse<>(T t)进行返回,
          不需要返回数据时(只返回成功的结果),使用new RpcResponse<>()
  

# 注意

```
    1.接口版本已经定义在抽象控制器BaseController下，子控制器直接定义后面的uri即可
      
    2.生成的apidoc文档不要提交到git/svn，在本地需要忽略掉。
```    
    
    
    

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



