package org.peidevs.waro

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MainSuite extends AnyFunSuite {
  test("Main has a greeting") {
    assert(Main.greeting() != null)
  }
}

