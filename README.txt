1.行业背景，项目介绍，搭建项目工程，svn使用
2.框架整合，后台管理商品列表实现，分页插件
3.后台管理，商品添加，类目选择，图片上传，富文本编辑器的使用
4.商品规格实现
5.前台系统搭建，首页商品分类展示jsonp-ajax跨域调用
6.cms系统实现，内容管理系统，前台大广告位的展示(轮播图)
7.cms系统添加缓存，redis，缓存同步
8.搜索功能的实现：solr实现
9.商品详情页面
10.单点登录系统，分布式session共享
11.购物车，订单系统实现
12.系统部署:分布式环境下的nginx反向代理
13.redis集群搭建，solr集群搭建，系统部署-虚拟机
14.项目总结
——————————————————————————

电商行业技术特点：
	技术新
	技术范围广
	分布式
	高并发、集群、负载均衡、高可用
	海量数据
	业务复杂
	系统安全
——————————————————————————
传统项目：
	1.模块之间耦合度高，一个升级其余都得升级
	2.开发困难，各团队需要整合代码
	3.系统的可拓展性差
	4.不能灵活的进行分布式部署
解决方式：
	0.集群：开多个tomcat部署相同的项目，用nginx反向代理进行集群
	1.把模块拆分成独立工程，单点运行，如果魔偶个点压力大可针对此点单独增加配置，其他点不受影响
		--系统之间要进行数据交互，需要额外的工作量进行接口开发
	2.把系统拆分成多个功能，要完成整个系统功能需要多个工程协作完成---被称为分布式
分布式架构:
	订单系统-会员系统-前台系统  后台管理-搜索系统  数据库 缓存 ====单点登录系统
——————————————————————————
技术架构：
	1.mysql -免费，性能较oracle差
	2.ssm
	3.jsp,jstl,jquery plugin,EasyUI,KindEditor富文本编辑器，css+div
	4.redis
	5.solr
	6.nginx（web服务器）
	7.httpclient 调用系统服务
人员配置：产品，项目经理，前端，后端，测试，运维
maven：
	依赖管理
	项目构建
	工程聚合
maven工程类型:war,jar,Pom.xml
___________________________________________________

bobo-parent：创建父工程-
bobo-common：创建common工程—放置通用工具类
bobo-manager：后台管理工程:聚合工程pom工程
___________________________________________________
互联网项目中最好使用单表查询，
	·可以冗余解决查询性能问题
	·为以后分库分表打好基础
sku:最小库存量单位，就是商品id-商品最细粒度的划分，每个sku都唯一对应一款商品，商品的颜色，配置都已经被唯一确定
___________________________________________________
mybatis逆向工程生产代码
ssm整合：
	·Dao层：使用mybatis框架，创建sqlMapConfig.xml
	·创建一个applicationContext-dao.xml
		·配置数据源
		·需要让Spring容器管理SpringFactory，单例存在
		·把mapper的代理对象放到容器中，使用扫描包的方式加载mapper的代理对象
	·Service层：
		·事务管理
		·把service实现类对象放在Spring容器中管理
	·表现层：
		·配置视图解析器
		·配置注解驱动
		·需要扫描controller
	·web.xml
		·Spring容器配置
		·mvc前端控制器配置
		·post乱码过滤
	·框架整合：
		·需要把配置文件放在bobo-manager-web下，因为只有web工程打包方式为war，其余为jar
___________________________________________________
mybatis分页插件：com.github.pagehelper
	·实现原理：
		1.mybatis里的sqlSessionFactory创建一个sqlSession
		2.sqlSession里有许多用户方法(insert...)
		3.executor（执行器对象）
		--
		mybatis的plugin实现了以下接口，可在插件中获得要执行的sql语句，在SQL语句中添加limit便可实现分页
		--
		4.MappedStatement-sql语句的封装
		—————————————————
		5.mybatis中有个接口-拦截器（org.apache.ibatis.plugin下的Interceptor接口）
		6.实现以上接口可对mybatis进行拓展
	·此分页插件对逆向工程生成的代码支持不好，不能对有查询条件的进行分页--使用修改后版本-3.4.2-fix		
___________________________________________________

三、
	类目选择：树形结构展示；参数父id，返回所有子集结构
	图片上传:
		·图片保存位置：
			1-传统项目：WEB-INF\image  图片过多过大时，可使用linux盘符映射
			  ·当用户量过大时，并发过高，此时可以添加服务器做tomcat集群(假设有三台tomcat，此时image有三个) 
			  ·集群环境出现问题：访问跟存放的位置不相同，有时可访问，有时不可以
			  ·解决1：将存放位置映射到同一个存储位置下，保持文件共享
			  ·解决2：将图片放在单独的服务器中（图片服务器-资源服务器）
				   专门的服务器，需要一个http服务器(tomcat-服务器用来专门处理资源性能不太好)
				   此处选择Apache、nginx等服务器--作为图片服务器：
					1.使用ftp协议(文件传输协议)进行图片上传
					2.使用linux自带的ftp服务器-vsftpd
				  步骤：
					1.用户上传图片--
					2.经过tomcat1，tomcat2将图片上传ftp服务器上
					3.用户访问图片--直接访问ftp服务器
			
		·需要安装
			linux：G:\VM_centos\CentOS-7-x86_64-Minimal-1708.iso
			nginx: 见nginx安装 --配置静态资源访问
			vsftpd：见ftp安装手册，客户端工具下载地址：https://www.filezilla.cn/download/client
			
	富文本编辑器使用KindEditor，
	商品添加实现
—————————————————————————
ftp工具类实现：
	上传及下载
	参考：kindeditor.net/docs/upload.html
	上传图片不需要持久化到dao，故不需要dao
	service层：接收controller传递对象-MultiPartFile对象，把文件上传到ftp服务器，生成一个新的文件		名，返回此文件的url路径，包装成图片上传插件的数据格式(富文本编辑器)
		//成功时
		{
       		 	"error" : 0,
        		"url" : "http://www.example.com/path/to/file.ext"
		}
		//失败时
		{
       			 "error" : 1,
       			 "message" : "错误信息"
		}
	注意事项：回显问题
	error 必须是Integer类型
	@JsonInclude(JsonInclude.Include.NON_NULL) 为null的json不返回



























	