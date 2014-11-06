import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import org.mockito.Mockito._

class ConfigSpec extends FlatSpec with Matchers with MockitoSugar{

    "Name of 'abcdefgh'" should "display generated list" in {
      implicit lazy val lister:Lister = mock[Lister with FuzzyNameLister]
      Config(baseName=Some("abcdefgh")).list
      verify(lister, times(1)).list("abcdefgh")
    }
}