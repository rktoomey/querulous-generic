package com.twitter.querulous
package unit

import org.specs.mock.JMocker
import org.specs.Specification
import com.twitter.querulous.database.{Database, DatabaseFactory, MemoizingDatabaseFactory}


class MemoizingDatabaseFactorySpec extends Specification with JMocker {
  val username = "username"
  val password = "password"
  val driver = "com.whatever"

  "MemoizingDatabaseFactory" should {
    "apply" in {
      val database1 = mock[Database]
      val database2 = mock[Database]
      val databaseFactory = mock[DatabaseFactory]
      val memoizingDatabase = new MemoizingDatabaseFactory(databaseFactory)

      expect {
        one(databaseFactory).apply(driver, "bar", username, password) willReturn database1
        one(databaseFactory).apply(driver, "baz", username, password) willReturn database2
      }
      memoizingDatabase(driver, "bar", username, password) mustBe database1
      memoizingDatabase(driver, "bar", username, password) mustBe database1
      memoizingDatabase(driver, "baz", username, password) mustBe database2
      memoizingDatabase(driver, "baz", username, password) mustBe database2
    }
  }
}
