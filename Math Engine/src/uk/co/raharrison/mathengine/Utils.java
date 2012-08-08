package uk.co.raharrison.mathengine;

import java.util.Arrays;
import java.util.Collections;

public final class Utils
{
	private Utils()
	{
	}

	public static int getLevenshteinDistance(String s, String t)
	{
		if (s == null || t == null)
		{
			throw new IllegalArgumentException("Strings must not be null");
		}

		int n = s.length();
		int m = t.length();

		if (n == 0)
		{
			return m;
		}

		else if (m == 0)
		{
			return n;
		}

		int p[] = new int[n + 1];
		int d[] = new int[n + 1];
		int _d[];
		int i;
		int j;

		char t_j;

		int cost;

		for (i = 0; i <= n; i++)
		{
			p[i] = i;
		}

		for (j = 1; j <= m; j++)
		{
			t_j = t.charAt(j - 1);
			d[0] = j;

			for (i = 1; i <= n; i++)
			{
				cost = s.charAt(i - 1) == t_j ? 0 : 1;

				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
			}
			_d = p;
			p = d;
			d = _d;
		}

		int levNum = p[n];

		return levNum;
	}

	public static double getLevenshteinDistancePercent(String s, String t)
	{
		// Determine percentage difference
		double levNum = getLevenshteinDistance(s, t);
		double percent = (levNum / Math.max(s.length(), t.length())) * 100.0;

		return percent;
	}

	// Does string have matching set of parenthesis
	public static boolean isMatchingParenthesis(String string)
	{
		int count = 0;

		for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) == '(')
				count++;

			if (string.charAt(i) == ')')
				count--;
		}

		return count == 0;
	}

	// Is string null or empty
	public static boolean isNullOrEmpty(String string)
	{
		if (string != null)
		{
			if (!string.equals(""))
			{
				return false;
			}
		}

		return true;
	}

	// Is character numeric
	public static boolean isNumeric(char character)
	{
		return Character.isDigit(character);
	}

	// Is string numeric (returns true if 'd' is present)
	public static boolean isNumeric(String string)
	{
		if(string.contains("d"))
			return false;
		
		try
		{
			Double.parseDouble(string);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// Reverse double array
	public static void reverse(double[] elements)
	{
		int left = 0; // index of leftmost element
		int right = elements.length - 1; // index of rightmost element

		while (left < right)
		{
			// exchange the left and right elements
			double temp = elements[left];
			elements[left] = elements[right];
			elements[right] = temp;

			// move the bounds toward the centre
			left++;
			right--;
		}
	}

	// Reverse T array
	public static <T> void reverse(T[] elements)
	{
		Collections.reverse(Arrays.asList(elements));
	}

	// Remove spaces and to convert to lower
	public static String standardizeString(String string)
	{
		return string.replace(" ", "").trim().toLowerCase();
	}

	// Convert a String to Title Case (e.g sample -> Sample)
	public static String toTitleCase(String input)
	{
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray())
		{
			if (Character.isSpaceChar(c))
			{
				nextTitleCase = true;
			}
			else if (nextTitleCase)
			{
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}

			titleCase.append(c);
		}

		return titleCase.toString();
	}
}
