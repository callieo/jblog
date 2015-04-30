package taichitech.jblog.jse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionTest {
	public static void main(String[] args) {
		arrayListTest(args);
	}

	public static void arrayListTest(String[] args) {
		List<String> collection = new ArrayList<String>();
		collection.add("警察");
		collection.add("醫生");
		collection.add("醫生");
		collection.add("護士");
		collection.add("工人");
		System.out.println(collection);
		System.out.println("第一個醫生後的第30000個職業是："
				+ (collection.get(collection.indexOf("醫生") + 2)));
	}

	public static void hashMapTest(String[] args) {
		Map<String, String> collection = new HashMap<String, String>();
		collection.put(null, "警察");
		collection.put("doctor1", "醫生");
		collection.put("doctor2", "醫生");
		collection.put("nurse", "護士");
		collection.put(null, "工人");
		System.out.println(collection);
		System.out.println("Worker的職業是：" + collection.get("worker"));

	}

	public static void hashSetTest(String[] args) {
		HashSet<String> collection = new HashSet<String>();
		collection.add("警察");
		collection.add("醫生");
		collection.add("公務員");
		collection.add("護士");
		collection.add("工人");
		System.out.println(collection);
	}

	public static void treeMapTest(String[] args) {
		Map<String, String> collection = new TreeMap<String, String>();
		collection.put("police", "警察");
		collection.put(null, "醫生");
		collection.put("doctor2", "醫生");
		collection.put("nurse", "護士");
		collection.put("worker", "工人");
		System.out.println(collection);
		System.out.println("Worker的職業是：" + collection.get("worker"));
	}

	public static void copyOnWriteArrayListTest(String[] args) {
		List<String> collection = new CopyOnWriteArrayList<String>();
		collection.add("警察");
		collection.add("醫生");
		collection.add("醫生");
		collection.add("護士");
		collection.add("工人");
		System.out.println("Before for:" + collection.hashCode());
		for (String string : collection) {
			System.out.println("In for:" + collection.hashCode());
			String target = "醫生";
			if (target.equals(string)) {
				System.out.println("Before move:" + collection.hashCode());
				collection.remove(target);
				System.out.println("after move:" + collection.hashCode());
			}
			System.out.println(collection);
		}
		System.out.println("第一個醫生的位置是：" + (collection.indexOf("醫生") + 1));
	}

	public static void forArrayListTest(String[] args) {
		List<String> collection = new ArrayList<String>();
		collection.add("警察");
		collection.add("醫生");
		collection.add("醫生");
		collection.add("護士");
		collection.add("工人");
		System.out.println(collection.hashCode());
		for (String string : collection) {
			System.out.println(collection.hashCode());
			String target = "醫生";
			if (target.equals(string)) {
				collection.remove(target);
			}
		}
		System.out.println(collection);
		System.out.println("第一個醫生的位置是：" + (collection.indexOf("醫生") + 1));
	}
}
