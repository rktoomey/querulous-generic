package com.twitter.querulous.unit

import org.specs.Specification
import org.specs.mock.{JMocker, ClassMocker}
import java.sql.{ResultSet, SQLException, SQLIntegrityConstraintViolationException}
import com.twitter.querulous.test.FakeQueryEvaluator
import com.twitter.querulous.evaluator.{AutoDisablingQueryEvaluator, Transaction}
import com.twitter.xrayspecs.Time
import com.twitter.xrayspecs.TimeConversions._


//class AutoDisablingQueryEvaluatorSpec extends Specification with JMocker with ClassMocker {
//  "AutoDisablingQueryEvaluator" should {
//    "select" in {
//      val trans = mock[Transaction]
//      val disableErrorCount = 5
//      val disableDuration = 1.minute
//      val queryEvaluator = new FakeQueryEvaluator(trans, List(mock[ResultSet]))
//      val autoDisablingQueryEvaluator = new AutoDisablingQueryEvaluator(queryEvaluator, disableErrorCount, disableDuration)
//      Time.freeze()
//
//      "when there are no failures" >> {
//        autoDisablingQueryEvaluator.select("SELECT 1 FROM DUAL") { _ => 1 } mustEqual List(1)
//      }
//
//      "when there are some failures" >> {
//        "when the failures are SQLIntegrityConstraintViolationException" >> {
//          var invocationCount = 0
//
//          invocationCount = 0
//          (0 until disableErrorCount + 1) foreach { i =>
//            autoDisablingQueryEvaluator.select("SELECT 1 FROM DUAL") { resultSet =>
//              invocationCount += 1
//              throw new SQLIntegrityConstraintViolationException
//            } must throwA[SQLIntegrityConstraintViolationException]
//          }
//          invocationCount mustEqual disableErrorCount + 1
//        }
//
//        "when the failures are any other exception" >> {
//          "when there are more than disableErrorCount failures" >> {
//            var invocationCount = 0
//
//            (0 until disableErrorCount + 1) foreach { i =>
//              autoDisablingQueryEvaluator.select("SELECT 1 FROM DUAL") { resultSet =>
//                invocationCount += 1
//                throw new SQLException
//              } must throwA[SQLException]
//            }
//            invocationCount mustEqual disableErrorCount
//          }
//
//          "when there are more than disableErrorCount failures but disableDuration has elapsed" >> {
//            var invocationCount = 0
//
//            (0 until disableErrorCount + 1) foreach { i =>
//              autoDisablingQueryEvaluator.select("SELECT 1 FROM DUAL") { resultSet =>
//                invocationCount += 1
//                throw new SQLException
//              } must throwA[SQLException]
//            }
//            invocationCount mustEqual disableErrorCount
//
//            Time.advance(1.minute)
//            autoDisablingQueryEvaluator.select("SELECT 1 FROM DUAL") { resultSet =>
//              invocationCount += 1
//            }
//            invocationCount mustEqual disableErrorCount + 1
//          }
//        }
//      }
//    }
//  }
//}
