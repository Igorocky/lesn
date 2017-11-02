package utils

import java.util.concurrent.ConcurrentHashMap
import javax.inject.Singleton


@Singleton
class SessionStorage {
  private val map = new ConcurrentHashMap[String, Session]()
  private val defaultSession = Session()

  def get(id: String) = map.getOrDefault(id, defaultSession)
  def get(id: Option[String]) = id.map(map.getOrDefault(_, defaultSession)).getOrElse(defaultSession)

  def put(id: String, ses: Session) = map.put(id, ses)
  def put(id: Option[String], ses: Session) = id.foreach(map.put(_, ses))

  def remove(id: String) = map.remove(id)
  def remove(id: Option[String]) = id.foreach(map.remove)
}
