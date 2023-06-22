CREATE TABLE member (
  member_no NUMBER NOT NULL,
  member_name VARCHAR2(100) NOT NULL,
  member_email VARCHAR2(100) NOT NULL,
  password VARCHAR2(100) NOT NULL,
  member_birth DATE NOT NULL,
  member_status NUMBER DEFAULT 0 NOT NULL,
  member_address VARCHAR2(1000) NOT NULL,
  member_detail_address VARCHAR2(1000) NULL,
  CONSTRAINT PK_MEMBER PRIMARY KEY (member_no)
);

insert into member(member_no,member_name, member_email, password, member_birth, member_status, member_address, member_detail_address) 
values(1,'박해준','cyon8254@gmail.com','1234',sysdate,0,'성남시','아튼빌');
CREATE TABLE service_board (
  service_board_no NUMBER NOT NULL,
  service_board_title VARCHAR2(100) NOT NULL,
  service_board_content VARCHAR2(4000) NOT NULL,
  service_board_create_date DATE NOT NULL,
  service_board_hits NUMBER DEFAULT 0 NOT NULL,
  service_date DATE NOT NULL,
  nation VARCHAR2(100) NOT NULL,
  member_no NUMBER NOT NULL,
  CONSTRAINT PK_SERVICE_BOARD PRIMARY KEY (service_board_no),
  CONSTRAINT FK_SERVICE_BOARD_MEMBER FOREIGN KEY (member_no)
    REFERENCES member (member_no)
);

CREATE SEQUENCE service_board_seq;
create sequence member_seq;

INSERT INTO service_board(service_board_no)VALUES(service_board_seq.nextval);

insert into member(member_no,member_name, member_email, password, member_birth, member_status, member_address, member_detail_address) 
values(member_seq.nextval,'박해준','cyon8254@gmail.com','1234',sysdate,0,'성남시','아튼빌');
delete from member;
--
ALTER TABLE service_board
ADD CONSTRAINT FK_SERVICE_BOARD_MEMBER
FOREIGN KEY (member_no)
REFERENCES member(member_no)
ON DELETE CASCADE;

--멤버 상태 기본값 설정 
ALTER TABLE member MODIFY (member_status DEFAULT 1); 

--게시판 조회수 기본값 설정 
ALTER TABLE service_board MODIFY (service_board_hits DEFAULT 0); 


