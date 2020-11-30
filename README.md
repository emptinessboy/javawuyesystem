# VUE-物业管理系统后端

该项目主要是我本学期数据库课程的命题大作业。这里的代码是JAVAWEB的后端的实现部分。前端请见：[https://git.huxiaofan.com/emptinessboy/vuewuyesystem](https://git.huxiaofan.com/emptinessboy/vuewuyesystem)

> 预览地址：[https://wyglxt.app.huxiaofan.com/](https://wyglxt.app.huxiaofan.com/)

## 特点

该项目使用了响应式设计，采用了完全的前后端分离。

前端使用了vue+element，后端使用Java语言写的Servlet，数据库使用的MySQL8。实现了用户记住登录，区分管理员和普通用户登录，权限管理，物业服务管理，在线结算等功能。

演示地址：https://wyglxt.app.huxiaofan.com

### 技术栈

后端部分：JAVA Servlet，FastJSON，MYSQL 8

运行环境： Tomcat 9.39

## 接口信息

> 本后端接受普通的地址形式参数 parameter ，返回格式为 application/json。使用 axios 请求接口时需要进行 qs 转换，并携带 token。

======

### OrderManage 接口

访问路径 /api/orders

- GET方法：不需要参数。获得JSON格式的输出
- 返回实例如下

`[{date: "2020-11-29 22:42:47", cno: "10000", id: "19", sid: "4", status: "1"}]
0: {date: "2020-11-29 22:42:47", cno: "10000", id: "19", sid: "4", status: "1"}`

- POST方法：参数（method[accept],id[员工ID]）
- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### OrderManage 接口

访问路径 /api/orders

