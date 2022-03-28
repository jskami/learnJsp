/*======== 22.03.04 프로젝트용 테이블 설계 =========*/
use webjava;

/* 관리자 테이블 */
drop table tbladmin; --(패스워드 암호화시키기 위해 구조를 바꾸려고 하는거라서 삭제하고 다시 만들듯)

create table tbladmin (
	a_id VARCHAR(50) NOT NULL PRIMARY KEY, -- 아이디
	a_passwd VARCHAR(500) NOT NULL, -- 비번
	a_name VARCHAR(50) NOT NULL, -- 성명
	a_rdate DATETIME NOT NULL DEFAULT SYSDATE(), -- 최초 입력일과 수정일을 설정하는게 좋다.	
	a_udate DATETIME NOT NULL DEFAULT SYSDATE() -- 수정일
);

-- 암복호화 시키기(hex: 16진수 변환, aes: mysql에서 제공하는 양방향암호화 알고리즘)	
insert into tbladmin (a_id, a_passwd, a_name)
	values ('admin', hex(aes_encrypt('1234', sha2('123456!',512))),'관리자'); -- 관리자 추가

insert into tbladmin (a_id, a_passwd, a_name) 
	values ('subadmin', hex(aes_encrypt('12345', sha2('123456!',512))),'보조관리자');

select hex(aes_encrypt('tiger', sha2('aabbcc',512))); -- 암호화, '123!'이 key이다.
select aes_decrypt (unhex('1E77A52EC2F562C2160437B7FF908D2B'), sha2('aabbcc', 512)); -- 복호화, 암호화된 pw를 일반 pw로 전환.

select * from tbladmin; -- 관리자 테이블 리스팅!

select * from tbladmin 
	where a_passwd = hex(aes_encrypt('1234', sha2('123456!',512))); -- 관리자 테이블의 레코드중에서 해당하는 비번을 갖고있는 관리자를 찾아라!
	
select * from tbladmin where
	a_id = 'subadmin' and
	a_passwd = hex(aes_encrypt('12345', sha2('123456!',512))); -- 관리자 테이블의 특정 관리자의 비번을 상세하게 서치하고 찾아라!
	

/* tblboard2 테이블 */
create table tblboard2 (
 b_num int not null primary key AUTO_INCREMENT,
 b_subject varchar(100) not null,
 b_contents varchar(2000) not null,
 b_file varchar(200), -- 업로드용 필드, null값은 허용(파일 업로드는 선택사항이니까) 
 b_name varchar(50) not null,
 b_date datetime not null default sysdate()
);

select * from tblboard2 order by b_num desc limit 10;
select count(*) from tblboard2;
select * from tblboard2;
INSERT INTO tblboard2 (b_subject, b_name, b_contents) VALUES ('부관리자에게','관리자','메일 확인해주세요.');
INSERT INTO tblboard2 (b_subject, b_name, b_contents) VALUES ('관리자에게','부관리자','메일 확인했습니다.');

/* tblnotice2 테이블*/
create table tblnotice2 (
 n_num int not null primary key AUTO_INCREMENT,
 n_subject varchar(100) not null,
 n_contents varchar(2000) not null,
 n_name varchar(50) not null,
 n_date datetime not null default sysdate()
);

select * from tblnotice2;
INSERT INTO tblnotice2 (n_subject, n_name, n_contents) VALUES ('공지사항입니다.','관리자','홈페이지 공사 기간동안 이용을 할 수 없음을 알려드립니다.');
INSERT INTO tblnotice2 (n_subject, n_name, n_contents) VALUES ('홈페이지 이용 안내문입니다.','관리자','정상적인 홈페이지 이용을 위해 회원가입을 부탁드립니다.');

alter table tblnotice2 add n_file varchar(500) null;


