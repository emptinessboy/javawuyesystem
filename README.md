# VUE-物业管理系统后端

该项目主要是我本学期数据库课程的命题大作业。这里的代码是JAVAWEB的后端的实现部分。前端请见：[https://git.huxiaofan.com/emptinessboy/vuewuyesystem](https://git.huxiaofan.com/emptinessboy/vuewuyesystem)

> 预览地址：[https://wyglxt.app.huxiaofan.com/](https://wyglxt.app.huxiaofan.com/)

## 特点

该项目使用了响应式设计，采用了完全的前后端分离。

前端使用了vue+element，后端使用Java语言写的Servlet，数据库使用的MySQL8。实现了用户记住登录，区分管理员和普通用户登录，权限管理，物业服务管理，在线结算等功能。并使用了MySQL的事务特性，语句出错后自动回滚。

演示地址：https://wyglxt.app.huxiaofan.com

### 技术栈

后端部分：JAVA Servlet，FastJSON，MYSQL 8

运行环境： Tomcat 9.39， JDK 1.8+

## 接口信息

> 本后端接受普通的地址形式参数 parameter ，返回格式为 application/json。使用 axios 请求接口时需要进行 qs 转换，并携带 token。

**除 Login 以外接口，都需要用户在参数中携带 token**

======

### OrderManage 接口

访问路径 /api/orders

- GET方法：不需要参数。获得JSON格式的输出工单列表
- 返回实例如下

`[{date: "2020-11-29 22:42:47", cno: "10000", id: "19", sid: "4", status: "1"}]
0: {date: "2020-11-29 22:42:47", cno: "10000", id: "19", sid: "4", status: "1"}`

- POST方法：参数（method[accept],id[员工ID]）
- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### ListMembers 接口

访问路径 /api/listmembers

- GET方法：不需要参数。获得JSON格式的输出住户列表
- 返回实例如下

`[{"cphone":"1366666666","cno":"10000","cname":"胡晓帆","caddress":"xx小区，xx栋，888室","cregtime":"2020-11-21 14:05:22","csex":"男","cmoney":"318"},{"cphone":"13123123123","cno":"10001","cname":"李明","caddress":"xx小区，xx栋，68室","cregtime":"2020-12-02 19:20:31","csex":"女","cmoney":"0"}]`

- POST方法：

参数（method[delete],cno[客户ID]）

参数（method[modify],row[cno][客户ID],row[cname][用户姓名],row[caddress][用户住址],row[cregtime][注册时间],row[csex][用户性别],row[cphone][用户电话号码]）

- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### AddMembers 接口

访问路径 /api/listmembers

- GET方法：不需要参数。获得JSON格式的输出自动生成的唯一的新员工ID
- 返回实例如下

`[{"newid":"10040"}]`

- POST方法：参数（id[用户ID]],name[用户姓名],sex[用户性别],money[余额],phone[用户电话],address[用户住址],date[入住时间],pass[用户密码]）
- 返回状态 200 / 201-204 内部错误 / 401 认证错误