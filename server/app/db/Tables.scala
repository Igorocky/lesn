package db

import java.sql.Timestamp
import java.time.{ZoneOffset, ZonedDateTime}

import db.TypeConversions._
import shared.dto._
import shared.utils.StrUtils
import slick.jdbc.H2Profile.api._
import upickle.default._

trait HasId {
  def id: Rep[Long]
}

trait HasOrder {
  def order: Rep[Int]
}

trait HasIdAndOrder extends HasId with HasOrder

trait HasParent {
  def parentId: Rep[Long]
}

trait HasOptionalParent {
  def parentId: Rep[Option[Long]]
}

object TypeConversions {
  implicit val listOfStringsColumnType = MappedColumnType.base[List[String], String](
    StrUtils.listToStr,
    StrUtils.strToList
  )
  implicit val mapOfStringsColumnType = MappedColumnType.base[Map[String, String], String](
    write[Map[String, String]](_),
    read[Map[String, String]]
  )
  implicit val zdtColumnType = MappedColumnType.base[ZonedDateTime, Timestamp](
    zdt => new Timestamp(zdt.withZoneSameInstant(ZoneOffset.UTC).toEpochSecond*1000),
    ts => ZonedDateTime.ofInstant(ts.toInstant, ZoneOffset.UTC)
  )
  implicit val userRoleColumnType = MappedColumnType.base[UserRole, Int](
    _.code,
    UserRole.fromInt
  )
}

class UserTable(tag: Tag) extends Table[UserFull](tag, "USERS") with HasId {
  override def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def role = column[UserRole]("role")
  def login = column[String]("login")
  def salt = column[String]("salt")
  def passHash = column[String]("passHash")

  def * = (id.?, role, login, salt, passHash) <> (UserFull.tupled, UserFull.unapply)
}


object Tables {
  val userTable = TableQuery[UserTable]
}