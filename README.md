# springboot+jpa代码自动生成工具,集成swagger2

现已支持生成springboot完整项目文件,记得修改下application.yml中的数据库数据源配置,改成自己的数据库即可~~~
## 命令示例:
```
./hoopa.exe -gen test -tables="user" -conn=root:123456@tcp\(127.0.0.1:3306\)/test_db -driver=mysql -group=com.example.user
```
支持多表,中间用逗号分割,如果tables为空,则遍历整个数据库

## 生成的目录结构
```
├── test
	├── pom.xml
	└── src
    	├── main
		│   ├── java
		|   |   └── com.example.user
		|   |   	├── config
		|   |   	|   └── Swagger2Config.java
		|   |   	├── controller
		|   |   	|   └── UserController.java
		|   |   	├── dao
		|   |   	|   └── UserDao.java
		|   |   	├── dto
		|   |   	|   └── Result.java
		|   |   	├── enums
		|   |   	|   └── ErrorCodeEnum.java
		|   |   	├── exception
		|   |   	|   ├── BusinessException.java
		|   |   	|   └── GlobalDefaultExceptionHandler.java
		|   |   	├── model
		|   |   	|   └── User.java
		|   |   	├── service
		|   |   	|   └── UserService.java
		|   |   	└── Application.java
		│   └── resources
		│  		├── application.yml
		│  		├── bootstrap.yml
		│  		└── log4j2.xml
		└── test
		    └── java
				└── com.example.user
					└── ApplicationTests.java
```

## TODO
生成springboot完整项目