/* 쇼핑몰 구현 */
use webjava;

/* ========== 22.01.28 추가 ======== */
-- DB를 생성하자
-- create database webjava default character set utf8;
-- 유저 생성 및 암호 지정
-- grant all privileges on webjava.* to javauser@localhost identified by 'webjava';

/* tblboard 테이블 */
create table tblboard (
 b_num int not null primary key AUTO_INCREMENT,
 b_subject varchar(100) not null,
 b_contents varchar(2000) not null,
 b_name varchar(50) not null,
 b_date datetime not null default sysdate()
);
INSERT INTO tblboard (b_subject, b_name, b_contents) VALUES ('제목이다','홍길동','jsp프로그래밍');

/* tblnotice 테이블 */
create table tblnotice (
 n_num int not null primary key AUTO_INCREMENT,
 n_subject varchar(100) not null,
 n_contents varchar(2000) not null,
 n_name varchar(50) not null,
 n_date datetime not null default sysdate()
);
INSERT INTO tblnotice (n_subject, n_name, n_contents) VALUES ('제목이다','홍길동','jsp프로그래밍');


/* ========== 22.01.28 종료 ======== */

-- 1. 고객을 만들자
create table tblmember (
	m_id VARCHAR(50) NOT NULL PRIMARY KEY, -- 아이디
	m_passwd VARCHAR(50) NOT NULL, -- 비번
	m_name VARCHAR(50) NOT NULL, -- 성명
	m_rdate DATETIME NOT NULL DEFAULT SYSDATE(), -- 최초 입력일과 수정일을 설정하는게 좋다.	
	m_udate DATETIME NOT NULL DEFAULT SYSDATE() -- 수정일
);
-- select * from tblmember;

/* 상품 테이블 */
create table tblproduct (
	p_code int not null primary key auto_increment,
	p_name VARCHAR(100) not null, 
	p_price int not null,
	p_rdate datetime not null default sysdate(),
	p_udate datetime not null default sysdate()
);
alter table tblproduct auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblproduct;

/* 장바구니 main -- 구매자의 정보를 갖고 있는 장바구니 테이블이다. */
create table tblcartmain (
	cm_code int not null primary key auto_increment, -- 장바구니 번호
	m_id varchar(50) not null, -- 이 영수증의 고유 번호를 이 고객이 구매한 것-의 의미 = 고객번호
	cm_rdate datetime not null default sysdate(),
	cm_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);
alter table tblcartmain auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblcartmain;

/* 장바구니 sub -- 장바구니 main에 상품을 담을 수 없는 이유가 몇개가 담길지 모르기 때문 */
create table tblcartsub (
	cs_code int not null primary key auto_increment,
	cm_code int not null, -- 중요도 순서로 작성, 장바구니 번호 , FK
	p_code int not null, -- 상품 번호, FK
	cs_cnt int not null, -- 수량(개수)이 필요하다.
	cs_rdate datetime not null default sysdate(),
	cs_udate datetime not null default sysdate(),
	foreign key (cm_code) references tblcartmain(cm_code), -- 키 설정을 해줘야 하는데, 두 개 설정 필요, 영수증으로부터 오는 것
	foreign key (p_code) references tblproduct(p_code) -- 상품번호로 부터 오는 것
);
alter table tblcartsub auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblcartsub;

/* 주문 main */
create table tblordermain (
	om_code int not null primary key auto_increment,
	m_id varchar(50) not null, -- FK, 주문자가 필요하지만, 카트메인에 있다. 그걸 포링키로 끌고오기보다는 그래도 신규로 정해주는게 좋다.
	om_rdate datetime not null default sysdate(),
	om_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);
alter table tblordermain auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblordermain;

/* 주문 sub */
create table tblordersub (
	os_code int not null primary key auto_increment,
	om_code int not null, -- FK
	p_code int not null, -- FK
	os_cnt int not null, -- 수량(개수)이 필요하다.
	os_rdate datetime not null default sysdate(),
	os_udate datetime not null default sysdate(),	
	foreign key (om_code) references tblordermain(om_code),
	foreign key (p_code) references tblproduct(p_code)
);
alter table tblordersub auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblordersub;

/* 전체 테이블 삭제 (만들어 놓으면 편함, 역순으로 삭제, 포링키 먼저 삭제하고 프라이머리키 삭제 순서) */
/*
drop table tblordersub;
drop table tblordermain;
drop table tblcartsub;
drop table tblcartmain;
drop table tblproduct;
drop table tblmember;
*/

-- 고객을 추가해보자
insert into tblmember (m_id, m_name, m_passwd) values ('tiger', '홍길동', '1234');
insert into tblmember (m_id, m_name, m_passwd) values ('lion', '김삿갓', '1234');
select * from tblmember;

-- 상품을 추가해보자
insert into tblproduct (p_name, p_price) values ('삼성냉장고200L', 1000000);
insert into tblproduct (p_name, p_price) values ('엘지냉장고200L', 800000);
insert into tblproduct (p_name, p_price) values ('농심새우깡', 2000);
insert into tblproduct (p_name, p_price) values ('롯데콘칩', 1000);
select * from tblproduct;

-- 이제 카트에 담아보자
insert into tblcartmain (m_id) values ('tiger');
insert into tblcartmain (m_id) values ('lion');
select * from tblcartmain;

