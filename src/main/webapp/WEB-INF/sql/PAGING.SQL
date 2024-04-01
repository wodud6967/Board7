--테이블에 샘플 데이터 추가 : 1000개
--알트 작은따움표
DECLARE
  DATA1 VARCHAR2(100):='HTML 게시물';
    BEGIN
        FOR I IN 9..1000
        LOOP
            INSERT INTO board (
                BNO,
                MENU_ID,
                TITLE,
                content,
                WRITER,
                REGDATE,
                HIT
            ) VALUES (
                I,
                'MENU01',
                DATA1 || I,
                '내용',
                'kjy',
                sysdate,
                0
            );
        END LOOP;
        COMMIT;
END;
/    
DELETE FROM BOARD 
WHERE BNO IN (998, 999, 678, 234);
SELECT * FROM BOARD ORDER BY BNO DESC;

SELECT ROWNUM BNO, MENU_ID, TITLE, CONTENT, WRITER, REGDATE, HIT
FROM BOARD
ORDER BY BNO ASC;

  SELECT  ROW_NUMBER()  OVER (ORDER BY BNO DESC) RN,
          BNO,  MENU_ID,  TITLE,  CONTENT,  WRITER,  REGDATE,  HIT
   FROM   BOARD
   WHERE  RN BETWEEN 1 AND 10;
   
   SET TIMING ON
   SELECT *
   FROM
  (SELECT  ROW_NUMBER()  OVER (ORDER BY BNO DESC) RN,
          BNO,  MENU_ID,  TITLE,  CONTENT,  WRITER,  REGDATE,  HIT
   FROM   BOARD)T
   WHERE  T.RN BETWEEN 1 AND 10;
   
--3. OFFSET .. FETCH
SET TIMING ON
 SELECT BNO,
        MENU_ID,  
        TITLE,  
        CONTENT, 
        WRITER,  
        TO_CHAR(REGDATE, 'YYYY-MM-DD') REGDATE,  
        HIT
   FROM   BOARD
   ORDER BY BNO DESC
   OFFSET 10 ROWS FETCH NEXT 10 ROWS ONLY;