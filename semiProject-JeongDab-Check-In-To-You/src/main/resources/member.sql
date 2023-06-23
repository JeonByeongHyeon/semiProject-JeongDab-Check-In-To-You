CREATE TABLE member (
  member_no NUMBER NOT NULL,
  member_name VARCHAR2(100) NOT NULL,
  member_email VARCHAR2(100) NOT NULL,
  password VARCHAR2(100) NOT NULL,
  member_birth DATE NOT NULL,
  member_status NUMBER DEFAULT 1 NOT NULL,
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

insert into member(member_no,member_name, member_email, password, member_birth,member_status, member_address, member_detail_address) 
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



-- 회원보기
SELECT * FROM member;


-- 게시글 보기
SELECT * FROM service_board;

-- 샘플 게시글 등록
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);

select count(*)from service_board

-- 상세글보기(게시글번호로 검색)
SELECT s.service_board_no,s.service_board_title,s.service_board_content,
to_char(service_board_create_date,'YYYY.MM.DD') as service_board_create_date,
s.service_board_hits,to_char(service_date,'YYYY.MM.DD')as service_date,s.nation,m.member_name,m.member_email
FROM service_board s
INNER JOIN member m ON s.member_no=m.member_no
WHERE s.service_board_no=21;

commit

select * from member;