-- 카트sub내 상품 정보가 필요하다.
insert into tblcartsub (cm_code, p_code, cs_cnt) values (1001, 1001, 1); -- 카트 메인코드(tiger)1001, 상품 코드 1001, 구매 수량
insert into tblcartsub (cm_code, p_code, cs_cnt) values (1001, 1003, 5);
insert into tblcartsub (cm_code, p_code, cs_cnt) values (1002, 1002, 1);
insert into tblcartsub (cm_code, p_code, cs_cnt) values (1002, 1004, 3);

select * from tblcartsub;

/* tiger의 장바구니 내역을 조회 (난이도 조금 있음, JOIN사용할 거야) */
select cm.cm_code, cm.m_id, m.m_name, cs.p_code, p.p_name, p.p_price, cs.cs_cnt, p.p_price * cs.cs_cnt -- 장바구니main번호, 고객번호, 고객이름, 장바구니sub의 상품번호, 상품이름, 상품가격, 장바구니sub의 상품수량, 상품가격 x 장바구니sub수량 //
	from tblcartmain cm, tblcartsub cs, tblproduct p, tblmember m -- AS 없어도 별칭 부여 되나보다.
	where cm.cm_code = cs.cm_code -- cm.cm_code = cs.cm_code 이게 INNER JOIN의 핵심이다. 여기가 JOIN 라인
		and p.p_code = cs.p_code -- 왼쪽에 주 key 오른쪽에 보조 key를 두는게 관례이다.
		and m.m_id = cm.m_id -- 장바구니main은 고객id와 묶여있으니까
		and cm.m_id = 'tiger'; -- 기준은 항상 고객이다.

/* tiger의 장바구니 합계 */
-- select cm.cm_code, cm.m_id, cs.p_code, p.p_name, p.p_price, cs.cs_cnt, p.p_price * cs.cs_cnt -- 장바구니main번호, 고객번호, 고객이름, 장바구니sub의 상품번호, 상품이름, 상품가격, 장바구니sub의 상품수량, 상품가격 x 장바구니sub수량 //
select sum(p.p_price * cs.cs_cnt) -- 장바구니main번호, 고객번호, 고객이름, 장바구니sub의 상품번호, 상품이름, 상품가격, 장바구니sub의 상품수량, 상품가격 x 장바구니sub수량 //
	from tblcartmain cm, tblcartsub cs, tblproduct p -- AS 없어도 별칭 부여 되나보다.
	where cm.cm_code = cs.cm_code -- cm.cm_code = cs.cm_code 이게 INNER JOIN의 핵심이다. 여기가 JOIN 라인
		and p.p_code = cs.p_code -- 왼쪽에 주 key 오른쪽에 보조 key를 두는게 관례이다.
		and cm.m_id = 'tiger'; -- 기준은 항상 고객이다.

-- sum(가격 x 수량) : 총합계
-- 가격 x 수량 : 해당 구매자의 구매 품목과 가격

/* 장바구니의 tiger가 저장한 모든 내용을 구매 (구매할 때는 장바구니 목록이 삭제가 되어야 한다.) */
insert into tblordermain (m_id) values ('tiger');
select * from tblordermain;
insert into tblordersub (om_code, p_code, os_cnt) -- values를 넣으면(반복구문으로 읽고 쓰는 과정을 해야 한다.)되지만 우리는 직접 읽어올거야(?)
	select 1001, p_code, cs_cnt from tblcartsub where cm_code = (select cm_code from tblcartmain where m_id = 'tiger'); -- 일괄 insert 구문, 카트sub에 타이거가 있어야 하는데 확인해보니 없네, 그래서 카트main에서 tiger(m_id)를 조건으로 카트main 코드를 가져온다. 결국 호랑이의 장바구니 정보만 나온다.(사자까지 나오지 않는거야)

/* tiger가 가장 최근에 구매한 내역 */
select om_code from tblordermain where m_id = 'tiger' order by om_code desc limit 1; -- 가장 최근에 주문한 주문 번호

select om.om_code, om.m_id, os.p_code, os.os_cnt, p.p_price * os.os_cnt -- 이제 여러개를 알아내야 하니까 JOIN을 해야 해
	from tblordermain om, tblordersub os, tblproduct p -- JOIN한거야
	where om.om_code = os.os_code
		and p.p_code = os.p_code
		and om.om_code = (select om_code from tblordermain where m_id = 'tiger' order by om_code desc limit 1); -- 최근에 주문한 번호를 subquery(부질의)로 만들어준다. + 상품의 가격도 JOIN해보자

-- tiger가 가장 최근에 구매한 금액 총 합계 조회
select sum(p.p_price * os.os_cnt)
	from tblordermain om, tblordersub os, tblproduct p -- JOIN한거야
	where om.om_code = os.os_code
		and p.p_code = os.p_code
		and om.om_code = (select om_code from tblordermain where m_id = 'tiger' order by om_code desc limit 1);

desc tblmember;
desc tblcartsub;
desc tblcartmain;

delete from tblordersub;
delete from tblordermain;

delete from tblcartsub;
delete from tblcartmain;

select * from tblcartmain;
select * from tblcartsub;




