package cn.huimin100.hmsp.market.controller;

import cn.huimin100.hmsp.market.exception.*;
import cn.huimin100.hmsp.market.model.*;
import cn.huimin100.hmsp.market.service.StorehouseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storehouse")
@Api(value = "StorehouseController", description = "oprations for Storehouse")
public class StorehouseController {
    @Autowired
    private StorehouseService storehouseService;

    @ApiOperation(value="addStorehouse", notes="Create Storehouse")
    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Storehouse addStorehouse(@RequestBody Storehouse storehouse) {
        return storehouseService.addStorehouse(storehouse);
    }

    @ApiOperation(value="updateStorehouse", notes="Update Storehouse")
    @RequestMapping(value="", method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Storehouse updateStorehouse(@RequestBody Storehouse storehouse) throws StorehouseNotFound {
        return storehouseService.updateStorehouse(storehouse);
    }

    @ApiOperation(value="deleteStorehouseById", notes="Delete Storehouse By Id")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteStorehouseById(@PathVariable Integer id) throws StorehouseNotFound{
        storehouseService.deleteStorehouseById(id);
        return "ok";
    }

    @ApiOperation(value="getStorehouse", notes="Get Storehouse Info By Id")
    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Storehouse getStorehouse(@PathVariable Integer id) {
        return storehouseService.getStorehouseById(id);
    }

	@ApiOperation(value="getAllStorehouse", notes="Get All Storehouse info")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "query",required = false, value = "Filter. e.g. col1:v1,col2:v2 ...",dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "fields",required = false,value = "Fields returned. e.g. col1,col2 ...",dataType = "String"),*/
            @ApiImplicitParam(paramType = "query",name = "sortby",required = false,value = "Order corresponding to each sortby field. e.g. col1:desc,col2:asc ...",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",required = false,value = "Limit the size of result set. Must be an integer",dataType = "Int"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",required = false,value = "Start position of result set. Must be an integer",dataType = "Int")})
    @RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Storehouse> getAllStorehouse(@RequestParam(value = "query",required = false) String query,
                                 /*@RequestParam(value = "fields",required = false) String fields,*/
                                 @RequestParam(value = "sortby",required = false) String sortby,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pagesize",required = false) Integer pagesize) {
        return storehouseService.getAllStorehouse(query,/*fields,*/sortby,page,pagesize);
    }
}
