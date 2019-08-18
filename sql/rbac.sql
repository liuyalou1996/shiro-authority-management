# 用户表
create table tbl_user
(
  user_id bigint primary key auto_increment comment '主键id',
  username varchar(64) comment '用户名',
  password varchar(64) comment '密码',
  status tinyint comment '状态(0启用，1禁用)',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';

# 角色表
create table tbl_role
(
  role_id bigint primary key auto_increment comment '主键id',
  role_name varchar(64) comment '角色名',
  description varchar(255) comment '角色描述',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '角色表';

# 权限表
create table tbl_permission
(
  perm_id bigint primary key auto_increment comment '主键id',
  perm_name varchar(64) comment '权限名',
  description varchar(255) comment '权限描述',
  create_time datetime comment '创建时间',
  update_time datetime comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '权限表';

# 用户、角色表
create table tbl_user_role
(
  id bigint primary key auto_increment comment '主键id',
  user_id bigint comment '用户id',
  role_id bigint comment '角色id',
  create_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户-角色表';

# 角色、权限表
create table tbl_role_permission
(
  id bigint primary key auto_increment comment '主键id',
  role_id bigint comment '角色id',
  perm_id bigint comment '权限id',
  creat_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 操作日志表
create table tbl_operation_log
(
  id bigint primary key auto_increment comment '主键id',
  op_type TINYINT comment '操作类型',
  op_content varchar(255) comment '操作内容',
  user_id bigint comment '用户id',
  create_time datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 新增用户
insert into tbl_user( username, password, status, create_time, update_time)
values('root','lyl',0,now(),now());

# 新增角色
insert into tbl_role(role_name, description, create_time, update_time)
values('admin','管理员',now(),now());
insert into tbl_role(role_name, description, create_time, update_time)
values('user','普通用户',now(),now());

# 新增权限
insert into tbl_permission(perm_name, description, create_time, update_time)
values('user:insert','新增用户',now(),now());
insert into tbl_permission(perm_name, description, create_time, update_time)
values('user:delete','删除用户',now(),now());
insert into tbl_permission(perm_name, description, create_time, update_time)
values('user:update','修改用户',now(),now());
insert into tbl_permission(perm_name, description, create_time, update_time)
values('user:select','查询用户',now(),now());

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
