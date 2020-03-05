该项目是一个调查表后台程序

### 技术栈

* Java8
* SpringBoot
* Gradle
* Docker
* Junit5

### 启动项目

安装好 Docker Compose，进入项目的docker目录，文件如下图所示

```
├── conf
│   └── my.cnf
├── docker-compose.yml
└── init
    └── init.sql
```

 docker compose 的命令如下：

1. 启动服务 `docker-compose up -d`

开始下载服务和启动依赖的mysql容器

接着可以使用

```
docker ps -a 查看容器id
docker logs id 查看日志
docker ps 查看是否成功启动
```

![docker容器](https://github.com/esmusssein777/questionnaire/blob/master/png/docker.png?raw=true)



打开 http://localhost:8080/swagger-ui.html#/ 

可以看到下图 

![Swagger](https://github.com/esmusssein777/questionnaire/blob/master/png/swaggerAPI.png?raw=true)

2. 停止所有服务 `docker-compose stop`

### 测试项目

进入 config 目录，将名字为

`questionnaire.postman_collection.json` 的 Json 文件导入到 Postman中，可在其中测试api

