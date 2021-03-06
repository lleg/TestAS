CREATE SEQUENCE BPM_COMMON.SEQ_CONFIG_PARAM
       INCREMENT BY 1
       NOMINVALUE
       NOMAXVALUE
       CACHE 20
       NOCYCLE
       NOORDER;

CREATE TABLE BPM_COMMON.CONFIG_PARAM
(
   ID           NUMBER(10)           NOT NULL,
   PARAM_CODE   VARCHAR2(100 Byte),
   PARAM_DESC   VARCHAR2(500 Byte),
   PARAM_VALUE  VARCHAR2(255 Byte)
);

ALTER TABLE BPM_COMMON.CONFIG_PARAM
   ADD PRIMARY KEY (ID);


INSERT INTO BPM_COMMON.CONFIG_PARAM (   ID,   PARAM_CODE,   PARAM_DESC,   PARAM_VALUE ) VALUES (   BPM_COMMON.SEQ_CONFIG_PARAM.NEXTVAL,   'ServerUrl',   'BPM Server URL',   'https://84.23.55.153:9444/' );

INSERT INTO BPM_COMMON.CONFIG_PARAM (   ID,   PARAM_CODE,   PARAM_DESC,   PARAM_VALUE ) VALUES (   BPM_COMMON.SEQ_CONFIG_PARAM.NEXTVAL,   'ServerLogin',   'BPM Server user',   'deadmin' );

INSERT INTO BPM_COMMON.CONFIG_PARAM (   ID,   PARAM_CODE,   PARAM_DESC,   PARAM_VALUE ) VALUES (   BPM_COMMON.SEQ_CONFIG_PARAM.NEXTVAL,   'ServerPassword',   'BPM Server user password',   'deadmin' );
