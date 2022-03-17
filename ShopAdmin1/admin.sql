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

-- 1. 고객을 만들자
create table tblmember2 (
	m_id VARCHAR(50) NOT NULL PRIMARY KEY, -- 아이디
	m_passwd VARCHAR(50) NOT NULL, -- 비번
	m_name VARCHAR(50) NOT NULL, -- 성명
	m_phone int not null,
	m_email VARCHAR(50) NOT NULL,
	m_rdate DATETIME NOT NULL DEFAULT SYSDATE(), -- 최초 입력일과 수정일을 설정하는게 좋다.	
	m_udate DATETIME NOT NULL DEFAULT SYSDATE() -- 수정일
);

drop table tblmember2;

/* 상품 테이블 */
create table tblproduct2 (
	p_code int not null primary key auto_increment,
	p_name VARCHAR(100) not null, 
	p_price int not null,
	p_rdate datetime not null default sysdate(),
	p_udate datetime not null default sysdate()
);

/* 주문 main */
create table tblordermain2 (
	om_code int not null primary key auto_increment,
	m_id varchar(50) not null, -- FK, 주문자가 필요하지만, 카트메인에 있다. 그걸 포링키로 끌고오기보다는 그래도 신규로 정해주는게 좋다. 
	om_rdate datetime not null default sysdate(),
	om_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);
alter table tblordermain2 auto_increment=1001; -- 상품번호를 1001부터 시작해서 자동증가
-- select * from tblordermain;
	