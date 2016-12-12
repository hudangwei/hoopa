package main

const (
	ApplicationTPL = `package {{groupPath}};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
`
	ConfigTPL = `package {{groupPath}}.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("test")
				.pathMapping("")
				.select()
				.apis(RequestHandlerSelectors.basePackage("{{groupPath}}.controller"))	//通过指定扫描包暴露接口
				.paths(PathSelectors.any())	//设置过滤规则暴露接口
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Api")
				.description("接口测试")
				.version("1.0")
				.termsOfServiceUrl("")
				.license("")
				.licenseUrl("")
				.build();
	}
}
`
	ControllerTPL = `package {{groupPath}}.controller;

import {{groupPath}}.dto.Result;
import {{groupPath}}.enums.ErrorCodeEnum;
import {{groupPath}}.exception.*;
import {{groupPath}}.model.*;
import {{groupPath}}.service.{{BigModelName}}Service;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{{AllLittleModelName}}")
@Api(value = "{{BigModelName}}Controller", description = "oprations for {{BigModelName}}")
public class {{BigModelName}}Controller {
    @Autowired
    private {{BigModelName}}Service {{LittleModelName}}Service;

    @ApiOperation(value="add{{BigModelName}}", notes="Create {{BigModelName}}")
    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<{{BigModelName}}> add{{BigModelName}}(@RequestBody {{BigModelName}} {{LittleModelName}}) {
        Result<{{BigModelName}}> r = new Result<>(ErrorCodeEnum.OK,{{LittleModelName}}Service.add{{BigModelName}}({{LittleModelName}}));
        return r;
    }

    @ApiOperation(value="update{{BigModelName}}", notes="Update {{BigModelName}}")
    @RequestMapping(value="", method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<{{BigModelName}}> update{{BigModelName}}(@RequestBody {{BigModelName}} {{LittleModelName}}) throws BusinessException {
        Result<{{BigModelName}}> r = new Result<>(ErrorCodeEnum.OK,{{LittleModelName}}Service.update{{BigModelName}}({{LittleModelName}}));
        return r;
    }

    @ApiOperation(value="delete{{BigModelName}}By{{BigPkName}}", notes="Delete {{BigModelName}} By {{BigPkName}}")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result<String> delete{{BigModelName}}By{{BigPkName}}(@PathVariable {{PkTypeName}} id) throws BusinessException {
        {{LittleModelName}}Service.delete{{BigModelName}}By{{BigPkName}}(id);
        Result<String> r = new Result<>(ErrorCodeEnum.OK,"");
        return r;
    }

    @ApiOperation(value="get{{BigModelName}}", notes="Get {{BigModelName}} Info By {{BigPkName}}")
    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<{{BigModelName}}> get{{BigModelName}}(@PathVariable {{PkTypeName}} id) throws BusinessException {
        Result<{{BigModelName}}> r = new Result<>(ErrorCodeEnum.OK,{{LittleModelName}}Service.get{{BigModelName}}By{{BigPkName}}(id));
        return r;
    }

	@ApiOperation(value="getAll{{BigModelName}}", notes="Get All {{BigModelName}} info")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "query",required = false, value = "Filter. e.g. col1:v1,col2:v2 ...",dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "fields",required = false,value = "Fields returned. e.g. col1,col2 ...",dataType = "String"),*/
            @ApiImplicitParam(paramType = "query",name = "sortby",required = false,value = "Order corresponding to each sortby field. e.g. col1:desc,col2:asc ...",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",required = false,value = "Limit the size of result set. Must be an integer",dataType = "Int"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",required = false,value = "Start position of result set. Must be an integer",dataType = "Int")})
    @RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Page<{{BigModelName}}>> getAll{{BigModelName}}(@RequestParam(value = "query",required = false) String query,
                                 /*@RequestParam(value = "fields",required = false) String fields,*/
                                 @RequestParam(value = "sortby",required = false) String sortby,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pagesize",required = false) Integer pagesize) {
        Result<Page<{{BigModelName}}>> r = new Result<>(ErrorCodeEnum.OK,{{LittleModelName}}Service.getAll{{BigModelName}}(query,/*fields,*/sortby,page,pagesize));
        return r;
    }
}
`

	DaoTPL = `package {{groupPath}}.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import {{groupPath}}.model.*;

{{daoStruct}}
`

	DtoTPL = `package {{groupPath}}.dto;

import {{groupPath}}.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(ErrorCodeEnum codeEnum,T data){
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.data = data;
    }
}
`

	EnumsTPL = `package {{groupPath}}.enums;

public enum ErrorCodeEnum {
    {{enumsStruct}}    

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
`

	ExceptionTPL1 = `package {{groupPath}}.exception;

import {{groupPath}}.enums.ErrorCodeEnum;

public class BusinessException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum; //异常对应的描述信息
    public BusinessException(String msg) {
        super(msg);
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public BusinessException(ErrorCodeEnum codeEnum) {
        super();
        this.errorCodeEnum = codeEnum;
    }
}
`
	ExceptionTPL2 = `package {{groupPath}}.exception;

import {{groupPath}}.dto.Result;
import {{groupPath}}.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultExceptionHandler(HttpServletRequest request, Exception e) {
        Result result = new Result(ErrorCodeEnum.SYSTEM_ERROR, null);
        logger.error("========通用异常捕获！========", e);
        return result;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result BusinessExceptionHandler(HttpServletRequest request, BusinessException e) {
        Result result = new Result(e.getErrorCodeEnum(),null);
        logger.error("========业务逻辑异常捕获！========", e);
        logger.error("========业务逻辑异常捕获！========", e.getErrorCodeEnum().getCode());
        logger.error("========业务逻辑异常捕获！========", e.getErrorCodeEnum().getMessage());
        return result;
    }
}
`

	ModelTPL = `package {{groupPath}}.model;

import javax.persistence.*;

{{importTimePkg}}
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="{{tableName}}")
@Data
{{modelStruct}}
`

	ServiceTPL = `package {{groupPath}}.service;

import {{groupPath}}.dao.*;
import {{groupPath}}.enums.ErrorCodeEnum;
import {{groupPath}}.exception.*;
import {{groupPath}}.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class {{BigModelName}}Service {

    @Autowired
    private {{BigModelName}}Dao {{LittleModelName}}Dao;

    @Transactional
    public {{BigModelName}} add{{BigModelName}}({{BigModelName}} {{LittleModelName}}) {
        return {{LittleModelName}}Dao.save({{LittleModelName}});
    }

    @Transactional
    public {{BigModelName}} update{{BigModelName}}({{BigModelName}} {{LittleModelName}}) throws BusinessException {
        {{BigModelName}} {{LittleModelName}}Update = {{LittleModelName}}Dao.findOne({{LittleModelName}}.get{{BigPkName}}());
        if ({{LittleModelName}}Update==null){
            throw new BusinessException(ErrorCodeEnum.{{ALLBigModelName}}_NOTFOUND);
        }
        /*TODO: need add logic eg:
        if (user.getName()!=null) {
            userUpdate.setName(user.getName());
        }
        if (user.getAge()!=0) {
            userUpdate.setAge(user.getAge());
        }
        */
        {{LittleModelName}}Dao.save({{LittleModelName}}Update);
        return {{LittleModelName}}Update;
    }

    @Transactional
    public void delete{{BigModelName}}By{{BigPkName}}({{PkTypeName}} id) throws BusinessException {
        {{BigModelName}} {{LittleModelName}}Delete = {{LittleModelName}}Dao.findOne(id);
        if ({{LittleModelName}}Delete==null) {
            throw new BusinessException(ErrorCodeEnum.{{ALLBigModelName}}_NOTFOUND);
        }
        {{LittleModelName}}Dao.delete(id);
    }

    public {{BigModelName}} get{{BigModelName}}By{{BigPkName}}({{PkTypeName}} id) throws BusinessException {
        {{BigModelName}} {{LittleModelName}} = {{LittleModelName}}Dao.findOne(id);
        if ({{LittleModelName}}==null) {
            throw new BusinessException(ErrorCodeEnum.{{ALLBigModelName}}_NOTFOUND);
        }
        return {{LittleModelName}};
    }

	public Page<{{BigModelName}}> getAll{{BigModelName}}(String query, /*String fields, */String sortby,Integer page, Integer pageSize) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        PageRequest pageRequest;
        if (sortby != null && sortby.length() > 0) {
            List<Order> orders = new ArrayList<Order>();
            String[] sortFields = sortby.split(",");
            for (String sortField : sortFields) {
                String[] orderbys = sortField.split(":");
                if(orderbys[1].equals("desc")) {
                    orders.add(new Order(Sort.Direction.DESC,orderbys[0]));
                } else if (orderbys[1].equals("asc")) {
                    orders.add(new Order(Sort.Direction.ASC, orderbys[0]));
                }
            }
            pageRequest = new PageRequest(page, pageSize,new Sort(orders));
        }else{
            pageRequest = new PageRequest(page,pageSize);
        }

        return {{LittleModelName}}Dao.findAll(new Specification<{{BigModelName}}>() {
            @Override
            public Predicate toPredicate(Root<{{BigModelName}}> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> preList = new ArrayList<Predicate>();
                if (query != null && query.length() > 0) {
                    String[] queryFields = query.split(",");
                    for (String queryField : queryFields) {
                        String[] queryKv = queryField.split(":");
                        preList.add(cb.equal(root.get(queryKv[0]),queryKv[1]));
                    }
                }
                return cq.where(preList.toArray(new Predicate[preList.size()])).getRestriction();
            }
        },pageRequest);
    }
}
`
)
