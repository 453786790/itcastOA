

INSERT  INTO `itcast_privilege`(`id`,`name`,`url`,`parentId`) VALUES (1,'系统管理',NULL,NULL),(2,'岗位管理','/role_list',1),(3,'部门管理','/department_list',1),(4,'用户管理','/user_list',1),(5,'岗位列表','/role_list',2),(6,'岗位删除','/role_delete',2),(7,'岗位添加','/role_add',2),(8,'岗位修改','/role_edit',2),(9,'部门列表','/department_list',3),(10,'部门删除','/department_delete',3),(11,'部门添加','/department_add',3),(12,'部门修改','/department_edit',3),(13,'用户列表','/user_list',4),(14,'用户删除','/user_delete',4),(15,'用户添加','/user_add',4),(16,'用户修改','/user_edit',4),(17,'用户初始化密码','/user_initPassword',4),(18,'网上交流',NULL,NULL),(19,'论坛管理','/forumManage_list',18),(20,'论坛','/forum_list',18),(21,'设置权限','/role_setPrivilegeUI',2),(22,'审批流转',NULL,NULL),(23,'审批流转管理','/processDefinition_list',22),(24,'表单模版管理','/template_list',22),(25,'起草申请','/flow_templateList',22),(26,'我的申请查询','/flow_myApplicationList',22),(27,'待我审批','/flow_myTaskList',22);


INSERT  INTO `itcast_role`(`id`,`name`,`description`) VALUES (1,'管理员','管理员');



INSERT  INTO `itcast_role_privilege`(`privilegeId`,`roleId`) VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1);



INSERT  INTO `itcast_user`(`id`,`loginName`,`name`,`gender`,`phone`,`email`,`description`,`password`,`departmentid`) VALUES (2,'admin','admin','1',NULL,'','admin','81dc9bdb52d04dc20036dbd8313ed055',NULL);
