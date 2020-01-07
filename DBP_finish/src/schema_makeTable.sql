
CREATE TABLE authority
(
	authority_name       CHAR(18) NOT NULL ,
	rent_reserve_manage  CHAR(5) DEFAULT  'N'  NOT NULL ,
	overdue_manage       CHAR(5) DEFAULT  'N'  NOT NULL ,
	customer_manage      CHAR(5) DEFAULT  'N'  NOT NULL ,
	car_manage           CHAR(5) DEFAULT  'N'  NOT NULL ,
	QnA_manage           CHAR(5) NULL 
);

CREATE UNIQUE INDEX XPK업무 ON authority
(authority_name   ASC);

ALTER TABLE authority
	ADD CONSTRAINT  XPK업무 PRIMARY KEY (authority_name);

CREATE TABLE car
(
	car_num              CHAR(18) NOT NULL ,
	price                NUMBER NOT NULL ,
	occupied             CHAR(5) NULL ,
	color                CHAR(18) NULL ,
	model_name           CHAR(18) NOT NULL ,
	m_num                number NULL ,
	rent_count           INT NULL 
);

CREATE UNIQUE INDEX XPK자동차 ON car
(car_num   ASC);

ALTER TABLE car
	ADD CONSTRAINT  XPK자동차 PRIMARY KEY (car_num);

CREATE TABLE company
(
	local                CHAR(18) DEFAULT  'SEOUL'  NOT NULL ,
	company_num          CHAR(18) NOT NULL ,
	company_name         CHAR(18) NULL ,
	company_tel          CHAR(18) NULL 
);

CREATE UNIQUE INDEX XPK업체 ON company
(company_num   ASC);

ALTER TABLE company
	ADD CONSTRAINT  XPK업체 PRIMARY KEY (company_num);

CREATE TABLE customer
(
	u_id                 CHAR(18) NOT NULL ,
	name                 CHAR(18) NOT NULL ,
	phone                CHAR(18) NOT NULL ,
	penalty              CHAR(18) DEFAULT  'N'  NOT NULL ,
	age                  NUMBER NOT NULL  CONSTRAINT  check_age CHECK (age >= 20),
	sex                  CHAR(2) NULL ,
	password             CHAR(18) NOT NULL ,
	license_num          CHAR(18) NULL ,
	c_num                number NOT NULL 
);

CREATE UNIQUE INDEX XPK회원 ON customer
(c_num   ASC);

ALTER TABLE customer
	ADD CONSTRAINT  XPK회원 PRIMARY KEY (c_num);

CREATE TABLE license
(
	license_vdate        DATE NOT NULL ,
	license_type         CHAR(18) NULL ,
	license_num          CHAR(18) NOT NULL 
);

CREATE UNIQUE INDEX XPK면허 ON license
(license_num   ASC);

ALTER TABLE license
	ADD CONSTRAINT  XPK면허 PRIMARY KEY (license_num);

CREATE TABLE manager
(
	m_id                 CHAR(18) NOT NULL ,
	name                 CHAR(18) NOT NULL ,
	company_num          CHAR(18) NULL ,
	phone                CHAR(18) NOT NULL ,
	password             CHAR(18) NOT NULL ,
	authority_name       CHAR(18) NOT NULL ,
	m_num                number NOT NULL 
);

CREATE UNIQUE INDEX XPK관리자 ON manager
(m_num   ASC);

ALTER TABLE manager
	ADD CONSTRAINT  XPK관리자 PRIMARY KEY (m_num);

CREATE TABLE model
(
	model_name           CHAR(18) NOT NULL ,
	model_count          INTEGER DEFAULT  0  NOT NULL  CONSTRAINT  MODEL_COUNT_916306813 CHECK (model_count >= 0),
	brand                CHAR(18) NOT NULL ,
	passenger            NUMBER NULL ,
	year                 DATE NULL 
);

CREATE UNIQUE INDEX XPK차종 ON model
(model_name   ASC);

ALTER TABLE model
	ADD CONSTRAINT  XPK차종 PRIMARY KEY (model_name);

CREATE TABLE penalty
(
	penalty_record       CHAR(18) NULL ,
	restrictions         CHAR(18) NULL ,
	late_fee             CHAR(18) NULL ,
	penalty_level        CHAR(18) NOT NULL 
);

CREATE UNIQUE INDEX XPK패널티 ON penalty
(penalty_level   ASC);

ALTER TABLE penalty
	ADD CONSTRAINT  XPK패널티 PRIMARY KEY (penalty_level);

CREATE TABLE overdue
(
	overdue              CHAR(18) NULL ,
	overdue_count        INTEGER DEFAULT  0  NOT NULL  CONSTRAINT  OVERDUE_COUNT_MIN_2043123800 CHECK (overdue_count >= 0),
	penalty_level        CHAR(18) NULL ,
	c_num                number NOT NULL ,
	m_num                number NULL 
);

CREATE UNIQUE INDEX XPK연체 ON overdue
(c_num   ASC);

ALTER TABLE overdue
	ADD CONSTRAINT  XPK연체 PRIMARY KEY (c_num);

