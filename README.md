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
         
    
## 规范
    1. 暴露接口服务时，在服务实现类使用注解com.alibaba.dubbo.config.annotation.Service;
           格式： @Service(version = "1.0",  owner="",stub = "",mock="")
           version初始为1.0,必填
           owner填写负责人标识,必填
           stub和mock如果存在,则填写
           注：如果spring回滚失效,也请写入interfaceName="接口全路径"
    2.  存放目录规则：
         xxx.api 服务接口定义存放目录
                      |--- stub 本地存根存放目录
                      |--- mock 服务降级存放目录
    3. 项目命名规范:
       sic777-xx 为业务子模块
       sic777-xx-api 为业务服务接口和实体、stub存根等存放位置
       sic777-xx-server 为业务具体实现代码存放问题
       注：xx为项目模块标识
    4. 错误码定义规范：
        不管是服务端还是网关的错误码，统一定义在sic777-common-demo项目下的
        com.sic777.common.error.ERROR枚举下   
          
    5. 服务端启动：
        @SpringBootApplication
        @DubboComponentScan("${Spring扫描路径}")
        @ComponentScan("${Dubbo扫描路径}")
        public class StudentServerLuncher {
            public static void main(String[] args) {
                com.sic777.dubbo.provider.DubboProviderLuncher.start(new Thread(() -> {
                     //填写优雅关闭需要处理的代码
                }));
            }
        }
     6. 客户端启动：
        @SpringBootApplication
        @DubboComponentScan("${Spring扫描路径}")
        @ComponentScan("${Dubbo扫描路径}")
        public class GatewayLuncher {
            public static void main(String[] args) {
                com.sic777.dubbo.comsumer.DubboConsumerLuncher.start(new Thread(() -> {
                     //填写优雅关闭需要处理的代码
                }));
            }
        }
      7. 所有的校验必须服务端也必须得有，在stub编写(仅处理参数校验)
         如果涉及到跨服务调用,请在server端编写
      
      8.在mybatis环境下,如果需要支持事务,请在启动类上添加注解org.springframework.transaction.annotation.EnableTransactionManagement
        
## 部署
    1. 执行mvc clean package进行打包
    2. 将target目录下的.gz包解压,修改配置文件config/application.properties
        logging.config=config/log4j2.xml
    3. 修改log4j.xml:
        LOG_FILE_NAME修改为项目的名称
    4. 修改启动脚本和停止脚本,根据提示配置即可
    5. 执行sh ./bin/startup.sh 启动 sh ./bin/shutdown.sh停止
        
        
## 使用建议

    1. 一个服务里边做一件事情,不要跨服务同时新增/删除/修改数据,会出现分布式事务的问题。
    