// Dependencies file for maintaining library dependencies
import sbt._

object Dependencies {

  object versions {
    lazy val spark = "2.4.5"
  }

  /* Spark */
  lazy val sparkCore = "org.apache.spark" %% "spark-core" % versions.spark % "provided"
  lazy val sparkSql = "org.apache.spark" %% "spark-sql" % versions.spark % "provided"


  lazy val rootDependencies = Seq(
    sparkCore,
    sparkSql
  )
}
