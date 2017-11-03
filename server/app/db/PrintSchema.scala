package db

import java.nio.file.{Files, Paths, StandardOpenOption}

import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext

class PrintSchema(implicit private val ec: ExecutionContext) {
  import Tables._

  val tables = List(userTable)

  val buff = new StringBuilder
  buff.append("===========================\n")
  tables.foreach(_.schema.create.statements.map(">>>" + _).map(_ + "\n").foreach(buff.append))
  buff.append("###########################\n")
  tables.foreach(_.schema.drop.statements.map(">>>" + _).map(_ + "\n").foreach(buff.append))
  buff.append("===========================\n\n\n")
  Files.write(Paths.get("schema.txt"), buff.toString().getBytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND)
}
