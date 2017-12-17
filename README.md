# zhump_weixin
一： 微信工具类
  1,Loginservlet 以get方式去请求然后转发到微信服务器上面
  注意事项: 
      1:url回调地址的配置，首先登录微信公众号平台，选择接口权限，找到网页授权接口这一栏，然后单击修改
        在里面配置的是 域名信息 实例：www.aaa.com 错误实例： http://www.aaa.com www.aaa.com/bbb
      2:在项目当中url的写法要加上 http:// 不然会包 回调地址配置错误
