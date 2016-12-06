package cn.huimin100.hmsp.market.model;

import javax.persistence.*;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="supermarketadmin")
@Data
public class Supermarketadmin {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(value="主键id")
    @Column(name="S_Id",nullable = false)
    private Integer sid;
    @ApiModelProperty(value="市超管理员名称")
    @Column(name="S_AdminLogin",length=20)
    private String sadminlogin;
    @ApiModelProperty(value="超市管理员账号")
    @Column(name="S_AdminLoginNo",length=20)
    private String sadminloginno;
    @ApiModelProperty(value="联系电话")
    @Column(name="S_SuperMarketTelphone",length=15)
    private String ssupermarkettelphone;
    @ApiModelProperty(value="邮箱")
    @Column(name="S_SuperMarketEmail",length=30)
    private String ssupermarketemail;
    @ApiModelProperty(value="关联SuperMarket表id")
    @Column(name="S_SuperMarketId")
    private Integer ssupermarketid;
    @ApiModelProperty(value="超市管理员密码")
    @Column(name="S_AdminPassword",length=50)
    private String sadminpassword;
    @ApiModelProperty(value="超市会员等级")
    @Column(name="S_AdminGrade")
    private Integer sadmingrade;
    @ApiModelProperty(value="过期时间")
    @Column(name="S_AdminTimeOut")
    private Date sadmintimeout;
    
    @Column(name="S_CostExpireTime")
    private Date scostexpiretime;
    @ApiModelProperty(value="手机验证")
    @Column(name="S_MobileCheck",length=100)
    private String smobilecheck;
    @ApiModelProperty(value="webWPF登录验证")
    @Column(name="S_SecurtyCheck",length=50)
    private String ssecurtycheck;
    
    @Column(name="S_weixin",length=100)
    private String sweixin;
    @ApiModelProperty(value="资金帐号id")
    @Column(name="S_userid")
    private Integer suserid;
    @ApiModelProperty(value="0:未激活 1:激活 2:禁用")
    @Column(name="S_flag")
    private Integer sflag;
}

