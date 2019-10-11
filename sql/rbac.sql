# 创建数据库
create database shiro DEFAULT CHARSET=utf8mb4;

# 用户表
create table tbl_user
(
  user_id bigint primary key auto_increment comment '用户id',
  username varchar(64) comment '用户名',
  password varchar(64) comment '密码',
  status char(1) comment '状态(0:启用,1:禁用,2:锁定)',
  salt varchar(32) comment '盐值',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户表';

# 角色表
create table tbl_role
(
  role_id bigint primary key auto_increment comment '角色id',
  role_name varchar(64) comment '角色名',
  description varchar(255) comment '角色描述',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '角色表';

# 资源信息表，同时包含了权限信息
create table tbl_resource
(
  resource_id bigint primary key auto_increment comment '资源id',
  parent_id bigint comment '资源父id',
  resource_name varchar(32) comment '资源名称',
  resource_url varchar(64) comment '资源url',
  resource_icon varchar(32) comment '资源图标',
  type char(1) comment '资源类型,1:菜单,2:按钮',
  priority tinyint comment '资源显示顺序',
  status char(1) comment '状态,0:启用,1:禁用',
  perm_name varchar(64) comment '权限名称',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '资源信息表';


# 用户角色关联表
create table tbl_user_role
(
  id bigint primary key auto_increment comment '主键id',
  user_id bigint comment '用户id',
  role_id bigint comment '角色id',
  create_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户角色关联表';

# 角色资源关联表
create table tbl_role_resource
(
  id bigint primary key auto_increment comment '主键id',
  role_id bigint comment '角色id',
  resource_id bigint comment '权限id',
  creat_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '角色资源关联表';

# 操作日志表
create table tbl_operation_log
(
  id bigint primary key auto_increment comment '主键id',
  op_type tinyint comment '操作类型',
  op_content varchar(255) comment '操作内容',
  user_id bigint comment '用户id',
  create_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# 新增用户
insert into tbl_user( username, password, status, create_time, update_time)
values('root','lyl','0',now(),now());

# 新增角色
insert into tbl_role(role_name, description, create_time, update_time)
values('admin','管理员',now(),now());
insert into tbl_role(role_name, description, create_time, update_time)
values('user','普通用户',now(),now());

# 新增资源
insert into tbl_resource(resource_id, parent_id, resource_name, resource_url, resource_icon, type, priority, status, perm_name, create_time, update_time)
values (1,0,'用户管理',null,null,1,1,0,null,now(),now());
insert into tbl_resource(resource_id, parent_id, resource_name, resource_url, resource_icon, type, priority, status, perm_name, create_time, update_time)
values (2,1,'新增用户',null,null,1,1,0,null,now(),now());
insert into tbl_resource(resource_id, parent_id, resource_name, resource_url, resource_icon, type, priority, status, perm_name, create_time, update_time)
values (3,1,'删除用户',null,null,1,1,0,null,now(),now());
insert into tbl_resource(resource_id, parent_id, resource_name, resource_url, resource_icon, type, priority, status, perm_name, create_time, update_time)
values (4,1,'修改用户',null,null,1,1,0,null,now(),now());
insert into tbl_resource(resource_id, parent_id, resource_name, resource_url, resource_icon, type, priority, status, perm_name, create_time, update_time)
values (5,1,'查询用户',null,null,1,1,0,null,now(),now());

# 用户角色映射
insert into tbl_user_role(user_id, role_id, create_time)
values(1,1,now());

# 角色权限映射
insert into tbl_role_permission(role_id, perm_id, creat_time)
values (1,1,now());
insert into tbl_role_permission(role_id, perm_id, creat_time)
values (1,2,now());
insert into tbl_role_permission(role_id, perm_id, creat_time)
values (1,3,now());
insert into tbl_role_permission(role_id, perm_id, creat_time)
values (1,4,now());
