/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50618
Source Host           : localhost:3306
Source Database       : hfangw

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2017-09-16 16:06:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_atta
-- ----------------------------
DROP TABLE IF EXISTS `file_atta`;
CREATE TABLE `file_atta` (
  `ATTA_ID` varchar(32) DEFAULT NULL,
  `ATTA_NAME` varchar(100) DEFAULT NULL,
  `ATTA_PATH` varchar(255) DEFAULT NULL,
  `ATTA_TYPE` varchar(20) DEFAULT NULL,
  `ATTA_SIZE` int(11) DEFAULT NULL,
  `UPLOAD_TIME` datetime DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `ATTA_DESC` varchar(255) DEFAULT NULL,
  `ATTA_ITEM_PATH` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_atta
-- ----------------------------

-- ----------------------------
-- Table structure for info_tcategory
-- ----------------------------
DROP TABLE IF EXISTS `info_tcategory`;
CREATE TABLE `info_tcategory` (
  `COLUMN_ID` varchar(32) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `RANK` int(11) DEFAULT NULL,
  `COLUMN_NAME` varchar(100) DEFAULT NULL,
  `COLUMN_DESCRIBE` varchar(255) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `USING_FLAG` varchar(2) DEFAULT NULL,
  `COLUMN_PATH` varchar(255) DEFAULT NULL,
  `LOGIN_FLAG` varchar(2) DEFAULT NULL,
  `COLUMN_URL` varchar(200) DEFAULT NULL,
  `IS_MAP_SERVICE` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_tcategory
-- ----------------------------
INSERT INTO `info_tcategory` VALUES ('402881fd515b4f9b01515b58cf050002', '-1', '1', '最新资讯', '', '1', '1', '2017-09-03 10:52:40', '0', '0', '', null, '', '0');
INSERT INTO `info_tcategory` VALUES ('40285c815e459b39015e45a7f365000b', '-1', '1', '关于我们', '', '2', '1', '2017-09-03 10:53:08', '0', '0', '', null, '', '0');
INSERT INTO `info_tcategory` VALUES ('402881fd515b4f9b01515b5914d40003', '402881fd515b4f9b01515b58cf050002', '2', '楼盘活动', '', '1', '1', '2017-09-03 10:54:18', '0', '0', '.402881fd515b4f9b01515b58cf050002', null, '', '0');
INSERT INTO `info_tcategory` VALUES ('402881fd515b4f9b01515b5987390004', '402881fd515b4f9b01515b58cf050002', '2', '市场动态', '', '2', '1', '2017-09-03 10:54:32', '0', '0', '.402881fd515b4f9b01515b58cf050002', null, '', '0');

-- ----------------------------
-- Table structure for info_tcontent
-- ----------------------------
DROP TABLE IF EXISTS `info_tcontent`;
CREATE TABLE `info_tcontent` (
  `CONTENT_ID` varchar(32) DEFAULT NULL,
  `CONTENT_MAIN_TITLE` varchar(255) DEFAULT NULL,
  `CONTENT_SUB_TITLE` varchar(255) DEFAULT NULL,
  `ISSUE_ORGAN` varchar(255) DEFAULT NULL,
  `ISSUE_USERNAME` varchar(255) DEFAULT NULL,
  `CONTENT_ABSTRACT` varchar(255) DEFAULT NULL,
  `CONTENT` longtext,
  `ISSUR_DATE` datetime DEFAULT NULL,
  `LOSE_DATE` datetime DEFAULT NULL,
  `USING_FLAG` varchar(2) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `ATTA_ID` varchar(255) DEFAULT NULL,
  `IS_TOP` varchar(2) DEFAULT NULL,
  `RECOMMEND` varchar(2) DEFAULT NULL,
  `VISITOR_VOLUME` int(11) DEFAULT NULL,
  `CHECK_FLAG` varchar(2) DEFAULT NULL,
  `CHECK_BY` varchar(32) DEFAULT NULL,
  `CHECK_DATE` datetime DEFAULT NULL,
  `CHECK_OPINION` varchar(255) DEFAULT NULL,
  `COLUMN_ID` varchar(32) DEFAULT NULL,
  `NEED_CHECK` varchar(2) DEFAULT NULL,
  `IS_PIC_VIDEO` varchar(2) DEFAULT NULL,
  `BACK_GROUND` varchar(50) DEFAULT NULL,
  `LINK_URL` varchar(255) DEFAULT NULL,
  `AREA_ID` varchar(32) DEFAULT NULL,
  `JUMP_URL_FLAG` varchar(1) DEFAULT NULL,
  `JUMP_URL` varchar(200) DEFAULT NULL,
  `THEME_ID` varchar(255) DEFAULT NULL,
  `DEPARTMENT_NAME` varchar(255) DEFAULT NULL,
  `ZIP_CODE` varchar(255) DEFAULT NULL,
  `WORK_DATE` varchar(255) DEFAULT NULL,
  `CONTACT_PHONE` varchar(255) DEFAULT NULL,
  `CONTACT_PERSON` varchar(255) DEFAULT NULL,
  `COMPLAINTS_PHONE` varchar(255) DEFAULT NULL,
  `BELONG_AREA` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `INTRODUCTION` varchar(255) DEFAULT NULL,
  `LNG` varchar(255) DEFAULT NULL,
  `LAT` varchar(255) DEFAULT NULL,
  `DEPARTMENT_SITE` varchar(255) DEFAULT NULL,
  `IS_MAP_SEARCH` varchar(255) DEFAULT NULL,
  `BM_SERVICE_ID` varchar(255) DEFAULT NULL,
  KEY `FKF37AB1F62B988DEA` (`AREA_ID`),
  KEY `FKF37AB1F67BCA4D95` (`CREATE_BY`),
  CONSTRAINT `FKF37AB1F62B988DEA` FOREIGN KEY (`AREA_ID`) REFERENCES `t_sys_area` (`area_id`),
  CONSTRAINT `FKF37AB1F67BCA4D95` FOREIGN KEY (`CREATE_BY`) REFERENCES `t_sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_tcontent
-- ----------------------------

-- ----------------------------
-- Table structure for t_ggfw_type
-- ----------------------------
DROP TABLE IF EXISTS `t_ggfw_type`;
CREATE TABLE `t_ggfw_type` (
  `TYPE_ID` varchar(255) NOT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `TYPE_PATH` longtext,
  `TYPE_NAME` varchar(100) DEFAULT NULL,
  `TYPE_CODE` varchar(20) DEFAULT NULL,
  `TYPE_SORT` varchar(20) DEFAULT NULL,
  `TYPE_MEMO` varchar(200) DEFAULT NULL,
  `TYPE_LOGO` varchar(200) DEFAULT NULL,
  `TYPE_URL` varchar(200) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `USING_FLAG` varchar(2) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ggfw_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_hfw_contacts
-- ----------------------------
DROP TABLE IF EXISTS `t_hfw_contacts`;
CREATE TABLE `t_hfw_contacts` (
  `oid` varchar(50) NOT NULL,
  `linkman` varchar(20) DEFAULT NULL COMMENT '红包领取人',
  `linkphone` varchar(50) DEFAULT NULL COMMENT '红包领取人手机',
  `packetId` varchar(50) DEFAULT NULL COMMENT '红包id',
  `status` int(10) DEFAULT NULL COMMENT '红包兑换状态(1 已领取 2 已领取)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`oid`),
  KEY `pk_contacts_packet` (`packetId`),
  KEY `FK9712E8C4705907EC` (`packetId`),
  CONSTRAINT `FK9712E8C4705907EC` FOREIGN KEY (`packetId`) REFERENCES `t_hfw_redpacket` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hfw_contacts
-- ----------------------------

-- ----------------------------
-- Table structure for t_hfw_kft
-- ----------------------------
DROP TABLE IF EXISTS `t_hfw_kft`;
CREATE TABLE `t_hfw_kft` (
  `OID` varchar(255) NOT NULL,
  `HOUSING_PROJ_ID` varchar(255) DEFAULT NULL,
  `JHDD` varchar(255) DEFAULT NULL,
  `KF_TIME` varchar(255) DEFAULT NULL,
  `BM_END_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK902E4748FF492187` (`HOUSING_PROJ_ID`),
  CONSTRAINT `FK902E4748FF492187` FOREIGN KEY (`HOUSING_PROJ_ID`) REFERENCES `t_housing_project` (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hfw_kft
-- ----------------------------

-- ----------------------------
-- Table structure for t_hfw_kft_yy
-- ----------------------------
DROP TABLE IF EXISTS `t_hfw_kft_yy`;
CREATE TABLE `t_hfw_kft_yy` (
  `OID` varchar(255) NOT NULL,
  `LXXM` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `KFT_ID` varchar(255) DEFAULT NULL,
  `YYSJ` datetime DEFAULT NULL,
  `KF_DATE` varchar(255) DEFAULT NULL,
  `BUDGET` varchar(255) DEFAULT NULL,
  `PROJ_NAME` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `PICKUP_FLAG` varchar(255) DEFAULT NULL,
  `AREA_ID` varchar(255) DEFAULT NULL,
  `HANDLE_FLAG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK797A83772B988DEA` (`AREA_ID`),
  KEY `FK797A8377CEEC1351` (`KFT_ID`),
  CONSTRAINT `FK797A83772B988DEA` FOREIGN KEY (`AREA_ID`) REFERENCES `t_sys_area` (`area_id`),
  CONSTRAINT `FK797A8377CEEC1351` FOREIGN KEY (`KFT_ID`) REFERENCES `t_hfw_kft` (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hfw_kft_yy
-- ----------------------------

-- ----------------------------
-- Table structure for t_hfw_realty_consultant
-- ----------------------------
DROP TABLE IF EXISTS `t_hfw_realty_consultant`;
CREATE TABLE `t_hfw_realty_consultant` (
  `OID` varchar(255) NOT NULL,
  `CONS_NAME` varchar(255) DEFAULT NULL,
  `MOTTO` varchar(255) DEFAULT NULL,
  `PIC` varchar(255) DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hfw_realty_consultant
-- ----------------------------

-- ----------------------------
-- Table structure for t_hfw_redpacket
-- ----------------------------
DROP TABLE IF EXISTS `t_hfw_redpacket`;
CREATE TABLE `t_hfw_redpacket` (
  `oid` varchar(32) NOT NULL,
  `packet_name` varchar(200) DEFAULT NULL COMMENT '红包名称',
  `packet_money` varchar(32) DEFAULT NULL COMMENT '红包金额',
  `start_time` date DEFAULT NULL COMMENT '开始时间',
  `end_time` date DEFAULT NULL COMMENT '结束时间',
  `houseId` varchar(255) DEFAULT NULL COMMENT '户型id',
  `newId` varchar(32) DEFAULT NULL COMMENT '楼盘活动id',
  `is_del` int(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`oid`),
  KEY `FKD4AF19E82EE062E7` (`houseId`),
  CONSTRAINT `FKD4AF19E82EE062E7` FOREIGN KEY (`houseId`) REFERENCES `t_housing_project` (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hfw_redpacket
-- ----------------------------

-- ----------------------------
-- Table structure for t_housing_project
-- ----------------------------
DROP TABLE IF EXISTS `t_housing_project`;
CREATE TABLE `t_housing_project` (
  `OID` varchar(255) NOT NULL,
  `AREA_ID` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `SALE_HOUS_STATUS` varchar(255) DEFAULT NULL,
  `HOUS_TYPE` varchar(255) DEFAULT NULL,
  `BUILDING_TYPE` varchar(255) DEFAULT NULL,
  `HOUS_ACREAGE` varchar(255) DEFAULT NULL,
  `LAUNCH_TIME` varchar(255) DEFAULT NULL,
  `SALES_LINE` varchar(255) DEFAULT NULL,
  `DISCOUNT` varchar(255) DEFAULT NULL,
  `LATEST_OPENING` varchar(255) DEFAULT NULL,
  `DECO_STANDARD` varchar(255) DEFAULT NULL,
  `PERIOD_RIGHT` varchar(255) DEFAULT NULL,
  `SEARCH_UNIT_PRICE` varchar(255) DEFAULT NULL,
  `PROJ_TYPE` varchar(255) DEFAULT NULL,
  `HOT_SELL_LABEL` varchar(255) DEFAULT NULL,
  `PROJ_NAME` varchar(255) DEFAULT NULL,
  `PICS` varchar(255) DEFAULT NULL,
  `PICS_DESC` varchar(255) DEFAULT NULL,
  `LR_DATE` datetime DEFAULT NULL,
  `DEL_FLAG` varchar(255) DEFAULT NULL,
  `LNGLAT` varchar(255) DEFAULT NULL,
  `ATTENTION` int(11) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK7D9CC68C395D569A` (`SALE_HOUS_STATUS`),
  KEY `FK7D9CC68C2B988DEA` (`AREA_ID`),
  CONSTRAINT `FK7D9CC68C2B988DEA` FOREIGN KEY (`AREA_ID`) REFERENCES `t_sys_area` (`area_id`),
  CONSTRAINT `FK7D9CC68C395D569A` FOREIGN KEY (`SALE_HOUS_STATUS`) REFERENCES `t_sys_dict` (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_housing_project
-- ----------------------------

-- ----------------------------
-- Table structure for t_housing_project_price
-- ----------------------------
DROP TABLE IF EXISTS `t_housing_project_price`;
CREATE TABLE `t_housing_project_price` (
  `OID` varchar(255) NOT NULL,
  `HOUSING_PROJ_ID` varchar(255) DEFAULT NULL,
  `VAL_1` varchar(255) DEFAULT NULL,
  `VAL_2` varchar(255) DEFAULT NULL,
  `VAL_3` varchar(255) DEFAULT NULL,
  `VAL_4` varchar(255) DEFAULT NULL,
  `VAL_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK7F02C9B6FF492187` (`HOUSING_PROJ_ID`),
  CONSTRAINT `FK7F02C9B6FF492187` FOREIGN KEY (`HOUSING_PROJ_ID`) REFERENCES `t_housing_project` (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_housing_project_price
-- ----------------------------

-- ----------------------------
-- Table structure for t_net_certification
-- ----------------------------
DROP TABLE IF EXISTS `t_net_certification`;
CREATE TABLE `t_net_certification` (
  `ID` varchar(32) NOT NULL,
  `CERTIFICATION_NAME` varchar(50) DEFAULT NULL,
  `CERTIFICATION_TYPE` varchar(1) DEFAULT NULL,
  `CERTIFICATION_IMG` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_net_certification
-- ----------------------------

-- ----------------------------
-- Table structure for t_net_user
-- ----------------------------
DROP TABLE IF EXISTS `t_net_user`;
CREATE TABLE `t_net_user` (
  `ID` varchar(32) NOT NULL,
  `User_login` varchar(50) DEFAULT NULL,
  `User_pass` varchar(50) DEFAULT NULL,
  `User_name` varchar(200) DEFAULT NULL,
  `User_type` varchar(50) DEFAULT NULL,
  `User_cardno` varchar(50) DEFAULT NULL,
  `User_current_area` varchar(50) DEFAULT NULL,
  `User_census` varchar(50) DEFAULT NULL,
  `User_address` varchar(255) DEFAULT NULL,
  `User_tel` varchar(50) DEFAULT NULL,
  `User_phone` varchar(50) DEFAULT NULL,
  `User_email` varchar(100) DEFAULT NULL,
  `Company_organ_code` varchar(50) DEFAULT NULL,
  `Company_business_license` varchar(50) DEFAULT NULL,
  `Company_type` varchar(32) DEFAULT NULL,
  `Company_reg_capital` varchar(50) DEFAULT NULL,
  `Company_legal` varchar(50) DEFAULT NULL,
  `Society_name` varchar(200) DEFAULT NULL,
  `Society_charge_dept` varchar(200) DEFAULT NULL,
  `Society_register_number` varchar(100) DEFAULT NULL,
  `Society_nature` varchar(32) DEFAULT NULL,
  `Society_law_person` varchar(50) DEFAULT NULL,
  `Society_msz` varchar(50) DEFAULT NULL,
  `Society_contact_person` varchar(50) DEFAULT NULL,
  `Society_address` varchar(200) DEFAULT NULL,
  `User_createdate` datetime NOT NULL,
  `User_image_url` varchar(200) DEFAULT NULL,
  `User_activating` varchar(50) DEFAULT NULL,
  `User_attest` varchar(50) DEFAULT NULL,
  `User_del_status` varchar(32) DEFAULT NULL,
  `USER_LXR` varchar(100) DEFAULT NULL,
  `USER_CARDTYPE` varchar(32) DEFAULT NULL,
  `USER_SECR_QUES_ID_1` varchar(32) DEFAULT NULL,
  `USER_SECR_QUES_ID_2` varchar(32) DEFAULT NULL,
  `USER_SECR_QUES_ANSW_1` varchar(200) DEFAULT NULL,
  `USER_SECR_QUES_ANSW_2` varchar(200) DEFAULT NULL,
  `USER_SECR_MODI_DATE` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `USER_PASS_MODI_DATE` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `USER_HEAD_IMAGE` varchar(255) DEFAULT NULL,
  `VERIFY_EMAIL_CODE` varchar(32) DEFAULT NULL,
  `SAFE_EMAIL` varchar(100) DEFAULT NULL,
  `VAID_CODE` varchar(32) DEFAULT NULL,
  `EMAIL_UNVERIFIED` varchar(100) DEFAULT NULL,
  `SEX` varchar(1) DEFAULT NULL,
  `BIRTH_DAY` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CERTT_YPE` varchar(10) DEFAULT NULL,
  `ZIP_CODE` varchar(50) DEFAULT NULL,
  `QQ` varchar(50) DEFAULT NULL,
  `COMPANY_NAME` varchar(255) DEFAULT NULL,
  `WEBSITE` varchar(255) DEFAULT NULL,
  `WEIBO_SITE` varchar(255) DEFAULT NULL,
  `COMPANY_INFO_SITE` varchar(255) DEFAULT NULL,
  `COMPANY_INTRODUCTION` varchar(255) DEFAULT NULL,
  `TAX_NO` varchar(255) DEFAULT NULL,
  `DEPOSIT_BANK` varchar(255) DEFAULT NULL,
  `BANK_ACCOUNT` varchar(255) DEFAULT NULL,
  `CONTACTER_NAME` varchar(255) DEFAULT NULL,
  `CONTACTER_TEL` varchar(255) DEFAULT NULL,
  `CONTACTER_EMAIL` varchar(255) DEFAULT NULL,
  `FAX_NUM` varchar(50) DEFAULT NULL,
  `SETUP_DATE` varchar(30) DEFAULT NULL,
  `COMPNAY_REGISTERED_ADDR` varchar(50) DEFAULT NULL,
  `BUSSINESS_SCOPE` varchar(255) DEFAULT NULL,
  `REGISTERED_CAPITAL` varchar(20) DEFAULT NULL,
  `PRODUCE_BUSSINESS_ADDR` varchar(50) DEFAULT NULL,
  `USER_CHECK_STATUS` varchar(2) DEFAULT NULL,
  `BANGDING_PHONE_STATUS` varchar(1) DEFAULT NULL,
  `MZ` varchar(20) DEFAULT NULL,
  `N_USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTACTER_PHONE` varchar(20) DEFAULT NULL,
  `JPUSH_REG_ID` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `N_USER_ID` (`N_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_net_user
-- ----------------------------
INSERT INTO `t_net_user` VALUES ('40288186504561aa0150457a06c40002', '叶', '202cb962ac59075b964b07152d234b70', '良辰', '1', '368886198305089635', null, null, '', '13105576938', '13105576938', 'liang@sins.com', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-02-15 16:29:45', null, null, null, '1', null, null, null, null, null, null, '2015-10-14 09:56:40', '2016-02-15 16:29:45', null, null, null, '2535', null, null, '2016-02-15 16:29:45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '1', null, null);
INSERT INTO `t_net_user` VALUES ('402881d94c6eee7f014c6ef1fd8f0002', 'Zww', '202cb962ac59075b964b07152d234b70', 'Zww', '1', '4879764646494949', null, null, '', '18096605337', '18096605337', '1191683548@qq.com', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-02-15 16:29:45', null, null, null, null, null, null, null, null, null, null, '2016-02-15 16:29:45', '2016-02-15 16:29:45', null, null, null, '3052', null, null, '2016-02-15 16:29:45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '3', null, null);
INSERT INTO `t_net_user` VALUES ('402881e94ff3a9cc014ff3b34b7a000a', 'yaozhiyuan', 'e10adc3949ba59abbe56e057f20f883e', '姚志远', '1', '340403199305065', null, null, '', '', '18256027749', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-09-22 14:17:37', null, null, '1', '0', null, null, null, null, null, null, '2015-12-09 15:12:39', '2016-02-15 16:29:45', null, null, null, '49e5928478b849a893a6603266bd2280', null, '0', '2016-02-15 16:29:45', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '汉族', '4', null, null);
INSERT INTO `t_net_user` VALUES ('402881f04c0b9b83014c0dda772e0069', 'zhangsansan', 'e10adc3949ba59abbe56e057f20f883e', '张三三', '1', '522323198907163192', null, null, '', '', '15196215236', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-03-12 19:59:23', null, null, '1', '0', null, null, null, null, null, null, '2015-12-18 13:51:38', '2016-02-15 16:29:45', null, null, null, '8692c7a5478549158adeb8f4c1cc5bed', null, '1', '2016-02-15 16:29:45', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '汉族', '5', null, null);
INSERT INTO `t_net_user` VALUES ('402881f4511d8e5401511e7ca6c6000a', 'wanger', 'e10adc3949ba59abbe56e057f20f883e', 'sdfsdfsdfsdf', '2', '511702198002221308', null, null, null, null, '15056236854', null, null, '42342354', '企业法人', null, '王二', null, null, null, null, null, null, null, null, '2015-11-19 14:44:24', null, null, '1', null, null, null, null, null, null, null, '2015-11-19 15:07:17', '2016-02-15 16:29:45', null, null, null, null, null, '0', '2016-02-15 16:29:45', null, null, null, 'sdfsdfsdfsdf', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '汉族', '6', null, null);
INSERT INTO `t_net_user` VALUES ('e4c0b7844b35b43d014b35c66268000e', 'lu2009dong', '202cb962ac59075b964b07152d234b70', '卢栋', '1', '142234198707171019', null, null, '', '', '13034706957', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-01-29 20:59:28', null, null, '1', '0', null, null, null, null, null, null, '2015-09-17 09:23:49', '2016-02-15 16:29:45', null, null, null, '59aaf03cbe0d416a8c972070210e86bc', null, '0', '2016-02-15 16:29:45', '1', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '汉族', '7', null, null);
INSERT INTO `t_net_user` VALUES ('e4c0b7844b35d441014b35e15267000b', 'boot', '202cb962ac59075b964b07152d234b70', '彭志峰', '1', '340821198306031011', null, null, '', '', '18656058621', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2015-01-29 21:28:54', null, null, '1', '0', null, null, null, null, null, null, '2015-12-11 11:54:01', '2016-02-15 16:29:45', null, null, null, 'bbca7430bbd94199ac7277a81b85f3f7', null, '1', '2016-02-15 16:29:45', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '汉族', '8', null, null);

-- ----------------------------
-- Table structure for t_net_user_certificate
-- ----------------------------
DROP TABLE IF EXISTS `t_net_user_certificate`;
CREATE TABLE `t_net_user_certificate` (
  `id` varchar(32) NOT NULL,
  `CERTIFICATE_ID` varchar(50) DEFAULT NULL,
  `CERTIFICATE_NO` varchar(20) DEFAULT NULL,
  `CARTIFICATE_BEGIN_DATE` varchar(20) DEFAULT NULL,
  `CARTIFICATE_END_DATE` varchar(20) DEFAULT NULL,
  `CERTIFICATE_IMAGE_ADDR` varchar(50) DEFAULT NULL,
  `CERTIFICATION_STATUS` varchar(2) DEFAULT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `CERTIFICATION_TYPE` varchar(1) DEFAULT NULL,
  `CERTIFICATE_NAME` varchar(255) DEFAULT NULL,
  `AUDIT_CONTENT` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_net_user_certificate
-- ----------------------------

-- ----------------------------
-- Table structure for t_net_user_module
-- ----------------------------
DROP TABLE IF EXISTS `t_net_user_module`;
CREATE TABLE `t_net_user_module` (
  `MODULE_ID` varchar(32) NOT NULL,
  `MODULE_NAME` varchar(100) DEFAULT NULL,
  `MODULE_URL` varchar(100) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `MODULE_APP` varchar(200) DEFAULT NULL,
  `MODULE_DATE` datetime DEFAULT NULL,
  `MODULE_DESC` varchar(200) DEFAULT NULL,
  `MODULE_STATUS` varchar(1) DEFAULT NULL,
  `MODULE_TYPE` varchar(1) DEFAULT NULL,
  `MODULE_CONTROL` varchar(50) DEFAULT NULL,
  `MODULE_LEVEL` int(11) DEFAULT NULL,
  `MODULE_IMG` varchar(100) DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  `MODULE_ORDER` int(11) DEFAULT NULL,
  `MODULE_PIDS` varchar(500) DEFAULT NULL,
  `CONTROL_TYPE` varchar(1) DEFAULT NULL,
  `IS_VALIDATE` varchar(1) DEFAULT NULL,
  `VALIDATE_CONTENT` varchar(100) DEFAULT NULL,
  `IS_JS_REQUEST` varchar(1) DEFAULT NULL,
  `MODULE_TAG` varchar(200) DEFAULT NULL,
  `MENU_OWNER` varchar(2) DEFAULT NULL,
  `AUTHEN` varchar(1) DEFAULT NULL,
  `module_path` varchar(500) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_net_user_module
-- ----------------------------
INSERT INTO `t_net_user_module` VALUES ('402882935308070f0153080c28980001', '1', '1', '-1', null, '2016-02-22 04:15:27', '1', '0', '1', '', '1', null, '0', '1', null, '1', '0', '', '0', null, null, null, '-1.', '2016-02-22 16:24:52', '2016-02-22 16:15:27');
INSERT INTO `t_net_user_module` VALUES ('402882935308070f0153080c5f2d0002', '12', '12', '402882935308070f0153080c28980001', null, '2016-02-22 04:15:41', '12', '0', '1', '', '2', null, '0', '12', null, '1', '1', '12', '1', null, null, null, '-1.402882935308070f0153080c5f2d0002.', '2016-02-23 14:34:53', '2016-02-22 16:15:41');
INSERT INTO `t_net_user_module` VALUES ('402882935308070f0153080ce6d10003', '13', '13', '402882935308070f0153080c5f2d0002', null, '2016-02-22 04:16:16', '13', '0', '2', '13', '3', null, '1', '13', null, '1', '1', '13', '0', null, null, null, '-1.402882935308070f0153080c5f2d0002.402882935308070f0153080ce6d10003.', '2016-02-22 16:27:09', '2016-02-22 16:16:16');

-- ----------------------------
-- Table structure for t_second_hand_house
-- ----------------------------
DROP TABLE IF EXISTS `t_second_hand_house`;
CREATE TABLE `t_second_hand_house` (
  `OID` varchar(255) NOT NULL,
  `AREA_ID` varchar(255) DEFAULT NULL,
  `HOUSE_NAME` varchar(255) DEFAULT NULL,
  `ACREAGE_DICT_ID` varchar(255) DEFAULT NULL,
  `HOUS_TYPE_DICT_ID` varchar(255) DEFAULT NULL,
  `BUILDING_AGE_DICT_ID` varchar(255) DEFAULT NULL,
  `FLOOR_DICT_ID` varchar(255) DEFAULT NULL,
  `BUILDING_TYPE_DICT_ID` varchar(255) DEFAULT NULL,
  `SELLING_PRICE_DICT_ID` varchar(255) DEFAULT NULL,
  `SELLING_PRICE` varchar(255) DEFAULT NULL,
  `REFE_DOWN_PAYMENT` varchar(255) DEFAULT NULL,
  `REFE_MONTH_FOR` varchar(255) DEFAULT NULL,
  `UNIT_PRICE` varchar(255) DEFAULT NULL,
  `HOUS_TYPE` varchar(255) DEFAULT NULL,
  `ACREAGE` varchar(255) DEFAULT NULL,
  `ORIENTATIONS` varchar(255) DEFAULT NULL,
  `FLOOR` varchar(255) DEFAULT NULL,
  `DECO_STANDARD` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `VILLAGE` varchar(255) DEFAULT NULL,
  `LNG_LAT` varchar(255) DEFAULT NULL,
  `BUILDING_AGE` varchar(255) DEFAULT NULL,
  `PICS_1` varchar(255) DEFAULT NULL,
  `PICS_2` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DETAIL_INFO` varchar(255) DEFAULT NULL,
  `DEL_FLAG` varchar(255) DEFAULT NULL,
  `LATEST_EDIT_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK454AA4B02B988DEA` (`AREA_ID`),
  CONSTRAINT `FK454AA4B02B988DEA` FOREIGN KEY (`AREA_ID`) REFERENCES `t_sys_area` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_second_hand_house
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_app_param
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_app_param`;
CREATE TABLE `t_sys_app_param` (
  `PARAM_id` varchar(32) NOT NULL,
  `PARAM_CODE` varchar(50) NOT NULL,
  `PARAM_VAL` varchar(200) DEFAULT NULL,
  `PARAM_NAME` varchar(100) DEFAULT NULL,
  `PARAM_DESC` varchar(500) DEFAULT NULL,
  `PARAM_PATH` varchar(1000) DEFAULT NULL,
  `PARAM_SORT` varchar(2) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `USING_FLAG` varchar(2) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`PARAM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_app_param
-- ----------------------------
INSERT INTO `t_sys_app_param` VALUES ('40285c815e459b39015e45b2eecc000f', 'SSGJZ', '观澜华庭,滨湖前城', '热销楼盘', '用英文 , 号隔开', '-1.', '1', '-1', '0', '0', '2017-09-03 11:06:09', '2017-09-03 11:05:08');
INSERT INTO `t_sys_app_param` VALUES ('4028829352f81bad0152f846dd1f0006', 'MAIL_SEND_YJFWQDXZ', '', '邮件发送人的邮件服务器地址', '邮件发送人的邮件服务器地址', '-1.40288293530688b5015306f85a860002.', '1', '40288293530688b5015306f85a860002', '0', '0', '2016-02-19 14:47:10', '2016-02-19 14:45:39');
INSERT INTO `t_sys_app_param` VALUES ('4028829352f81bad0152f847efff0007', 'MAIL_SEND_FSRYHM', '', '邮件发送人的用户名', '邮件发送人的用户名', '-1.40288293530688b5015306f85a860002.', '', '40288293530688b5015306f85a860002', '0', '0', '2016-02-19 14:47:17', '2016-02-19 14:46:50');
INSERT INTO `t_sys_app_param` VALUES ('4028829352f81bad0152f848d1640008', 'MAIL_SEND_FSRMM', '', '邮件发送人的密码', '邮件发送人的密码', '-1.40288293530688b5015306f85a860002.', '', '40288293530688b5015306f85a860002', '0', '0', '2016-02-19 14:47:47', '2016-02-19 14:47:47');
INSERT INTO `t_sys_app_param` VALUES ('4028829352f81bad0152f84967630009', 'MAIL_SEND_YJDZ', '', '邮件发送人的邮件地址', '邮件发送人的邮件地址', '-1.40288293530688b5015306f85a860002.', '', '40288293530688b5015306f85a860002', '0', '0', '2016-02-19 14:48:26', '2016-02-19 14:48:26');
INSERT INTO `t_sys_app_param` VALUES ('4028829352f81bad0152f86e4d13000a', 'MAIL_SEND_XMFWDZ', '', '系统（项目）访问地址', '', '-1.4028829352f81bad0152f86e4d13000a.', '', '40288293530688b5015306f85a860002', '0', '0', '2016-02-22 11:20:32', '2016-02-19 15:28:44');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b5015306f85a860002', 'MAIL_SEND', '', '邮件管理', '邮件管理', '-1.', '', '-1', '0', '0', '2016-02-22 11:14:12', '2016-02-22 11:14:12');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b5015306ff1d4b0003', 'YYGL', '', '应用管理', '', '-1.', '2', '-1', '0', '0', '2016-02-22 11:21:40', '2016-02-22 11:21:35');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b5015306ffc55f0004', 'YYGL_SYSTEM_NAME', '', '产品名称', '配置html文档的标题，即产品名称', '-1.40288293530688b5015306ffc55f0004.', '1', '40288293530688b5015306ff1d4b0003', '0', '0', '2016-02-22 11:24:50', '2016-02-22 11:22:18');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b501530701609e0005', 'YYGL_UEDT_PROJ_PATH', '', 'web编辑器', 'web编辑器中对于之前已上传的图片、视频等文件进行在线管理时，需要配置系统应用访问前缀，以实现在线管理功能的正常使用。 \r\n注：此参数为可选配置参数，如配置为空字符，则系统会自动动态获取项目访问前缀；', '-1.40288293530688b501530701609e0005.', '2', '40288293530688b5015306ff1d4b0003', '0', '0', '2016-02-22 11:24:21', '2016-02-22 11:24:04');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b501530705ad120006', 'YYGL_LUCENE_INDEX_POS', 'test/index', 'LUCENE索引位置', 'e:/test/index 表示索引建立在操作系统某一盘符下。 \r\ntest/index 表示索引建立在项目根目录下。 手动生成索引可访问连接：当前网站访问前缀/generateIndex.action', '-1.null.', '3', '40288293530688b5015306ff1d4b0003', '0', '0', '2016-02-22 11:28:45', '2016-02-22 11:28:45');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b5015307065b0b0007', 'YYGL_ATTAPATH', 'F:/atta/', '附件存放位置', '项目附件存放位置，如：E:/atta/，/atta/；\r\n注意：如参数中不带盘符，则附件会上传存放到项目更目录下；', '-1.null.', '4', '40288293530688b5015306ff1d4b0003', '0', '0', '2016-02-22 11:29:30', '2016-02-22 11:29:30');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b5015307071fb70008', 'YYGL_WZCSFWL', '40756', '网站初始访问量', '', '-1.null.', '5', '40288293530688b5015306ff1d4b0003', '0', '0', '2016-02-22 11:30:20', '2016-02-22 11:30:20');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b501530708125d0009', 'NRGL', '', '内容管理', '', '-1.', '', '-1', '0', '0', '2016-02-22 11:31:22', '2016-02-22 11:31:22');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b501530708c25a000a', 'NRGL_LMSMRZK', '0', '栏目树是否默认展开', '此参数主要用于控制，内容发布管理和内容发布审核管理相应页面，左侧栏目树是否默认展开，其中1表示默认展开，0表示默认闭合', '-1.40288293530688b501530708c25a000a.', '1', '40288293530688b501530708125d0009', '0', '0', '2016-02-22 11:32:13', '2016-02-22 11:32:07');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b50153070966a4000b', 'NRGL_QHSFKX', '1', '区划是否可手动选择', '此参数主要用于控制内容发布页面，区划是否可选，其中1表示可手动选择区划，0表示默认使用当前登录人所在区划。', '-1.null.', '', '40288293530688b501530708125d0009', '0', '0', '2016-02-22 11:32:49', '2016-02-22 11:32:49');
INSERT INTO `t_sys_app_param` VALUES ('40288293530688b50153070a0f75000c', 'NRGL_MRXJQH', '1', '上级区划是否默认查看下级区划发布的内容', '1表示默认可查看下级区划发布的内容，0表示不可以查看；', '-1.null.', '3', '40288293530688b501530708125d0009', '0', '0', '2016-02-22 11:33:33', '2016-02-22 11:33:33');
INSERT INTO `t_sys_app_param` VALUES ('402882a1530cd07d01530d023d780008', 'ATTAPATH', 'F:/atta/', '附件存放位置', '项目附件存放位置，如：E:/atta/，/atta/；\r\n注意：如参数中不带盘符，则附件会上传存放到项目更目录下；', '-1.', '', '-1', '0', '0', '2016-02-23 15:22:43', '2016-02-23 15:22:43');

-- ----------------------------
-- Table structure for t_sys_area
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_area`;
CREATE TABLE `t_sys_area` (
  `area_id` varchar(32) NOT NULL,
  `top_id` varchar(32) DEFAULT NULL,
  `area_code` varchar(20) NOT NULL,
  `area_name` varchar(100) NOT NULL,
  `parent_code` varchar(50) NOT NULL,
  `area_level` varchar(1) NOT NULL,
  `del_flag` varchar(1) NOT NULL,
  `area_sort` varchar(10) DEFAULT NULL,
  `area_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_area
-- ----------------------------
INSERT INTO `t_sys_area` VALUES ('20130419093149037845634429844034', '-1', '360000', '某某省', '-1', '2', '0', '1', '-1.20130419093149037845634429844034.');
INSERT INTO `t_sys_area` VALUES ('40285c815e459b39015e45adeb62000e', '402880815399823b01539992bfcb0011', '340103', '庐阳区', 'HF', '3', '0', '1', '-1.20130419093149037845634429844034.20130419093149037845634429844034.40285c815e459b39015e45adeb62000e.');
INSERT INTO `t_sys_area` VALUES ('402880815399823b01539992bfcb0011', '20130419093149037845634429844034', 'HF', '合肥市', '360000', '2', '0', '2', '-1.20130419093149037845634429844034.20130419093149037845634429844034.');
INSERT INTO `t_sys_area` VALUES ('4028d58150ff7a4c0150ffa0662e0006', '4028d58150ff7a4c0150ffa012de0005', 'YH', '瑶海区', 'HF', '3', '0', '1', '-1.20130419093149037845634429844034.20130419093149037845634429844034.4028d58150ff7a4c0150ffa012de0005.');

-- ----------------------------
-- Table structure for t_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `dict_id` varchar(32) NOT NULL,
  `dict_code` varchar(50) DEFAULT NULL,
  `dict_name` varchar(100) DEFAULT NULL,
  `dict_memo` varchar(255) DEFAULT NULL,
  `dict_path` varchar(255) DEFAULT NULL,
  `status_flag` varchar(1) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e4589408c0009', 'HXFL', '户型分类', '', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e4589ab3f000a', 'HXFL_1', '一室', '', '-1.40285c815e4571ca015e4589ab3f000a.', '0', '0', '40285c815e4571ca015e4589408c0009');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458a3f66000b', 'JZLX', '建筑类型', '', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458a6e72000c', 'RXBQ', '热销标签', '', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458a9770000d', 'LPDJ', '楼盘单价', '', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458abbde000e', 'SLZT', '售楼状态', '', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458b1c9c000f', 'ESF_SJ', '售价', '二手房', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458b41ff0010', 'ESF_MJ', '面积', '二手房', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458b6a6a0011', 'ESF_FX', '房型', '二手房', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458b8fb90012', 'ESF_JZND', '建造年代', '二手房', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458bb4240013', 'ESF_LC', '楼层', '二手房', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458be0100014', 'ESF_FWLX', '房屋类型', '二手房', '-1.', '0', '0', '-1');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e458c772f0015', 'HXFL_2', '二室', '', '-1.40285c815e4571ca015e458c772f0015.', '0', '0', '40285c815e4571ca015e4589408c0009');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e459094b30016', 'JZLX_1', '住宅', '', '-1.40285c815e4571ca015e459094b30016.', '0', '0', '40285c815e4571ca015e458a3f66000b');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e4590e83f0017', 'JZLX_2', '别墅', '', '-1.40285c815e4571ca015e4590e83f0017.', '0', '0', '40285c815e4571ca015e458a3f66000b');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e45914c9f0018', 'RXBQ_1', '学区房', '', '-1.40285c815e4571ca015e45914c9f0018.', '0', '0', '40285c815e4571ca015e458a6e72000c');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e4591a9f80019', 'LPDJ_1', '8千以下', '', '-1.40285c815e4571ca015e4591a9f80019.', '0', '0', '40285c815e4571ca015e458a9770000d');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e45921463001a', 'LPDJ_2', '8千-1万', '', '-1.40285c815e4571ca015e45921463001a.', '0', '0', '40285c815e4571ca015e458a9770000d');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e45934faf001c', 'SLZT_1', '在售', '', '-1.40285c815e4571ca015e45934faf001c.', '0', '0', '40285c815e4571ca015e458abbde000e');
INSERT INTO `t_sys_dict` VALUES ('40285c815e4571ca015e45937399001d', 'SLZT_2', '期房', '', '-1.40285c815e4571ca015e45937399001d.', '0', '0', '40285c815e4571ca015e458abbde000e');

-- ----------------------------
-- Table structure for t_sys_holiday
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_holiday`;
CREATE TABLE `t_sys_holiday` (
  `oid` varchar(255) NOT NULL,
  `holiday_date` varchar(27) DEFAULT NULL,
  `holiday_type` varchar(1) DEFAULT NULL,
  `holiday_memo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_holiday
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_local
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_local`;
CREATE TABLE `t_sys_local` (
  `LOCAL_ID` varchar(32) NOT NULL,
  `TOP_ID` varchar(32) DEFAULT NULL,
  `LOCAL_CODE` varchar(100) DEFAULT NULL,
  `LOCAL_NAME` varchar(100) DEFAULT NULL,
  `TOP_CODE` varchar(100) DEFAULT NULL,
  `LOCAL_LEVEL` varchar(1) DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  `LOCAL_SORT` varchar(10) DEFAULT NULL,
  `LOCAL_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOCAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_local
-- ----------------------------
INSERT INTO `t_sys_local` VALUES ('110000', '-1', '110000', '北京市', '-1', '1', '0', '1', '-1.110000.');

-- ----------------------------
-- Table structure for t_sys_log_action_desc
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log_action_desc`;
CREATE TABLE `t_sys_log_action_desc` (
  `OID` varchar(32) NOT NULL,
  `ACTION_URL` varchar(200) DEFAULT NULL,
  `ACTION_DESC` varchar(200) DEFAULT NULL,
  `ACTION_KEY` varchar(100) DEFAULT NULL,
  `USE_FLAG` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_log_action_desc
-- ----------------------------
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e4571ca015e457b2cb20003', 'buidingSearch.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e4571ca015e457b49f30004', 'houseSearch.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e4571ca015e457b58810005', 'newsFrames.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e4571ca015e457b5bec0006', 'newsList.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e4571ca015e457e658d0008', 'yuyueKftFrames.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e459b39015e45a496aa0002', 'editInfoTcategoryAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('40285c815e459b39015e45a723c80006', 'delInfoTcategoryAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028808152eddd1c0152ee061a500014', 'getJSONSysModule.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028819f520b3f0801520b5ec0990006', 'batchRestoreInfoContent.action', '从回收站中批量还原发布内容', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028819f5216003401521600d96c0001', 'ggg.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881e551cdfa540151ce0df5670004', 'delSysModuleAjax.action', '异步删除菜单信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881ee51899597015189b4b0060004', 'editSysModuleAjax.action', 'ajax添加修改系统菜单', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881ee518a9b8b01518aa4faa60008', 'editSysRole.action', '保存修改角色信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881ee518a9b8b01518aa9d6f5003b', 'editSysUser.action', '初始化修改个人信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881f151c33bce0151c345e7b40005', 'editSysDictAjax.action', 'ajax添加修改数据字典目录', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881f152108fa90152109062e00001', 'createLucene.action', '生成新闻发布索引', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881f644fd6b840144fd97a30b0006', 'doLoginSuccess.action', '工作人员登录', '登录成功', '0');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881fd518a5d4301518a64ae9b000c', 'editInfoTcontent.action', '初始化添加或删除内容发布页面', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881fd518a5d4301518a653cc8000d', 'saveOrUpdateInfoTcontent.action', '保存内容发布信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881fd518a5d4301518a65c4940010', 'auditInfoTcontent.action', '初始化栏目内容审核页面', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881fd518a5d4301518a65d17a0011', 'addOrUpdateAuditInfoContent.action', '发布内容审核信息保存', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402881fd518a728101518a7a795c0005', 'editMyUserInfo.action', '修改个人信息页面初始化', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829352f81bad0152f82f33db0001', 'chkSysAppParamCodeUniqueAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829352f81bad0152f82f348a0002', 'editSysAppParamAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829352f81bad0152f8307c7a0005', 'delSysAppParamAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882935307d881015307eca6ba0003', 'editNetUserModuleAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882935308145301530816de4a0001', 'delNetUserModuleAjax.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52df10300152df1c42e00001', 'editGgfwTypeAjax.action', '异步提交公共服务分类信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52e282090152e2852d3e0004', 'delGgfwTypeAjax.action', '异步删除公共服务分类信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52e305e20152e3067ecf0001', 'editZjblTypeAjax.action', '异步提交证件办理分类信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52e360af0152e3612a9a0002', 'chkZjblTypeCodeUniqueAjax.action', '证件办理分类编码唯一性校验', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52e360af0152e3627c880005', 'delZjblTypeAjax.action', '删除证件办理分类信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52e360af0152e365bdc40008', 'chkGgfwTypeCodeUniqueAjax.action', '服务分类编码唯一性校验', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52e8b1880152e94e896b0004', 'clearSysLog.action', '清理系统日志', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('4028829a52ecc5330152ecfb14cf0002', 'deleteLogActionDesc.action', '删除系统功能描述信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882a1530bf76501530c3ede4f0002', 'resetPassword.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882a1530bf76501530c42501c0003', 'selectDetailInfo.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882a1530cd07d01530cd1c7e30002', 'deleteNetUser.action', '未定义', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b7aed590009', 'delSysOrgan.action', '删除机构信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b7cccf1000a', 'delSysRole.action', '删除角色信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b816f92000c', 'deleteSysUser.action', '删除用户信息', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b825a6b000e', 'editSysNotice.action', '增加或修改公告信息提交', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b852eb50011', 'deleteSysNotice.action', '删除公告', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b87cbb40012', 'editSysWorkbench.action', '新增或者修改工作台信息提交', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882b6520b4b4001520b87e5210014', 'deleteSysWorkbench.action', '删除工作台', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882be5184e1dd015185246896003b', 'saveOrUpdateOrgan.action', '保存或修改机构信息提交', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882be518532f901518539d3430001', 'editLogActionDesc.action', '修改或添加系统功能描述信息提交', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882be518579f60151857ba69f0006', 'validatCode.action', '验证码验证', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882be5185817f01518581feaf0001', 'doWebUserLogin.action', '网站用户登录验证', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882be5185817f0151858200bf0002', 'webIndex.action', '进入网站首页', '未定义', '1');
INSERT INTO `t_sys_log_action_desc` VALUES ('402882be51cdbfd00151cdc15f7d0002', 'saveSysHoliday.action', '节假日信息保存', '未定义', '1');

-- ----------------------------
-- Table structure for t_sys_log_info
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log_info`;
CREATE TABLE `t_sys_log_info` (
  `OID` varchar(32) NOT NULL,
  `TYPE` int(11) NOT NULL,
  `ACTTEXT` varchar(20000) DEFAULT NULL,
  `USERNAME` varchar(200) NOT NULL,
  `USEROID` varchar(32) NOT NULL,
  `ORGANID` varchar(32) DEFAULT NULL,
  `ACTTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IP` varchar(50) NOT NULL,
  `area_id` varchar(32) DEFAULT NULL,
  `roleid` varchar(255) DEFAULT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  `ACTION_URL` varchar(255) DEFAULT NULL,
  `USER_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `area_id` (`area_id`),
  KEY `FKC31FF8462B988DEA` (`area_id`),
  CONSTRAINT `FKC31FF8462B988DEA` FOREIGN KEY (`area_id`) REFERENCES `t_sys_area` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_log_info
-- ----------------------------
INSERT INTO `t_sys_log_info` VALUES ('40285c815e4571ca015e4592a9c7001b', '0', '工作人员登录', 'admin', '1', '', '2017-09-03 10:29:53', '0:0:0:0:0:0:0:1', '20130419093149037845634429844034', '', null, 'doLoginSuccess.action', '1');
INSERT INTO `t_sys_log_info` VALUES ('40285c815e459b39015e459d179f0001', '0', '工作人员登录', 'admin', '1', '', '2017-09-03 10:41:17', '0:0:0:0:0:0:0:1', '20130419093149037845634429844034', '', null, 'doLoginSuccess.action', '1');
INSERT INTO `t_sys_log_info` VALUES ('40285c815e459b39015e45a71dc30005', '1', 'org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [pt.wzgl.nrgl.pojo.InfoTcategory#40285c815e459b39015e45a496c90003]\r\n	at org.hibernate.impl.SessionFactoryImpl$2.handleEntityNotFound(SessionFactoryImpl.java:449)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.load(DefaultLoadEventListener.java:233)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.proxyOrLoad(DefaultLoadEventListener.java:269)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.onLoad(DefaultLoadEventListener.java:152)\r\n	at org.hibernate.impl.SessionImpl.fireLoad(SessionImpl.java:1080)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:977)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:970)\r\n	at util.base.dao.BaseDaoImpl.onLoad(BaseDaoImpl.java:432)\r\n	at pt.wzgl.nrgl.dao.impl.InfoTcategoryDaoImpl.initInfoTcategory(InfoTcategoryDaoImpl.java:45)\r\n	at pt.wzgl.nrgl.service.impl.InfoTcategoryServiceImpl.initInfoTcategory(InfoTcategoryServiceImpl.java:38)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:90)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)\r\n	at com.sun.proxy.$Proxy23.initInfoTcategory(Unknown Source)\r\n	at pt.wzgl.nrgl.action.ContentManagerAction.getJSONInfoTcategory(ContentManagerAction.java:122)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeAction(DefaultActionInvocation.java:453)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeActionOnly(DefaultActionInvocation.java:292)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:255)\r\n	at util.interceptor.LoginInterceptor.intercept(LoginInterceptor.java:27)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at util.interceptor.LoginfoInterceptor.intercept(LoginfoInterceptor.java:91)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor.doIntercept(DefaultWorkflowInterceptor.java:176)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.validator.ValidationInterceptor.doIntercept(ValidationInterceptor.java:265)\r\n	at org.apache.struts2.interceptor.validation.AnnotationValidationInterceptor.doIntercept(AnnotationValidationInterceptor.java:68)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ConversionErrorInterceptor.intercept(ConversionErrorInterceptor.java:138)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.StaticParametersInterceptor.intercept(StaticParametersInterceptor.java:190)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.FileUploadInterceptor.intercept(FileUploadInterceptor.java:243)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor.intercept(ModelDrivenInterceptor.java:100)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ChainingInterceptor.intercept(ChainingInterceptor.java:145)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.PrepareInterceptor.doIntercept(PrepareInterceptor.java:171)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.ServletConfigInterceptor.intercept(ServletConfigInterceptor.java:164)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.MultiselectInterceptor.intercept(MultiselectInterceptor.java:75)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.CheckboxInterceptor.intercept(CheckboxInterceptor.java:90)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.I18nInterceptor.intercept(I18nInterceptor.java:176)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.AliasInterceptor.intercept(AliasInterceptor.java:192)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor.intercept(ExceptionMappingInterceptor.java:187)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.impl.StrutsActionProxy.execute(StrutsActionProxy.java:54)\r\n	at org.apache.struts2.dispatcher.Dispatcher.serviceAction(Dispatcher.java:510)\r\n	at org.apache.struts2.dispatcher.ng.ExecuteOperations.executeAction(ExecuteOperations.java:77)\r\n	at org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.doFilter(StrutsPrepareAndExecuteFilter.java:91)\r\n	at util.filter.StrutsFilter.doFilter(StrutsFilter.java:24)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at util.filter.SetCharacterEncodingFilter.doFilter(SetCharacterEncodingFilter.java:43)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.springframework.orm.hibernate3.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:198)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:76)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:218)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\r\n	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:958)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:452)\r\n	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1087)\r\n	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:637)\r\n	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:316)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n', 'admin', '1', null, '2017-09-03 10:52:14', '0:0:0:0:0:0:0:1', null, null, null, null, null);
INSERT INTO `t_sys_log_info` VALUES ('40285c815e459b39015e45a723ea0007', '1', 'org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [pt.wzgl.nrgl.pojo.InfoTcategory#40285c815e459b39015e45a496c90003]\r\n	at org.hibernate.impl.SessionFactoryImpl$2.handleEntityNotFound(SessionFactoryImpl.java:449)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.load(DefaultLoadEventListener.java:233)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.proxyOrLoad(DefaultLoadEventListener.java:269)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.onLoad(DefaultLoadEventListener.java:152)\r\n	at org.hibernate.impl.SessionImpl.fireLoad(SessionImpl.java:1080)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:977)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:970)\r\n	at util.base.dao.BaseDaoImpl.onLoad(BaseDaoImpl.java:432)\r\n	at pt.wzgl.nrgl.dao.impl.InfoTcategoryDaoImpl.initInfoTcategory(InfoTcategoryDaoImpl.java:45)\r\n	at pt.wzgl.nrgl.service.impl.InfoTcategoryServiceImpl.initInfoTcategory(InfoTcategoryServiceImpl.java:38)\r\n	at pt.wzgl.nrgl.service.impl.InfoTcategoryServiceImpl.delInfoTcategory(InfoTcategoryServiceImpl.java:76)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:90)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)\r\n	at com.sun.proxy.$Proxy23.delInfoTcategory(Unknown Source)\r\n	at pt.wzgl.nrgl.action.ContentManagerAction.delInfoTcategoryAjax(ContentManagerAction.java:242)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeAction(DefaultActionInvocation.java:453)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeActionOnly(DefaultActionInvocation.java:292)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:255)\r\n	at util.interceptor.LoginInterceptor.intercept(LoginInterceptor.java:27)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at util.interceptor.LoginfoInterceptor.intercept(LoginfoInterceptor.java:91)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor.doIntercept(DefaultWorkflowInterceptor.java:176)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.validator.ValidationInterceptor.doIntercept(ValidationInterceptor.java:265)\r\n	at org.apache.struts2.interceptor.validation.AnnotationValidationInterceptor.doIntercept(AnnotationValidationInterceptor.java:68)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ConversionErrorInterceptor.intercept(ConversionErrorInterceptor.java:138)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.StaticParametersInterceptor.intercept(StaticParametersInterceptor.java:190)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.FileUploadInterceptor.intercept(FileUploadInterceptor.java:243)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor.intercept(ModelDrivenInterceptor.java:100)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ChainingInterceptor.intercept(ChainingInterceptor.java:145)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.PrepareInterceptor.doIntercept(PrepareInterceptor.java:171)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.ServletConfigInterceptor.intercept(ServletConfigInterceptor.java:164)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.MultiselectInterceptor.intercept(MultiselectInterceptor.java:75)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.CheckboxInterceptor.intercept(CheckboxInterceptor.java:90)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.I18nInterceptor.intercept(I18nInterceptor.java:176)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.AliasInterceptor.intercept(AliasInterceptor.java:192)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor.intercept(ExceptionMappingInterceptor.java:187)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.impl.StrutsActionProxy.execute(StrutsActionProxy.java:54)\r\n	at org.apache.struts2.dispatcher.Dispatcher.serviceAction(Dispatcher.java:510)\r\n	at org.apache.struts2.dispatcher.ng.ExecuteOperations.executeAction(ExecuteOperations.java:77)\r\n	at org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.doFilter(StrutsPrepareAndExecuteFilter.java:91)\r\n	at util.filter.StrutsFilter.doFilter(StrutsFilter.java:24)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at util.filter.SetCharacterEncodingFilter.doFilter(SetCharacterEncodingFilter.java:43)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.springframework.orm.hibernate3.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:198)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:76)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:218)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\r\n	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:958)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:452)\r\n	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1087)\r\n	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:637)\r\n	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:318)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n', 'admin', '1', null, '2017-09-03 10:52:15', '0:0:0:0:0:0:0:1', null, null, null, null, null);
INSERT INTO `t_sys_log_info` VALUES ('40285c815e459b39015e45a72b450008', '1', 'org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [pt.wzgl.nrgl.pojo.InfoTcategory#40285c815e459b39015e45a4c7f10004]\r\n	at org.hibernate.impl.SessionFactoryImpl$2.handleEntityNotFound(SessionFactoryImpl.java:449)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.load(DefaultLoadEventListener.java:233)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.proxyOrLoad(DefaultLoadEventListener.java:269)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.onLoad(DefaultLoadEventListener.java:152)\r\n	at org.hibernate.impl.SessionImpl.fireLoad(SessionImpl.java:1080)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:977)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:970)\r\n	at util.base.dao.BaseDaoImpl.onLoad(BaseDaoImpl.java:432)\r\n	at pt.wzgl.nrgl.dao.impl.InfoTcategoryDaoImpl.initInfoTcategory(InfoTcategoryDaoImpl.java:45)\r\n	at pt.wzgl.nrgl.service.impl.InfoTcategoryServiceImpl.initInfoTcategory(InfoTcategoryServiceImpl.java:38)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:90)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)\r\n	at com.sun.proxy.$Proxy23.initInfoTcategory(Unknown Source)\r\n	at pt.wzgl.nrgl.action.ContentManagerAction.getJSONInfoTcategory(ContentManagerAction.java:122)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeAction(DefaultActionInvocation.java:453)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeActionOnly(DefaultActionInvocation.java:292)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:255)\r\n	at util.interceptor.LoginInterceptor.intercept(LoginInterceptor.java:27)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at util.interceptor.LoginfoInterceptor.intercept(LoginfoInterceptor.java:91)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor.doIntercept(DefaultWorkflowInterceptor.java:176)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.validator.ValidationInterceptor.doIntercept(ValidationInterceptor.java:265)\r\n	at org.apache.struts2.interceptor.validation.AnnotationValidationInterceptor.doIntercept(AnnotationValidationInterceptor.java:68)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ConversionErrorInterceptor.intercept(ConversionErrorInterceptor.java:138)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.StaticParametersInterceptor.intercept(StaticParametersInterceptor.java:190)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.FileUploadInterceptor.intercept(FileUploadInterceptor.java:243)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor.intercept(ModelDrivenInterceptor.java:100)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ChainingInterceptor.intercept(ChainingInterceptor.java:145)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.PrepareInterceptor.doIntercept(PrepareInterceptor.java:171)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.ServletConfigInterceptor.intercept(ServletConfigInterceptor.java:164)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.MultiselectInterceptor.intercept(MultiselectInterceptor.java:75)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.CheckboxInterceptor.intercept(CheckboxInterceptor.java:90)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.I18nInterceptor.intercept(I18nInterceptor.java:176)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.AliasInterceptor.intercept(AliasInterceptor.java:192)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor.intercept(ExceptionMappingInterceptor.java:187)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.impl.StrutsActionProxy.execute(StrutsActionProxy.java:54)\r\n	at org.apache.struts2.dispatcher.Dispatcher.serviceAction(Dispatcher.java:510)\r\n	at org.apache.struts2.dispatcher.ng.ExecuteOperations.executeAction(ExecuteOperations.java:77)\r\n	at org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.doFilter(StrutsPrepareAndExecuteFilter.java:91)\r\n	at util.filter.StrutsFilter.doFilter(StrutsFilter.java:24)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at util.filter.SetCharacterEncodingFilter.doFilter(SetCharacterEncodingFilter.java:43)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.springframework.orm.hibernate3.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:198)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:76)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:218)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\r\n	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:958)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:452)\r\n	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1087)\r\n	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:637)\r\n	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:318)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n', 'admin', '1', null, '2017-09-03 10:52:17', '0:0:0:0:0:0:0:1', null, null, null, null, null);
INSERT INTO `t_sys_log_info` VALUES ('40285c815e459b39015e45a730d90009', '1', 'org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [pt.wzgl.nrgl.pojo.InfoTcategory#40285c815e459b39015e45a4c7f10004]\r\n	at org.hibernate.impl.SessionFactoryImpl$2.handleEntityNotFound(SessionFactoryImpl.java:449)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.load(DefaultLoadEventListener.java:233)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.proxyOrLoad(DefaultLoadEventListener.java:269)\r\n	at org.hibernate.event.def.DefaultLoadEventListener.onLoad(DefaultLoadEventListener.java:152)\r\n	at org.hibernate.impl.SessionImpl.fireLoad(SessionImpl.java:1080)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:977)\r\n	at org.hibernate.impl.SessionImpl.load(SessionImpl.java:970)\r\n	at util.base.dao.BaseDaoImpl.onLoad(BaseDaoImpl.java:432)\r\n	at pt.wzgl.nrgl.dao.impl.InfoTcategoryDaoImpl.initInfoTcategory(InfoTcategoryDaoImpl.java:45)\r\n	at pt.wzgl.nrgl.service.impl.InfoTcategoryServiceImpl.initInfoTcategory(InfoTcategoryServiceImpl.java:38)\r\n	at pt.wzgl.nrgl.service.impl.InfoTcategoryServiceImpl.delInfoTcategory(InfoTcategoryServiceImpl.java:76)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:110)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:90)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:202)\r\n	at com.sun.proxy.$Proxy23.delInfoTcategory(Unknown Source)\r\n	at pt.wzgl.nrgl.action.ContentManagerAction.delInfoTcategoryAjax(ContentManagerAction.java:242)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:606)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeAction(DefaultActionInvocation.java:453)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invokeActionOnly(DefaultActionInvocation.java:292)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:255)\r\n	at util.interceptor.LoginInterceptor.intercept(LoginInterceptor.java:27)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at util.interceptor.LoginfoInterceptor.intercept(LoginfoInterceptor.java:91)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor.doIntercept(DefaultWorkflowInterceptor.java:176)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.validator.ValidationInterceptor.doIntercept(ValidationInterceptor.java:265)\r\n	at org.apache.struts2.interceptor.validation.AnnotationValidationInterceptor.doIntercept(AnnotationValidationInterceptor.java:68)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ConversionErrorInterceptor.intercept(ConversionErrorInterceptor.java:138)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.StaticParametersInterceptor.intercept(StaticParametersInterceptor.java:190)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.FileUploadInterceptor.intercept(FileUploadInterceptor.java:243)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor.intercept(ModelDrivenInterceptor.java:100)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ChainingInterceptor.intercept(ChainingInterceptor.java:145)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.PrepareInterceptor.doIntercept(PrepareInterceptor.java:171)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.ServletConfigInterceptor.intercept(ServletConfigInterceptor.java:164)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ParametersInterceptor.doIntercept(ParametersInterceptor.java:211)\r\n	at com.opensymphony.xwork2.interceptor.MethodFilterInterceptor.intercept(MethodFilterInterceptor.java:98)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.MultiselectInterceptor.intercept(MultiselectInterceptor.java:75)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.interceptor.CheckboxInterceptor.intercept(CheckboxInterceptor.java:90)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.I18nInterceptor.intercept(I18nInterceptor.java:176)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.AliasInterceptor.intercept(AliasInterceptor.java:192)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor.intercept(ExceptionMappingInterceptor.java:187)\r\n	at com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:249)\r\n	at org.apache.struts2.impl.StrutsActionProxy.execute(StrutsActionProxy.java:54)\r\n	at org.apache.struts2.dispatcher.Dispatcher.serviceAction(Dispatcher.java:510)\r\n	at org.apache.struts2.dispatcher.ng.ExecuteOperations.executeAction(ExecuteOperations.java:77)\r\n	at org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.doFilter(StrutsPrepareAndExecuteFilter.java:91)\r\n	at util.filter.StrutsFilter.doFilter(StrutsFilter.java:24)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at util.filter.SetCharacterEncodingFilter.doFilter(SetCharacterEncodingFilter.java:43)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.springframework.orm.hibernate3.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:198)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:76)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:218)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)\r\n	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:958)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:452)\r\n	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1087)\r\n	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:637)\r\n	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:318)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n', 'admin', '1', null, '2017-09-03 10:52:19', '0:0:0:0:0:0:0:1', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_sys_module
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_module`;
CREATE TABLE `t_sys_module` (
  `module_id` varchar(32) NOT NULL,
  `module_name` varchar(100) DEFAULT NULL,
  `module_url` varchar(100) DEFAULT NULL,
  `parent_module_id` varchar(32) DEFAULT NULL,
  `module_date` datetime NOT NULL,
  `module_desc` varchar(200) DEFAULT NULL,
  `module_status` varchar(1) DEFAULT NULL,
  `module_type` varchar(1) DEFAULT NULL,
  `module_control` varchar(50) DEFAULT NULL,
  `module_level` int(11) DEFAULT NULL,
  `module_img` varchar(100) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT NULL,
  `module_order` int(11) DEFAULT NULL,
  `module_pids` varchar(255) DEFAULT NULL,
  `control_type` varchar(1) DEFAULT NULL,
  `is_validate` varchar(1) DEFAULT NULL,
  `validate_content` varchar(100) DEFAULT NULL,
  `is_js_request` varchar(1) DEFAULT NULL,
  `organ_id` varchar(32) DEFAULT NULL,
  `module_app` varchar(200) DEFAULT NULL,
  `rate` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_module
-- ----------------------------
INSERT INTO `t_sys_module` VALUES ('40288081537a8f8a01537a92d53a0002', '楼盘管理', '', '402881f4510ddf0a01510dea37bd0002', '2016-03-15 21:59:17', '网站管理', '0', '1', '', '2', '', '0', '1', ',-1,402881f4510ddf0a01510dea37bd0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('40288081537a8f8a01537a93318c0003', '最新楼盘', 'queryHousingProjectList.action?proj_type=1', '40288081537a8f8a01537a92d53a0002', '2016-03-15 09:59:40', '最新楼盘', '0', '1', '', '3', '', '0', '1', ',-1,402881f4510ddf0a01510dea37bd0002,40288081537a8f8a01537a92d53a0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('40288081537a8f8a01537a937abe0004', '团购楼盘', 'queryHousingProjectList.action?proj_type=2', '40288081537a8f8a01537a92d53a0002', '2016-03-15 09:59:59', '团购楼盘', '0', '1', '', '3', '', '0', '2', ',-1,402881f4510ddf0a01510dea37bd0002,40288081537a8f8a01537a92d53a0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('40288081537a8f8a01537a94ecf80005', '预约看房', 'yuyueKftFrames.action', '40288081537a8f8a01537a92d53a0002', '2016-03-15 10:01:34', '看房团', '0', '1', '', '3', '', '0', '3', ',-1,402881f4510ddf0a01510dea37bd0002,40288081537a8f8a01537a92d53a0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('40288081537a8f8a01537a9582e90006', '置业顾问', 'queryRealtyConsultantList.action', '40288081537a8f8a01537a92d53a0002', '2016-03-15 10:02:12', '置业顾问', '0', '1', '', '3', '', '0', '4', ',-1,402881f4510ddf0a01510dea37bd0002,40288081537a8f8a01537a92d53a0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028819452f71ec90152f7a067170005', '办事指南管理', 'javascript:void(0)', '402881f4510ddf0a01510dea37bd0002', '2016-02-19 11:43:50', '办事指南管理', '0', '1', '', '2', '', '1', '2', ',-1,402881f4510ddf0a01510dea37bd0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028819452f71ec90152f7a2de400006', '事项编制', 'bsznServiceBaseList.action', '4028819452f71ec90152f7a067170005', '2016-02-19 11:46:32', '事项编制', '0', '1', '', '3', '', '1', '1', ',-1,402881f4510ddf0a01510dea37bd0002,4028819452f71ec90152f7a067170005', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028819840949d350140952450450002', '数据字典', 'initSysDictInfo.action', '402881f83e1b00d9013e1b08b0b40000', '2013-08-19 13:55:19', '', '0', '1', '', '3', '', '0', '10', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1664b013eb1b7330b0029', '添加', 'initSysRole.action', '402881f83e1b00d9013e1b0c38440003', '2013-05-17 16:59:33', '', '0', '2', 'add', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b0c38440003', '3', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1664b013eb1b793f8002a', '修改', 'initSysRole.action', '402881f83e1b00d9013e1b0c38440003', '2013-05-17 16:59:58', '', '0', '2', 'edit', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b0c38440003', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1664b013eb1b85b20002b', '删除', 'delSysRole.action', '402881f83e1b00d9013e1b0c38440003', '2013-05-17 17:00:49', '', '0', '2', 'del', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b0c38440003', '1', '1', '是否确定删除该角色？', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1664b013eb1b8add4002c', '查看', 'getSysRoleDetail.action', '402881f83e1b00d9013e1b0c38440003', '2013-05-17 17:01:10', '', '0', '2', 'look', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b0c38440003', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1bcb2013eb1c140b7003c', '添加', 'editSysUser.action', '402881f83e1b2683013e1bd874150004', '2013-05-17 17:10:32', '', '0', '2', 'add', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b2683013e1bd874150004', '3', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1bcb2013eb1c18c08003d', '修改', 'editSysUser.action', '402881f83e1b2683013e1bd874150004', '2013-05-17 17:10:51', '', '0', '2', 'edit', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b2683013e1bd874150004', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1bcb2013eb1c21193003e', '删除', 'deleteSysUser.action', '402881f83e1b2683013e1bd874150004', '2013-05-17 05:11:25', '', '0', '2', 'del', '4', '', '1', '0', '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b2683013e1bd874150004', '1', '1', '是否确定删除该用户？', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23eb1bcb2013eb1c25dfe003f', '查看', 'getSysUserInfo.action', '402881f83e1b2683013e1bd874150004', '2013-05-17 17:11:45', '', '0', '2', 'look', '4', '', '1', null, '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b2683013e1bd874150004', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23ec59891013ec5aab2820003', '机构管理', 'querySysOrganList.action', '402881f83e1b00d9013e1b08b0b40000', '2013-05-21 01:58:18', '', '0', '1', '', '3', '', '0', '2', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23ef32511013ef331a8ed0001', '添加', 'initSysOrgan.action', '402881c23ec59891013ec5aab2820003', '2013-05-30 10:08:38', '', '0', '2', 'add', '4', '', '1', '0', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881c23ec59891013ec5aab2820003', '3', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23ef32511013ef33206010002', '修改', 'initSysOrgan.action', '402881c23ec59891013ec5aab2820003', '2013-05-21 01:58:18', '', '0', '2', 'edit', '4', '', '1', '0', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881c23ec59891013ec5aab2820003', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23ef32511013ef332f0de0005', '删除', 'delSysOrgan.action', '402881c23ec59891013ec5aab2820003', '2013-05-30 10:10:02', '', '0', '2', 'del', '4', '', '1', '0', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881c23ec59891013ec5aab2820003', '1', '1', '是否确定删除该机构？', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881c23ef32511013ef33376e60006', '查看', 'getSysOrganDetail.action', '402881c23ec59891013ec5aab2820003', '2013-05-30 10:10:36', '', '0', '2', 'look', '4', '', '1', '0', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881c23ec59891013ec5aab2820003', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881e656058c9f015605dc45f50012', '红包领取', 'queryHFWContantsList.action', '40288081537a8f8a01537a92d53a0002', '2016-07-20 09:12:17', '网站管理', '0', '1', '', '3', '', '0', '6', ',-1,402881f4510ddf0a01510dea37bd0002,40288081537a8f8a01537a92d53a0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f4510ddf0a01510dea37bd0002', '网站管理', 'javascript:void(0)', '-1', '2015-11-16 09:30:32', '网站管理', '0', '1', '', '1', '20151123_11323260.gif', '0', '10', ',-1', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f4510ddf0a01510dec81470003', '内容管理', 'javascript:void(0)', '402881f4510ddf0a01510dea37bd0002', '2015-11-16 09:33:02', '网站管理', '0', '1', '', '2', '', '0', '2', ',-1,402881f4510ddf0a01510dea37bd0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f4510dee1301510df354bb0006', '栏目信息管理', 'showInfoTcategoryList.action', '402881f4510ddf0a01510dec81470003', '2015-11-16 09:40:29', '网站管理', '0', '1', '', '3', '', '0', '1', ',-1,402881f4510ddf0a01510dea37bd0002,402881f4510ddf0a01510dec81470003', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f4510f051e01510f0691570003', '内容发布管理', 'initInfoTcontent.action', '402881f4510ddf0a01510dec81470003', '2015-11-16 14:41:07', '网站管理', '0', '1', '', '3', '', '0', '2', ',-1,402881f4510ddf0a01510dea37bd0002,402881f4510ddf0a01510dec81470003', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f4510f051e01510f175153000b', '内容发布审核管理', 'initAuditInfoTcontent.action', '402881f4510ddf0a01510dec81470003', '2015-11-16 14:59:25', '网站管理', '0', '1', '', '3', '', '0', '3', ',-1,402881f4510ddf0a01510dea37bd0002,402881f4510ddf0a01510dec81470003', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f4510f051e01510f17f18a000c', '回收站', 'queryInfoTcontentRecycleList.action', '402881f4510ddf0a01510dec81470003', '2015-11-16 15:00:06', '网站管理', '0', '1', '', '3', '', '0', '4', ',-1,402881f4510ddf0a01510dea37bd0002,402881f4510ddf0a01510dec81470003', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118b85e8c0002', '节假日管理', 'querySysHolidayList.action', '402881f83e1b00d9013e1b08b0b40000', '2015-11-18 11:51:54', '', '1', '1', '', '3', '', '0', '8', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118b927af0003', '公告管理', 'querySysNoticeList.action', '402881f83e1b00d9013e1b08b0b40000', '2015-11-18 11:52:46', '', '0', '1', '', '3', '', '0', '7', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118bbcc310005', '添加', 'initSysNotice.action', '402881f45118b560015118b927af0003', '2015-11-18 11:55:39', '', '0', '2', 'add', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b927af0003', '3', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118bc97ff0006', '修改', 'initSysNotice.action', '402881f45118b560015118b927af0003', '2015-11-18 11:56:31', '', '0', '2', 'edit', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b927af0003', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118bd49c50007', '查看', 'getSysNoticeInfo.action', '402881f45118b560015118b927af0003', '2015-11-18 11:57:17', '', '0', '2', 'look', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b927af0003', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118be28ba0008', '删除', 'deleteSysNotice.action', '402881f45118b560015118b927af0003', '2015-11-18 11:58:14', '', '0', '2', 'del', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b927af0003', '1', '1', '是否确定删除该公告吗？', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118beab900009', '添加', 'initSysWorkbench.action', '402881f45118b560015118b98bf90004', '2015-11-18 11:58:47', '', '0', '2', 'add', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b98bf90004', '3', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118bf28a8000a', '修改', 'initSysWorkbench.action', '402881f45118b560015118b98bf90004', '2015-11-18 11:59:19', '', '0', '2', 'edit', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b98bf90004', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f45118b560015118bfb753000b', '删除', 'deleteSysWorkbench.action', '402881f45118b560015118b98bf90004', '2015-11-18 11:59:56', '', '0', '2', 'del', '4', '', '1', null, '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000,402881f45118b560015118b98bf90004', '1', '1', '是否确定删除该数据？', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402881f83e1a9b53013e1ac7f2f9000a', '系统管理', 'javascript:void(0)', '-1', '2013-04-18 09:35:11', '', '0', '1', '', '1', 'fmenu11.gif', '0', '11', '', '1', '0', '', '0', '', null, '');
INSERT INTO `t_sys_module` VALUES ('402881f83e1b00d9013e1b08b0b40000', '基础管理', '', '402881f83e1a9b53013e1ac7f2f9000a', '2013-04-18 10:45:54', '', '0', '1', '', '2', '', '0', '1', '402881f83e1a9b53013e1ac7f2f9000a', null, null, null, null, null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881f83e1b00d9013e1b0b7f740002', '菜单管理', 'initSysModule.action', '402881f83e1b00d9013e1b08b0b40000', '2013-04-18 10:48:58', '', '0', '1', '', '3', '', '0', '3', '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a', null, null, null, null, null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881f83e1b00d9013e1b0c38440003', '角色管理', 'querySysRoleList.action', '402881f83e1b00d9013e1b08b0b40000', '2013-04-18 10:49:45', '', '0', '1', '', '3', '', '0', '4', '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a', null, null, null, null, null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881f83e1b2683013e1bd7b3450002', '区划管理', 'initSysArea.action', '402881f83e1b00d9013e1b08b0b40000', '2013-04-18 02:32:01', 'querySysAreaList.action?topId=', '0', '1', '', '3', '', '0', '1', '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a', '1', '0', '', '0', null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881f83e1b2683013e1bd874150004', '用户管理', 'querySysUserList.action', '402881f83e1b00d9013e1b08b0b40000', '2013-04-18 02:32:50', '', '0', '1', '', '3', '', '0', '5', '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a', null, null, null, null, null, null, null);
INSERT INTO `t_sys_module` VALUES ('402881f83e1b2683013e1bd995250008', '日志管理', 'querySysLog.action', '402881f83e1b00d9013e1b08b0b40000', '2013-04-18 02:34:04', '', '0', '1', '', '3', '', '0', '10', '402881f83e1b00d9013e1b08b0b40000,402881f83e1a9b53013e1ac7f2f9000a', null, null, null, null, null, null, null);
INSERT INTO `t_sys_module` VALUES ('4028829352f74e040152f818d31c0004', '系统参数', 'initSysAppParamInfo.action', '402881f83e1b00d9013e1b08b0b40000', '2016-02-19 13:55:22', '系统参数管理', '0', '1', '', '3', '', '0', '11', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402882935307d55d015307d6cba80001', '网站用户菜单管理', 'initNetUserModule.action', '4028829a52ddd22c0152de3f017a0004', '2016-02-22 03:17:10', '配置管理', '1', '1', '', '3', '', '0', '3', ',-1,402881f4510ddf0a01510dea37bd0002,4028829a52ddd22c0152de3f017a0004', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028829a52ddd22c0152de3f017a0004', '配置管理', 'javascript:void(0)', '402881f4510ddf0a01510dea37bd0002', '2016-02-14 01:26:57', '配置管理', '1', '1', '', '2', '', '0', '3', ',-1,402881f4510ddf0a01510dea37bd0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028829a52ddd22c0152de41f6450005', '办证主题分类', 'initZjblTypeInfo.action', '4028829a52ddd22c0152de3f017a0004', '2016-02-14 01:30:10', '办证主题分类', '1', '1', '', '3', '', '0', '1', ',-1,402881f4510ddf0a01510dea37bd0002,4028829a52ddd22c0152de3f017a0004', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028829a52ddd22c0152de42ca770006', '服务主题分类', 'initGgfwTypeInfo.action', '4028829a52ddd22c0152de3f017a0004', '2016-02-14 01:31:05', '服务主题分类', '1', '1', '', '3', '', '0', '2', ',-1,402881f4510ddf0a01510dea37bd0002,4028829a52ddd22c0152de3f017a0004', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('402882a1530bf0e201530bf504dc0003', '注册用户管理', 'initNetUserManager.action', '4028829a52ddd22c0152de3f017a0004', '2016-02-23 10:28:40', '配置管理', '0', '1', '', '3', '', '0', '4', ',-1,402881f4510ddf0a01510dea37bd0002,4028829a52ddd22c0152de3f017a0004', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028a5815467020701546707df850002', '二手房源', 'querySecondHandHouseList.action', '40288081537a8f8a01537a92d53a0002', '2016-04-30 19:57:29', '网站管理', '0', '1', '', '3', '', '0', '5', ',-1,402881f4510ddf0a01510dea37bd0002,40288081537a8f8a01537a92d53a0002', '1', '0', '', '0', null, null, '');
INSERT INTO `t_sys_module` VALUES ('4028a58154b3d3e90154b3daec150002', '首页图片管理', 'web/setting/bannerPicManage.jsp', '402881f83e1b00d9013e1b08b0b40000', '2016-05-15 05:59:09', 'BANNER图管理', '0', '1', '', '3', '', '0', '11', '402881f83e1a9b53013e1ac7f2f9000a,402881f83e1b00d9013e1b08b0b40000', '1', '0', '', '0', null, null, '');

-- ----------------------------
-- Table structure for t_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice`;
CREATE TABLE `t_sys_notice` (
  `notice_id` varchar(255) NOT NULL,
  `notice_title` varchar(255) DEFAULT NULL,
  `notice_memo` longtext,
  `notice_atta` varchar(255) DEFAULT NULL,
  `notice_date_type` varchar(255) DEFAULT NULL,
  `notice_end_date` varchar(27) DEFAULT NULL,
  `notice_type` varchar(255) DEFAULT NULL,
  `del_flag` varchar(2) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `insert_date` varchar(27) DEFAULT NULL,
  `yw_type` varchar(255) DEFAULT NULL,
  `area_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notice_id`),
  KEY `FK5FCEF1752B988DEA` (`area_id`),
  KEY `FK5FCEF175296C0AC1` (`yw_type`),
  CONSTRAINT `FK5FCEF175296C0AC1` FOREIGN KEY (`yw_type`) REFERENCES `t_sys_dict` (`dict_id`),
  CONSTRAINT `FK5FCEF1752B988DEA` FOREIGN KEY (`area_id`) REFERENCES `t_sys_area` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_organ
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_organ`;
CREATE TABLE `t_sys_organ` (
  `organ_id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `organ_code` varchar(50) NOT NULL,
  `organ_name` varchar(100) NOT NULL,
  `organ_full_name` varchar(100) DEFAULT NULL,
  `organ_simp_code` varchar(50) NOT NULL,
  `organ_sort` bigint(20) DEFAULT NULL,
  `organ_addr` varchar(200) DEFAULT NULL,
  `organ_tel` varchar(20) DEFAULT NULL,
  `area_id` varchar(32) NOT NULL,
  `organ_path` varchar(255) DEFAULT NULL,
  `organ_flag` varchar(1) NOT NULL,
  `del_flag` varchar(1) NOT NULL,
  PRIMARY KEY (`organ_id`),
  KEY `FK7F056BB42B988DEA` (`area_id`),
  CONSTRAINT `FK7F056BB42B988DEA` FOREIGN KEY (`area_id`) REFERENCES `t_sys_area` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_organ
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `role_id` varchar(32) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `Role_status` varchar(1) DEFAULT NULL,
  `Del_flag` varchar(1) DEFAULT NULL,
  `Role_desc` varchar(100) DEFAULT NULL,
  `area_id` varchar(32) NOT NULL,
  `opt_id` varchar(32) DEFAULT NULL,
  `an_identity` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `FKD28DE2532B988DEA` (`area_id`),
  CONSTRAINT `FKD28DE2532B988DEA` FOREIGN KEY (`area_id`) REFERENCES `t_sys_area` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_role_module
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_module`;
CREATE TABLE `t_sys_role_module` (
  `oid` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `module_id` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK45C001D8AA30194A` (`role_id`),
  KEY `FK45C001D8657E398A` (`module_id`),
  CONSTRAINT `FK45C001D8657E398A` FOREIGN KEY (`module_id`) REFERENCES `t_sys_module` (`module_id`),
  CONSTRAINT `FK45C001D8AA30194A` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role_module
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` varchar(32) NOT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_sex` varchar(100) DEFAULT NULL,
  `user_birthday` datetime NOT NULL,
  `user_post` char(32) DEFAULT NULL,
  `user_phone` varchar(200) DEFAULT NULL,
  `user_tel` varchar(20) DEFAULT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `user_sort` varchar(50) DEFAULT NULL,
  `organ_id` varchar(32) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT NULL,
  `user_status` varchar(1) DEFAULT NULL,
  `area_id` varchar(32) DEFAULT NULL,
  `serial_id` bigint(20) DEFAULT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKD28F4DA82B988DEA` (`area_id`),
  KEY `FKD28F4DA8F9FF52CA` (`organ_id`),
  KEY `FKD28F4DA86C531B9A` (`user_post`),
  CONSTRAINT `FKD28F4DA82B988DEA` FOREIGN KEY (`area_id`) REFERENCES `t_sys_area` (`area_id`),
  CONSTRAINT `FKD28F4DA86C531B9A` FOREIGN KEY (`user_post`) REFERENCES `t_sys_dict` (`dict_id`),
  CONSTRAINT `FKD28F4DA8F9FF52CA` FOREIGN KEY (`organ_id`) REFERENCES `t_sys_organ` (`organ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', 'admin', '202cb962ac59075b964b07152d234b70', '0', '2013-01-07 00:00:00', null, '12345678901', '0551-5545031', 'admin@126.com', '1         ', null, '0', '0', '20130419093149037845634429844034', '1', null);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `oid` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKF8082CADAA30194A` (`role_id`),
  KEY `FKF8082CAD4F5ADD2A` (`user_id`),
  CONSTRAINT `FKF8082CAD4F5ADD2A` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`user_id`),
  CONSTRAINT `FKF8082CADAA30194A` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_workbench
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_workbench`;
CREATE TABLE `t_sys_workbench` (
  `workbench_id` varchar(32) NOT NULL,
  `workbench_name` varchar(100) DEFAULT NULL,
  `workbench_url` varchar(150) DEFAULT NULL,
  `workbench_system` varchar(255) DEFAULT NULL,
  `system_name` varchar(200) DEFAULT NULL,
  `workbench_useflag` varchar(1) DEFAULT NULL,
  `workbench_delflag` varchar(1) DEFAULT NULL,
  `workbench_default` varchar(1) DEFAULT NULL,
  `workbench_service_type` varchar(32) DEFAULT NULL,
  `area_id` varchar(32) DEFAULT NULL,
  `workbench_typesetting` varchar(1) DEFAULT NULL,
  `workbench_module` varchar(32) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  KEY `FK232CD5E22B988DEA` (`area_id`),
  KEY `FK232CD5E26BF106A8` (`workbench_module`),
  KEY `FK232CD5E28C70E40A` (`workbench_service_type`),
  CONSTRAINT `FK232CD5E22B988DEA` FOREIGN KEY (`area_id`) REFERENCES `t_sys_area` (`area_id`),
  CONSTRAINT `FK232CD5E26BF106A8` FOREIGN KEY (`workbench_module`) REFERENCES `t_sys_module` (`module_id`),
  CONSTRAINT `FK232CD5E28C70E40A` FOREIGN KEY (`workbench_service_type`) REFERENCES `t_sys_dict` (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_workbench
-- ----------------------------

-- ----------------------------
-- Table structure for t_zjbl_type
-- ----------------------------
DROP TABLE IF EXISTS `t_zjbl_type`;
CREATE TABLE `t_zjbl_type` (
  `TYPE_ID` varchar(255) NOT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `TYPE_PATH` longtext,
  `TYPE_NAME` varchar(100) DEFAULT NULL,
  `TYPE_CODE` varchar(20) DEFAULT NULL,
  `TYPE_SORT` varchar(20) DEFAULT NULL,
  `TYPE_MEMO` varchar(200) DEFAULT NULL,
  `TYPE_LOGO` varchar(200) DEFAULT NULL,
  `TYPE_URL` varchar(200) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `USING_FLAG` varchar(2) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zjbl_type
-- ----------------------------
