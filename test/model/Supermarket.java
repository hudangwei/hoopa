package cn.huimin100.hmsp.market.model;

import javax.persistence.*;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="supermarket")
@Data
public class Supermarket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(value="自动增长id")
    @Column(name="S_Id",nullable = false)
    private Integer sid;
    @ApiModelProperty(value="超市名称")
    @Column(name="S_SuperMarket",length=30)
    private String ssupermarket;
    @ApiModelProperty(value="所属小区id")
    @Column(name="S_CircleId")
    private Integer scircleid;
    @ApiModelProperty(value="小区具体地址")
    @Column(name="S_Address",length=50)
    private String saddress;
    @ApiModelProperty(value="超市营业开始时间")
    @Column(name="S_ShopStartTime",length=30)
    private String sshopstarttime;
    @ApiModelProperty(value="超市营业结束时间")
    @Column(name="S_ShopEndeTime",length=30)
    private String sshopendetime;
    @ApiModelProperty(value="运费")
    @Column(name="S_SendMoney")
    private Float ssendmoney;
    @ApiModelProperty(value="免运费")
    @Column(name="S_NoSendMoney")
    private Float snosendmoney;
    @ApiModelProperty(value="预计送货时间")
    @Column(name="S_SendTime",length=50)
    private String ssendtime;
    @ApiModelProperty(value="预计送货范围")
    @Column(name="S_SendArea",length=100)
    private String ssendarea;
    @ApiModelProperty(value="超市图片")
    @Column(name="S_Image",length=100)
    private String simage;
    @ApiModelProperty(value="经营范围")
    @Column(name="S_BusinessScope",length=100)
    private String sbusinessscope;
    @ApiModelProperty(value="联系电话")
    @Column(name="S_Telephone",length=100)
    private String stelephone;
    @ApiModelProperty(value="百度地图地址")
    @Column(name="S_InBaiDuAddress",length=300)
    private String sinbaiduaddress;
    @ApiModelProperty(value="等级id")
    @Column(name="S_LeaveId")
    private Integer sleaveid;
    @ApiModelProperty(value="超市状态（0，开通;1,未开通3删除）")
    @Column(name="S_State")
    private Integer sstate;
    
    @Column(name="S_ScoreNumbers")
    private Integer sscorenumbers;
    @ApiModelProperty(value="200和伊利客资一样-1伊利导入-2业务员开通 -3用户注册-4商企客资注册-5内部需要过滤定单 -6 java版客服")
    @Column(name="S_Score")
    private Integer sscore;
    @ApiModelProperty(value="联系人")
    @Column(name="S_RelationPeople",length=20)
    private String srelationpeople;
    
    @Column(name="S_SuperMarketGrade")
    private Integer ssupermarketgrade;
    
    @Column(name="S_MemberEndTime")
    private Date smemberendtime;
    
    @Column(name="S_X_Position")
    private Double sxposition;
    
    @Column(name="S_Y_Position")
    private Double syposition;
    @ApiModelProperty(value="现在用于(推荐的手机号)")
    @Column(name="S_SuperMarketCheck",length=150)
    private String ssupermarketcheck;
    @ApiModelProperty(value="超市编号")
    @Column(name="S_Code")
    private Integer scode;
    @ApiModelProperty(value="是否供货0供货1暂不供货2注册未开通账号")
    @Column(name="S_IsSupply")
    private Integer sissupply;
    @ApiModelProperty(value="否是加盟0加盟1未加盟")
    @Column(name="S_IsJoin")
    private Integer sisjoin;
    @ApiModelProperty(value="工员ID")
    @Column(name="S_PloyeeId")
    private Integer sployeeid;
    @ApiModelProperty(value="开通日期")
    @Column(name="S_CreateDay")
    private Date screateday;
    @ApiModelProperty(value="所属仓库")
    @Column(name="S_Storehouse")
    private Integer sstorehouse;
    @ApiModelProperty(value="0台球用户1超市2企业3批市4内部用户5大客户6校园客户7KA8二批9厂家对接10市场连锁20大中企业（文具)21小企业（文具)22批发（文具)23门店（文具)")
    @Column(name="S_UserType")
    private Integer susertype;
    @ApiModelProperty(value="营类型经1便利店2超市3烟酒店4网吧5学校6餐饮7食杂8批零")
    @Column(name="S_BusinessType")
    private Integer sbusinesstype;
    @ApiModelProperty(value="分类0新星1明星2金牛3问题4瘦狗")
    @Column(name="S_FeiL")
    private Integer sfeil;
    @ApiModelProperty(value="超市在小区中的序号(现在为是否允许赊账0不允许200允许)")
    @Column(name="MarketNum")
    private Integer marketnum;
    @ApiModelProperty(value="0普通10A类连锁连20B类连锁30c131c232c333c4,40D")
    @Column(name="S_KeyPoint")
    private Integer skeypoint;
    
    @Column(name="s_lineId")
    private Integer slineid;
    
    @Column(name="s_num")
    private Integer snum;
    @ApiModelProperty(value="1:20平米以下, 2:20-40平米, 3:40-60平米, 4:60-80平米, 5:80-100平米, 6:100-120平米, 7:120平米以上,")
    @Column(name="s_squaremetre")
    private Integer ssquaremetre;
    @ApiModelProperty(value="资金帐号")
    @Column(name="S_userid")
    private Integer suserid;
    
    @Column(name="business_user",length=14,nullable = false)
    private String businessuser;
    
    @Column(name="service_user",length=14,nullable = false)
    private String serviceuser;
    @ApiModelProperty(value="gps定位地址")
    @Column(name="S_NewAddress",length=50)
    private String snewaddress;
    @ApiModelProperty(value="S_gpsx")
    @Column(name="S_gpsx")
    private Double sgpsx;
    
    @Column(name="S_gpsAddress",length=50)
    private String sgpsaddress;
    @ApiModelProperty(value="gpsy坐标")
    @Column(name="S_gpsy")
    private Double sgpsy;
    @ApiModelProperty(value="新街道id")
    @Column(name="S_NewCircleId",length=12)
    private String snewcircleid;
    
    @Column(name="updatetime")
    private Date updatetime;
    @ApiModelProperty(value="距离仓库距离单位米")
    @Column(name="distance")
    private Integer distance;
    @ApiModelProperty(value="抢单开启状态1开启，2为关闭")
    @Column(name="openOrClose",length=20)
    private String openorclose;
    @ApiModelProperty(value="抢单送货开始时间")
    @Column(name="startDate",length=50)
    private String startdate;
    @ApiModelProperty(value="抢单送货结束时间")
    @Column(name="stopDate",length=50)
    private String stopdate;
    @ApiModelProperty(value="环路范围（内）")
    @Column(name="ringRd")
    private Integer ringrd;
    @ApiModelProperty(value="0:社区店 1:街道店 2:商圈店 3:写字楼店 4:连锁类 5:军/政大院店 6:工/矿企业店 7:景区/公园 8:医院 9:学校 10:批发 11:其他")
    @Column(name="S_classic")
    private Integer sclassic;
    @ApiModelProperty(value="废弃  0普通街道\r\n1写字楼\r\n2商场/步行街\r\n3休闲娱乐\r\n4中式餐饮\r\n5西式餐厅\r\n6医院\r\n7大/专学校\r\n8初/高级中学\r\n9小学校\r\n10培训机构\r\n11商务快捷式酒店\r\n12星级酒店\r\n13批市\r\n14散批\r\n15直营店\r\n16加盟店\r\n17其他特通\r\n18团购\r\n19会所\r\n")
    @Column(name="S_definition")
    private Integer sdefinition;
    @ApiModelProperty(value="0:中小型超市 1:便利店 2:食杂店 3:名烟名酒店 4:水吧/报亭 5:蔬果店 6:彩票店 7:文具店 8:台球 9:网吧/网咖 10:连锁便利店 11:大/专院校 12:中/小学校 13:棋牌室/健身房/茶楼/电影院/电玩城 14:酒吧/KTV 15:批零兼营 16:批发市场 17:高中档餐饮 18:普通餐饮 19:粮油店 20:加油站 21:团购 22:其他")
    @Column(name="S_type")
    private Integer stype;
}

