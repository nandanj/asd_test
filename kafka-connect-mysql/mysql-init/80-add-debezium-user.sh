create_debezium_user() {
  log_info "Creating ${MYSQL_DEBEZIUM_USER} user "
mysql $mysql_flags <<EOSQL
    CREATE USER '${MYSQL_DEBEZIUM_USER}'@'%' IDENTIFIED WITH mysql_native_password BY '${MYSQL_DEBEZIUM_PASSWORD}';
EOSQL

  log_info "Granting privileges to user ${MYSQL_DEBEZIUM_USER} ..."
mysql $mysql_flags <<EOSQL
      GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT  ON *.* TO '${MYSQL_DEBEZIUM_USER}'@'%';
      FLUSH PRIVILEGES ;
EOSQL
}

if ! [ -v MYSQL_RUNNING_AS_SLAVE ]; then
  create_debezium_user
fi