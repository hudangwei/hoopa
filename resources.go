package main

import (
	"fmt"
	"os"
	"path"
	"strings"
)

const (
	resApplicationYmlTPL = `server:
  port: ${PORT:${SERVER_PORT:9000}}
spring: 
  jpa: 
    hibernate: 
      naming: 
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://xx.xx.xx.xx:3306/db
    username: root
    password: 123456
    maximum-pool-size: 100
    max-idle: 10
    max-wait: 10000
    min-idle: 5
    initial-size: 5
    validation-query: SELECT 1
    test-on-borrow: false
    test-while-idle: true
    time-between-eviction-runs-millis: 18800

---

server: 
  port: 9000

spring:
  profiles: dev
  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://xx.xx.xx.xx:3306/db
    username: root
    password: 123456

---
`

	resBootstrapYmlTPL = `spring:
  application:
    name: {{projectName}}
`

	resLog4j2XmlTPL = `<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn">
	<Appenders>
		<Console name="Console" target="SYSTEM_OUT">
			<PatternLayout pattern="%date %level %class %method : %msg%n"/>
		</Console>
		<File name="RollingFileError" fileName="E:/logs/error.log"
			  filePattern="E:logs/logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">
			<ThresholdFilter level="ERROR"/>
			<PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
			<Policies>
				<TimeBasedTriggeringPolicy/>
				<SizeBasedTriggeringPolicy size="100 MB"/>
			</Policies>
		</File>
	</Appenders>

	<Loggers>
		<Root level="info">
			<AppenderRef ref="Console"/>
			<AppenderRef ref="RollingFileError" />
		</Root>
		<Logger name="org.hibernate.SQL" level="trace" additivity="false">
			<AppenderRef ref="Console"/>
		</Logger>
		<Logger name="org.hibernate.type.descriptor" level="trace" additivity="false">
			<AppenderRef ref="Console"/>
		</Logger>
		<Logger name="java.sql" level="debug" additivity="false">
			<AppenderRef ref="Console"/>
		</Logger>
		<Logger name="springfox.documentation" level="warn" additivity="false">
			<AppenderRef ref="Console"/>
		</Logger>
	</Loggers>
</Configuration>
`
)

func writeResourcesFiles(mPath, projectName, fileName, data string) {
	fpath := path.Join(mPath, fileName)
	var f *os.File
	var err error
	if isExist(fpath) {
		fmt.Printf("[WARN] '%v' already exists. Do you want to overwrite it? [Yes|No] ", fpath)
		if askForConfirmation() {
			f, err = os.OpenFile(fpath, os.O_RDWR|os.O_TRUNC, 0666)
			if err != nil {
				fmt.Printf("[WARN] %v\n", err)
				return
			}
		} else {
			fmt.Printf("[WARN] Skipped create file '%s'\n", fpath)
			return
		}
	} else {
		f, err = os.OpenFile(fpath, os.O_CREATE|os.O_RDWR, 0666)
		if err != nil {
			fmt.Printf("[WARN] %v\n", err)
			return
		}
	}

	fileStr := strings.Replace(data, "{{projectName}}", projectName, -1)

	if _, err := f.WriteString(fileStr); err != nil {
		fmt.Printf("[ERRO] Could not write resources file to %s\n", fpath)
		os.Exit(2)
	}
	CloseFile(f)
}
