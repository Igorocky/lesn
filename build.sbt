import play.sbt.PlayImport._
import sbt.Keys._
import sbt.Project.projectToRef

val projVersion = "0.1-SNAPSHOT"

val scalaV = "2.12.2"
lazy val scalaTestVersion = "3.0.1"
lazy val upickleVersion = "0.4.4"

lazy val server = (project in file("server")).settings(
  name := """lesn""",
  version := projVersion,
  scalaVersion := scalaV,
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  pipelineStages := Seq(digest, gzip),
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
//  resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  libraryDependencies ++= Seq(
    "org.webjars" % "font-awesome" % "4.7.0"
    ,"org.webjars" % "bootstrap" % "3.3.7-1"
    ,evolutions
    ,guice
//    ,cache
    ,"com.typesafe.play" %% "play-slick" % "3.0.1"
    ,"com.typesafe.play" %% "play-slick-evolutions" % "3.0.1"
    ,"com.lihaoyi" %%% "upickle" % upickleVersion
    ,"com.github.japgolly.scalacss" %% "core" % "0.5.3"
    ,"com.h2database" % "h2" % "1.4.192"
    ,"org.apache.commons" % "commons-lang3" % "3.6"
    ,"org.apache.commons" % "commons-io" % "1.3.2"


    ,"org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
    ,"org.scalacheck" %% "scalacheck" % "1.13.4" % Test
  ),
  libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.7.25",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "ch.qos.logback" % "logback-core" % "1.2.3"
  )
).enablePlugins(PlayScala, WebScalaJSBundlerPlugin)
  .dependsOn(sharedJvm, macrosesJvm)

lazy val client = (project in file("client"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, ScalaJSWeb)
  .settings(
    scalaVersion := scalaV,
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core" % "1.1.1"
      ,"com.github.japgolly.scalajs-react" %%% "extra" % "1.1.1"
      ,"com.olvind" %%% "scalajs-react-components" % "0.8.0"
      ,"com.lihaoyi" %%% "upickle" % upickleVersion

      ,"org.scalatest" %%% "scalatest" % scalaTestVersion % Test
    )
    ,npmDependencies in Compile ++= Seq(
      "react" -> "15.6.1",
      "react-dom" -> "15.6.1",
//      "material-ui" -> "^0.17.0",
      "material-ui" -> "next",
//      "react-tap-event-plugin" -> "^2.0.1",
      "webpack-merge" -> "4.1.0",
      "semantic-ui-react" -> "0.75.1"
//      "semantic-ui-css" -> "0.75.1"
//      "imports-loader" -> "0.7.0",
//      "expose-loader" -> "0.7.1"
    ),
    webpackConfigFile := Some(baseDirectory.value / "src/main/resources/my.webpack.config.js"),
    webpackBundlingMode := BundlingMode.LibraryOnly(),
    emitSourceMaps := false
  ).dependsOn(sharedJs, macrosesJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(
    scalaVersion := scalaV,
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test
    )
  ).jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm.dependsOn(macrosesJvm)
lazy val sharedJs = shared.js.dependsOn(macrosesJs)

lazy val macroses = (crossProject.crossType(CrossType.Pure) in file("macroses")).settings(
  version := projVersion,
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaV

    ,"org.scalatest" %%% "scalatest" % scalaTestVersion % Test
    ,"com.lihaoyi" %%% "upickle" % upickleVersion % Test
  )
).jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val macrosesJvm = macroses.jvm
lazy val macrosesJs = macroses.js