-- 1. 고객을 만들자
create table tblmember2 (
	m_id VARCHAR(50) NOT NULL PRIMARY KEY, -- 아이디
	m_passwd VARCHAR(50) NOT NULL, -- 비번
	m_name VARCHAR(50) NOT NULL, -- 성명
	m_phone VARCHAR(20) not null,
	m_rdate DATETIME NOT NULL DEFAULT SYSDATE(), -- 최초 입력일과 수정일을 설정하는게 좋다.	
	m_udate DATETIME NOT NULL DEFAULT SYSDATE() -- 수정일
);
select * from tblmember2;
drop table tblmember2;

/* 상품 테이블 */
create table tblproduct2 (
	p_code int not null primary key auto_increment,
	p_name VARCHAR(100) not null, 
	p_price int not null,
	p_rdate datetime not null default sysdate(),
	p_udate datetime not null default sysdate()
);
alter table tblproduct2 auto_increment=1;
-- alter table tblproduct2 auto_increment=1001;
-- drop table tblproduct2;


/* 주문 main (장바구니의 상품이 주문될때 주문번호를 따서 저장되는 테이블) */
/* 구매가 완료되고 나면 더 이상 장바구니 main 정보를 유지시킬 필요가 없다. 
   주문이 완료되면 삭제시킨다. carmain에 있는 정보를 끌어다가 쓰면 안된다. */
create table tblordermain2 (
	om_code int not null primary key auto_increment,
	m_id varchar(50) not null, -- FK, 주문자가 필요하지만, 카트메인에 있다. 그걸 포링키로 끌고오기보다는 그래도 신규로 정해주는게 좋다. 
	om_rdate datetime not null default sysdate(),
	om_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);
alter table tblordermain2 auto_increment=1; -- 상품번호를 1부터 시작해서 자동증가
-- alter table tblordermain2 auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblordermain;

/* 주문 sub */
create table tblordersub2 (
	os_code int not null primary key auto_increment,
	om_code int not null, -- FK
	p_code int not null, -- FK
	os_cnt int not null, -- 수량(개수)이 필요하다.
	os_rdate datetime not null default sysdate(),
	os_udate datetime not null default sysdate(),	
	foreign key (om_code) references tblordermain(om_code),
	foreign key (p_code) references tblproduct(p_code)
);
alter table tblordersub auto_increment=1; -- 상품번호를 1001부터 시작해서 자동증가
-- alter table tblordersub auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblordersub;

/* 장바구니 main -- 구매자의 정보를 갖고 있는 장바구니 테이블이다. */
create table tblcartmain2 (
	cm_code int not null primary key auto_increment, -- 장바구니 번호
	m_id varchar(50) not null, -- 이 영수증의 고유 번호를 이 고객이 구매한 것-의 의미 = 고객번호
	cm_rdate datetime not null default sysdate(),
	cm_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);
alter table tblcartmain2 auto_increment=1; -- 상품번호를 1001부터 시작해서 자동증가
-- alter table tblcartmain2 auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblcartmain;

/* 장바구니 sub -- 장바구니 main에 상품을 담을 수 없는 이유가 몇개가 담길지 모르기 때문 */
create table tblcartsub2 (
	cs_code int not null primary key auto_increment,
	cm_code int not null, -- 중요도 순서로 작성, 장바구니 번호 , FK
	p_code int not null, -- 상품 번호, FK
	cs_cnt int not null, -- 수량(개수)이 필요하다.
	cs_rdate datetime not null default sysdate(),
	cs_udate datetime not null default sysdate(),
	foreign key (cm_code) references tblcartmain(cm_code), -- 키 설정을 해줘야 하는데, 두 개 설정 필요, 영수증으로부터 오는 것
	foreign key (p_code) references tblproduct(p_code) -- 상품번호로 부터 오는 것
);
alter table tblcartsub auto_increment=1; -- 상품번호를 1001부터 시작해서 자동증가
-- alter table tblcartsub auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblcartsub;

-- 고객을 추가해보자
insert into tblmember2 (m_id, m_name, m_passwd, m_phone) values ('tiger', '홍길동', '1234', '01012345678');
insert into tblmember2 (m_id, m_name, m_passwd, m_phone) values ('lion', '김삿갓', '1234', '01012345671');
insert into tblmember2 (m_id, m_name, m_passwd, m_phone) values ('bee', '꿀벌', '1234', '01012345672');
select * from tblmember2;

