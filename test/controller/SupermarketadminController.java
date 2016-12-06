package cn.huimin100.hmsp.market.controller;

import cn.huimin100.hmsp.market.exception.*;
import cn.huimin100.hmsp.market.model.*;
import cn.huimin100.hmsp.market.service.SupermarketadminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supermarketadmin")
@Api(value = "SupermarketadminController", description = "oprations for Supermarketadmin")
public class SupermarketadminController {
    @Autowired
    private SupermarketadminService supermarketadminService;

    @ApiOperation(value="addSupermarketadmin", notes="Create Supermarketadmin")
    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Supermarketadmin addSupermarketadmin(@RequestBody Supermarketadmin supermarketadmin) {
        return supermarketadminService.addSupermarketadmin(supermarketadmin);
    }

    @ApiOperation(value="updateSupermarketadmin", notes="Update Supermarketadmin")
    @RequestMapping(value="", method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Supermarketadmin updateSupermarketadmin(@RequestBody Supermarketadmin supermarketadmin) throws SupermarketadminNotFound {
        return supermarketadminService.updateSupermarketadmin(supermarketadmin);
    }

    @ApiOperation(value="deleteSupermarketadminBySid", notes="Delete Supermarketadmin By Sid")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteSupermarketadminBySid(@PathVariable Integer id) throws SupermarketadminNotFound{
        supermarketadminService.deleteSupermarketadminBySid(id);
        return "ok";
    }

    @ApiOperation(value="getSupermarketadmin", notes="Get Supermarketadmin Info By Sid")
    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Supermarketadmin getSupermarketadmin(@PathVariable Integer id) {
        return supermarketadminService.getSupermarketadminBySid(id);
    }

	@ApiOperation(value="getAllSupermarketadmin", notes="Get All Supermarketadmin info")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "query",required = false, value = "Filter. e.g. col1:v1,col2:v2 ...",dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "fields",required = false,value = "Fields returned. e.g. col1,col2 ...",dataType = "String"),*/
            @ApiImplicitParam(paramType = "query",name = "sortby",required = false,value = "Order corresponding to each sortby field. e.g. col1:desc,col2:asc ...",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",required = false,value = "Limit the size of result set. Must be an integer",dataType = "Int"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",required = false,value = "Start position of result set. Must be an integer",dataType = "Int")})
    @RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Supermarketadmin> getAllSupermarketadmin(@RequestParam(value = "query",required = false) String query,
                                 /*@RequestParam(value = "fields",required = false) String fields,*/
                                 @RequestParam(value = "sortby",required = false) String sortby,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pagesize",required = false) Integer pagesize) {
        return supermarketadminService.getAllSupermarketadmin(query,/*fields,*/sortby,page,pagesize);
    }
}
