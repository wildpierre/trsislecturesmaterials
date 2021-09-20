CREATE TABLE SCHOOL (  SCHOOL_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),  SCHOOL_NUMBER INT,  SCHOOL_NAME VARCHAR(128));
CREATE TABLE BATCH (  BATCH_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),  BATCH_NUMBER VARCHAR(45),  BATCH_SCHOOL_ID INT NOT NULL,  CONSTRAINT fk_BATCH_SCHOOL1    FOREIGN KEY (BATCH_SCHOOL_ID)    REFERENCES SCHOOL (SCHOOL_ID));
CREATE TABLE STUDENT (  STUD_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),  STUD_FNAME VARCHAR(128),  STUD_LNAME VARCHAR(128),  STUD_PNAME VARCHAR(128),  STUD_NUM_ZACH VARCHAR(45),  STUD_BATCH_ID INT NOT NULL,  CONSTRAINT fk_STUDENT_BATCH    FOREIGN KEY (STUD_BATCH_ID)    REFERENCES BATCH (BATCH_ID));