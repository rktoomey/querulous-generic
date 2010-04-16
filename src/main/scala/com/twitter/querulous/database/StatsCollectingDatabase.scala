package com.twitter.querulous.database

import java.sql.Connection

class StatsCollectingDatabaseFactory(
  databaseFactory: DatabaseFactory,
  stats: StatsCollector) extends DatabaseFactory {

  def apply(jdbcDriver: String, jdbcUrl: String, username: String, password: String) = {
    new StatsCollectingDatabase(databaseFactory(jdbcDriver, jdbcUrl, username, password), stats)
  }
}

class StatsCollectingDatabase(database: Database, stats: StatsCollector)
  extends Database {

  override def open(): Connection = {
    stats.time("database-open-timing") {
      database.open()
    }
  }

  override def close(connection: Connection) = {
    stats.time("database-close-timing") {
      database.close(connection)
    }
  }
}
