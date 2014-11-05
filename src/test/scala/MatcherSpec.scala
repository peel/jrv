import java.util.regex.PatternSyntaxException

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.util.{Success,Failure}

class MatcherSpec extends FlatSpec with Matchers{

    "The .* pattern" should "match 'string'" in {
      Matcher.matchPattern("string",".*") should equal (Success(true))
    }
    it should "match nothing" in {
      Matcher.matchPattern("string","\\d+") should equal (Success(false))
    }

    "The * pattern" should "fail on invalid pattern" in {
      an [PatternSyntaxException] should be thrownBy Matcher.matchPattern("string","*").get
    }

    "The .*.md pattern" should "filter out md file" in {
      val files = Seq("abc.md","abc.txt","def.zip")
      Matcher.matches(files,".*.md") should have size 1
    }
}