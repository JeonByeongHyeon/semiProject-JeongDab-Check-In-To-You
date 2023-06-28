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

--댓글 테이블 생성
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

-- 공지사항 테이블 생성
CREATE TABLE notice(
	notice_no NUMBER NOT NULL,
	notice_board_title VARCHAR2(100) NOT NULL,
  	notice_board_content VARCHAR2(4000) NOT NULL,
  	notice_board_date DATE NOT NULL,
  	notice_board_hits NUMBER DEFAULT 0 NOT NULL,
  	member_no NUMBER NOT NULL,
  	CONSTRAINT PK_NOTICE_BOARD PRIMARY KEY (notice_no),
  CONSTRAINT FK_NOTICE_BOARD_MEMBER FOREIGN KEY (member_no)
    REFERENCES member (member_no)
);
-- 공지게시판 게시글 insert
INSERT INTO notice (notice_no, notice_board_title,notice_board_content,notice_board_date ,member_no) 
VALUES(notice_board_seq.nextval,'얘드라 할말이써','강아지좀 버리지말고 유기견입양해서 잘키우라니까',sysdate,62); 
commit


SELECT * FROM notice;
-- 상세공지사항보기
--이슈에 이 에러 추가하기
SELECT n.notice_no,n.notice_board_title, n.notice_board_content,to_char(notice_board_date ,'YYYY.MM.DD') AS notice_board_date,
n.notice_board_hits FROM notice n
INNER JOIN member m ON n.member_no = m.member_no
WHERE n.notice_no=1;
-----------------------------------------
SELECT notice_no,notice_board_title, notice_board_content,to_char(notice_board_date ,'YYYY.MM.DD') AS notice_board_date,
notice_board_hits FROM notice
WHERE notice_no=1;

--공지사항리스트보기
SELECT rnum,notice_no,notice_board_title, 
to_char(notice_board_date ,'YYYY.MM.DD') AS notice_board_date,
notice_board_hits FROM (SELECT row_number() over(ORDER BY notice_no DESC) as rnum,
notice_no,notice_board_title,notice_board_date,notice_board_hits FROM notice) n
WHERE rnum BETWEEN 1 AND 4;

--공지사항수정
UPDATE notice SET notice_board_title='수정테스트 개버리지마라ㅡㅡ',
notice_board_content='수정테스트 사지말고 입양해라고'
WHERE notice_no=1;
--공지사항삭제
delete from notice where notice_no=3;
-- 공지사항게시판 조회수
update notice set notice_board_hits=notice_board_hits+1 where notice_no=2;

--댓글 삽입

insert into reply (reply_no, reply_content, reply_date,member_no,service_board_no) values(reply_seq.nextval,'두번째 댓글',sysdate,62,65); 

insert into reply (reply_no, reply_content, reply_date,member_no,service_board_no) values(reply_seq.nextval,'야미',sysdate,62,65); 
select*from reply

--시퀀스 생성
CREATE SEQUENCE service_board_seq;
CREATE SEQUENCE notice_board_seq;
create sequence reply_seq;
create sequence member_seq;
--댓글

--댓글 조회

SELECT r.reply_no, r.reply_content, r.reply_date, m.member_no, m.member_name, sb.service_board_no, sb.service_board_title
FROM Reply r
INNER JOIN Member m ON r.member_no = m.member_no
INNER JOIN Service_board sb ON r.service_board_no = sb.service_board_no
where sb.service_board_no = 65 and m.member_no =62

--댓글 삭제
delete from reply where reply_no= 3 and member_no=62






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

ALTER TABLE reply MODIFY (reply_date DEFAULT sysdate);

-- 회원보기
SELECT * FROM member;


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

-- service_board 글 수정
SELECT * FROM service_board;
UPDATE service_board SET service_board_title='수정테스트제목',service_board_content='수정테스트내용', 
service_date= TO_DATE('2023-7-7','YYYY-MM-DD'), nation='수정테스트국가'
WHERE service_board_no=63;


select * from member;

select * from service_board;

delete from member where member_no = 66;

--조회수 업데이트
update service_board set service_board_hits=service_board_hits+1 where service_board_no=65

select * from reply;

UPDATE reply SET reply_content='내가 댓글 바꿔썽', reply_date=sysdate WHERE service_board_no=65 AND reply_no=4;
select * from reply;

ALTER TABLE reply
ADD CONSTRAINT FK_MEMBER_REPLY
FOREIGN KEY (member_no)
REFERENCES member(member_no)
ON DELETE CASCADE;

ALTER TABLE reply
ADD CONSTRAINT FK_SERVICE_BOARD_REPLY
FOREIGN KEY (service_board_no)
REFERENCES service_board(service_board_no)
ON DELETE CASCADE;

ALTER TABLE reply
DROP CONSTRAINT FK_MEMBER_REPLY;

ALTER TABLE reply
DROP CONSTRAINT FK_SERVICE_BOARD_REPLY;
select * from reply;

ALTER TABLE reply
ADD CONSTRAINT FK_MEMBER_REPLY
FOREIGN KEY (member_no)
REFERENCES member(member_no)
ON DELETE CASCADE;

ALTER TABLE reply
ADD CONSTRAINT FK_SERVICE_BOARD_REPLY
FOREIGN KEY (service_board_no)
REFERENCES service_board(service_board_no)
ON DELETE CASCADE;

ALTER TABLE reply
DROP CONSTRAINT FK_MEMBER_REPLY;

ALTER TABLE reply
DROP CONSTRAINT FK_SERVICE_BOARD_REPLY;




select r.reply_content, r.reply_date, m.member_name from reply r, member m, service_board b where r.member_no = m.member_no and r.service_board_no = b.service_board_no and r.service_board_no = 65;


insert into reply (reply_no,reply_content,reply_date) values(reply_seq.nextval,'tqt',sysdate)

insert into reply (reply_no,reply_content,reply_date) values(reply_seq.nextval,'dfdf',sysdate)