-- create row for each other adult cin RFA 1A
CREATE TABLE OTH_ADLT
(
    IDENTIFIER char(10) PRIMARY KEY NOT NULL, -- generated base on algorithm
    BIRTH_DT date DEFAULT NULL, -- other_adults.date_of_birth
    END_DT date DEFAULT NULL, -- null
    GENDER_CD char(1) NOT NULL, -- required but no value to map. Let's put M as temporary value
    OTH_ADLTNM char(35) NOT NULL, -- other_adults.name_prefix.value + " " + other_adults.first_name + " " + other_adults.middle_name+ " " + other_adults.last_name + " " + other_adults.name_suffix.value
    START_DT date DEFAULT NULL, -- Current date
    LST_UPD_ID char(3) NOT NULL, -- The ID (a sequential 3 digit base 62 number generated by the system) of the STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
    LST_UPD_TS timestamp NOT NULL, -- Current time stamp
    FKPLC_HM_T char(10) NOT NULL,
    COMNT_DSC char(160) DEFAULT ' ' NOT NULL,
    OTH_ADL_CD char(1) DEFAULT 'O' NOT NULL, -- O
    IDENTFD_DT date DEFAULT NULL,
    RESOST_IND char(1) DEFAULT 'NULL',  -- if rfa1b_forms.lived_in_other_state == true then Y. if rfa1b_forms.lived_in_other_state == false then N. if rfa1b_forms.lived_in_other_state == null then null
    PASSBC_CD char(1) DEFAULT 'NULL' -- U
);
CREATE UNIQUE INDEX OTHADL22 ON OTH_ADLT (FKPLC_HM_T)