import org.apache.spark.sql.SparkSession
import scala.math._

object SparkPi {
  def main(args: Array[String]) {

    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("SparkPi")
      .getOrCreate()

    val slices = if (args.length > 0) args(0).toInt else 2
    val n = math.min(100000L * slices, Int.MaxValue).toInt // avoid overflow
    val count = spark.sparkContext.parallelize(1 until n, slices).map { i =>
      val x = random * 2 - 1
      val y = random * 2 - 1
      if (x*x + y*y < 1) 1 else 0
    }.reduce(_ + _)
    println("\n\nPi is roughly " + 4.0 * count / (n - 1))
    println("\n\n")
    spark.stop()
  }
}
