import java.util.*;
import java.lang.Math;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;

public class ExcelPopulator{
	

	public static void main(String[] args)
	{
		String target = args[0];
		int dataSize = Integer.valueOf(args[1]);
		int variables = Integer.valueOf(args[2]);
		if (dataSize < 0 || variables < 0)
		{
			System.err.println("Cannot have negative values");
			return;
		}
		makeDoc(target, dataSize, variables);
		System.out.println("Done");
	}

	private static boolean makeDoc(String name, int dataSize, int variables)
	{
		try{
			File currentDir = new File("");
			File result = new File(currentDir.getAbsolutePath()+"/"+name+".csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(result));
			String line1 = "";
			for (int i = 0; i < variables; i++)
			{
				line1 += "V"+Integer.toString(i)+",";
			}
			writer.write(line1, 0, line1.length());
			writer.newLine();
			Random randomizer = new Random();
			for (int i = 0; i < dataSize; i++)
			{
				for (int x = 0; x < variables; x++)
				{
					populate(writer, x, randomizer);
					//writer.write("1,", 0 , 2);
				}
				writer.newLine();
			}
			writer.flush();
			writer.close();
		}
		catch(IOException x){System.err.println(x); return false;}
		return true;
	}

	private static boolean populate(BufferedWriter writer, int mode, Random numGen)
	{
		try{
			double seed = numGen.nextGaussian();
			//float seed = 1;
			if (mode == 0)
			{
				seed = 5+seed*40;
				int discrete = (int)seed;
				writer.write(String.valueOf(discrete)+",", 0, String.valueOf(discrete).length()+1);
			}
			else if (mode <= 5)
			{
				seed *= 100;
				writer.write(String.valueOf(seed)+",", 0, String.valueOf(seed).length()+1);

			}
			else if (mode <= 10)
			{
				seed += 1000;
				seed *= 300;
				seed = Math.abs(seed);
				writer.write(String.valueOf(seed)+",", 0, String.valueOf(seed).length()+1);
			}
			else if (mode <= 15)
			{
				seed = 500+seed*200;
				int discrete = (int)seed;
				writer.write(String.valueOf(discrete)+",", 0, String.valueOf(discrete).length()+1);
			}
			else
			{
				writer.write(String.valueOf(seed)+",", 0, String.valueOf(seed).length()+1);
			}
		}
		catch(IOException x){System.err.println(x); return false;}
		return true;
	}
}