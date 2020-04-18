// build.sbt
import Dependencies._

ThisBuild / organization := "psschwei"
ThisBuild / scalaVersion := "2.11.12"
ThisBuild / version      := "1.0.0"


lazy val root = (project in file("."))
  .settings(
    name := "kubernetes",
    libraryDependencies ++= rootDependencies,
    assemblyShadeRules in assembly := Seq(ShadeRule.rename("shapeless.**" -> "new_shapeless.@1").inAll),
    assemblyMergeStrategy in assembly := {
      case "META-INF/services/org.apache.spark.sql.sources.DataSourceRegister" => MergeStrategy.concat
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }

  )

