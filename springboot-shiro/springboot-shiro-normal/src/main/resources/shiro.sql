
-- 新建一个数据库
CREATE DATABASE shiro;
USE shiro;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50),
pwd VARCHAR(50)
);

-- 权限表
DROP TABLE IF EXISTS permission;
CREATE TABLE permission(
id INT PRIMARY KEY AUTO_INCREMENT,
permission VARCHAR(50)
);


-- 角色表
DROP TABLE IF EXISTS role;
CREATE TABLE role(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50));

-- 权限-角色表
DROP TABLE IF EXISTS permission_role;
CREATE TABLE permission_role(
pid INT(3),
CONSTRAINT  fk_permission FOREIGN KEY(pid) REFERENCES permission(id),
rid INT(3),
CONSTRAINT  fk_role FOREIGN KEY(rid) REFERENCES role(id)
);


-- 用户-角色表
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role(
uid INT(3),
CONSTRAINT  fk_user FOREIGN KEY(uid) REFERENCES user(id),
rid INT(3),
CONSTRAINT  fk_roles FOREIGN KEY(rid) REFERENCES role(id)
);
-- 张三为管理员 李四为用户

INSERT INTO user VALUES(NULL,'xlccc','admin'),(NULL,'admin','123');
INSERT INTO permission VALUES(NULL,'add'),
(NULL,'delete'),
(NULL,'update'),
(NULL,'query');
INSERT INTO role VALUES(NULL,'admin'),(NULL,'customer');

-- 管理员有4个权限 用户只有查询权限
INSERT INTO permission_role VALUES(1,1),(2,1),(3,1),(4,1),(4,2);
INSERT INTO user_role VALUES(1,1),(2,2);
