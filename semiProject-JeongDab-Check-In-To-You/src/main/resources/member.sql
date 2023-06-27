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


CREATE TABLE reply (
    reply_no NUMBER NOT NULL,
    reply_content VARCHAR2(1000),
    reply_date DATE NOT NULL,
    member_no NUMBER NOT NULL,
    service_board_no NUMBER NOT NULL,
    CONSTRAINT PK_REPLY PRIMARY KEY (reply_no),
    CONSTRAINT FK_MEMBER_REPLY FOREIGN KEY (member_no) REFERENCES member (member_no),
    CONSTRAINT FK_SERVICE_BOARD_REPLY FOREIGN KEY (service_board_no) REFERENCES service_board (service_board_no)
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
SELECT  FROM member;


-- 게시글 보기
SELECT * FROM service_board;

-- 샘플 게시글 등록
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',62);

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

insert into member(member_no,member_name, member_email, password, member_birth,member_address, member_detail_address) 
values(member_seq.nextval,'박해준','cyon8254@gmail.com','1234',sysdate,'성남시','아튼빌');



SELECT row_number() over(ORDER BY service_board_no DESC) as rnum,service_board_no,service_board_title,service_date,service_board_hits
FROM service_board; 




SELECT service_board_no,service_board_title,TO_CHAR(service_date,'YYYY.MM.DD') as service_date,service_board_hits
from(
	SELECT row_number() over(ORDER BY service_board_no DESC) as rnum,service_board_no,service_board_title,service_date,service_board_hits FROM  service_board
)
inner join member m on m.member_no =sb.member_no
where rnum between 1 and 5



SELECT rnum,service_board_no,service_board_title,TO_CHAR(service_date,'YYYY.MM.DD') as service_date,service_board_hits
FROM (
	SELECT row_number() over(ORDER BY service_board_no DESC) as rnum,service_board_no,service_board_title,service_date,service_board_hits  FROM  service_board 
) sb WHERE rnum BETWEEN 1 AND 5

--게시글 삭제
delete from service_board where service_board_no= 74;



INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'30캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',6);

INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'31캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'32캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'33캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'34캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'35캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'36캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'37캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'38캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'39캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'40캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'41캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'42캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'43캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'44캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'45캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'46캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'47캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'48캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,
service_board_create_date,service_date,nation,member_no) VALUES
(service_board_seq.nextval,'49캐나다로 가는 애견이동봉사자 구합니다!','2023년 7월 1일 출국하시는 캐나다행 봉사자구해요~!',
sysdate,TO_DATE('2023-7-1','YYYY-MM-DD'),'캐나다',22);

ALTER TABLE member
ADD CONSTRAINT unique_email  UNIQUE (member_email);
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,service_date) VALUES(service_board_seq.nextval,이강인,이강인,'YYYY-MM-DD')
INSERT INTO service_board(service_board_no,service_board_title,service_board_content,service_date) VALUES(service_board_seq.nextval,이강인,이강인,'YYYY-MM-DD')

<<<<<<< HEAD
-- service_board 글 수정
SELECT * FROM service_board;
UPDATE service_board SET service_board_title='수정테스트제목',service_board_content='수정테스트내용', 
service_date= TO_DATE('2023-7-7','YYYY-MM-DD'), nation='수정테스트국가'
WHERE service_board_no=63;
=======

select * from member;
>>>>>>> refs/heads/main

delete from member where member_no = 66;

--조회수 업데이트
update service_board set service_board_hits=service_board_hits+1 where service_board_no=65

