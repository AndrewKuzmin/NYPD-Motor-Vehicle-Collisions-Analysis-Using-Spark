DROP KEYSPACE IF EXISTS collision_db;
CREATE KEYSPACE collision_db WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use collision_db;

CREATE TABLE  collisions_by_zip(
  zip text,
  col_date date,
  key int,
  col_time text,
  borough text,
  on_street text,
  cross_street text,
  off_street text,
  location text,
  PRIMARY KEY(zip, col_date, key)  
 );
 
