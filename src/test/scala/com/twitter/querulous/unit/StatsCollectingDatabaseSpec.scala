package com.twitter.querulous.unit

import org.specs.Specification
import org.specs.mock.{ClassMocker, JMocker}

//class StatsCollectingDatabaseSpec extends Specification with JMocker with ClassMocker {
//  "StatsCollectingDatabase" should {
//    Time.freeze()
//    val latency = 1.second
//    val connection = mock[Connection]
//    val stats = new FakeStatsCollector
//    val pool = new StatsCollectingDatabase(new FakeDatabase(connection, latency), stats)
//
//    "collect stats" in {
//      "when closing" >> {
//        pool.close(connection)
//        stats.times("database-close-timing") mustEqual latency.inMillis
//      }
//
//      "when opening" >> {
//        pool.open()
//        stats.times("database-open-timing") mustEqual latency.inMillis
//      }
//    }
//  }
//}
