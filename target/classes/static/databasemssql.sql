
DROP TABLE IF EXISTS [users] ; 
 CREATE TABLE users (
   [id] int NOT NULL IDENTITY,
   [email] varchar(55) NOT NULL,
   [password] varchar(55) NOT NULL,
   [first_name] varchar(55) NOT NULL,
   [last_name] varchar(55) NOT NULL,
   [status]  smallint DEFAULT 1,
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int DEFAULT NULL,
   [update_by] int DEFAULT NULL,
   PRIMARY KEY ([id])
 )   ;

 DROP TABLE IF EXISTS [role] ; 
 CREATE TABLE role (
    [id] int NOT NULL IDENTITY,
    [role_name] varchar(55) NOT NULL,
    [status]  smallint DEFAULT 1,
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [create_by] int NOT NULL,
    [update_by] int NOT NULL,
    PRIMARY KEY ([id]),
    CONSTRAINT [create_by_key_role] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
    CONSTRAINT [update_by_key_role] FOREIGN KEY ([update_by]) REFERENCES users ([id])
  );

CREATE INDEX [create_by_idx] ON role ([create_by]);
CREATE INDEX [update_by_idx] ON role ([update_by]);

DROP TABLE IF EXISTS [permission] ; 
 CREATE TABLE permission (
    [id] int NOT NULL IDENTITY,
    [permission_name] varchar(55) NOT NULL,
    [status]  smallint DEFAULT 1,
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
     [create_by] int NOT NULL,
     [update_by] int NOT NULL,
    PRIMARY KEY ([id]),
    CONSTRAINT [create_by_key_permission] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
    CONSTRAINT [update_by_key_permission] FOREIGN KEY ([update_by]) REFERENCES users ([id])
  );

CREATE INDEX [create_by_idx] ON permission ([create_by]);
CREATE INDEX [update_by_idx] ON permission ([update_by]);

DROP TABLE IF EXISTS [role_permission] ; 
 CREATE TABLE role_permission (
   [id] int NOT NULL IDENTITY,
   [role_id] int NOT NULL,
   [permission_id] int NOT NULL,
   [status] smallint DEFAULT '1',
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int NOT NULL,
    [update_by] int NOT NULL,
   PRIMARY KEY ([id])
  ,
   CONSTRAINT [role_permission_permission_id] FOREIGN KEY ([permission_id]) REFERENCES permission ([id]),
   CONSTRAINT [role_permission_role_id] FOREIGN KEY ([role_id]) REFERENCES role ([id]),
          CONSTRAINT [create_by_key_role_permission] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
          CONSTRAINT [update_by_key_role_permission] FOREIGN KEY ([update_by]) REFERENCES users ([id])
 )  ;

CREATE INDEX [role_id_idx] ON role_permission ([role_id]);
CREATE INDEX [permission_id_idx] ON role_permission ([permission_id]);
CREATE INDEX [create_by_idx] ON role_permission ([create_by]);
CREATE INDEX [update_by_idx] ON role_permission ([update_by]);


 DROP TABLE IF EXISTS [user_role] ; 
 CREATE TABLE user_role (
    [id] int NOT NULL IDENTITY,
    [user_id] int NOT NULL,
    [role_id] int NOT NULL,
    [status] smallint DEFAULT '1',
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
     [create_by] int NOT NULL,
     [update_by] int NOT NULL,
    PRIMARY KEY ([id]),
    CONSTRAINT [user_role_user_id] FOREIGN KEY ([user_id]) REFERENCES users ([id]),
    CONSTRAINT [user_role_role_id] FOREIGN KEY ([role_id]) REFERENCES role ([id]),
    CONSTRAINT [create_by_key_user_role] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
    CONSTRAINT [update_by_key_user_role] FOREIGN KEY ([update_by]) REFERENCES users ([id])
  )  ;

CREATE INDEX [role_id_idx] ON user_role ([role_id]);
CREATE INDEX [user_id_key_idx] ON user_role ([user_id]);
CREATE INDEX [create_by_idx] ON user_role ([create_by]);
CREATE INDEX [update_by_idx] ON user_role ([update_by]);

DROP TABLE IF EXISTS [country] ;
 CREATE TABLE country (
   [id] int NOT NULL IDENTITY,
   [name] varchar(55) NOT NULL,
   [code] varchar(10)  DEFAULT NULL,
   [status]  smallint DEFAULT 1,
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int NOT NULL DEFAULT 1,
   [update_by] int NOT NULL DEFAULT 1,
   PRIMARY KEY ([id])
 );

 DROP TABLE IF EXISTS [state] ;
  CREATE TABLE state (
    [id] int NOT NULL IDENTITY,
    [name] varchar(55) NOT NULL,
    [code] varchar(10)  DEFAULT NULL,
    [parent_id] int NOT NULL,
    [status]  smallint DEFAULT 1,
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [create_by] int NOT NULL DEFAULT 1,
    [update_by] int NOT NULL DEFAULT 1,
    PRIMARY KEY ([id]),
    CONSTRAINT [state_parent_id_key] FOREIGN KEY ([parent_id]) REFERENCES country ([id])
  );
