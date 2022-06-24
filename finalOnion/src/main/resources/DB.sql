-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- T_MEMBER Table Create SQL
CREATE TABLE T_MEMBER
(
    MEMBERID           VARCHAR2(100)    NOT NULL, 
    MEMBER_NAME        VARCHAR2(100)    NOT NULL, 
    MEMBER_PASSWORD    VARCHAR2(100)    NOT NULL, 
    MEMBER_ADDRESS     VARCHAR2(100)    NOT NULL, 
    ONION_POINT        NUMBER           NOT NULL, 
    EMBER_NICKNAME     VARCHAR2(100)    NOT NULL, 
    MEMBER_PICTURE     VARCHAR2(100)    NOT NULL, 
    ENABLED            NUMBER(1)        NOT NULL, 
    MEMBER_TEL         VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (MEMBERID)
)



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
select chatting from chatting where CHATTINGROOM_NO = (
	select CHATTINGROOM_NO from CHATTINGROOM where
	CHATTINGROOM_TITLE LIKE '%양 파%' and  CHATTINGROOM_TITLE like '%아이유%'
	)
select c.chatting ,m.MEMBER_NICKNAME FROM chatting c, T_MEMBER m where CHATTINGROOM_NO = (
   select CHATTINGROOM_NO from CHATTINGROOM where
   CHATTINGROOM_TITLE LIKE '%아이유%' and  CHATTINGROOM_TITLE like '%양 파%'
   )
   AND
   c.MEMBERID = m.MEMBERID
   order by c.chatting_date
select * from CHATTINGROOM
select * from chatting


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

-- AUTHORITY Table Create SQL
CREATE TABLE AUTHORITY
(
    MEMBERID    VARCHAR2(100)    NOT NULL, 
    AUTHORITY       VARCHAR2(100)    NOT NULL, 
     PRIMARY KEY (MEMBERID)
)

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