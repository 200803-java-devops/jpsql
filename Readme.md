# j(ank)psql
A Java based facsimile of the psql client, demonstrating basic JDBC API use.
Requires a `db.properties` file with `url`, `user`, and `password` variables for a PostgreSQL database.
Build with `mvn package` (on Windows requires Inno Setup and WiX tools: `choco install innosetup wixtoolset`).