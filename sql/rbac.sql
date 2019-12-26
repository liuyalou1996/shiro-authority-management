# 创建数据库
create database shiro DEFAULT CHARSET=utf8mb4;

# 用户表
create table tbl_user
(
  user_id bigint primary key auto_increment comment '用户id',
  username varchar(64) comment '用户名',
  password varchar(64) comment '密码',
  status char(1) comment '状态(0:禁用,1:启用,2:锁定)',
  salt varchar(32) comment '盐值',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户表';

# 新增用户
insert into tbl_user (user_id, username, password, salt, status, create_time, update_time)
values (1, 'root', '0e40accdff6a29d9bae818324aee80b8', '6cb007ce3a578cc024f8024b5e9326b4', '1', now(), now());
insert into tbl_user (user_id, username, password, salt, status, create_time, update_time)
values (2, 'guest', '2373be683da86fc5b48ceb3312f7cc93', '9061bf91cb4e550a4857c4dfa0e75b06', '1', now(), now());

# 角色表
create table tbl_role
(
  role_id bigint primary key auto_increment comment '角色id',
  role_name varchar(64) comment '角色名',
  description varchar(255) comment '角色描述',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '角色表';

# 添加角色
insert into tbl_role (role_id, role_name, description, create_time, update_time)
values (1, 'admin', '系统管理员，拥有所有操作权限', now(), now());
insert into tbl_role (role_id, role_name, description, create_time, update_time)
values (2, 'user', '普通用户，只有查询权限', now(), now());

# 资源信息表，同时包含了权限信息
create table tbl_resource
(
  resource_id int primary key auto_increment comment '资源id',
  parent_id int comment '资源父id',
  resource_name varchar(32) comment '资源名称',
  resource_code varchar(64) comment  '资源权限码',
  resource_type char(1) comment '资源类型,1:目录,2:菜单,3:按钮',
  resource_url varchar(128) comment '资源url',
  resource_icon varchar(32) comment '资源图标',
  resource_order tinyint comment '资源显示顺序',
  status char(1) comment '状态,1:启用,0:禁用',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '资源信息表';

# 新增用户
insert into tbl_resource(resource_id,parent_id,resource_name,resource_code,resource_type,resource_url,resource_icon,resource_order,status,create_time,update_time)
values (1, 0, '系统管理', null, '1', null, null, 1, '1', now(), null);

insert into tbl_resource(resource_id,parent_id,resource_name,resource_code,resource_type,resource_url,resource_icon,resource_order,status,create_time,update_time)
values (2, 1, '用户管理', null, '2', 'system/management/user', 'layui-icon layui-icon-user', 1, '1', now(), null);

insert into tbl_resource(resource_id,parent_id,resource_name,resource_code,resource_type,resource_url,resource_icon,resource_order,status,create_time,update_time)
values (3, 2, '新增用户', 'user:add', '3', null, null, null, '1', now(), null);

insert into tbl_resource(resource_id,parent_id,resource_name,resource_code,resource_type,resource_url,resource_icon,resource_order,status,create_time,update_time)
values (4, 2, '删除用户', 'user:delete', '3', null, null, null, '1', now(), null);

insert into tbl_resource(resource_id,parent_id,resource_name,resource_code,resource_type,resource_url,resource_icon,resource_order,status,create_time,update_time)
values (5, 2, '修改用户', 'user:update', '3', null, null, null, '1' , now(), null);

insert into tbl_resource(resource_id,parent_id,resource_name,resource_code,resource_type,resource_url,resource_icon,resource_order,status,create_time,update_time)
values (6, 2, '查询用户', 'user:view', '3', null, null, null, '1' , now(), null);

# 用户角色关联表
create table tbl_user_role
(
  id bigint primary key auto_increment comment '主键id',
  user_id bigint comment '用户id',
  role_id bigint comment '角色id',
  create_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户角色关联表';

# 用户角色映射
insert into tbl_user_role(user_id, role_id, create_time)
values(1,1,now());
insert into tbl_user_role(user_id, role_id, create_time)
values(2,2,now());

# 角色资源关联表
create table tbl_role_resource
(
  id bigint primary key auto_increment comment '主键id',
  role_id bigint comment '角色id',
  resource_id bigint comment '资源id',
  creat_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '角色资源关联表';

# 角色和资源关联
# 系统管理员拥有所有权限
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (1, 1, now());
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (1, 2, now());
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (1, 3, now());
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (1, 4, now());
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (1, 5, now());

# 普通用户只有查看权限
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (2, 1, now());
insert into tbl_role_resource (role_id, resource_id, creat_time)
values (2, 2, now());

# 操作日志表
create table tbl_operation_log
(
  id bigint primary key auto_increment comment '主键id',
  op_type char(1) comment '操作类型',
  op_content varchar(255) comment '操作内容',
  user_id bigint comment '用户id',
  create_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

select * from tbl_user;

INSERT INTO tbl_operation_log ( op_type, op_content, user_id, create_time )
VALUES ( '1', '新增记录', 1, now() );

# 根据用户名获取权限
select * from tbl_resource tr
inner join tbl_role_resource trr on trr.resource_id = tr.resource_id
inner join tbl_user_role tur on tur.role_id = trr.role_id
inner join tbl_user tu on tu.user_id = tur.user_id
where tu.username = 'guest';

# 根据用户名获取角色
select * from tbl_role tr
inner join tbl_user_role tur on tur.role_id = tr.role_id
inner join tbl_user tu on tu.user_id = tur.user_id
where tu.username = 'guest';
