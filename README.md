# springboot+jpa代码自动生成工具,集成swagger2


## 命令示例:
```
./hoopa.exe -gen test -tables="user" -conn=root:123456@tcp\(127.0.0.1:3306\)/test_db -driver=mysql -group=com.example.user
```
支持多表,中间用逗号分割

## 生成的目录结构
```
├── test
    ├── controller
	│   └── UserController.java
	├── dao
	│   └── UserDao.java
	├── exception
	│   └── UserNotFound.java
	├── model
	│   └── User.java
	└── service
	    └── UserService.java
```

## TODO
待添加复杂的查询