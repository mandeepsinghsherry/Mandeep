FROM mysql:8.0.33
LABEL "project"="Vprofile"
LABEL "Author"="MANDEEP SINGH SANDHU"

ENV MYSQL_ROOT_PASSWORD="admin"
ENV MYSQL_DATABASE="admin"

ADD db_backup.sql docker-entrypoint-initdb.d/db_backup.sql
