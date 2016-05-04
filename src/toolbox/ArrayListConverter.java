package toolbox;

import java.util.ArrayList;
import java.util.List;

public class ArrayListConverter {
	public static <T> List<T> toList(T[] array) {
		List<T> output = new ArrayList<T>();
		for (T element : array) {
			output.add(element);
		}
		return output;
	}

	public static List<Integer> toList(int[] array) {
		List<Integer> output = new ArrayList<Integer>();
		for (Integer element : array) {
			output.add(element);
		}
		return output;
	}

	public static int[] stringArrayToIntArray(String[] arrToConvert) {
		int[] arr = new int[arrToConvert.length];
		for (int i = 0; i < arrToConvert.length; i++) {
			arr[i] = Integer.parseInt(arrToConvert[i]);
		}
		return arr;
	}

	public static int[] stringListToIntArray(List<String> listToConvert) {
		int[] arr = new int[listToConvert.size()];
		for (int i = 0; i < listToConvert.size(); i++) {
			arr[i] = Integer.parseInt(listToConvert.get(i));
		}
		return arr;
	}

	public static ArrayList<Integer> stringArrayToIntList(String[] arrToConvert) {
		ArrayList<Integer> list = new ArrayList<Integer>(arrToConvert.length);
		for (int i = 0; i < arrToConvert.length; i++) {
			list.add(Integer.parseInt(arrToConvert[i]));
		}
		return list;
	}

	public static String[] toArray(List<String> list) {
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static int[] calculate(int[] arr, char operator, double hdSclaingFactor) {
		int[] result = new int[arr.length];
		switch (operator) {
		case '*':
			for (int i = 0; i < arr.length; i++) {
				result[i] = (int) (arr[i] * hdSclaingFactor);
			}
			break;
		}
		return result;
	}
}