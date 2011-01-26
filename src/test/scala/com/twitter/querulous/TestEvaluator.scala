package com.twitter.querulous

import com.twitter.querulous.database.SingleConnectionDatabaseFactory
import com.twitter.querulous.query.SqlQueryFactory
import com.twitter.querulous.evaluator.StandardQueryEvaluatorFactory

object TestEvaluator {
  val testDatabaseFactory = new SingleConnectionDatabaseFactory
  val testQueryFactory = new SqlQueryFactory
  val testEvaluatorFactory = new StandardQueryEvaluatorFactory(testDatabaseFactory, testQueryFactory)
}
