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
- 返回示例如下

`[{date: "2020-11-29 22:42:47", cno: "10000", id: "19", sid: "4", status: "1"}]
0: {date: "2020-11-29 22:42:47", cno: "10000", id: "19", sid: "4", status: "1"}`

- POST方法：参数（method[accept],id[员工ID]）
- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### ListMembers 接口

访问路径 /api/listmembers

- GET方法：不需要参数。获得JSON格式的输出住户列表
- 返回示例如下

`[{"cphone":"1366666666","cno":"10000","cname":"胡晓帆","caddress":"xx小区，xx栋，888室","cregtime":"2020-11-21 14:05:22","csex":"男","cmoney":"318"},{"cphone":"13123123123","cno":"10001","cname":"李明","caddress":"xx小区，xx栋，68室","cregtime":"2020-12-02 19:20:31","csex":"女","cmoney":"0"}]`

- POST方法：

参数（method[delete],cno[客户ID]）

参数（method[modify],row[cno][客户ID],row[cname][用户姓名],row[caddress][用户住址],row[cregtime][注册时间],row[csex][用户性别],row[cphone][用户电话号码]）

- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### AddMembers 接口

访问路径 /api/listmembers

- GET方法：不需要参数。获得JSON格式的输出自动生成的唯一的新员工ID
- 返回示例如下

`[{"newid":"10040"}]`

- POST方法：参数（id[用户ID]],name[用户姓名],sex[用户性别],money[余额],phone[用户电话],address[用户住址],date[入住时间],pass[用户密码]）
- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### ServiceManage 接口

访问路径 /api/servicemanage

- GET方法：参数：want[类型]。获得JSON格式的输出自动生成的唯一的新服务ID
- want参数值为 newid 的返回示例如下

`[{"newid":"9"}]`

- want参数值为 slist 的返回示例如下

`[{"sname":"楼道清理","sdesc":"帮助业主清理单元楼楼道卫生保洁","stime":"12","sprice":"50.0","sid":"1"},{"sname":"上门开锁","sdesc":"专业的开人员，帮助业主开锁。（本服务需要业主实名登记）","stime":"4","sprice":"200.0","sid":"2"}]`

- POST方法：

参数（method[delete],sid[服务ID]）。请求后删除对应的服务。

参数（method[空],sid[服务ID],name[服务名称],price[服务价格],desc[服务介绍]）。请求后添加对应的服务。

- 返回状态 200 / 201-204 内部错误 / 401 认证错误

### MoneyApi 接口

访问路径 /api/moneyapi

- GET方法：不需要参数。获得JSON格式的输出订单历史记录列表
- 返回示例如下

`[{"date":"2020-11-22 10:52:26","times":"1","method":"服务消费","money":"50.0","cno":"10006","staff":"1","id":"1","sid":"6"},{"date":"2020-11-23 10:51:13","times":"1","method":"物业费充值","money":"40.0","cno":"10023","staff":"-","id":"22","sid":"-"}]`

- POST方法：

参数（method[income],id[用户ID],money[充值金额]）。请求后对指定用户进行充值。

参数（method[delete],id[服务记录的ID]）。请求后删除对应的服务/充值记录。

参数（method[pay],uid[用户ID],service[订购的服务ID],date[日期时间字符串],times[次数],staff[员工ID]）。请求后删除对应的服务/充值记录。

- 返回状态 200 / 401 认证错误
   - 205 Fail，用户ID不存在！
   - 206 Fail，用户余额不足！
   - 207 Fail，用户扣款失败！
   - 202 Fail，修改服务记录失败！

### StaffManage 接口

访问路径 /api/staff

- GET方法：参数：want[类型]。获得JSON格式的输出自动生成的唯一的新员工ID
- want参数值为 eid 的返回示例如下

`[{"newid":"2"}]`

- want参数值为 elist 时返回员工列表，示例如下

`[{"eid":"1","ename":"胡晓帆","isadmin":"管理员","esrore":"280","esex":"男"},{"eid":"2","ename":"王帅帅","isadmin":"普通员工","esrore":"10","esex":"男"},{"eid":"3","ename":"刘拖地","isadmin":"普通员工","esrore":"6","esex":"女"}]`

- want参数值为 escore 时返回排序后的员工绩效，示例如下

`[{"eid":"1","ename":"胡晓帆","esrore":"280","esex":"男"},{"eid":"6","ename":"李水电","esrore":"55","esex":"男"},{"eid":"2","ename":"王帅帅","esrore":"10","esex":"男"}`

- POST方法：

参数（isadmin[是否管理员],id[员工ID],method[delete]）。请求后删除对应员工。

参数（name[员工姓名],sex[员工性别],pass[员工密码],isadmin[是否管理员]）。新增员工。

- 返回状态 200 / 201-204 内部错误 / 401 认证错误，权限不足

### ShowData 接口

访问路径 /api/show

- GET方法：不需要参数。获得JSON格式的输出统计信息
- 返回示例如下

`[{"count_cno":"30","count_cmoney":"4586"},{"count_allsmoney":"944.0","count_allstimes":"17"},{"days":["2020-11-25","2020-11-26","2020-11-27","2020-11-28","2020-11-29","2020-11-30","2020-12-01"]},{"income":[0,100,0,100,0,0,0],"pay":[0,350,0,50,0,0,0]}]`

其中 income，pay 的值为最近七天的进账和出账信息。

### LoginApi 登录接口

访问路径：/api/login

- POST方法：

参数（type[staff],user[用户名],pass[密码]）。员工登录。

参数（type[空],user[用户名],pass[密码]）。客户登录。

- 返回状态 200 / 401 认证错误，权限不足

- 返回数据格式为 json，示例为。

`[{"eid":"1","ename":"胡晓帆","isadmin":"1","token":"77fbc75846cbc8ef009023931af0f043"}]`

token 采用时间戳+随机数盐+md5 生成，前端对接时需要存好 token，以后每次请求时需要携带。否则需要重新登录。（后端 token 有效期 30 分钟）

### LoginOutApi 登出接口

访问路径：/api/logout

支持 get post 请求，过程需要携参数带 token，校验完成自动在服务端注销并返回 200.
