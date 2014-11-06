import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.util.{Success,Failure}

class MatcherSpec extends FlatSpec with Matchers{

    "The .* pattern" should "match 'string'" in {
      val matcher = new Matcher
      matcher.matchPattern("string",".*") should equal (Success(true))
    }
    it should "match nothing" in {
      val matcher = new Matcher
      matcher.matchPattern("string","\\d+") should equal (Success(false))
    }
    it should "not die on empty files list" in {
      val matcher = new Matcher
      matcher.matchPattern("string","\\d+") should equal (Success(false))
    }

    "The * pattern" should "inform on invalid pattern" in {
      val matcher = new Matcher
      matcher.matches("string"::Nil,"*") should equal (Seq())
    }

    "The .*.md pattern" should "filter out md file" in {
      val matcher = new Matcher
      val files = Seq("abc.md","abc.txt","def.zip")
      matcher.matches(files,".*.md") should have size 1
    }

    "Drops" should "show diff of all and matches" in {
      val matcher = new Matcher
      val all = Seq("abc.md","abc.txt","def.zip")
      val matches = Seq("abc.md","abc.txt")
      matcher.drops(all, matches) should have size 1
    }
}