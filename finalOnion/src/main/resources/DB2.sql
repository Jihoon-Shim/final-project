-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- T_MEMBER Table Create SQL
CREATE TABLE T_MEMBER
(
    MEMBERID           VARCHAR2(100)    NOT NULL, 
    MEMBER_NAME        VARCHAR2(100)    NOT NULL, 
    MEMBER_PASSWORD    VARCHAR2(100)    NOT NULL, 
    MEMBER_ADDRESS     VARCHAR2(100)    NOT NULL, 
    ONION_POINT        NUMBER           NOT NULL, 
    MEMBER_NICKNAME     VARCHAR2(100)    NOT NULL, 
    MEMBER_PICTURE     VARCHAR2(100)    NOT NULL, 
    ENABLED            NUMBER(1)        NOT NULL, 
    MEMBER_TEL         VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (MEMBERID)
)
select * from t_member where memberId='test3'
select * from FILEVO;

select * from chattingRoom
select * from chatting

select chattingRoom_No, chattingRoom_title from chattingRoom
	where chattingRoom_No in (
	select distinct chattingRoom_No from chatting where memberId='java')
	
select distinct * from (
select memberId from chatting where chattingRoom_No=1) where memberId not in 'java'

select * from TRADING_BOARD where memberId = 'java' and trade_status = 1 and board_content like '%ㅇ%';

select distinct memberId from (
	select distinct memberId from chatting where chattingRoom_title=4 )
where not memberId = 'java'

select * from (
select chatting from chatting 
	where chattingRoom_No in (
	select distinct c.chattingRoom_No from chatting c, chattingRoom r where memberId='java'
	and chattingRoom_title like '%java%' and chattingRoom_title like '%spring%'
	and c.chattingRoom_no = r.chattingRoom_no)
	order by chatting_no desc
	) where rownum <=1
update chatting set reception = 1
update (
select reception from (
select memberId, reception
FROM chatting
where CHATTINGROOM_NO = (
   select CHATTINGROOM_NO from CHATTINGROOM where
   CHATTINGROOM_TITLE LIKE '%java%' and  CHATTINGROOM_TITLE like '%bear%'))
   where memberId= 'java' and reception = 1)
set reception = 0

select distinct nvl(max(c.reception), 0) as reception from chatting c, chattingRoom r 
where memberId= 'java' and chattingRoom_title = 'java and bear' 
and c.chattingRoom_no = r.chattingRoom_no and reception = 1

select distinct nvl(max(c.reception), 0) as reception from chatting c, chattingRoom r 
where chattingRoom_title like '%bear%' and memberId != 'bear' and reception = 1
and c.chattingRoom_no = r.chattingRoom_no 

select * from TRADING_BOARD
-- TAG Table Create SQL
CREATE TABLE TAG
(
    TAGID      NUMBER           NOT NULL, 
    TAG        VARCHAR2(100)    NOT NULL, 
    TAGHITS    NUMBER           NOT NULL, 
     PRIMARY KEY (TAGID)
)
CREATE SEQUENCE TAG_SEQ
DROP SEQUENCE TAG_SEQ
SELECT * FROM TAG
SELECT * FROM BOARDTAG
SELECT * FROM TRADING_BOARD



-- TRADING_BOARD Table Create SQL
CREATE TABLE TRADING_BOARD
(
    BOARD_NO           NUMBER           NOT NULL, 
    BOARD_TITLE        VARCHAR2(100)    NOT NULL, 
    BOARD_CONTENT      CLOB             NOT NULL, 
    BOARD_KIND         VARCHAR2(100)    NOT NULL, 
    TRADE_STATUS       NUMBER(1)        NOT NULL, 
    TRADE_PRICE        NUMBER           NOT NULL, 
    PRODUCT_PICTURE    VARCHAR2(100)    NOT NULL, 
    HITS               NUMBER           NOT NULL, 
    MEMBERID           VARCHAR2(100)    NOT NULL, 
    BOARD_DATE         DATE             NOT NULL, 
     PRIMARY KEY (BOARD_NO)
)
SELECT * FROM TRADING_BOARD

