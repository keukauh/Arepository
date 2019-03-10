基于SpringMVC框架的简易实现smartMVC
环境 JDK1.5 maven项目  tomcat服务器8.5  mysql数据库
---------------------------------------------------------------------
MVC 模式: 解决用户界面问题的标准模式！

M Model 模型，封装业务逻辑 V View 视图，代表显示界面 C Controller 控制器，是用于连接整合 M和V

Sun给出web用户界面的建议：

使用Java Bean 作为Model，处理业务逻辑
使用JSP 作为视图，显示数据
使用Servlet作为控制器，整合JSP和JavaBean
--------------------------------------------------------------------------
db.properties 数据库配置信息
mvc.xml 框架配置文件
|-cn.smartmvc
    |-dao 数据库访问层
        |-UserDao
    |-entity 封装JavaBean
        |-User
    |-mvc 核心包
        |-AccessFilter 登录过滤器
        |-Controller 子控制器
        |-DispatchServlet 前端控制器
        |-Handler 封装子控制器对象和方法
        |-HandlerMapping 封装URL与Handler
        |-LoginController 登录控制器
        |-RequestMapping 注解（url路径）
    |-util 获取数据库连接小工具
        |-DBUtils
    |-web 传统Servlet方式接受请求，响应页面
        |-AddUserServlet
        |-DelUserServlet
        |-ListUserServlet
