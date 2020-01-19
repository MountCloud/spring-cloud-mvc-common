# USE 使用
##  USE spring cloud common ，使用spring cloud common方式
```
<parent>
  <groupId>org.mountcloud</groupId>
  <artifactId>spring-cloud-common-parent</artifactId>
  <version>2.2.1.RELEASE-Hoxton.RELEASE-1.1</version>
</parent>
<dependency>
  <groupId>org.mountcloud</groupId>
  <artifactId>spring-cloud-mvc-common</artifactId>
</dependency>
```
## OR Use alone，或者单独引用。
```
<dependency>
  <groupId>org.mountcloud</groupId>
  <artifactId>spring-cloud-mvc-common</artifactId>
  <version>1.1</version>
</dependency>
```

# Note
  After creating a project, you often need to do some repetitive work, such as copying the public components from the previous project.

## Expansion:
```
1: ApplicationContextConfig.java, you can get ApplicationContext singleton.
2: EncodeConfig.java, configured with a password encryptor.
3: WebMvcConfiguration.java, mainly rewrites RequestMappingHandlerMapping, extends Mapping registration function, supports parent class registration Mapping and mapping path printing.
4: log, which is used to print all request information. You can configure its switch.
```

## constraint:
```
1: controller, defines ApiController, and implements unified mapping prefix (/ api) by inheriting ApiController or ApiControllerInterface. BaseController is to restrict the standard MVC specification.
2: service, constraint standard MVC specification.
```

## Configuration:
```
1: logging.show-requestlog = true, enable or disable logging of all requests.
2: logging.show-mapping = true, whether to print mapping information.
```

# 描述
在创建项目之后，经常需要做一些重复的工作，比如复制之前项目中的公共组件，这个项目将最基础的工具包提取了出来.

## 扩展：
```
1：ApplicationContextConfig.java，可以获取ApplicationContext单例。
2：EncodeConfig.java，配置了密码加密器。
3：WebMvcConfiguration.java，主要重写了RequestMappingHandlerMapping，扩展Mapping注册功能，支持父类注册Mapping与mapping路径打印。
4：log，主要是打印所有请求信息，可以配置它的开关。
```

## 约束：
```
1：controller，定义了ApiController，通过继承ApiController或者ApiControllerInterface实现mapping统一前缀（/api），BaseController是为了约束标准的MVC规范。
2：service，约束标准MVC规范。
```

## 配置：
```
1：logging.show-requestlog=true，开启或关闭所有请求的日志。
2：logging.show-mapping=true，是否打印mapping信息。
```

# Files 文件列表
```
.
├── .gitignore
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── org
    │           └── mountcloud
    │               └── springcloud
    │                   └── mvc
    │                       └── common
    │                           ├── config
    │                           │   ├── APIConfig.java
    │                           │   ├── ApplicationContextConfig.java
    │                           │   ├── EncodeConfig.java
    │                           │   └── WebMvcConfiguration.java
    │                           ├── controller
    │                           │   ├── ApiControllerInterface.java
    │                           │   ├── ApiController.java
    │                           │   └── BaseController.java
    │                           ├── handler
    │                           │   └── ControllerApiRequestMappingHandlerMapping.java
    │                           ├── log
    │                           │   ├── RequestLoggerFilter.java
    │                           │   ├── RequestLogger.java
    │                           │   └── RequestLoggerOperate.java
    │                           ├── service
    │                           │   └── BaseService.java
    │                           └── util
    │                               └── RequestUtil.java
    └── test
        └── java
            └── org
                └── mountcloud
                    └── springcloud
                        └── mvc
                            └── common
                                └── TestAnnocation.java
```