CREATE INDEX [parent_id_idx] ON state ([parent_id]);

   DROP TABLE IF EXISTS [city] ;
    CREATE TABLE city (
      [id] int NOT NULL IDENTITY,
      [name] varchar(55) NOT NULL,
      [code] varchar(10)  DEFAULT NULL,
      [parent_id] int NOT NULL,
      [status]  smallint DEFAULT 1,
      [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [create_by] int NOT NULL DEFAULT 1,
      [update_by] int NOT NULL DEFAULT 1,
      PRIMARY KEY ([id]),
      CONSTRAINT [city_parent_id_key] FOREIGN KEY ([parent_id]) REFERENCES state ([id])
    );
 CREATE INDEX [parent_id_idx] ON city ([parent_id]);


DROP TABLE IF EXISTS [company] ;
 CREATE TABLE company (
   [id] int NOT NULL IDENTITY,
   [name] varchar(55) NOT NULL,
   [code] varchar(10) DEFAULT NULL,
   [gst_no] varchar(25) DEFAULT NULL,
   [parent_id] int DEFAULT NULL,
   [type]   smallint DEFAULT 1,
   [status]  smallint DEFAULT 1,
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int NOT NULL,
   [update_by] int NOT NULL,
   PRIMARY KEY ([id]),
   CONSTRAINT [company_create_by] FOREIGN KEY ([update_by]) REFERENCES users ([id]),
   CONSTRAINT [company_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id])
 );
CREATE INDEX [parent_id_idx] ON company ([parent_id]);

DROP TABLE IF EXISTS [client] ;
 CREATE TABLE client (
   [id] int NOT NULL IDENTITY,
   [name] varchar(55) NOT NULL,
   [code] varchar(10) DEFAULT NULL,
   [gst_no] varchar(25) DEFAULT NULL,
   [parent_id] int DEFAULT NULL,
   [type]   smallint DEFAULT 1,
   [status]  smallint DEFAULT 1,
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int NOT NULL,
   [update_by] int NOT NULL,
   PRIMARY KEY ([id]),
   CONSTRAINT [client_create_by] FOREIGN KEY ([update_by]) REFERENCES users ([id]),
   CONSTRAINT [client_update_by] FOREIGN KEY ([create_by]) REFERENCES users ([id])
 );
CREATE INDEX [parent_id_idx] ON client ([parent_id]);

