package cn.huimin100.hmsp.market.controller;

import cn.huimin100.hmsp.market.exception.*;
import cn.huimin100.hmsp.market.model.*;
import cn.huimin100.hmsp.market.service.SupermarketService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supermarket")
@Api(value = "SupermarketController", description = "oprations for Supermarket")
public class SupermarketController {
    @Autowired
    private SupermarketService supermarketService;

    @ApiOperation(value="addSupermarket", notes="Create Supermarket")
    @RequestMapping(value="", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Supermarket addSupermarket(@RequestBody Supermarket supermarket) {
        return supermarketService.addSupermarket(supermarket);
    }

    @ApiOperation(value="updateSupermarket", notes="Update Supermarket")
    @RequestMapping(value="", method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Supermarket updateSupermarket(@RequestBody Supermarket supermarket) throws SupermarketNotFound {
        return supermarketService.updateSupermarket(supermarket);
    }

    @ApiOperation(value="deleteSupermarketBySid", notes="Delete Supermarket By Sid")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteSupermarketBySid(@PathVariable Integer id) throws SupermarketNotFound{
        supermarketService.deleteSupermarketBySid(id);
        return "ok";
    }

    @ApiOperation(value="getSupermarket", notes="Get Supermarket Info By Sid")
    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Supermarket getSupermarket(@PathVariable Integer id) {
        return supermarketService.getSupermarketBySid(id);
    }

	@ApiOperation(value="getAllSupermarket", notes="Get All Supermarket info")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "query",required = false, value = "Filter. e.g. col1:v1,col2:v2 ...",dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "fields",required = false,value = "Fields returned. e.g. col1,col2 ...",dataType = "String"),*/
            @ApiImplicitParam(paramType = "query",name = "sortby",required = false,value = "Order corresponding to each sortby field. e.g. col1:desc,col2:asc ...",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",required = false,value = "Limit the size of result set. Must be an integer",dataType = "Int"),
            @ApiImplicitParam(paramType = "query",name = "pagesize",required = false,value = "Start position of result set. Must be an integer",dataType = "Int")})
    @RequestMapping(value="", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Supermarket> getAllSupermarket(@RequestParam(value = "query",required = false) String query,
                                 /*@RequestParam(value = "fields",required = false) String fields,*/
                                 @RequestParam(value = "sortby",required = false) String sortby,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pagesize",required = false) Integer pagesize) {
        return supermarketService.getAllSupermarket(query,/*fields,*/sortby,page,pagesize);
    }
}
