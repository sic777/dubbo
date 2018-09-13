[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)]()

# 工程结构说明

    base-framework                                      基础框架父工程
        |---common                                      通用工程(框架无关)
        |---database-redis                              Redis工程(框架无关)
        |---database-springboot-mybatis                 Mybatis工程
        |---database-mongo                              MongoDB工程
        |---dubbo-bean                                  Dubbo基本实体定义(框架无关)
        |---dubbo-common                                Dubbo通用工程(框架无关)
        |---dubbo-consumer-common                       Dubbo服务消费者通用工程(框架无关)
        |---dubbo-provider-common                       Dubbo服务提供者通用工程(框架无关)
        |---dubbo-springboot-common                     Dubbo-SpringBoot通用工程
        |---dubbo-springboot-consumer                   Dubbo-SpringBoot服务消费者
        |---dubbo-springboot-provider                   Dubbo-SpringBoot服务提供者
        |---logger                                      日志工程
        |---restful-base                                Restful基本封装(框架无关)
        |---restful-netty                               基于Netty的Restful框架
        |---restful-restexpress                         基于restexpress的Restful框架(本身也基于Netty)
        |---restful-springboot                          基于SpringBoot的Restful框架
        |---demo                                        Demo父工程
            |---dubbo-springboot-demo                   Dubbo集成Springboot例子
                    |---dubbo-springboot-demo-api       Dubbo-SpringBoot公用Api包
                    |---dubbo-springboot-demo-client    Dubbo-SpringBoot服务消费者
                    |---dubbo-springboot-demo-server    Dubbo-SpringBoot服务提供者

# 数据格式说明

    ​		数据返回格式有两种:

1. FIXED


    ```
       当code为0时候为成功，其他情况为失败，
       除了鉴权失败(403)、服务不可用(503)、URL NOT FOUND(404)、Method Not Allow(405)等错误，
       其他业务异常的HTTP状态码均为200
    ```
         
   
   ```
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
       
   ```
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


# 错误码

        
        
        错误码格式：f xxxx yyyy
    
        f:错误类型标识
        xxxx:系统标识（0000为系统默认保留，从0001开始）
        yyyy：错误码（0000~9999）
        
        

###  **参数校验相关错误码**

| 错误码       |             错误描述               |
| -------     | ----------------------------------|
| 100000000   |       param invalid,details:'%s'  |
| 100000001   |       '%s' must not be null       |
| 100000002   |       '%s' value must not be empty|
| 100000003   |       '%s' value must not be null |

### **权限相关错误码**

| 错误码       |             错误描述                    |
| --------    | -------------------------------------- |
| 200000000   | invalid access                         |
| 200000001   | 'Access-Token' value must not be empty |

### **资源未找到相关错误码**

| 错误码         | 错误描述                        |
| ------------- | ------------------------------ |
| 300000000     | requested path: '%s' not found |
| 300000001     |                                |

### **不支持的操作相关错误码**

| 错误码           | 错误描述                                |
| --------------- | -------------------------------------- |
| 400000000       | Method {%s} Not Allowed,URI {'%s'}     |


### **服务不可用相关错误码**

| 错误码     |   错误描述           |
| ------    | ------------------- |
| 500000000 | service unavailable |

 
    
    
    

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


# 版本更新说明

        1.  0.0.1_BETA
            初始化版本，提供基础功能
        
        2.  2.0.0_BETA
            新增api统计功能
        
        3.  3.0.0_BETA
            1. 项目结构调整
            2. 错误码重新定义
            3. 新增支持Rpc错误码抛出
        