ALTER TABLE TRADING_BOARD
    ADD CONSTRAINT FK_MEMBERID FOREIGN KEY (MEMBERID)
        REFERENCES T_MEMBER (MEMBERID)
--FK_MEMBERID : MEMBER TO TRADING_BOARD
 
        CREATE SEQUENCE TRADING_BOARD_SEQ;
        DROP SEQUENCE TRADING_BOARD_SEQ;

-- CHATTINGROOM Table Create SQL
CREATE TABLE CHATTINGROOM
(
    CHATTINGROOM_NO       NUMBER           NOT NULL, 
    CHATTINGROOM_TITLE    VARCHAR2(100)    NOT NULL, 
    CHATTINGROOM_DATE     DATE             NOT NULL, 
     PRIMARY KEY (CHATTINGROOM_NO)
)
	 CREATE SEQUENCE CHATTINGROOM_SEQ;
	 DROP SEQUENCE CHATTINGROOM_SEQ

-- EMOTICON Table Create SQL
CREATE TABLE EMOTICON
(
    EMOTICON_NO         NUMBER           NOT NULL, 
    EMOTICON_ADDRESS    VARCHAR2(100)    NOT NULL, 
    EMOTICON_NAME       VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (EMOTICON_NO)
)
CREATE SEQUENCE EMOTICON_SEQ
DROP SEQUENCE EMOTICON_SEQ


-- CHATTING Table Create SQL
CREATE TABLE CHATTING
(
    CHATTING_NO        NUMBER           NOT NULL, 
    CHATTINGROOM_NO    NUMBER           NOT NULL, 
    CHATTING_DATE      DATE             NOT NULL, 
    RECEPTION          NUMBER(1)        NOT NULL, 
    CHATTING           VARCHAR2(100)    NOT NULL, 
    MEMBERID           VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (CHATTING_NO)
)

ALTER TABLE CHATTING
    ADD CONSTRAINT FK_MEMBERID2 FOREIGN KEY (MEMBERID)
        REFERENCES T_MEMBER (MEMBERID)
--FK_MEMBERID2 : T_MEMBER TO CHATTING
        
        
ALTER TABLE CHATTING
    ADD CONSTRAINT FK_CHATTINGROOM_NO FOREIGN KEY (CHATTINGROOM_NO)
        REFERENCES CHATTINGROOM (CHATTINGROOM_NO)

        CREATE SEQUENCE CHATTING_SEQ
        DROP SEQUENCE CHATTING_SEQ
        

-- ADMINBOARD Table Create SQL
CREATE TABLE ADMINBOARD
(
    ADMINBOARD_NO         NUMBER    NOT NULL, 
    ADMINBOARD_KIND       VARCHAR2(100)    NOT NULL, 
    ADMINBOARD_TITLE      VARCHAR2(100)    NOT NULL, 
    ADMINBOARD_CONTENT    CLOB             NOT NULL, 
    ADMINBOARD_DATE       DATE             NOT NULL, 
    MEMBERID              VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (ADMINBOARD_NO)
)

ALTER TABLE ADMINBOARD
    ADD CONSTRAINT FK_MEMBERID3 FOREIGN KEY (MEMBERID)
        REFERENCES T_MEMBER (MEMBERID)
/
CREATE SEQUENCE ADMINBOARD_SEQ
DROP SEQUENCE ADMINBOARD_SEQ

--FK_MEMBERID3 : T_MEMBER TO ADMINBOARD


-- BOARDTAG Table Create SQL
CREATE TABLE BOARDTAG
(
    BOARD_NO    NUMBER    NOT NULL, 
    TAGID       NUMBER    NOT NULL, 
    TAGDATE     DATE      NOT NULL, 
     PRIMARY KEY (BOARD_NO, TAGID)
)

