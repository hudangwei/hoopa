package main

import (
	"fmt"
	"os"
	"path"
	"strings"
)

const (
	ApplicationTestsTPL = `package {{groupPath}};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCheckHealthController() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/checkhealth/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"code\":200,\"message\":\"ok\",\"data\":{\"id\":1,\"status\":0,\"message\":\"ok\"}}")));
    }
}
`
)

func writeTestsFiles(mPath string, group string) {
	fpath := path.Join(mPath, "ApplicationTests.java")
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

	template := ""
	template = ApplicationTestsTPL
	fileStr := strings.Replace(template, "{{groupPath}}", group, -1)

	if _, err := f.WriteString(fileStr); err != nil {
		fmt.Printf("[ERRO] Could not write test file to %s\n", fpath)
		os.Exit(2)
	}
	CloseFile(f)
}
