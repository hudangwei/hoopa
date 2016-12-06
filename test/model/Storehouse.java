package cn.huimin100.hmsp.market.model;

import javax.persistence.*;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="storehouse")
@Data
public class Storehouse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Id",nullable = false)
    private Integer id;
    @ApiModelProperty(value="仓库名称")
    @Column(name="Name",length=50)
    private String name;
    
    @Column(name="CityId")
    private Integer cityid;
    
    @Column(name="branch_Id")
    private Integer branchid;
    
    @Column(name="Details")
    private String details;
    
    @Column(name="X_Position")
    private Double xposition;
    
    @Column(name="Y_Position")
    private Double yposition;
    
    @Column(name="storeAddress",length=100)
    private String storeaddress;
    
    @Column(name="IsCenterStore")
    private Integer iscenterstore;
    
    @Column(name="MyCenterStore")
    private Integer mycenterstore;
    @ApiModelProperty(value="0erp1停用2wms")
    @Column(name="disabled")
    private Integer disabled;
    @ApiModelProperty(value="0真实库1非真实库2wms")
    @Column(name="is_unreal")
    private Integer isunreal;
    @ApiModelProperty(value="是否需要同步 0不需要1需要")
    @Column(name="sync_state")
    private Short syncstate;
}

