/*
 Navicat Premium Data Transfer

 Source Server         : Mysql80
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3307
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 25/12/2018 17:08:12
*/

SET
  NAMES
  utf8mb4;
SET
  FOREIGN_KEY_CHECKS
    =
      0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails`
(
  `appId`                  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
  `resourceIds`            varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `appSecret`              varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `scope`                  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `grantTypes`             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `redirectUrl`            varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `authorities`            varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `access_token_validity`  int(11)                                                        NULL DEFAULT NULL,
  `refresh_token_validity` int(11)                                                        NULL DEFAULT NULL,
  `additionalInformation`  varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `autoApproveScopes`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  PRIMARY KEY (`appId`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`
(
  `token_id`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `token`             blob                                                          NULL,
  `authentication_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_id`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `authentication`    blob                                                          NULL,
  `refresh_token`     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`
(
  `userId`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL,
  `clientId`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL,
  `scope`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL,
  `status`         varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL,
  `expiresAt`      timestamp(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
  `client_id`               varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
  `resource_ids`            varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `client_secret`           varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `scope`                   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `authorized_grant_types`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `authorities`             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  `access_token_validity`   int(11)                                                        NULL DEFAULT NULL,
  `refresh_token_validity`  int(11)                                                        NULL DEFAULT NULL,
  `additional_information`  varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `autoapprove`             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details`
VALUES ('client', NULL, '{noop}secret', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials',
        NULL, NULL, NULL, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
  `token_id`          varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `token`             blob                                                          NULL,
  `authentication_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_id`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
  `code`           varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `authentication` blob                                                          NULL
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`
(
  `token_id`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `token`          blob                                                          NULL,
  `authentication` blob                                                          NULL
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`
(
  `id`          bigint(20)                                                    NOT NULL AUTO_INCREMENT,
  `url`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
  `discription` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL,
  `is_delete`   bit(1)                                                        NOT NULL DEFAULT b'0',
  `create_by`   bigint(20)                                                    NULL     DEFAULT NULL,
  `create_time` date                                                          NULL     DEFAULT NULL,
  `modify_by`   bigint(20)                                                    NULL     DEFAULT NULL,
  `modify_ip`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL,
  `modify_time` date                                                          NULL     DEFAULT NULL,
  `version`     int(11)                                                       NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_url` (`url`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
  `id`          bigint(20)                                                   NOT NULL AUTO_INCREMENT,
  `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `discription` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_by`   bigint(20)                                                   NULL DEFAULT NULL,
  `create_time` datetime(0)                                                  NULL DEFAULT NULL,
  `modify_by`   bigint(20)                                                   NULL DEFAULT NULL,
  `modify_time` datetime(0)                                                  NULL DEFAULT NULL,
  `modify_ip`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete`   bit(1)                                                       NOT NULL,
  `version`     int(11)                                                      NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_name` (`name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`
(
  `id`          bigint(20)                                                   NOT NULL AUTO_INCREMENT,
  `role_id`     bigint(20)                                                   NOT NULL,
  `resource_id` bigint(20)                                                   NOT NULL,
  `enable`      bit(1)                                                       NOT NULL DEFAULT b'0',
  `create_by`   bigint(20)                                                   NULL     DEFAULT NULL,
  `create_time` datetime(0)                                                  NULL     DEFAULT NULL,
  `modify_by`   bigint(20)                                                   NULL     DEFAULT NULL,
  `modify_time` datetime(0)                                                  NULL     DEFAULT NULL,
  `modify_ip`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL,
  `is_delete`   bit(1)                                                       NOT NULL,
  `version`     int(11)                                                      NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
  `id`          bigint(20)                                                   NOT NULL AUTO_INCREMENT,
  `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password`    varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `age`         int(11)                                                      NOT NULL,
  `sex`         bit(1)                                                       NOT NULL,
  `create_by`   bigint(20)                                                   NULL DEFAULT NULL,
  `create_time` datetime(0)                                                  NULL DEFAULT NULL,
  `modify_by`   bigint(20)                                                   NULL DEFAULT NULL,
  `modify_time` datetime(0)                                                  NULL DEFAULT NULL,
  `modify_ip`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete`   bit(1)                                                       NOT NULL,
  `version`     int(11)                                                      NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_username` (`username`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
  `id`          bigint(20)                                                   NOT NULL AUTO_INCREMENT,
  `user_id`     bigint(20)                                                   NOT NULL,
  `role_id`     bigint(20)                                                   NOT NULL,
  `create_by`   bigint(20)                                                   NULL DEFAULT NULL,
  `create_time` datetime(0)                                                  NULL DEFAULT NULL,
  `modify_by`   bigint(20)                                                   NULL DEFAULT NULL,
  `modify_time` datetime(0)                                                  NULL DEFAULT NULL,
  `modify_ip`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete`   bit(1)                                                       NOT NULL,
  `version`     int(11)                                                      NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;


SET
  FOREIGN_KEY_CHECKS
    =
      1;


INSERT INTO `sys_user`( `username`, `password`, `name`, `age`, `sex`, `create_by`, `create_time`, `modify_by`, `modify_time`, `modify_ip`, `is_delete`, `version`) VALUES ( 'admin', '$2a$10$ueKVLLKaLKLUrVnwImgrB.LSnezwXFCaTiSRrl8yNQ28uOi/.grvy', 'ADMIN', 18, b'1', 1, '2019-01-10 08:16:23', 1, '2019-01-10 08:16:23', '127.0.0.1', b'0', 0);
INSERT INTO `sys_role`( `create_by`, `create_time`, `is_delete`, `modify_by`, `modify_ip`, `modify_time`, `version`, `discription`, `name`) VALUES ( 1, '2019-01-09 07:37:36', b'0', 1, '127.0.0.1', '2019-01-09 07:37:36', 0, NULL, 'ADMIN');
INSERT INTO `sys_user_role`( `user_id`, `role_id`, `create_by`, `create_time`, `modify_by`, `modify_time`, `modify_ip`, `is_delete`, `version`) VALUES ( 1, 1, 1, '2019-01-10 16:58:56', 1, '2019-01-10 16:59:01', '127.0.0.1', b'0', 0);