ALTER TABLE BOARDTAG
    ADD CONSTRAINT FK_BOARD_NO FOREIGN KEY (BOARD_NO)
        REFERENCES TRADING_BOARD (BOARD_NO)

ALTER TABLE BOARDTAG
    ADD CONSTRAINT FK_TAGID FOREIGN KEY (TAGID)
        REFERENCES TAG (TAGID)

        CREATE SEQUENCE BOARDTAG_SEQ
        DROP SEQUENCE BOARDTAG_SEQ

-- TEMP Table Create SQL
CREATE TABLE TEMP
(
    MEMBERID     VARCHAR2(100)    NOT NULL, 
    TEMP         NUMBER           NOT NULL, 
    TEMPCOUNT    NUMBER           NOT NULL, 
     PRIMARY KEY (MEMBERID)
)
ALTER TABLE TEMP
    ADD CONSTRAINT FK_MEMBERID4 FOREIGN KEY (MEMBERID)
        REFERENCES T_MEMBER (MEMBERID)
--FK_MEMBERID4 : T_MEMBER TO TEMP

        drop table AUTHORITY
        
-- AUTHORITY Table Create SQL
CREATE TABLE AUTHORITY
(
    MEMBERID    VARCHAR2(100)    NOT NULL, 
    AUTHORITY       VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (MEMBERID)
)
SELECT * FROm AUTHORITY
ALTER TABLE AUTHORITY
    ADD CONSTRAINT FK_MEMBERID5 FOREIGN KEY (MEMBERID)
        REFERENCES T_MEMBER (MEMBERID)
--FK_MEMBERID5 : T_MEMBER TO AUTHORITY

-- REPORTINGBOARD Table Create SQL
CREATE TABLE REPORTINGBOARD
(
    REPORTBOARD_NO         NUMBER           NOT NULL, 
    REPORTBOARD_TITLE      VARCHAR2(100)    NOT NULL, 
    REPORTBOARD_DATE       DATE             NOT NULL, 
    REPORTBOARD_CONTENT    CLOB             NOT NULL, 
    MEMBERID               VARCHAR2(100)    NOT NULL, 
    REPORTEDID             VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (REPORTBOARD_NO)
)

