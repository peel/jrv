import java.util.regex.{PatternSyntaxException, Pattern}

import scala.util.{Failure, Success, Try}

class Matcher{
  def matchPattern(name: String, r: String) = Try(Pattern.compile(r).matcher(name).matches())

  def matches(files: Seq[String], regex: String):Seq[String]=files.filter(matchPattern(_, regex) match {
    case Success(bool)  => bool
    case Failure(e: PatternSyntaxException) => println("invalid regex"); false
    case _ => false
  })

  def drops(all: Seq[String], matches:Seq[String])=all.diff(matches)

}
