package main

import (
	"flag"
	"fmt"
	"os"
	"path"
	"strings"
)

type docValue string

func (d *docValue) String() string {
	return fmt.Sprint(*d)
}

func (d *docValue) Set(value string) error {
	*d = docValue(value)
	return nil
}

var driver docValue
var tables docValue
var conn docValue
var group docValue
var genFileName docValue

func init() {
	flag.Var(&genFileName, "gen", "folder,your generate to folder name")
	flag.Var(&group, "group", "groupId, eg: com.yoursite.projectname")
	flag.Var(&driver, "driver", "database driver: mysql")
	flag.Var(&tables, "tables", "specify tables to generate model")
	flag.Var(&conn, "conn", "connection string used by the driver to connect to a database instance")
}

var version = "0.0.1"

func main() {
	fmt.Printf("current version is %s.\n", version)
	flag.Parse()
	currpath, _ := os.Getwd()
	projectPath := path.Join(currpath, string(genFileName))
	groupPathList := strings.Split(string(group), ".")
	appPath := path.Join(projectPath, "src", "main", "java")
	testPath := path.Join(projectPath, "src", "test", "java")
	for _, v := range groupPathList {
		appPath = path.Join(appPath, v)
		testPath = path.Join(testPath, v)
	}
	resourcesPath := path.Join(projectPath, "src", "main", "resources")
	fmt.Println("start ...")
	os.MkdirAll(appPath, 0755)
	os.MkdirAll(testPath, 0755)
	os.MkdirAll(resourcesPath, 0755)
	fmt.Printf("mkdir %s\n", appPath)
	fmt.Printf("mkdir %s\n", testPath)
	fmt.Printf("mkdir %s\n", resourcesPath)

	writePomXmlFiles(projectPath, string(group), string(genFileName))

	generateAppcode(string(driver), string(conn), string(tables), appPath, string(group))

	writeResourcesFiles(resourcesPath, string(genFileName), "application.yml", resApplicationYmlTPL)
	writeResourcesFiles(resourcesPath, string(genFileName), "bootstrap.yml", resBootstrapYmlTPL)
	writeResourcesFiles(resourcesPath, string(genFileName), "log4j2.xml", resLog4j2XmlTPL)

	writeTestsFiles(testPath, string(group))
	fmt.Println("end ...")
}