CREATE TABLE QnA_manager
(
	c_num                number NOT NULL ,
	m_num                number NOT NULL ,
	QnAnum               NUMBER NOT NULL 
);

CREATE UNIQUE INDEX XPK상담관리 ON QnA_manager
(QnAnum   ASC);

ALTER TABLE QnA_manager
	ADD CONSTRAINT  XPK상담관리 PRIMARY KEY (QnAnum);

CREATE TABLE QnA
(
	Q_content            CHAR(200) NULL ,
	finish               CHAR(18) NULL ,
	rating               NUMBER NULL ,
	QnAdate              DATE DEFAULT  SYSDATE  NULL ,
	A_content            CHAR(200) NULL ,
	QnAnum               NUMBER NOT NULL 
);

CREATE UNIQUE INDEX XPK상담 ON QnA
(QnAnum   ASC);

ALTER TABLE QnA
	ADD CONSTRAINT  XPK상담 PRIMARY KEY (QnAnum);

CREATE TABLE reserve
(
	reserve_num          CHAR(18) NOT NULL ,
	reserve_date         DATE DEFAULT  SYSDATE  NOT NULL ,
	rent_date            DATE NOT NULL ,
	return_date          DATE NOT NULL ,
	car_num              CHAR(18) NOT NULL ,
	c_num                number NULL ,
	m_num                number NULL 
);

CREATE UNIQUE INDEX 예약번호 ON reserve
(reserve_num   ASC);

ALTER TABLE reserve
	ADD CONSTRAINT  예약번호 PRIMARY KEY (reserve_num);

CREATE TABLE rent
(
	rent_number          CHAR(18) NOT NULL ,
	rent_date            DATE DEFAULT  SYSDATE  NOT NULL ,
	return_date          DATE DEFAULT  SYSDATE + 1  NOT NULL ,
	lender               CHAR(18) NOT NULL ,
	reserve_num          CHAR(18) NULL ,
	car_num              CHAR(18) NOT NULL ,
	c_num                number NULL ,
	m_num                number NULL 
);

CREATE UNIQUE INDEX XPK대여 ON rent
(rent_number   ASC);

ALTER TABLE rent
	ADD CONSTRAINT  XPK대여 PRIMARY KEY (rent_number);

ALTER TABLE car
	ADD (CONSTRAINT M_CAR_NUM_FK FOREIGN KEY (model_name) REFERENCES model (model_name));

ALTER TABLE car
	ADD (CONSTRAINT R_24 FOREIGN KEY (m_num) REFERENCES manager (m_num));

ALTER TABLE customer
	ADD (CONSTRAINT R_46 FOREIGN KEY (license_num) REFERENCES license (license_num));

ALTER TABLE manager
	ADD (CONSTRAINT COM_M_NUM_FK FOREIGN KEY (company_num) REFERENCES company (company_num));

ALTER TABLE manager
	ADD (CONSTRAINT A_M_NUM_FK FOREIGN KEY (authority_name) REFERENCES authority (authority_name));

ALTER TABLE overdue
	ADD (CONSTRAINT R_25 FOREIGN KEY (m_num) REFERENCES manager (m_num));

ALTER TABLE overdue
	ADD (CONSTRAINT U_OVER_ID_FK FOREIGN KEY (c_num) REFERENCES customer (c_num));

ALTER TABLE overdue
	ADD (CONSTRAINT R_48 FOREIGN KEY (penalty_level) REFERENCES penalty (penalty_level));

ALTER TABLE QnA_manager
	ADD (CONSTRAINT R_50 FOREIGN KEY (c_num) REFERENCES customer (c_num));

ALTER TABLE QnA_manager
	ADD (CONSTRAINT R_51 FOREIGN KEY (m_num) REFERENCES manager (m_num));

ALTER TABLE QnA
	ADD (CONSTRAINT R_63 FOREIGN KEY (QnAnum) REFERENCES QnA_manager (QnAnum));

ALTER TABLE reserve
	ADD (CONSTRAINT CAR_RESERVE_CNUM_FK FOREIGN KEY (car_num) REFERENCES car (car_num));

ALTER TABLE reserve
	ADD (CONSTRAINT R_23 FOREIGN KEY (m_num) REFERENCES manager (m_num));

ALTER TABLE reserve
	ADD (CONSTRAINT U_RESERVE_ID_FK FOREIGN KEY (c_num) REFERENCES customer (c_num));

ALTER TABLE rent
	ADD (CONSTRAINT CAR_RENT_NUM_FK FOREIGN KEY (car_num) REFERENCES car (car_num));

ALTER TABLE rent
	ADD (CONSTRAINT U_RENT_ID_FK FOREIGN KEY (c_num) REFERENCES customer (c_num));

ALTER TABLE rent
	ADD (CONSTRAINT RERSERVE_RENT_NUM_FK FOREIGN KEY (reserve_num) REFERENCES reserve (reserve_num));

ALTER TABLE rent
	ADD (CONSTRAINT R_22 FOREIGN KEY (m_num) REFERENCES manager (m_num));


