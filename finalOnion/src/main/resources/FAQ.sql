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
<select id="toplistsearch" resultType="org.kosta.myproject.vo.TagVO">
		SELECT TAG, TAGHITS 
		FROM(
			SELECT ROW_NUMBER() OVER(ORDER BY TAGHITS DESC) as rnum, TAG, TAGHITS
			FROM TAG
		)
	WHERE <![CDATA[ rnum <= 3]]>
</select>
SELECT TAG, TAGHITS FROM(	
SELECT TAG, TAGHITS, RANK() OVER (PARTITION BY TAG ORDER BY TO_CHAR(TAGDATE, 'YYYYMMDDHHMMSS') DESC) AS RNK
FROM (
	SELECT t.TAG,t.TAGHITS, b.TAGDATE
	FROM TAG t, BOARDTAG b
	WHERE t.TAGID = b.TAGID	
)
WHERE TAGHITS>=3
)
WHERE RNK=1
SELECT * FROM ADMINBOARD
SELECT * FROM ADMINBOARD WHERE ADMINBOARD_KIND='FAQ'
INSERT INTO ADMINBOARD(ADMINBOARD_NO,ADMINBOARD_KIND,ADMINBOARD_TITLE,ADMINBOARD_CONTENT,ADMINBOARD_DATE,MEMBERID)
VALUES(ADMINBOARD_SEQ.nextval,'FAQ','커뮤니티 가이드라인','양파마켓은 동네 이웃 간의 연결을 도와 따뜻하고 활발한 교류가 있는 지역 사회를 만들기 위해 노력하고 있어요.

이웃을 향한 당신의 따뜻한 관심과 애정은 양파마켓의 가장 큰 동력이에요. 양파마켓을 사용하는 이웃 모두가 커뮤니티 가이드라인을 지키며 따뜻한 지역 커뮤니티를 함께 만들어요. 혼자 힘으로는 할 수 없지만, 우리가 함께라면 할 수 있어요!

양파마켓은 신뢰, 존중, 윤리를 서비스의 중요한 가치라고 생각해요. 우리는 이 가치를 지키기 위해 언제나 최선을 다할 거예요. 더 가깝고 따뜻한 지역 커뮤니티를 위해 동참해주세요.
우리의 따뜻함은 신뢰로부터 나와요. 우리는 가까운 이웃과의 만남이 따뜻함이 될 수 있도록 노력해요. 양파마켓은 언제나 사용자에게 귀 기울이고, 사용자를 위한 서비스를 제공하며 신뢰를 만들어요.

우리는 함께 신뢰를 만들어요.

항상 솔직하게 대화해요.
서로 배려하며, 약속은 반드시 지켜요.
자세하고 정확한 정보를 제공해요.
따뜻한 매너를 함께 만들어요
누구나 찾기 쉽고 안전한 공공장소에서 만나요.
이런 행동은 할 수 없어요.
판매 금지 물품 거래
중고거래 사기 등 이웃에게 손해를 입히는 행위
허위 정보 게시 등 이웃을 속이거나 기만하는 행위,존중,양파마켓의 모든 사용자가 우리 동네 이웃이라는 걸 기억해주세요. 어떠한 상황에서도 우리는 이웃의 다양성을 존중하고, 배려하기로 약속해요. 양파마켓은 누구나 존중받는 따뜻한 지역 커뮤니티를 지향하고 있어요.
우리는 서로 존중해요.
성별, 나이, 종교, 혼인 여부, 인종, 장애 등 이웃의 다양성을 존중해요.
우리 모두는 이 세상에 단 한 명 뿐인 소중한 사람이에요. 따뜻하게 배려해요.
온라인으로 연결되어 있지만, 동네에서 마주쳤던 이웃일 수 있다는 사실을 기억해요.<br>
개인 정보와 사생활은 소중해요. 지나치게 묻거나 공개하지 않아요.

이런 행동은 할 수 없어요.
다양성을 존중하지 않는 차별 및 혐오,거래 불이행 등 불쾌한 거래 경험을 주는 행위,욕설, 괴롭힘 등 불쾌감, 성적 수치심 등을 주는 행위, 배제하거나 소외시키는 행위
윤리
우리는 지역사회의 규범과 가치관을 존중해요. 지역사회에서 다양한 문제가 발생할 수 있다는 것을 알고 있어요. 서비스 내에 우려되는 문제들은 양파마켓에 적극적으로 알리고, 함께 해결해요.
우리는 윤리적으로 행동해요.
이웃이 어려움을 겪고 있다면, 기꺼이 도와요.
따뜻한 지역 커뮤니티를 위해 도덕적으로 선하게 행동해요.
이웃의 안전이 염려된다면 즉시 가까운 경찰서나 구조대에 연락해요.
지역사회 구성원인 우리는 서로가 서로에게 영향을 준다는 사실을 알고, 책임감 있게 행동해요.
관련 법규를 준수해요.

이런 행동은 할 수 없어요.
생명의 소중함을 스스로 버리는 행위(혹은 그에 준하는 모든 행위),생명을 거래하는 행위,폭력, 불법 행위를 조장하거나 도모하는 행위,사회 통념상 용인되기 어려운 모든 행위.
따뜻한 지역 커뮤니티를 만들기 위해서는 우리 모두의 관심과 노력이 필요해요.
부적절한 게시글이나 사용자를 발견하면 적극적으로 신고해주세요.우리 함께 소중한 가치들을 지켜내요.',to_date(SYSDATE),'javaking')