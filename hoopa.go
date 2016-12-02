package main

import (
	"flag"
	"fmt"
	"os"
	"path"
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
	apppath := path.Join(currpath, string(genFileName))

	fmt.Println("start ...")
	os.MkdirAll(apppath, 0755)
	fmt.Printf("mkdir %s\n", apppath)

	generateAppcode(string(driver), string(conn), string(tables), apppath, string(group))

	fmt.Println("end ...")
}
