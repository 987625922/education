# 视频管理后台

## 前言

> `gelingeducation`是一个在学习过程中诞生的项目，采用spring+hibernate+jpa+shiro+jwt构建，包含了后台代码和前台的`vue`代码，采用了前后端分离的方式构建了这一个项目，目的是整合阶段所学的知识和构建一个以后转型后端，找`java`工作的项目。

### 展示地址（更新不会有项目这么频繁，不能保证和代码是同一个版本）

[GLVideoManager](http://glvideomanager.hopto.org/)
账号:123456 密码：123456

### 组织结构

``` lua
gelingeducation
├── common -- 工具类，通用代码，过滤器，身份认证
         ├──── authentication 权限管理的类，包含jwt
         ├──── dto 对外的数据bean
         ├──── exception 全局exception处理
         ├──── filter 过滤器
         ├──── function function接口
         ├──── redis redis的类
         ├──── utils 工具类
├── controller -- 后台管理系统的接口
├── dao -- hibernate操作数据库的dao层
├── domain -- 存放数据映射的bean
└── service -- 后台管理系统的service层
```

### 后端技术

| 技术          | 版本         |
| ------------- | ------------ |
| spring        | 5.2.4        |
| hibernate     | 5.4.12       |
| redis.clients | 3.1.0        |
| shiro         | 1.4.2        |
| mysql         | 8.0.13/5.7.5 |
| docker        | 1.13.1       |
| openjdk       | 1.8.0_242    |
| nginx         | 1.17.6       |
| tomcat        | 9.0.34       |

### 前端项目地址

- [vue-gelingeducation](https://github.com/987625922/Vue-Gelingeducation/tree/master)

### 部署

1. 先用idea 标题栏build -> build artifacts -> 项目名:war ->build

2. <details>
   <summary>docker安装mysql并创建education数据库</summary>
    <pre><code>
    <p>1. 拉取镜像</p>
    <p>docker pull mysql</p>
    <p>2. 把容器的3306端口映射到主机上</p>
    <p>docker run -p 3306:3306 --name ed_mysql mysql</p>
    <p>3. 进入容器</p>
    <p>docker exec -it 8e08b2d49b85 /bin/bash</p>
    <p>4. 进入mysql</p>
    <p>mysql -uroot -p</p>
    <p>5. 创建education数据库</p>
    <p>CREATE DATABASE education;<p>
    </code></pre>
   </details>
   
3. <details>
   <summary>docker安装tomcat</summary>
   <pre><code>
   <p>1. docker pull tomcat</p>
   <p>拉取tomcat镜像</p>
   <p>2.docker run -it -d -p 6789:8080 tomcat</p>
   <p>把容器的8080映射到服务器的6789端口</p>
   <p>或者docker run -it -d -p 8889:8080 -v /usr/local/software/gelingeducation:/usr/local/tomcat/webapps/  --privileged=true tomcat</p>
   <p>顺便把文件也映射了，这样就可以通过idea直接更新项目,节省上传的步骤</p>
   <p>3. docker exec -it 8e08b2d49b85 /bin/bash<p>
   <p>进入容器，8e08b2d49b85 为容器的id</p>
   <p>4.cd /usr/local/tomcat/conf </p>
   <p>进入目录</p>
   <p>vim /conf/server.xml</p>
   <p>打开server.xml文件</p>
   <p>5.在server.xml 的 Host节点下新增Context 节点配置 保存并退出</p>
   <p><Context docBase="gelingeducation" path="/" reloadable="false" /></p>
   <p>path:指定访问该Web应用的URL入口。
   docBase:指定Web应用的文件路径，可以给定绝对路径，也可以给定相对于的appBase属性的相对路径，如果Web应用采用开放目录结构，则指定Web应用的根    目录，如果Web应用是个war文件，则指定war文件的路径。
   reloadable:如果这个属性设为true，tomcat服务器在运行状态下会监视在WEB-INF/classes和WEB-INF/lib目录下class文件的改动，如果监测到有class文     件被更新的，服务器会自动重新加载Web应用。</p>
   <p>6.把打包出来的war文件上传到服务器，再通过</p>
   <p>docker cp /opt/gelingeducation 8e08b2d49b85 :/usr/local/tomcat/webapps/ </p>
   <p>复制到容器</p>
   <p>7.docker restart 8e08b2d49b85</p>
   <p>重新启动tomcat</p>
   </code></pre>
   </details>

4. <details>
   <summary>docker安装nginx部署web前端</summary>
   <pre><code>
   <p> </p>   
   <p> 1.docker pull nginx</p>
   <p> 拉取nginx镜像</p>
   <p> 2.docker run -d --name gelingeducationnginx -p 7789:80 nginx</p>
   <p>运行run镜像，并把80端口映射到服务器的7789端口 </p>  
   <p>在浏览器输入ip:7789查看nginx服务器是否运行成功</p>
   <p>3.把打包好的vue前端代码（具体打包流程看前端github的remark）复制到容器的/usr/share/nginx/html下,注意是把打包出来的dist下所有文件复制到html下,(如果是映射路径的，记得去把容器里的html下文件删除掉，不然好像不会替换)</p>   
   <p>4.docker restart 容器id</p>
   <p>重启容器</p>
   <p>5.重新在浏览器输入ip:7789查看是否部署成功</p>
   <p>6. 如果发现前端所有的请求都是403</p> 
   <p>编辑容器的/etc/nginx/nginx.conf,把里面的user nginx;改成user root;</p>
   </code></pre>
   </details>