ALTER TABLE REPORTINGBOARD
    ADD CONSTRAINT FK_MEMBERID6 FOREIGN KEY (MEMBERID)
        REFERENCES T_MEMBER (MEMBERID)
        
        CREATE SEQUENCE REPORTINGBOARD_SEQ
        DROP SEQUENCE REPORTINGBOARD_SEQ
        
        --FK_MEMBERID : T_MEMBER TO TRADING_BOARD
        --FK_MEMBERID2 : T_MEMBER TO CHATTING
        --FK_MEMBERID3 : T_MEMBER TO ADMINBOARD
        --FK_MEMBERID4 : T_MEMBER TO TEMP
        --FK_MEMBERID5 : T_MEMBER TO AUTHORITY
        --FK_MEMBERID6:T_MEMBER TO REPORTINGBOARD
        
        DROP TABLE REPORTINGBOARD
        DROP TABLE AUTHORITY
        DROP TABLE TEMP
        DROP TABLE BOARDTAG
        DROP TABLE ADMINBOARD
        DROP TABLE CHATTING
        DROP TABLE EMOTICON
        DROP TABLE CHATTINGROOM
        DROP TABLE TRADING_BOARD
        DROP TABLE TAG
        DROP TABLE T_MEMBER
        
        
        select BOARD_NO, BOARD_TITLE,  rnum, BOARD_DATE, TRADE_PRICE, PRODUCT_PICTURE, MEMBERID,tem
		from( 
			select ROW_NUMBER() OVER(ORDER BY tem DESC) as rnum, 
			BOARD_NO, BOARD_TITLE, BOARD_DATE, TRADE_PRICE, PRODUCT_PICTURE, MEMBERID, tem
			from ( 
				select NVL(t.TEMP,0) as tem, b.BOARD_NO, b.BOARD_TITLE, b.BOARD_DATE, b.TRADE_PRICE, b.PRODUCT_PICTURE, b.MEMBERID
				from TEMP t right outer join TRADING_BOARD b on t.MEMBERID = b.MEMBERID
				WHERE b.BOARD_KIND = '삽니다'
			)
		) 
		where rnum BETWEEN 1 AND 4
		
		SELECT * FROM BOARDTAG
		
		t.TAGID,t.TAG,t.TAGHITS 
		SELECT *
		FROM BOARDTAG b		
		INNER JOIN TAG t 
		ON b.TAGID=t.TAGID;
		WHERE b.BOARD_NO=106
		
		SELECT t.TAGID,t.TAG,t.TAGHITS 
		FROM BOARDTAG b	, TAG t
		WHERE  b.TAGID=t.TAGID(+) AND b.BOARD_NO=106;
		
		select TAG from TAG WHERE  TAG LIKE '%'||'다'||'%' AND ROWNUM<=6;
		
		INSERT INTO TAG(TAGID,TAG,TAGHITS) VALUES(TAG_SEQ.nextval,'다',0);
		INSERT INTO TAG(TAGID,TAG,TAGHITS) VALUES(TAG_SEQ.nextval,'다라',0);
		INSERT INTO TAG(TAGID,TAG,TAGHITS) VALUES(TAG_SEQ.nextval,'다라마',0);
		INSERT INTO TAG(TAGID,TAG,TAGHITS) VALUES(TAG_SEQ.nextval,'나다',0);
		INSERT INTO TAG(TAGID,TAG,TAGHITS) VALUES(TAG_SEQ.nextval,'가나다',0);
		INSERT INTO TAG(TAGID,TAG,TAGHITS) VALUES(TAG_SEQ.nextval,'가다마',0);
		
		
		SELECT COUNT(*) FROM TRADING_BOARD b, (SELECT x.TAG , y.BOARD_NO FROM TAG x, BOARDTAG y WHERE x.TAGID=y.TAGID) t
		WHERE b.BOARD_KIND = '삽니다' AND b.BOARD_NO = t.BOARD_NO AND (b.BOARD_CONTENT LIKE  '%'||'강'||'%' OR b.BOARD_TITLE LIKE '%'||'강'||'%' OR t.TAG LIKE '%'||'강'||'%')
		
		select BOARD_NO, BOARD_TITLE,  rnum, BOARD_DATE, TRADE_PRICE, PRODUCT_PICTURE, MEMBERID,tem
		from( 
			select ROW_NUMBER() OVER(ORDER BY BOARD_DATE DESC) as rnum, 
			BOARD_NO, BOARD_TITLE, BOARD_DATE, TRADE_PRICE, PRODUCT_PICTURE, MEMBERID, tem
			from (
				select NVL(t.TEMP,0) as tem, b.BOARD_NO, b.BOARD_TITLE, b.BOARD_DATE, b.TRADE_PRICE, b.PRODUCT_PICTURE, b.MEMBERID
				from TEMP t 
				right outer join (
					SELECT BOARD_NO, BOARD_TITLE,BOARD_DATE, TRADE_PRICE, PRODUCT_PICTURE, MEMBERID, TRADE_STATUS, BOARD_KIND
					FROM TRADING_BOARD					
					WHERE BOARD_KIND = '삽니다' AND (BOARD_CONTENT LIKE  '%'||''||'%' OR BOARD_TITLE LIKE '%'||''||'%')
										) b
				on t.MEMBERID = b.MEMBERID
				WHERE b.BOARD_KIND = '삽니다' and b.TRADE_STATUS = 1
			)
		)
		where rnum BETWEEN 1 AND 10
		