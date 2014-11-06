import java.io.File

case class Config(regex: String = "*", dir: File=new File("."),baseName:Option[String]=None)(implicit val lister:Lister){
  def fileList:Seq[String]=(new Lister with FilesLister).list(dir)
  def list:Seq[String] =  baseName map lister.list getOrElse fileList
}

