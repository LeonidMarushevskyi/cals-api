-- Out of State Child Abuse Registry Information about SUBSTITUTE CARE PROVIDERS, OTHER ADULTS IN PLACEMENT HOME (either residing in the PLACEMENT HOME or with significant contact not residing in the PLACEMENT HOME), and COLLATERAL INDIVIDUAL.
CREATE TABLE OST_CHKT
(
    IDENTIFIER char(10) PRIMARY KEY NOT NULL, -- generated base on algorithm
    RCPNT_ID char(10), -- the ID from the SUBSTITUTE CARE PROVIDER or OTHER ADULTS IN PLACEMENT HOME
    RCPNT_CD char(1), -- Substitute Care Providers (S) or Other Adults in Placement Home (O)
    STATE_C smallint NOT NULL,
    REGMNT_IND char(1),
    REQUEST_DT date,
    RECEIVE_DT date,
    STATUS_DT date,
    STATUS_CD char(1),
    LST_UPD_ID char(3) NOT NULL, -- The ID (a sequential 3 digit base 62 number generated by the system) of the STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
    LST_UPD_TS timestamp NOT NULL, -- Current time stamp
    FKCOLTRL_T char(10)
);
CREATE UNIQUE INDEX OSTCHK22 ON OST_CHKT (RCPNT_ID, RCPNT_CD);
CREATE UNIQUE INDEX OSTCHK11 ON OST_CHKT (FKCOLTRL_T)