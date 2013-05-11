package model;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AmplCommunicationTools {
	
	public static List<Double> getResults(Data data, long[] aspiration, long[] scales, int mode) throws Exception {
		writeData("data.dat", aspiration, scales, data);
		String textResult = runAmpl(mode);
		System.out.println(textResult);
		//return parseAmplResult(textResult);
		return null;
	}
	
	private static void writeData(String file, long[] aspiration, long[] scales, Data data) throws Exception {
		PrintWriter out = new PrintWriter(new File(file));
		out.println("param mm := " + Data.months + ";");
		out.println("param cc := " + Data.components + ";");
		out.println("param pp := " + Data.props + ";");
		out.println("param rr := " + Data.resources + ";");
		out.println("param prc := " + Data.overstorage_cost + ";");

		write2dimTab(out, "CS", data.cost);
		write2dimTab(out, "ND", Data.need);
		write2dimTab(out, "SP", Data.supply);
		
		write1dimTab(out, "CN", Data.contract);
		out.close();
	}

	private static void write1dimTab(PrintWriter out, String name, long[] tab) {
		out.print("\nparam: " + name + " :=");
		for (int i = 0; i < tab.length; i++)
			out.print("\n\t" + (i + 1) + "\t" + tab[i]);
		out.print(";\n");
	}

	private static void write2dimTab(PrintWriter out, String name,
			double[][] tab) {
		out.print("\nparam " + name + ":");
		for (int i = 0; i < tab[0].length; i++)
			out.print("\t" + (i + 1));
		out.print("\t:=");
		for (int i = 0; i < tab.length; i++) {
			out.print("\n\t" + (i + 1));
			for (int j = 0; j < tab[0].length; j++)
				out.print("\t" + tab[i][j]);
		}
		out.print(";\n");
	}

	private static void write2dimTab(PrintWriter out, String name,
			long[][] tab) {
		out.print("\nparam " + name + ":");
		for (int i = 0; i < tab[0].length; i++)
			out.print("\t" + (i + 1));
		out.print("\t:=");
		for (int i = 0; i < tab.length; i++) {
			out.print("\n\t" + (i + 1));
			for (int j = 0; j < tab[0].length; j++)
				out.print("\t" + tab[i][j]);
		}
		out.print(";\n");
	}

	private static String runAmpl(int mode) throws IOException {
		Process proc = Runtime.getRuntime().exec("ampl.exe run.run");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		String inputLine;
		String textResult = "";
		while ((inputLine = in.readLine()) != null)
			textResult += "\n" + inputLine;
		in.close();
		return textResult;
	}

	private static List<Double> parseAmplResult(String textResult) {
		List<Double> result = new ArrayList<Double>();
		textResult = textResult.substring(textResult.indexOf("X["));
		Scanner sc = new Scanner(textResult).useDelimiter("=*;");
		while (sc.hasNext()) {
			String[] val = sc.next().split("\\n");
			for (String s : val)
				
				if (s.length() > 1) {
					try
					{
					if (s.indexOf("=")!=-1)
						s = s.substring(s.indexOf("="));
					Double d = Double.parseDouble(s.substring(1));
					result.add(d);
					}
					catch(Exception e)
					{}
				}
		}
		return result;
	}
}
