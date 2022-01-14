
CREATE DATABASE EmployeeDB;

######################################## Employee  Data Seeding ########################################

CREATE TABLE Employee
(
    id int(11) auto_increment,
    FirstName varchar(50) null,
    MiddleName varchar(50) not null,
    LastName varchar(50) not null,
    DateOfJoining date not null,
    DateOfExit date null,
    Status int(15) null,
    constraint Employee_pk
        primary key (id)
);

INSERT INTO Employee (FirstName, MiddleName, LastName, DateOfJoining, DateOfExit, Status) VALUES('Ram','Kumar','Ganesan','2013-02-13','2022-03-31','1');
INSERT INTO Employee (FirstName, MiddleName, LastName, DateOfJoining, DateOfExit, Status) VALUES('Indhu','Mathy','Krish','2011-01-01','2022-01-31','1');
INSERT INTO Employee (FirstName, MiddleName, LastName, DateOfJoining, DateOfExit, Status) VALUES('Praneeth','Sai','Ramkumar','2018-09-20','2050-02-15','2');

######################################## RoleMaster Data Seeding ########################################

CREATE TABLE RoleMaster
(
    id int(11) auto_increment,
    RoleName varchar(50) not null,
    Status int(15) not null,
    constraint RoleMaster_pk
        primary key (id)
);

INSERT INTO RoleMaster (RoleName, Status) VALUES('Trainee','1');
INSERT INTO RoleMaster (RoleName, Status) VALUES('Developer','2');
INSERT INTO RoleMaster (RoleName, Status) VALUES('Senior Developer','3');
INSERT INTO RoleMaster (RoleName, Status) VALUES('Techical Lead','4');
INSERT INTO RoleMaster (RoleName, Status) VALUES('Arhitect','5');

######################################## EmployeeRoleMapping Data Seeding ########################################

CREATE TABLE EmployeeRoleMapping
(
    EmployeeId int(11) not null,
    RoleId int(11) not null,
    EffectiveDate date not null,
    constraint Employee_RoleMaster_FK
    FOREIGN KEY(EmployeeId) REFERENCES Employee(id),
    FOREIGN KEY(RoleId) REFERENCES RoleMaster(id)
);

INSERT INTO EmployeeRoleMapping (EmployeeId, RoleId,EffectiveDate) VALUES('1','2','2013-02-13');
INSERT INTO EmployeeRoleMapping (EmployeeId, RoleId,EffectiveDate) VALUES('2','3','2011-01-01');
INSERT INTO EmployeeRoleMapping (EmployeeId, RoleId,EffectiveDate) VALUES('3','4','2018-09-20');