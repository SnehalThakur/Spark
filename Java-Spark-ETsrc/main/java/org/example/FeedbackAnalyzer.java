package org.example;


import static org.apache.spark.sql.functions.col;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class FeedbackAnalyzer {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("Feedback Analyzer");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();


        // Define schema of the CSV file
        StructType schema = new StructType(new StructField[]{
                new StructField("employee_id", DataTypes.StringType, false, Metadata.empty()),
                new StructField("feedback_response1", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("feedback_response2", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("feedback_response3", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("feedback_response4", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("feedback_response5", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("client_id", DataTypes.StringType, false, Metadata.empty())
        });

        //Read the CSV file to a DataSet
        Dataset<Row> df = spark.read().format("csv").option("header","true").schema(schema).option("mode","PERMISSIVE").load(args[0]);
        df.show();


        //Add all the feedbacks of employees as final_feedback
        Dataset<Row> updated_df = df.select(df.col("employee_id"),
                df.col("client_id"),
                df.col("feedback_response1").plus(df.col("feedback_response2"))
                        .plus(df.col("feedback_response3"))
                        .plus(df.col("feedback_response4"))
                        .plus(df.col("feedback_response5")).alias("final_feedback")
        );

        updated_df.show();


        //Use the Window function Rank() to rank the feedbacks of emplyees
        Dataset<Row> ranked_df = updated_df.withColumn("rank", functions.rank().over(Window.partitionBy("client_id").orderBy(functions.desc("final_feedback"))));
        ranked_df.show();


        //Consider only the top rated employees under each client
        Dataset<Row> final_df = ranked_df.select(col("client_id"), col("employee_id"), col("final_feedback").as("top_feedback")).where(col("rank").equalTo("1")).orderBy("client_id");
        final_df.show();


        //Write the DataSet to a csv file.
        final_df.coalesce(1).write().mode(SaveMode.Overwrite).csv(args[1]);
    }

}