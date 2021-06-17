import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PrimeDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		Configuration conf = new Configuration();
		Job j = new Job(conf, "Prime Sum");
		j.setJarByClass(PrimeDriver.class);
		j.setMapperClass(PrimeMapper.class);
		j.setReducerClass(PrimeReducer.class);
		j.setMapOutputKeyClass(Text.class);
		j.setMapOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));

		System.exit(j.waitForCompletion(true) ? 0 : 1);
	}
}
