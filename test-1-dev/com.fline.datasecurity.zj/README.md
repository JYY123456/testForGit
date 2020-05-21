com.fline.framework.webdev
==================

## 1. 概述

本项目是浙江非线数联科技有限公司的web开发框架，用于开发web项目，支持常规开发组件(如redis、druid)，以及对接特殊的服务(如大数据管理服务平台的API操作)。

## 2. 包说明

源文件在src/main/java目录下。项目的包前缀为com.fline.framework.webdev, 在此包下分为config、dao、mgmtservice、controller、vo、util、aop、intercept8个包，分别处理配置、数据库、业务、服务、数据传递、工具、切面和拦截相关的业务。
### 2.1 config

config包下处理业务配置，用于接收项目的配置参数，如application.yml中的配置或自定义的配置, 以及自定义数据库配置、缓存配置等。
### 2.2 dao

dao包下分entity和mapper两个子包，entity为数据库操作实体，mapper为数据库操作的接口。mapper不做业务处理，只做纯粹的数据库表操作，因此会有数据操作风险，如插入已存在的数据，更新不存在的数据等。

### 2.3 mgmtservice

mgmtservice包下包括业务操作接口类和实现类，是业务操作的核心代码。mgmtservice中实现的业务操作，需包含各类校验，确保业务正常执行。如遇错误，应抛出相关异常，以便上层和其他功能模块调用。

### 2.4 controller

controller包下包含所有的rest服务。

### 2.5 vo

mgmtservice、controller之间传递的数据对象，原则上不应将dao/entity下的实体类直接作为参数在业务操作类之间传递，因为dao/entity中的对象包含了很多数据库相关的特性，甚至部分字段是未经过处理的，不便于理解的。如性别字段，由于数据库中是以int存储，会引起业务操作中的不可理解, 甚至暴露部分敏感字段。因此，建议mgmtservice返回的数据均用vo中的类，以此保障返回的数据的可读、可理解。

### 2.6 util

工具包。如加解密、http访问。

### 2.7 annotation

自定义注解。

### 2.8 intercept

服务拦截相关的类。

## 3. 安装部署

本项目采用maven开发，使用IDE导入本项目后，即可开发。
### 3.1 mvn 私服

http://121.40.214.176:8081/nexus 

### 3.2 启动类

com.fline.framework.webdev.Starter

## 4. 开发手册
参考doc文件夹下的手册。（待补充）

### 4.1 文件清单
doc/setting.xml 公司maven私服的配置文件。

