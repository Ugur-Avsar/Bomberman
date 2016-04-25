package toolbox;

import java.util.ArrayList;

public class ArrayListConverter {
	public static <T> ArrayList<T> toList(T[] array) {
		ArrayList<T> output = new ArrayList<T>();
		for (T element : array) {
			output.add(element);
		}
		return output;
	}

	public static ArrayList<Integer> toList(int[] array) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		for (Integer element : array) {
			output.add(element);
		}
		return output;
	}

	public static <T> Object[] toArray(ArrayList<T> list) {
		Object[] output = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			output[i] = list.get(i);
		}
		return output;
	}
}