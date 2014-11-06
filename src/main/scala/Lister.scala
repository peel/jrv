import java.io.File
import scala.annotation.tailrec
import scala.util.Random

abstract class Lister{
  def list(base: String):Seq[String]
}

trait FilesLister extends Lister{
  def list(base: String):Seq[String]=list(new File(base))
  def list(dir: File):Seq[String]=dir.list()
}

trait FuzzyNameLister extends Lister{
  def list(base: String):Seq[String]={
    for(i <- 1 to Random.nextInt(10)+1) yield generate(base)
  }

  private def generate(base: String):String={

    def toggle(char: Char):Char=char match{
      case _ if char.isUpper => char.toLower
      case _ => char.toUpper
    }

    def toggleAt(str: String, at: Int):String={
      val pre:String = str.take(at)
      val toggled:String = toggle(str(at)).toString
      val post:String = str.drop(at+1)
      val list:Seq[String] = pre::toggled::post::Nil
      list.flatten.foldLeft("")(_+_)
    }

    def changeCharAt(str: String, at: Int):String={
      val pre:String = str.take(at)
      val toggled:String = Random.alphanumeric.head.toString
      val post:String = str.drop(at+1)
      val list:Seq[String] = pre::toggled::post::Nil
      list.flatten.foldLeft("")(_+_)
    }

    def randomCharId=Random.nextInt(base.length)

    @tailrec
    def generateFor(base: String, chars: Int):String={
      if(chars>0) {
        generateFor(changeCharAt(toggleAt(base, randomCharId),randomCharId), chars-1)
      }else{
        base
      }
    }

    generateFor(base, Random.nextInt(base.length))
  }
}
