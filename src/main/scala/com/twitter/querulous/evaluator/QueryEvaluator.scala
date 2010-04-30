package com.twitter.querulous.evaluator

import java.sql.ResultSet
import com.twitter.querulous.query.SqlQueryFactory
import com.twitter.querulous.database.ApachePoolingDatabaseFactory
import com.twitter.xrayspecs.TimeConversions._

object QueryEvaluator extends QueryEvaluatorFactory {
  private def createEvaluatorFactory() = {
    val queryFactory = new SqlQueryFactory
    val databaseFactory = new ApachePoolingDatabaseFactory(null, 10, 10, 1.second, 10.millis, false, 0.seconds)
    new StandardQueryEvaluatorFactory(databaseFactory, queryFactory)
  }

  def apply(jdbcDriver: String, jdbcUrl: String, username: String, password: String) = {
    createEvaluatorFactory()(jdbcDriver, jdbcUrl, username, password)
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
  def nextId(tableName: String): Long
  def insert(query: String, params: Any*): Long
  def transaction[T](f: Transaction => T): T
}
