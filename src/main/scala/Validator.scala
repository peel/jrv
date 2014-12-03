import java.io.File

object Validator extends App {
  implicit val lister:Lister=new Lister with FuzzyNameLister

  val parser = new scopt.OptionParser[Config]("jrv"){
    head("jrv", "1.x")
    opt[String]('r', "regex") required() valueName "<pattern>" action {(x,c)=>
        c.copy(regex=x) } text "regex is a required pattern property"
    opt[File]('d', "dir") valueName "<directory>" action {(x,c)=>
        c.copy(dir=x) } text "directory that shall be scanned for filenames"
    opt[String]('b', "base-name") valueName "<basename>" action {(x,c)=>
      c.copy(baseName=Some(x)) } text "use a defined file basename to fuzzy-generate file list (no IO ops)"
    help("help") text "prints this usage text"
  }

  parser.parse(args,Config()) map {config =>
    val matcher = new Matcher
    val matches = matcher.matches(config.list, config.regex)
    val drops = matcher.drops(config.list, matches)
    def print(list: Seq[String])=list.foreach(s => println(s"- $s"))

    println(s"-- matches for ${config.regex} in ${config.dir} --------------------------")
    print(matches)
    println(s"-- dropped -------------------------------------------------------")
    print(drops)
  }
}

