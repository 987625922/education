# 视频管理后台

## 前言

> `gelingeducation`是一个在学习过程中诞生的项目，包含了后台代码和前台的`vue`代码，采用了前后端分离的方式构建了这一个项目，目的是整合阶段所学的知识和构建一个以后转型后端，找`java`工作的项目。

### 组织结构

``` lua
gelingeducation
├── common -- 工具类，通用代码，过滤器，身份认证
├── controller -- 后台管理系统的接口
├── dao -- hibernate操作数据库的dao层
├── domain -- 存放数据映射的bean
└── service -- 后台管理系统的service层
```

#### 后端技术

| 技术          | 版本   |
| ------------- | ------ |
| spring        | 5.2.4  |
| hibernate     | 5.4.12 |
| redis.clients | 3.1.0  |
| shiro         | 1.4.2  |

