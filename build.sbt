name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, PlayEnhancer)

inConfig(Test)(PlayEbean.scopedSettings)

playEbeanModels in Compile := Seq("models.*")
playEbeanModels in Test := Seq("models.*")


scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    javaJdbc,
    cache,
    javaWs
)

