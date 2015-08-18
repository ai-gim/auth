insert into auth_user(name, description, properties, account, password, state, email, time) value('admin', '超级管理员', null, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1 ,'' ,'1970-1-1 11:11:11');

insert into auth_role(name, description) value('超级管理员' ,'超级管理员角色');

insert into auth_role(name, description) value('管理员' ,'管理员角色');

insert into auth_role(name, description) value('操作员' ,'操作员角色');

insert into auth_role(name, description) value('监控员' ,'监控员角色');

insert into auth_group(name, description) value('默认组' ,'默认组');

insert into auth_user_role_relation value(1, 1);

insert into auth_user_group_relation value(1, 1);