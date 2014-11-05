import java.io.File
import java.util.regex.{PatternSyntaxException, Pattern}

import scala.util.{Random, Failure, Success, Try}

case class Config(regex: String = "*", dir: File=new File("."))

object Matcher{
  def matchPattern(name: String, r: String) = Try(Pattern.compile(r).matcher(name).matches())

  def matches(files: Seq[String], regex: String):Seq[String]=files.filter(matchPattern(_, regex) match {
    case Success(bool)  => bool
    case Failure(e: PatternSyntaxException) => println("invalid regex"); false
    case _ => false
})

  def printMatches(files: Seq[String], regex:String):Unit={
    matches(files,regex).foreach(s => println(s"- $s"))
  }
}

object NameGenerator{
  def generate={
    def randomString=Random.alphanumeric.take(10).foldLeft("")(_+_)
    generateWith(randomString)
  }
  def generateWith(base: String)={
    def toggle(char: Char)=char match{
      case _ if char.isUpper => char.toLower
      case _ => char.toUpper
    }
    val toggleNumber=Random.nextInt(base.length)

    (for{
      l <- base
      t <- toggleNumber
      toggled = toggle(l)
    } yield toggled).foldLeft("")(_+_)
  }

}

object validator extends App {

  val parser = new scopt.OptionParser[Config]("jrv"){
    head("jrv", "1.x")
    opt[String]('r', "regex") required() valueName "<pattern>" action {(x,c)=>
        c.copy(regex=x) } text "regex is a required pattern property"
    opt[File]('d', "dir") valueName "<directory>" action {(x,c)=>
        c.copy(dir=x) } text "directory that shall be scanned for filenames"
    help("help") text "prints this usage text"
  }

  parser.parse(args,Config()) map {config =>
    val dir = config.dir
    val regex = config.regex

    def list(dir: File)=dir.list()

    println(s"matches in $dir:")
    Matcher.printMatches(list(dir), regex)
  }
}

