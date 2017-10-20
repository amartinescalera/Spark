package info.antoniomartin.spark

class ReadCsv {

  def readDataFrame(): Dataframe {

    val df = sqlContext.read
    .format("com.databricks.spark.csv")
    .option("header", "true") // Use first line of all files as header
    .option("inferSchema", "true") // Automatically infer data types
    .load("cars.csv")

    val selectedData = df.select("year", "model")
    selectedData.write
    .format("com.databricks.spark.csv")
    .option("header", "true")
    .option("codec", "org.apache.hadoop.io.compress.GzipCodec")
    .save("newcars.csv.gz")
  }

}
