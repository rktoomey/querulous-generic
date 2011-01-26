package com.twitter.querulous.evaluator

import java.sql.ResultSet
import com.twitter.xrayspecs.TimeConversions._
import net.lag.configgy.ConfigMap
import com.twitter.querulous.StatsCollector
import com.twitter.querulous.query.{SqlQueryFactory, QueryFactory, Query}
import com.twitter.querulous.database.{ApachePoolingDatabaseFactory, DatabaseFactory}

object QueryEvaluatorFactory {
  def fromConfig(config: ConfigMap, databaseFactory: DatabaseFactory, queryFactory: QueryFactory): QueryEvaluatorFactory = {
    var factory: QueryEvaluatorFactory = new StandardQueryEvaluatorFactory(databaseFactory, queryFactory)
    config.getConfigMap("disable").foreach { disableConfig =>
      factory = new AutoDisablingQueryEvaluatorFactory(factory,
                                                       disableConfig("error_count").toInt,
                                                       disableConfig("seconds").toInt.seconds)
    }
    factory
  }

  def fromConfig(config: ConfigMap, statsCollector: Option[StatsCollector]): QueryEvaluatorFactory = {
    fromConfig(config,
               DatabaseFactory.fromConfig(config.configMap("connection_pool"), statsCollector),
               QueryFactory.fromConfig(config, statsCollector))
  }
}

object QueryEvaluator extends QueryEvaluatorFactory {
  private def createEvaluatorFactory() = {
    val queryFactory = new SqlQueryFactory
    val databaseFactory = new ApachePoolingDatabaseFactory(null, 10, 10, 1.second, 10.millis, false, 0.seconds)
    new StandardQueryEvaluatorFactory(databaseFactory, queryFactory)
  }

  def apply(jdbcDriver: String, jdbcUrl: String, username: String, password: String) = {
    createEvaluatorFactory()(jdbcDriver, jdbcUrl, username, password)
  }

  def apply(config: ConfigMap) = {
    createEvaluatorFactory()(config("hostname"), config("database"), config("username"), config("password"))
  }
}

trait QueryEvaluatorFactory {
  def apply(jdbcDriver: String, jdbcUrl: String, username: String, password: String): QueryEvaluator
}

trait QueryEvaluator {
  def select[A](query: String, params: Any*)(f: ResultSet => A): Seq[A]
  def selectOne[A](query: String, params: Any*)(f: ResultSet => A): Option[A]
  def count(query: String, params: Any*): Int
  def execute(query: String, params: Any*): Int
  def executeBatch(query: String)(f: Query => Unit): Int
  def nextId(tableName: String): Long
  def insert(query: String, params: Any*): Long
  def transaction[T](f: Transaction => T): T
}
