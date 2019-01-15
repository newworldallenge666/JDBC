配置步骤：

1.配置数据库
	【本系统使用的数据库名为DMSystem】
	（后面改为DMS，但是分离的时候还是DMSystem别问我为什么）
	【连接数据库的用户名为sa,连接数据库的密码为root】
	【sa用户是sql server自带的用户，所以在装数据库的时候只需要设置一个密码就可以了】

配置过程参照【Windows身份验证登录】-->根据https://jingyan.baidu.com/article/6766299787b69c54d51b84bb.html 
进行配置。【此处密码设为root】-->重启SQLServer，使用sa用户登录-->修改两个数据库文件的权限，可参照http://www.kungge.com/kungge/2717.html -->在SQL Server中附加


2.配置eclipse项目
	DMS项目上右键点击Build Path-->Configure Build Path-->Libraries-->Add External JARs-->选择JARs文件夹中的所有的jar包


