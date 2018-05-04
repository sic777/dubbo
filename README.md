## 目录框架

     sic777-framwork
             |--- sic777-db
                       |--- sic777-db-cassandra                                 cassandra基础库
                       |--- sic777-db-mongo                                     mongodb基础库
                       |--- sic777-db-redis                                     redis基础库
                       
             |--- sic777-micro-service                                          微服务框架
             
             |--- sic777-spring-boot-dubbo                                      RPC框架-DUBOO
                           |--- sic777-spring-boot-dubbo-commmon                DUBBO公共模块
                           |--- sic777-spring-boot-dubbo-consumer               DUBBO服务消费者
                           |--- sic777-spring-boot-dubbo-provider               DUBBO服务提供者
                           
             |--- sic777-utils sic777                                           内部工具箱
             
             //TODO
             |--- sic777-framework-demo                                         示例程序
                           |--- sic777-common-demo                              通用程序包
                           |--- sic777-biz-demo                                 业务模块
                                      |--- sic777-biz-student                   示例业务模块
                                                 |--- sic777-biz-student-api    API程序包
                                                 |--- sic777-biz-student-server 服务提供者
                           |--- sic777-gateway-demo                             服务消费者(网关程序)
         
    
## 使用建议

    1. 一个服务里边做一件事情,不要跨服务同时新增/删除/修改数据,会出现分布式事务的问题。