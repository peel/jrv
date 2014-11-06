import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ListerSpec extends FlatSpec with Matchers{

  "'abcdefgh' string" should "return list of transformed strings" in{
    val fnLister = new Lister with FuzzyNameLister
    fnLister.list("abcdefgh").length should be > 0
  }
  it should "contain list of strings" in{
    val fnLister = new Lister with FuzzyNameLister
    fnLister.list("abcdefgh").foreach(_ shouldBe a [String])
  }
}
