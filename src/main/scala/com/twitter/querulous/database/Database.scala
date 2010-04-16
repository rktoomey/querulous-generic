package com.twitter.querulous.database

import java.sql.Connection

trait DatabaseFactory {
  def apply(jdbcDriver: String, jdbcUrl: String, username: String, password: String): Database
}

trait Database {
  def open(): Connection

  def close(connection: Connection)

  def withConnection[A](f: Connection => A): A = {
    val connection = open()
    try {
      f(connection)
    } finally {
      close(connection)
    }
  }
}