-- 상품을 추가해보자
insert into tblproduct2 (p_name, p_price) values ('(PS4) 피파21 스탠다드 에디션 (한글판)', 62000);
insert into tblproduct2 (p_name, p_price) values ('(PS4) SIE 갓 오브 워 2018 한글판', 45000);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 반다이남코 다크 소울3', 28000);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 세키로:SHADOW DIE TWICE', 51000);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 인왕2 컴플리트 에디션 Complete Edition', 66500);
insert into tblproduct2 (p_name, p_price) values ('(PS4) The King of Fighters XV', 66000);
insert into tblproduct2 (p_name, p_price) values ('[PSN] PlayStation Store 기프트 카드 10만원', 100000);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 라이자의 아틀리에2 잃어버린 전승과 비밀의 요정 / 한글판', 39000);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 다크 소울 리마스터 Dark Souls Remastered', 32000);
insert into tblproduct2 (p_name, p_price) values ('(PS5) 스파이더맨 마일즈 모랄레스 한글판', 56000);
insert into tblproduct2 (p_name, p_price) values ('(PS5) SIEK 플레이스테이션 콜 오브 듀티:블랙 옵스 콜드 워', 74000);
insert into tblproduct2 (p_name, p_price) values ('(PS5) 반다이남코 플레이스테이션 엘든 링 한글판', 56000);
insert into tblproduct2 (p_name, p_price) values ('(PS5) 유비소프트 플레이스테이션 이모탈 피닉스 라이징', 14000);
insert into tblproduct2 (p_name, p_price) values ('(PS5) 파크라이6 리미티드 에디션 한글판', 48000);
insert into tblproduct2 (p_name, p_price) values ('(PS5) 저스트댄스 2021 한글정발 + PS5 카메라 새제품', 255900);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 더 라스트 오브 어스: 리마스터드', 20500);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 더 라스트 오브 어스 파트 2 한글판', 25070);
insert into tblproduct2 (p_name, p_price) values ('(PS4) 디트로이트 비컴 휴먼 Detroit Become Human', 35000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 링 피트 어드벤처', 69000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 모여봐요 동물의 숲', 51000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 몬스터헌터 라이즈', 46000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 별의 커비 스타 얼라이즈', 52800);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 슈퍼 마리오 파티', 52900);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 슈퍼마리오 오딧세이', 53000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 스위치 슈퍼 마리오 3D 월드+퓨리 월드', 52000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 스위치 젤다의전설 야생의숨결 한글판', 66700);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 포켓몬스터 레츠고 피카츄', 55890);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 포켓몬스터 브릴리언트 다이아몬드', 50700);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 스위치 정품 칩 팩 SWITCH 포켓몬스터소드 한글판', 34800);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 스위치 피트니스 복싱2 리듬 & 엑서사이즈 (한글판) 새제품 칩 팩', 49000);
insert into tblproduct2 (p_name, p_price) values ('(Nintendo Switch) 닌텐도 뉴클리어 매일매일 두뇌 트레이닝 (터치펜 동봉판) 한글판', 36000);
insert into tblproduct2 (p_name, p_price) values ('', );
select * from tblproduct2;

-- 이제 카트 main에 담아보자(장바구니 main)
insert into tblcartmain2 (m_id) values ('tiger');
insert into tblcartmain2 (m_id) values ('lion');
select * from tblcartmain2;

-- 카트sub에 상품 정보가 필요하다.(장바구니 sub)
insert into tblcartsub2 (cm_code, p_code, cs_cnt) values (1001, 1001, 1); -- 카트 메인코드(tiger)1001, 상품 코드 1001, 구매 수량
insert into tblcartsub2 (cm_code, p_code, cs_cnt) values (1001, 1003, 5);
insert into tblcartsub2 (cm_code, p_code, cs_cnt) values (1002, 1002, 1);
insert into tblcartsub2 (cm_code, p_code, cs_cnt) values (1002, 1004, 3);

select * from tblcartsub;