DROP TABLE IF EXISTS [company_client] ;
 CREATE TABLE company_client (
   [id] int NOT NULL IDENTITY,
   [company_id] int NOT NULL,
   [client_id] int DEFAULT NULL,
   [status]  smallint DEFAULT 1,
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int NOT NULL,
   [update_by] int NOT NULL,
   PRIMARY KEY ([id]),
   CONSTRAINT [company_client_create_by] FOREIGN KEY ([update_by]) REFERENCES users ([id]),
   CONSTRAINT [company_client_update_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
   CONSTRAINT [company_client_client_id] FOREIGN KEY ([client_id]) REFERENCES client ([id]),
   CONSTRAINT [company_client_company_id] FOREIGN KEY ([company_id]) REFERENCES company ([id]),
 );
CREATE INDEX [client_id_idx] ON company_client ([client_id]);
CREATE INDEX [company_id_idx] ON company_client ([company_id]);

 DROP TABLE IF EXISTS [item] ;
  CREATE TABLE item (
    [id] int NOT NULL IDENTITY,
    [name] varchar(15) NOT NULL,
    [long_name] varchar(55) DEFAULT NULL,
    [unit_measure] varchar(10) NOT NULL,
    [status]  smallint DEFAULT 1,
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [create_by] int NOT NULL,
    [update_by] int NOT NULL,
    PRIMARY KEY ([id]),
    CONSTRAINT [create_by_key_user_role] FOREIGN KEY ([update_by]) REFERENCES users ([id]),
    CONSTRAINT [update_by_key_user_role] FOREIGN KEY ([create_by]) REFERENCES users ([id])
  );

   DROP TABLE IF EXISTS [client_item] ;
    CREATE TABLE client_item (
      [id] int NOT NULL IDENTITY,
      [item_id] int NOT NULL,
      [client_id] int NOT NULL,
      [client_item_name] varchar(55) DEFAULT NULL,
      [status]  smallint DEFAULT 1,
      [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [create_by] int NOT NULL,
      [update_by] int NOT NULL,
      PRIMARY KEY ([id]),
      CONSTRAINT [client_item_item_id] FOREIGN KEY ([item_id]) REFERENCES item ([id]),
      CONSTRAINT [client_item_client_id] FOREIGN KEY ([client_id]) REFERENCES client ([id]),
      CONSTRAINT [client_item_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
      CONSTRAINT [client_item_update_by] FOREIGN KEY ([update_by]) REFERENCES users ([id])
    );
CREATE INDEX [client_id_idx] ON client_item ([client_id]);
CREATE INDEX [item_idx] ON client_item ([client_id]);
CREATE INDEX [create_by_idx] ON client_item ([create_by]);
CREATE INDEX [update_by_idx] ON client_item ([update_by]);

 DROP TABLE IF EXISTS [address] ;
  CREATE TABLE address (
    [id] int NOT NULL IDENTITY,
    [street_1] varchar(255) NOT NULL,
    [street_2] varchar(255) DEFAULT NULL,
    [street_3] varchar(255) DEFAULT NULL,
    [pin_code] varchar(10) NULL,
    [phone_no] int DEFAULT NULL,
    [mobile_no] int DEFAULT NULL,
    [alt_mobile_no] int DEFAULT NULL,
    [city_id] int DEFAULT NULL,
    [status]  smallint DEFAULT 1,
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [create_by] int NOT NULL,
    [update_by] int NOT NULL,
    PRIMARY KEY ([id]),
    CONSTRAINT [address_city_id] FOREIGN KEY ([city_id]) REFERENCES city ([id]),
    CONSTRAINT [address_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
    CONSTRAINT [address_item_update_by] FOREIGN KEY ([update_by]) REFERENCES users ([id])
  );


  DROP TABLE IF EXISTS [client_address] ;
    CREATE TABLE client_address (
      [id] int NOT NULL IDENTITY,
      [client_id] int NOT NULL,
      [address_id] int NOT NULL,
      [status]  smallint DEFAULT 1,
      [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [create_by] int NOT NULL,
      [update_by] int NOT NULL,
      PRIMARY KEY ([id]),
      CONSTRAINT [client_address_client_id] FOREIGN KEY ([client_id]) REFERENCES client ([id]),
      CONSTRAINT [client_address_address_id] FOREIGN KEY ([address_id]) REFERENCES address ([id]),
      CONSTRAINT [client_address_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
      CONSTRAINT [client_address_item_update_by] FOREIGN KEY ([update_by]) REFERENCES users ([id])
  );
CREATE INDEX [client_id_idx] ON client_address ([client_id]);


DROP TABLE IF EXISTS [account] ;
 CREATE TABLE account (
   [id] int NOT NULL IDENTITY,
   [account_id] int NOT NULL,
   [bank_name] varchar(25) DEFAULT NULL,
   [branch_name] varchar(25) DEFAULT NULL,
   [ifsc_code] varchar(10) DEFAULT NULL,
   [status]  smallint DEFAULT 1,
   [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
   [create_by] int NOT NULL,
   [update_by] int NOT NULL,
   PRIMARY KEY ([id]),
   CONSTRAINT [account_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
   CONSTRAINT [account_update_by] FOREIGN KEY ([update_by]) REFERENCES users ([id])
 );

 DROP TABLE IF EXISTS [client_account] ;
  CREATE TABLE client_account (
    [id] int NOT NULL IDENTITY,
    [account_id] int NOT NULL,
    [client_id] int NOT NULL,
    [status]  smallint DEFAULT 1,
    [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
    [create_by] int NOT NULL,
    [update_by] int NOT NULL,
    PRIMARY KEY ([id]),
    CONSTRAINT [client_account_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
    CONSTRAINT [client_account_update_by] FOREIGN KEY ([update_by]) REFERENCES users ([id])
  );

  DROP TABLE IF EXISTS [agency_account] ;
    CREATE TABLE agency_account (
      [id] int NOT NULL IDENTITY,
      [account_id] int NOT NULL,
      [agency_id] int NOT NULL,
      [status]  smallint DEFAULT 1,
      [create_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [update_time] datetime2(0) NOT NULL DEFAULT GETDATE(),
      [create_by] int NOT NULL,
      [update_by] int NOT NULL,
      PRIMARY KEY ([id]),
      CONSTRAINT [agency_account_create_by] FOREIGN KEY ([create_by]) REFERENCES users ([id]),
      CONSTRAINT [agency_account_update_by] FOREIGN KEY ([update_by]) REFERENCES users ([id])
    );