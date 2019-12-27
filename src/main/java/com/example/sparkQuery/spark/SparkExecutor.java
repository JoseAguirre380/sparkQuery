package com.example.sparkQuery.spark;


import com.example.sparkQuery.kieServer.KieExecutor;
import com.example.sparkQuery.model.Message;
import com.google.gson.Gson;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SparkExecutor {



    public void executeQuery(String json){

//        String query = "SELECT * FROM table";
//       // ResultSet results = session.execute(query);
//
//        final SparkSession sparkSession = SparkSession.builder().appName("Spark SQL Demo").master("local[5]").getOrCreate();
//
//        // Load JSON file data into DataFrame using SparkSession
//        final Dataset<Row> jsonDataFrame = sparkSession.read().json("src/main/resources/data.json");
//        // Print Schema to see column names, types and other metadata
//        jsonDataFrame.printSchema();
//
//        // Query name column from JSON where age column value is equal to 30
//
//        // DSL API with conditional expression
//        //System.out.println("DSL API with Condition Expression:");
//        //jsonDataFrame.select("name").where("age = 30").show();
//        // Pure DSL API
//        //System.out.println("Pure DSL API:");
//        //jsonDataFrame.select("name").where(jsonDataFrame.col("age").equalTo(30)).show();
//
//        // Create a view on DataFrame and execute the query on created view using SparkSession
//        System.out.println("SQL Query:");
//        jsonDataFrame.createOrReplaceTempView("people");
//        sparkSession.sql("SELECT name FROM people WHERE age = 30").show();

        KieExecutor kieExecutor = new KieExecutor();

        Message message = kieExecutor.ruleExecutor(json);

        Gson gson = new Gson();
        String jsonString = gson.toJson(message);

        List<String> jsonList = new ArrayList<>();
        jsonList.add(jsonString);
        SparkContext sparkContext = new SparkContext(new SparkConf()
                .setAppName("terniumSparkApplication").setMaster("local").set("spark.testing.memory","471859200"));


        String query = "SELECT * FROM table";

        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkContext);
        SQLContext sqlContext = new SQLContext(sparkContext);
        JavaRDD<String> data = javaSparkContext.parallelize(jsonList);

        sqlContext.createDataFrame(data, Message.class).registerTempTable("data");
        Dataset<Row> results = sqlContext.sql("SELECT * FROM data");
       // JavaPairRDD<String,String> pairRDD =

       // JavaRDD<Map> javaRDD = javaSparkContext.

       // results.printSchema();
    }
}
